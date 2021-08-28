package com.thibaultonboard.technicalchallenge202108.number;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.annotation.Resource;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author thibault2705
 */
@SpringBootTest
@AutoConfigureMockMvc
public class NumberFactControllerTest {
    @Autowired
    private MockMvc mvc;

    @Mock
    private NumberFactService numberFactService;

    @InjectMocks
    @Resource
    private NumberFactController numberFactController;

    public void given(int number, NumberFact numberFact) {
        // Initialize mocks created above
        MockitoAnnotations.initMocks(this);

        when(numberFactService.getTriviaFact(number))
                .thenReturn(numberFact);
    }

    @Test
    public void givenCorrectPathVariable() throws Exception {
        NumberFact numberFact = NumberFact.builder()
                .number(2)
                .text("2 is a primorial, as well as its own factorial.")
                .found(true)
                .build();

        given(2, numberFact);

        mvc.perform(MockMvcRequestBuilders.get("/fact/trivia/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("number", Matchers.equalTo(numberFact.getNumber())))
                .andExpect(jsonPath("text", Matchers.equalTo(numberFact.getText())))
                .andExpect(jsonPath("found", Matchers.equalTo(true)));

    }

    @Test
    public void givenIncorrectPathVariableType() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/fact/trivia/abc")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void givenUnExistingPathVariable() throws Exception {
        NumberFact numberFact = NumberFact.builder()
                .number(5)
                .found(false)
                .build();

        given(5, numberFact);
        mvc.perform(MockMvcRequestBuilders.get("/fact/trivia/5")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("number", Matchers.equalTo(numberFact.getNumber())))
                .andExpect(jsonPath("found", Matchers.equalTo(false)));
    }
}
