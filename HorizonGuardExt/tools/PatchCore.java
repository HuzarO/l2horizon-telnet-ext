import org.objectweb.asm.*;
import java.nio.file.*;
import java.security.*;
import java.util.*;
import java.util.jar.*;

/** Reproducible build-time patch of exactly two reviewed classes. No agent or runtime ASM dependency. */
public final class PatchCore implements Opcodes {
    static final String CLIENT = "l2/gameserver/network/l2/GameClient";
    static final String PACKET = "l2/gameserver/network/l2/c2s/L2GameClientPacket";
    static final String HOOKS = "com/l2horizon/guard/GuardHooks";
    static final Map<String, String> HASHES = Map.of(
            CLIENT, "22548727a954a4d66321016591d32e8509b9a44f4ae151c0d8b4c9fe27efa562",
            PACKET, "63505563e597df16f4bfd18d86e692a05dcfbc2c4b077cfa6ea0bab929606219");
    public static void main(String[] args) throws Exception {
        try (JarFile jar = new JarFile(args[0])) {
            for (var entry : HASHES.entrySet()) {
                String name = entry.getKey(); byte[] source = jar.getInputStream(jar.getJarEntry(name + ".class")).readAllBytes();
                String digest = HexFormat.of().formatHex(MessageDigest.getInstance("SHA-256").digest(source));
                if (!digest.equals(entry.getValue())) throw new IllegalArgumentException("Unreviewed core class " + name + ": " + digest);
                ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
                int[] matches = new int[3];
                new ClassReader(source).accept(new ClassVisitor(ASM9, writer) {
                    @Override public MethodVisitor visitMethod(int access, String method, String desc, String sig, String[] ex) {
                        if (name.equals(PACKET) && method.equals("run") && desc.equals("()V")) {
                            matches[0]++; return super.visitMethod(ACC_PRIVATE, "horizonGuard$run", desc, sig, ex);
                        }
                        if (name.equals(CLIENT) && method.equals("setState") && desc.equals("(L" + CLIENT + "$GameClientState;)V")) {
                            matches[1]++;
                            return super.visitMethod(ACC_PRIVATE, "horizonGuard$setState", desc, sig, ex);
                        }
                        if (name.equals(CLIENT) && method.equals("onDisconnection") && desc.equals("()V")) {
                            matches[2]++;
                            return super.visitMethod(ACC_PRIVATE, "horizonGuard$onDisconnection", desc, sig, ex);
                        }
                        return super.visitMethod(access, method, desc, sig, ex);
                    }
                    @Override public void visitEnd() {
                        super.visitField(ACC_PUBLIC | ACC_STATIC | ACC_FINAL, "HORIZON_GUARD_ABI", "I", null, 1).visitEnd();
                        if (name.equals(PACKET)) {
                            MethodVisitor m = super.visitMethod(ACC_PUBLIC | ACC_FINAL, "run", "()V", null, null);
                            m.visitCode(); m.visitVarInsn(ALOAD, 0);
                            m.visitMethodInsn(INVOKESTATIC, HOOKS, "beforePacket", "(L" + PACKET + ";)Z", false);
                            Label done = new Label(); m.visitJumpInsn(IFEQ, done); m.visitVarInsn(ALOAD, 0);
                            m.visitMethodInsn(INVOKESPECIAL, PACKET, "horizonGuard$run", "()V", false);
                            m.visitLabel(done); m.visitFrame(F_SAME, 0, null, 0, null); m.visitInsn(RETURN); m.visitMaxs(0, 0); m.visitEnd();
                        } else {
                            // Obfuscated original methods intentionally drop 'this' from some return
                            // frames. Wrappers preserve ALL original instructions/frames unchanged.
                            MethodVisitor state = super.visitMethod(ACC_PUBLIC, "setState", "(L" + CLIENT + "$GameClientState;)V", null, null);
                            state.visitCode(); state.visitVarInsn(ALOAD, 0); state.visitVarInsn(ALOAD, 1);
                            state.visitMethodInsn(INVOKESPECIAL, CLIENT, "horizonGuard$setState", "(L" + CLIENT + "$GameClientState;)V", false);
                            state.visitVarInsn(ALOAD, 0); state.visitMethodInsn(INVOKESTATIC, HOOKS, "stateChanged", "(L" + CLIENT + ";)V", false);
                            state.visitInsn(RETURN); state.visitMaxs(0, 0); state.visitEnd();
                            MethodVisitor close = super.visitMethod(ACC_PROTECTED, "onDisconnection", "()V", null, null);
                            close.visitCode(); close.visitVarInsn(ALOAD, 0); close.visitMethodInsn(INVOKESTATIC, HOOKS, "disconnected", "(L" + CLIENT + ";)V", false);
                            close.visitVarInsn(ALOAD, 0); close.visitMethodInsn(INVOKESPECIAL, CLIENT, "horizonGuard$onDisconnection", "()V", false);
                            close.visitInsn(RETURN); close.visitMaxs(0, 0); close.visitEnd();
                        }
                        super.visitEnd();
                    }
                }, 0);
                if (name.equals(PACKET) ? matches[0] != 1 : matches[1] != 1 || matches[2] != 1) throw new IllegalStateException("Core integration points missing");
                Path output = Path.of(args[1], name + ".class"); Files.createDirectories(output.getParent()); Files.write(output, writer.toByteArray());
                System.out.println("Patched reviewed class " + name);
            }
        }
        Path pins = Path.of(args[1], "META-INF/horizon-core.properties"); Files.createDirectories(pins.getParent());
        String digest = HexFormat.of().formatHex(MessageDigest.getInstance("SHA-256").digest(Files.readAllBytes(Path.of(args[0]))));
        Files.writeString(pins, "server.sha256=" + digest + "\n");
    }
}
