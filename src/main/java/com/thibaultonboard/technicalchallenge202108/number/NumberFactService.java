package com.thibaultonboard.technicalchallenge202108.number;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author thibault2705
 */
@Service
public class NumberFactService {

    private static final int MIN = 0;
    private static final int MAX = 999999;
    public static final String X_RAPIDAPI_HOST = "x-rapidapi-host";
    public static final String X_RAPIDAPI_KEY = "x-rapidapi-key";

    @Autowired
    private RestTemplate restTemplate;

    @Value("${numbers_api.base_url}")
    private String baseUrl;

    @Value("${numbers_api.host}")
    private String host;

    @Value("${numbers_api.key}")
    private String key;

    /**
     * Return random fact of a number
     *
     * @param number
     * @return
     */
    public NumberFact getTriviaFact(int number) {
        UriComponents uriBuilder = UriComponentsBuilder
                .fromUriString(baseUrl)
                .path("/" + number)
                .path("/trivia")
                .queryParam("json", true)
                .build();

        final HttpHeaders headers = new HttpHeaders();
        headers.add(X_RAPIDAPI_HOST, host);
        headers.add(X_RAPIDAPI_KEY, key);

        final HttpEntity requestEntity = new HttpEntity(headers);

        final ResponseEntity<NumberFact> responseEntity = restTemplate
                .getForEntity(uriBuilder.toUriString(),
                        NumberFact.class,
                        requestEntity);

        NumberFact numberFact = responseEntity.getBody();
        return numberFact;
    }
}
