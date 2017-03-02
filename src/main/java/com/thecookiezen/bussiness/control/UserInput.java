package com.thecookiezen.bussiness.control;

import java.util.Collections;
import java.util.List;

public class UserInput {

    public static final UserInput EMPTY_USER_INPUT = new UserInput(Character.MIN_VALUE, Collections.emptyList(), "");

    private final char commandKey;
    private final List<Integer> coordinates;
    private final String additionalParameter;

    public UserInput(char commandKey, List<Integer> pointers, String additionalParameter) {
        this.commandKey = commandKey;
        this.coordinates = pointers;
        this.additionalParameter = additionalParameter;
    }

    public char getCommandKey() {
        return commandKey;
    }

    public List<Integer> getCoordinates() {
        return coordinates;
    }

    public String getAdditionalParameter() {
        return additionalParameter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserInput userInput = (UserInput) o;

        if (commandKey != userInput.commandKey) return false;
        if (!coordinates.equals(userInput.coordinates)) return false;
        return additionalParameter.equals(userInput.additionalParameter);
    }

    @Override
    public int hashCode() {
        int result = (int) commandKey;
        result = 31 * result + coordinates.hashCode();
        result = 31 * result + additionalParameter.hashCode();
        return result;
    }
}
