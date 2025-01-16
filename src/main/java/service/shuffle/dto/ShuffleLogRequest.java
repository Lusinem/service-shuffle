package service.shuffle.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShuffleLogRequest {
    private Integer number;
    private int[] shuffledArray;
}