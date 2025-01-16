package service.shuffle.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.shuffle.service.LogService;
import service.shuffle.service.ShuffleService;
import service.shuffle.utils.ValidationUtils;

@RestController
public class ShuffleController {

    private static final Logger logger = LogManager.getLogger(ShuffleController.class);
    private final ShuffleService shuffleService;

    private final LogService logService;

    public ShuffleController(ShuffleService shuffleService,LogService logService) {
        this.shuffleService = shuffleService;
        this.logService = logService;
    }

    /**
     * Handles the shuffle request by generating and returning a shuffled array of integers.
     *
     * Currently, the method accepts a single integer parameter via {@code @RequestParam}. However,
     * it could be refactored to use a {@code RequestInput} object, which would enable:
     *
     *   * Annotated validation for cleaner and more maintainable validation logic.
     *   * Flexibility for future request extensions without modifying the method signature.
     *
     *
     * @param number The number specifying the size of the shuffled array. Must pass validation checks.
     * @return {@code ResponseEntity<int[]>} containing the shuffled array if valid; otherwise, a
     *         {@code 400 Bad Request} response.
     */
    @PostMapping("/shuffle")
    public ResponseEntity<int[]> shuffle(@RequestParam Integer number) {
        if (!ValidationUtils.isValidNumber(number)) {
            logger.warn("Validation failed: Input number [{}] is outside the allowed range.",
                    number);
            return ResponseEntity.badRequest().build();
        }

        final int[] shuffledArray = shuffleService.generateShuffledArray(number);
        logService.sendLogRequest(number, shuffledArray);

        return ResponseEntity.ok(shuffledArray);
    }

}
