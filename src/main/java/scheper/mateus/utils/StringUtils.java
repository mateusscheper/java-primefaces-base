package scheper.mateus.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringUtils {

    private StringUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static String generateRandomPassword() {
        String upperCaseLetters = RandomStringUtils.random(3, 65, 90, true, true);
        String lowerCaseLetters = RandomStringUtils.random(3, 97, 122, true, true);
        String numbers = RandomStringUtils.randomNumeric(2);
        String specialChar = RandomStringUtils.random(2, 0, 4, false, false, '@', '!', '$', '%', '&');
        String totalChars = RandomStringUtils.randomAlphanumeric(2);
        String combinedChars = upperCaseLetters.concat(lowerCaseLetters)
                .concat(numbers)
                .concat(specialChar)
                .concat(totalChars);

        return randomizeChars(combinedChars);
    }

    public static String generateFileName() {
        String lowerCaseLetters = RandomStringUtils.random(10, 97, 122, true, true);
        String numbers = RandomStringUtils.randomNumeric(10);
        String combinedChars = lowerCaseLetters.concat(numbers);

        return randomizeChars(combinedChars);
    }

    private static String randomizeChars(String combinedChars) {
        List<Character> pwdChars = combinedChars.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toList());
        Collections.shuffle(pwdChars);
        return pwdChars.stream()
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }

    public static String capitalizeFully(String string, String splitMethod) {
        String[] stringList = string.split(splitMethod);
        StringBuilder treatedWords = new StringBuilder();
        for (String word : stringList) {
            word = word.toLowerCase();
            if (word.length() > 3) {
                word = word.replace(
                        String.valueOf(word.charAt(0)),
                        String.valueOf(word.charAt(0))
                                .toUpperCase()
                );
            }
            treatedWords.append(" ").append(word);
        }

        return treatedWords.toString().trim();
    }

    public static String hashPasswordWithMD5(String password) {
        return DigestUtils.md5Hex(password).toUpperCase();
    }

    public static boolean validatePassword(String hashedPassword, String password) {
        return hashPasswordWithMD5(password).equalsIgnoreCase(hashedPassword);
    }

    public static boolean validateEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        return pat.matcher(email).matches();
    }
}
