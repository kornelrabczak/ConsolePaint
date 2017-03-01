package com.thecookiezen.infrastructure;

import com.thecookiezen.bussiness.control.Request;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RequestFactoryTest {

    private static final RequestFactory factory = new RequestFactory();

    @Test
    public void should_return_empty_request_for_empty_string() {
        // when
        final Request actual = factory.createRequestFromString("");

        // then
        assertThat(actual).isEqualTo(Request.EMPTY_REQUEST);
    }

    @Test
    public void should_return_empty_request_for_invalid_string() {
        // when
        final Request actual = factory.createRequestFromString("C v 4");

        // then
        assertThat(actual).isEqualTo(Request.EMPTY_REQUEST);
    }

    @Test
    public void should_create_new_canvas_request() {
        // when
        final Request actual = factory.createRequestFromString("C 20 4");

        // then
        assertThat(actual.getCommandKey()).isEqualTo('C');
        assertThat(actual.getCoordinates()).containsExactly(20, 4);
        assertThat(actual.getAdditionalParameter()).isEmpty();
    }

    @Test
    public void should_create_new_line_request() {
        // when
        final Request actual = factory.createRequestFromString("L 1 2 6 2");

        // then
        assertThat(actual.getCommandKey()).isEqualTo('L');
        assertThat(actual.getCoordinates()).containsExactly(1, 2, 6, 2);
        assertThat(actual.getAdditionalParameter()).isEmpty();
    }

    @Test
    public void should_return_empty_request_for_invalid_new_line_command() {
        // when
        final Request actual = factory.createRequestFromString("L 6 v 3 6");

        // then
        assertThat(actual).isEqualTo(Request.EMPTY_REQUEST);
    }

    @Test
    public void should_create_new_rectangle_request() {
        // when
        final Request actual = factory.createRequestFromString("R 16 1 20 3");

        // then
        assertThat(actual.getCommandKey()).isEqualTo('R');
        assertThat(actual.getCoordinates()).containsExactly(16, 1, 20, 3);
        assertThat(actual.getAdditionalParameter()).isEmpty();
    }

    @Test
    public void should_create_new_fill_request() {
        // when
        final Request actual = factory.createRequestFromString("B 10 3 c");

        // then
        assertThat(actual.getCommandKey()).isEqualTo('B');
        assertThat(actual.getCoordinates()).containsExactly(10, 3);
        assertThat(actual.getAdditionalParameter()).isEqualTo("c");
    }

    @Test
    public void should_create_quit_request() {
        // when
        final Request actual = factory.createRequestFromString("Q");

        // then
        assertThat(actual.getCommandKey()).isEqualTo('Q');
        assertThat(actual.getCoordinates()).isEmpty();
        assertThat(actual.getAdditionalParameter()).isEmpty();
    }
}