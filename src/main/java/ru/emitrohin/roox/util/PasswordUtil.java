package ru.emitrohin.roox.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;

import java.util.regex.Pattern;

/**
 * GKislin
 * 05.01.2015.
 */

class PasswordUtil {
    private static final Pattern BCRYPT_PATTERN = Pattern.compile("\\A\\$2a?\\$\\d\\d\\$[./0-9A-Za-z]{53}");
    private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    public static String encode(String newPassword) {
        if (StringUtils.isEmpty(newPassword)) {
            return null;
        }
        if (isEncoded(newPassword)) {
            return newPassword;
        }
        return PASSWORD_ENCODER.encode(newPassword);
    }

    public static boolean isMatch(String rawPassword, String password) {
        return PASSWORD_ENCODER.matches(rawPassword, password);
    }

    private static boolean isEncoded(String newPassword) {
        return BCRYPT_PATTERN.matcher(newPassword).matches();
    }
}
