package com.example.shared;

import net.datafaker.Faker;
import net.datafaker.providers.base.Name;
import org.apache.commons.lang3.RandomStringUtils;

import java.text.SimpleDateFormat;
import java.util.concurrent.ThreadLocalRandom;

public class DataGenerationHelpers {
    private static final Faker fakerLibrary = new Faker();

    private DataGenerationHelpers() {
    }

    /**
     * @return a random date of birth. Format example: "1986-09-30"
     */
    public static String getRandomDateOfBirth() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(fakerLibrary.date().birthday());
    }

    /**
     * @return a random password consisting of 8-16 alphanumeric characters, with no special characters. Example: "Q0EnqZPm"
     */
    public static String getRandomPassword(int minLength, int maxLength, boolean includeUpperCase, boolean includeSpecialChars, boolean includeDigits) {
        return fakerLibrary
                .internet()
                .password(minLength,
                        maxLength,
                        includeUpperCase,
                        includeSpecialChars,
                        includeDigits);
    }

    /**
     * @return a random numeric string, with no special characters. Example: "Q0EnqZPm"
     */
    public static String getRandomNumericString(int numberOfDigits) {
        return String.valueOf(fakerLibrary.number().randomNumber(numberOfDigits, true));
    }

    /**
     * @return new random Name object containing various random person details.
     */
    public static Name getNewRandomPerson() {
        return fakerLibrary.name();
    }

    public static String getNewAlphaNumericStringOfSpecificLength(int randomStringLength) {
        return RandomStringUtils.randomAlphanumeric(randomStringLength);
    }

    /**
     * Returns a pseudo-random number between min and max, inclusive.
     * <code>Integer.MAX_VALUE - 1</code>.
     * https://stackoverflow.com/a/363692/3324415
     *
     * @param min Minimum value
     * @param max Maximum value.  Must be greater than min.
     * @return Integer between min and max, inclusive.
     */
    public static int getRandomNumberInRange(int min, int max) {
        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

}
