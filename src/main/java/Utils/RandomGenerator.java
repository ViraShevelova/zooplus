package Utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

public class RandomGenerator {
    public static final String specialCharactersString = "~`!@#$%^&*()-_=+[{]}\\<.>/?";

    public static String getRandomString(int maxLength) {
        var random = new Random();
        return RandomStringUtils.random(random.nextInt(maxLength), true, true);
    }

    public static String getRandomString(int minLength, int maxLength) {
        return RandomStringUtils.randomAlphabetic(minLength, maxLength);
    }

    public static String getRandomStringWithSpecialCharacters(int length) {
        String characters =
                "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*()-_=+[{]}\\|;:\'\",<.>/?";
        return RandomStringUtils.random( length, characters );
    }

    public static int getRandomInt() {
        var random = new Random();
        return random.nextInt();
    }

    public static Long getRandomPositiveLong() {
        var random = new Random();
        return random.nextLong();
    }

    public static long getRandomIntAsLong() {
        return getRandomInt();
    }

    public static long getRandomPositiveIntAsLong() {
        return Math.abs(getRandomInt());
    }
}
