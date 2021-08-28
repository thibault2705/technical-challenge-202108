package com.thibaultonboard.technicalchallenge202108.number;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * @author thibault2705
 */
@Getter
@Setter
@Builder
public class NumberFact {

    private String text;

    private int number;

    private boolean found;

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null || !(other instanceof NumberFact)) {
            return false;
        }

        NumberFact that = (NumberFact) other;
        return number == that.number &&
                found == that.found &&
                Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, number, found);
    }
}
