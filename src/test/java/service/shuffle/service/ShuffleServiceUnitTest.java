package service.shuffle.service;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ShuffleServiceUnitTest {

    private final ShuffleService shuffleService = new ShuffleService();

    @Test
    void generateShuffledArray_shouldReturnCorrectArray_whenInputIsValid() {
        int[] shuffledArray = shuffleService.generateShuffledArray(5);
        assertEquals(5, shuffledArray.length);
        assertTrue(Arrays.stream(shuffledArray).distinct().count() == 5);
        assertTrue(Arrays.stream(shuffledArray).allMatch(i -> i >= 1 && i <= 5));
    }

    @Test
    void generateShuffledArray_shouldReturnSingleElementArray_whenInputIsOne() {
        int[] shuffledArray = shuffleService.generateShuffledArray(1);
        assertEquals(1, shuffledArray.length);
        assertEquals(1, shuffledArray[0]);
    }

    @Test
    void generateShuffledArray_shouldReturnThousandElementsArray_whenInputIsMaxValue() {
        int[] shuffledArray = shuffleService.generateShuffledArray(1000);
        assertEquals(1000, shuffledArray.length);
        assertTrue(Arrays.stream(shuffledArray).distinct().count() == 1000);
    }
}