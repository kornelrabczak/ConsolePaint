package com.thecookiezen.infrastructure;

import com.thecookiezen.bussiness.control.Request;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class RequestFactory {

    private static final String COMMAND = "command";
    private static final String COORDINATE = "coordinate";
    private static final String PARAMETER = "parameter";

    private static final Pattern REQUEST_PATTERN = Pattern.compile("^(?<" + COMMAND +
            ">[A-Z])\\s?(?<" + COORDINATE + ">[\\d\\s]*)\\s?(?<" + PARAMETER + ">[a-z]?)$");

    private static final Pattern WHITESPACE_PATTERN = Pattern.compile("\\s");

    public Request createRequestFromString(String requestStringify) {
        final Matcher requestMatcher = REQUEST_PATTERN.matcher(requestStringify);
        if (requestMatcher.find()) {
            final List<Integer> pointers = WHITESPACE_PATTERN.splitAsStream(requestMatcher.group(COORDINATE))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            return new Request(requestMatcher.group(COMMAND).trim().charAt(0), pointers, requestMatcher.group(PARAMETER));
        }
        return Request.EMPTY_REQUEST;
    }
}
