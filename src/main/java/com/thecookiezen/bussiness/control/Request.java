package com.thecookiezen.bussiness.control;

import java.util.Collections;
import java.util.List;

public class Request {

    public static final Request EMPTY_REQUEST = new Request(Character.MIN_VALUE, Collections.emptyList(), "");

    private final char commandKey;
    private final List<Integer> coordinates;
    private final String additionalParameter;

    public Request(char commandKey, List<Integer> pointers, String additionalParameter) {
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
    public String toString() {
        return "Request{" +
                "commandKey=" + commandKey +
                ", coordinates=" + coordinates +
                ", additionalParameter=" + additionalParameter +
                '}';
    }
}
