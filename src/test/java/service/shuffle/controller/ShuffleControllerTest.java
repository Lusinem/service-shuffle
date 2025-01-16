package service.shuffle.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import service.shuffle.config.ShuffleNumberProperties;
import service.shuffle.service.LogService;
import service.shuffle.service.ShuffleService;
import service.shuffle.utils.ValidationUtils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ShuffleControllerTest {

    private ShuffleController shuffleController;
    private ShuffleService shuffleService;
    private LogService logService;

    @BeforeEach
    void setUp() {
        shuffleService = mock(ShuffleService.class);
        logService = mock(LogService.class);
        shuffleController = new ShuffleController(shuffleService, logService);

        ShuffleNumberProperties properties = new ShuffleNumberProperties();
        properties.setMin(1);
        properties.setMax(1000);

        ValidationUtils.initialize(properties);
    }

    @Test
    void shuffle_shouldReturnShuffledArray_whenValidNumber() {
        int number = 5;
        int[] expectedArray = {3, 1, 4, 2, 5};
        when(shuffleService.generateShuffledArray(number)).thenReturn(expectedArray);

        ResponseEntity<int[]> response = shuffleController.shuffle(number);

        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertArrayEquals(expectedArray, response.getBody());
        verify(logService, times(1)).sendLogRequest(number, expectedArray);
    }

    @Test
    void shuffle_shouldReturnBadRequest_whenNumberIsInvalid() {
        int invalidNumber = 1001;

        ResponseEntity<int[]> response = shuffleController.shuffle(invalidNumber);

        assertNotNull(response);
        assertEquals(400, response.getStatusCode().value());
        verifyNoInteractions(shuffleService);
        verifyNoInteractions(logService);
    }
}