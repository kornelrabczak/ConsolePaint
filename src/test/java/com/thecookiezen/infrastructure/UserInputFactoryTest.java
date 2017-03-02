package com.thecookiezen.infrastructure;

import com.google.common.collect.Lists;
import com.thecookiezen.bussiness.control.UserInput;
import org.junit.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class UserInputFactoryTest {

    private static final UserInputFactory factory = new UserInputFactory();

    @Test
    public void should_return_empty_request_for_empty_string() {
        // when
        final UserInput actual = factory.createUserInputFromString("");

        // then
        assertThat(actual).isEqualTo(UserInput.EMPTY_USER_INPUT);
    }

    @Test
    public void should_return_empty_request_for_invalid_string() {
        // when
        final UserInput actual = factory.createUserInputFromString("C v 4");

        // then
        assertThat(actual).isEqualTo(UserInput.EMPTY_USER_INPUT);
    }

    @Test
    public void should_create_new_canvas_request() {
        // when
        final UserInput actual = factory.createUserInputFromString("C 20 4");

        // then
        assertThat(actual).isEqualTo(new UserInput('C', Lists.newArrayList(20, 4), ""));
    }

    @Test
    public void should_create_new_line_request() {
        // when
        final UserInput actual = factory.createUserInputFromString("L 1 2 6 2");

        // then
        assertThat(actual).isEqualTo(new UserInput('L', Lists.newArrayList(1, 2, 6, 2), ""));
    }

    @Test
    public void should_return_empty_request_for_invalid_new_line_command() {
        // when
        final UserInput actual = factory.createUserInputFromString("L 6 v 3 6");

        // then
        assertThat(actual).isEqualTo(UserInput.EMPTY_USER_INPUT);
    }

    @Test
    public void should_create_new_rectangle_request() {
        // when
        final UserInput actual = factory.createUserInputFromString("R 16 1 20 3");

        // then
        assertThat(actual).isEqualTo(new UserInput('R', Lists.newArrayList(16, 1, 20, 3), ""));
    }

    @Test
    public void should_create_new_fill_request() {
        // when
        final UserInput actual = factory.createUserInputFromString("B 10 3 c");

        // then
        assertThat(actual).isEqualTo(new UserInput('B', Lists.newArrayList(10, 3), "c"));
    }

    @Test
    public void should_create_quit_request() {
        // when
        final UserInput actual = factory.createUserInputFromString("Q");

        // then
        assertThat(actual).isEqualTo(new UserInput('Q', Collections.emptyList(), ""));
    }
}