package grailstestapp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class PasswordValidation {
    private static final String PASSWORD_PATTERN =
            '^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$'

    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

    static boolean isValid(final String password) {
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
