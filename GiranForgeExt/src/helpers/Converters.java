/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.auth0.jwt.JWT
 *  com.auth0.jwt.JWTVerifier
 *  com.auth0.jwt.algorithms.Algorithm
 *  com.auth0.jwt.interfaces.DecodedJWT
 */
package helpers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Converters {
    private static final char[] sp1 = new char[]{'\u0005', 'U', 'U', 'S', '\u0000', '\u0006', 'T', '\u0006', '\u0006', '\u000f', 'U', '\u0005', '\u0002', '\u0004', 'R', '\u0004', '\u0006', 'Q', '\u0003', 'S', '\u0006', '\u0004', '\u0007', 'U', '\u000f', '\u0000', 'R', '\u0004', 'R', 'R', '\u000f', 'T', 'R', '\u0007', 'V', '\u0005', '\u0001', '\u000f', 'V', '\u0002', '\u0004', '\u000e', '\u000f', '\u000f', 'S', 'R', 'V', 'R', '\u0007', '\u0006', 'R', '\u0001', '\u0000', '\u0007', 'Q', 'V', 'Q', 'S', '\u000f', 'Q', 'S', '\u0004', '\u0000', 'T', 'V', '\u0000', '\u0000', '\u000e', 'Q', '\u000f', 'T', 'S', '\u000e', '\u0000', 'R', '\u0001', 'Q', '\u0005', '\u0000', 'Q', 'R', 'S', 'V', '\u0001', '\u0004', '\u0006', '\u0000', 'S', '\u0000', '\u000e', '\u0005', 'S', '\u0007', '\u0003', '\u0007', 'V', '\u0000', 'V', 'T', 'R', 'Q', 'R', '\u0002', 'U', '\u0004', '\u0006', 'U', '\u0000', 'S', '\u0007', '\u0004', '\u0001', 'U', 'S', '\u0000', 'V', '\u0001', 'S', '\u0002', '\u0002', '\u0003', '\u0004', 'U', '\u0000', 'T', '\u0000', 'S', '\u000e', '\u0001', '\u0005', 'V', 'Q', '\u0006', '\u0000', '\u0004', '\u0004', 'S', 'Q', '\u0006', '\u000e', '\u0005', '\u000e', '\u0002', '\u0004', 'V', '\u000f', 'R', 'Q', '\u0005', '\u000f', 'S', '\u0006', '\u0007', 'Q', '\u0001', 'U', 'V', '\u0003', 'S', 'T', 'Q', '\u0006', '\u0007', '\u0006', '\u0003', '\u0005', 'S', '\u0002', 'R', 'Q', '\u0001'};
    private static final char[] sp2 = new char[]{'R', 'Q', '\u0004', 'T', '\u0003', '\u0000', '\u0003', '\u0001', '\u0004', '\u0007', '\u0002', '\u0002', 'V', '\u0007', '\u000e', '\u0007', '\u0001', '\u0007', '\u000f', 'V', '\u0007', 'T', '\u0006', '\u000e', 'V', 'S', '\u0001', 'U', '\u0000', '\u0004', '\u0004', 'R', 'T', '\u000e', '\u0004', '\u0005', '\u0000', 'S', '\u0001', 'R', '\u0007', 'R', '\u0001', 'R', 'R', 'V', '\u0002', '\u0004', '\u0006', 'T', 'V', 'R', '\u0006', '\u0003', '\u0005', '\u0006', '\u0004', 'U', '\u0005', '\u0007', 'R', 'T', 'T', '\u0004', '\u0001', 'U', '\u000e', '\u0004', '\u0007', '\u0005', '\u0003', '\u0006', 'R', '\u0002', '\u000e', 'V', '\u0000', '\u0004', 'U', 'S', 'S', '\u000e', '\u0004', '\u0006', 'V', '\u000f', '\u000f', 'U', '\u0004', '\u0003', '\u0004', '\u0007', '\u0007', '\u000e', '\u000f', '\u000e', '\u0003', 'S', '\u0005', '\u000e', '\u000e', 'V', 'S', '\u000e', '\u0003', '\u0007', '\u0000', '\u0004', 'Q', 'V', 'R', 'U', '\u0001', '\u000e', 'T', 'Q', 'U', 'V', 'S', 'V', '\u0004', 'R', '\u0004', 'V', 'T', '\u0002', '\u0005', 'R', '\u0003', '\u0006', 'S', '\u0007', '\u000f', 'S', 'V', '\u000e', '\u000e', '\u000e', '\u000e', '\u0001', 'Q', 'S', '\u0003', '\u0002', 'V', '\u0003', '\u0003', '\u0002', 'S', 'R', '\u0004', '\u0005', '\u0003', '\u0003', '\u0002', '\u0007', 'R', 'Q', 'R', '\u0000', '\u0004', 'R', '\u0007', '\u0001', 'Q', '\u0001', 'R', '\u0004', '\u0003', '\u0000', '\u000f'};
    private static final char[] sp3 = new char[]{'R', '\u000f', '\u0001', '\u0002', 'Q', 'U', '\u000f', 'Q', '\u0004', '\u0001', '\u000f', '\u0003', '\u000f', '\u000e', 'U', 'Q', '\u0006', '\u0002', '\u0001', '\u0007', 'R', 'U', '\u0000', 'T', '\u0006', '\u0003', '\u0003', '\u000f', '\u0000', 'Q', '\u0002', '\u0006', 'R', 'T', 'T', '\u0003', '\u000e', '\u0002', '\u0001', 'R', '\u0007', 'R', 'T', '\u000e', '\u000e', 'S', 'S', '\u0002', '\u0003', 'S', 'S', '\u000e', 'V', '\u0003', '\u0005', 'Q', 'U', 'Q', 'R', 'S', 'U', '\u0000', 'S', 'R', '\u0005', 'T', 'Q', '\u0003', 'Q', '\u0004', '\u0002', '\u0005', '\u0002', '\u0002', '\u000e', 'Q', '\u0004', '\u000f', 'U', '\u0005', '\u000e', '\u0004', '\u000e', 'V', '\u0004', 'V', 'S', '\u0006', 'T', 'Q', '\u0000', '\u0004', '\u000e', '\u0002', '\u0005', 'S', '\u000f', 'S', 'T', '\u0005', 'U', '\u0006', '\u0004', '\u0005', 'T', 'R', '\u0000', 'Q', 'U', '\u000f', '\u0006', '\u000e', '\u0006', '\u0007', 'U', '\u000f', 'Q', '\u0003', '\u000f', 'T', '\u0001', '\u000f', 'Q', 'T', '\u000f', 'S', '\u0000', 'Q', '\u0005', '\u000e', '\u0006', 'V', '\u0007', '\u0006', '\u000f', 'V', 'V', 'V', '\u000e', 'R', 'V', 'U', 'T', '\u000f', '\u0006', 'T', '\u000e', 'U', '\u0006', '\u0006', '\u0005', '\u000f', '\u0000', '\u0000', '\u0006', 'T', '\u000e', 'V', 'R', 'U', '\u0005', 'Q', '\u0000', '\u0003', '\u0003', '\u0005', 'S', '\u0007', '\u0004', 'Q'};
    private static final char b = '7';

    public static String longToDate(long time) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm dd.MM.yyyy");
        Date date = new Date(time);
        return formatter.format(date);
    }

    private static String gds() {
        StringBuilder sb = new StringBuilder();
        for (char c : sp1) {
            sb.append((char)(c ^ 0x37));
        }
        for (char c : sp2) {
            sb.append((char)(c ^ 0x37));
        }
        for (char c : sp3) {
            sb.append((char)(c ^ 0x37));
        }
        return sb.toString();
    }

    public static DecodedJWT b64(String s) {
        String gds = Converters.gds();
        Algorithm alg = Algorithm.HMAC256((String)gds);
        JWTVerifier verifier = JWT.require((Algorithm)alg).build();
        return verifier.verify(s);
    }
}

