package service.shuffle.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import service.shuffle.service.LogService;
import service.shuffle.service.ShuffleService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ShuffleController.class)
public class ShuffleControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ShuffleService shuffleService;

    @MockitoBean
    private LogService logService;

    @BeforeEach
    public void setUp() {
        when(shuffleService.generateShuffledArray(5)).thenReturn(new int[]{1, 2, 3, 4, 5});
    }

    @Test
    public void shuffle_shouldReturnBadRequest_whenInputIsNull() throws Exception {
        mockMvc.perform(post("/shuffle")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest());
    }


    @Test
    public void shuffle_shouldReturnShuffledArray_whenInputIsValid() throws Exception {
        mockMvc.perform(post("/shuffle?number=5")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[1, 2, 3, 4, 5]"));
    }
}