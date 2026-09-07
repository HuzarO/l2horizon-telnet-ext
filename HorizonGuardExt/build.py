#!/usr/bin/env python3
"""Build the GS extension and bounded legacy AuthGuard overrides with JDK 17+, without Ant/Maven.

Only writes this project's build/ and dist/. The supplied server distribution is read-only.
"""
import argparse
import hashlib
import os
from pathlib import Path
import subprocess
import zipfile
import shutil

ROOT = Path(__file__).resolve().parent
ASM_HASH = 'ed825d10ab1399c8c0cb669e688cf0c8c82629b4c8399b58352b68e92ca10fcb'

def main():
    p = argparse.ArgumentParser()
    p.add_argument('--server', required=True, type=Path)
    p.add_argument('--jdk', required=True, type=Path)
    p.add_argument('--test', action='store_true')
    p.add_argument('--fixtures', type=Path, default=ROOT / 'tests/fixtures')
    args = p.parse_args()
    native_windows = (args.jdk / 'bin/javac.exe').is_file()
    def platform_path(path):
        s = str(Path(path).resolve())
        if native_windows and s.startswith('/mnt/') and len(s) > 6 and s[6] == '/':
            return s[5].upper() + ':' + s[6:]
        return s.replace('\\', '/')
    def tool(name): return str(args.jdk / 'bin' / (name + ('.exe' if native_windows else '')))
    def cp(paths): return (';' if native_windows else ':').join(platform_path(p) for p in paths)
    build = ROOT / 'build'; dist = ROOT / 'dist'
    build.mkdir(exist_ok=True); dist.mkdir(exist_ok=True)
    # Remove only generated class files, avoiding stale overrides in rebuilt archives.
    for folder in ('gs', 'auth', 'tools', 'tests'):
        (build / folder).mkdir(exist_ok=True)
        for file in (build / folder).rglob('*.class'): file.unlink()
    def compile_tree(source, output, dependencies):
        sources = sorted(source.rglob('*.java'))
        response = build / (output.name + '-sources.args')
        response.write_text('\n'.join('"' + platform_path(s) + '"' for s in sources), encoding='utf-8')
        subprocess.run([tool('javac'), '--release', '17', '-encoding', 'UTF-8', '-cp', cp(dependencies), '-d', platform_path(output), '@' + platform_path(response)], check=True)
    gs_deps = sorted((args.server / 'gameserver').glob('*.jar'))
    # Explicitly prioritize AuthGuardExt: its L2LoginClient adds the verification API.
    auth_deps = [args.server / 'authserver/AuthGuardExt.ext.jar'] + sorted((args.server / 'authserver').glob('*.jar'))
    compile_tree(ROOT / 'src', build / 'gs', gs_deps)
    compile_tree(ROOT / 'authsrc', build / 'auth', auth_deps)
    asm = ROOT / 'tools/lib/asm-9.10.1.jar'
    if hashlib.sha256(asm.read_bytes()).hexdigest() != ASM_HASH: raise SystemExit('ASM dependency checksum mismatch')
    compile_tree(ROOT / 'tools', build / 'tools', [asm])
    subprocess.run([tool('java'), '-cp', cp([build / 'tools', asm]), 'PatchCore', platform_path(args.server / 'gameserver/server.jar'), platform_path(build / 'gs')], check=True)
    for folder, name in [('gs', 'HorizonGuard.ext.jar'), ('auth', 'AuthGuardHardening.ext.jar')]:
        with zipfile.ZipFile(dist / name, 'w', zipfile.ZIP_DEFLATED) as jar:
            def add(name, body):
                info = zipfile.ZipInfo(name, (1980, 1, 1, 0, 0, 0)); info.compress_type = zipfile.ZIP_DEFLATED
                info.external_attr = 0o100644 << 16; jar.writestr(info, body)
            add('META-INF/MANIFEST.MF', b'Manifest-Version: 1.0\r\nImplementation-Version: 1\r\n\r\n')
            for file in sorted((build / folder).rglob('*')):
                if file.is_file(): add(file.relative_to(build / folder).as_posix(), file.read_bytes())
        print(name, hashlib.sha256((dist / name).read_bytes()).hexdigest())
    if args.test:
        run_dir = build / 'test-run'; run_dir.mkdir(exist_ok=True)
        for name in ('bootstrap.bin', 'open-request.bin', 'accepted-response.bin', 'audit.manifest', 'release-public.blob'):
            shutil.copyfile(args.fixtures / name, run_dir / name)
        compile_tree(ROOT / 'tests', build / 'tests', [build / 'gs', build / 'auth'] + auth_deps + gs_deps)
        subprocess.run([tool('java'), '-Xverify:all', '-Dhorizon.guard.core=' + platform_path(args.server / 'gameserver/server.jar'), '-cp', cp([build / 'tests', dist / 'HorizonGuard.ext.jar', dist / 'AuthGuardHardening.ext.jar'] + auth_deps + gs_deps),
                        'com.l2horizon.guard.GuardTests', platform_path(run_dir)], check=True, cwd=run_dir)

if __name__ == '__main__': main()
