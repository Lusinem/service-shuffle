package service.shuffle.utils;

import service.shuffle.config.ShuffleNumberProperties;

public class ValidationUtils {

    private static int minNumber;
    private static int maxNumber;

    public static void initialize(ShuffleNumberProperties properties) {
        minNumber = properties.getMin();
        maxNumber = properties.getMax();
    }

    /**
     * Validates the input number.
     *
     * @param number the input number.
     * @return true if the number is valid, false otherwise.
     */
    public static boolean isValidNumber(final Integer number) {
        return number != null && number >= minNumber && number <= maxNumber;
    }
}
