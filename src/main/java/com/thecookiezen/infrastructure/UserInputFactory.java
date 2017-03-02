package com.thecookiezen.infrastructure;

import com.thecookiezen.bussiness.control.UserInput;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class UserInputFactory {

    private static final String COMMAND = "command";
    private static final String COORDINATE = "coordinate";
    private static final String PARAMETER = "parameter";

    private static final Pattern REQUEST_PATTERN = Pattern.compile("^(?<" + COMMAND +
            ">[A-Z])\\s?(?<" + COORDINATE + ">[\\d\\s]*)\\s?(?<" + PARAMETER + ">[a-z]?)$");

    private static final Pattern WHITESPACE_PATTERN = Pattern.compile("\\s");

    public UserInput createUserInputFromString(String requestStringify) {
        final Matcher requestMatcher = REQUEST_PATTERN.matcher(requestStringify);
        if (requestMatcher.find()) {
            final List<Integer> pointers = WHITESPACE_PATTERN.splitAsStream(requestMatcher.group(COORDINATE))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            return new UserInput(requestMatcher.group(COMMAND).trim().charAt(0), pointers, requestMatcher.group(PARAMETER));
        }
        return UserInput.EMPTY_USER_INPUT;
    }
}
