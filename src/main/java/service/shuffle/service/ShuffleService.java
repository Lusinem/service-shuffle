package service.shuffle.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import service.shuffle.controller.ShuffleController;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

@Service
public class ShuffleService {

    private static final Logger logger = LogManager.getLogger(ShuffleController.class);

    /**
     * Generates a shuffled list of integers from 1 to the specified number.
     *
     * @param number the upper bound of the range (inclusive).
     * @return a shuffled list of integers.
     */
    public int[] generateShuffledArray(final int number) {
        logger.debug("Generating shuffled array for number: {}", number);
        int[] numbers = IntStream.rangeClosed(1, number).toArray();
        final Random random = new Random();

        for (int i = numbers.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            // Swap elements
            int temp = numbers[i];
            numbers[i] = numbers[j];
            numbers[j] = temp;
        }

        logger.debug("Shuffled array generated: {}", Arrays.toString(numbers));
        return numbers;
    }
}
