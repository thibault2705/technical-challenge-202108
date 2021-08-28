package com.thibaultonboard.technicalchallenge202108.number;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mock;

/**
 * @author thibault2705
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({UriComponents.class, UriComponentsBuilder.class})
public class NumberFactServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private NumberFactService numberFactService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void givenCorrectParam() {
        NumberFact numberFact = NumberFact.builder()
                .number(2)
                .text("2 is a primorial, as well as its own factorial.")
                .found(true)
                .build();

        given(numberFact);

        NumberFact response = numberFactService.getTriviaFact(2);

        Assert.assertEquals(numberFact.getNumber(), response.getNumber());
        Assert.assertEquals(numberFact.getText(), response.getText());
        Assert.assertEquals(numberFact.isFound(), response.isFound());
    }

    private void given(NumberFact numberFact) {
        ResponseEntity<NumberFact> responseEntity = new ResponseEntity<NumberFact>(numberFact, HttpStatus.OK);

        when(restTemplate.getForEntity(
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<NumberFact>>any(),
                ArgumentMatchers.any(HttpEntity.class)))
                .thenReturn(responseEntity);

        PowerMockito.mockStatic(UriComponentsBuilder.class);
        UriComponentsBuilder uriComponentsBuilder = mock(UriComponentsBuilder.class, RETURNS_DEEP_STUBS);

        when(uriComponentsBuilder
                .path(ArgumentMatchers.anyString()))
                .thenReturn(uriComponentsBuilder);
        when(uriComponentsBuilder
                .queryParam(ArgumentMatchers.anyString(), ArgumentMatchers.any(Boolean.class)))
                .thenReturn(uriComponentsBuilder);

        when(UriComponentsBuilder
                .fromUriString(ArgumentMatchers.any()))
                .thenReturn(uriComponentsBuilder);

    }
}
