package com.thibaultonboard.technicalchallenge202108.number;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author thibault2705
 */
@RestController
public class NumberFactController {

    @Autowired
    private NumberFactService numberFactService;

    @GetMapping("/fact/trivia/{number}")
    public ResponseEntity<NumberFact> randomFact(@PathVariable int number) {
        NumberFact numberFact = numberFactService.getTriviaFact(number);

        return ResponseEntity.ok(numberFact);
    }
}
