package com.thecookiezen.bussiness.entity;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PointTest {

    @Test
    public void should_return_valid_point_from_two_integers() {
        // given
        int x = 1, y = 3;

        // when
        final Point actual = new Point(x, y);

        // then
        assertThat(actual.getX()).isEqualTo(x);
        assertThat(actual.getY()).isEqualTo(y);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_exception_for_negative_integers() {
        // given
        int x = -1, y = 3;

        // when
        new Point(x, y);
    }

}