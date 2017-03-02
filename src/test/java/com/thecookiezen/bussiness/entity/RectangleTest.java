package com.thecookiezen.bussiness.entity;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RectangleTest {

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_exception_for_same_points() {
        // given
        Point p1 = new Point(2, 4);
        Point p2 = new Point(2, 4);

        // when
        new Rectangle(p1, p2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_exception_for_points_in_vertical_line() {
        // given
        Point p1 = new Point(2, 3);
        Point p2 = new Point(2, 5);

        // when
        new Rectangle(p1, p2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_exception_for_points_in_horizontal_line() {
        // given
        Point p1 = new Point(1, 4);
        Point p2 = new Point(3, 4);

        // when
        new Rectangle(p1, p2);
    }

    @Test
    public void should_return_points_for_2x2_rectangle() {
        // given
        Point p1 = new Point(2, 2);
        Point p2 = new Point(3, 1);

        // when
        final Rectangle actual = new Rectangle(p1, p2);

        // then
        assertThat(actual.getLines()).containsOnly(
                new Line(new Point(2, 2), new Point(3,2)),
                new Line(new Point(2, 2), new Point(2,1)),
                new Line(new Point(3, 1), new Point(3,2)),
                new Line(new Point(3, 1), new Point(2,1))
        );
    }

    @Test
    public void should_return_points_for_5x3_rectangle() {
        // given
        Point p1 = new Point(8, 8);
        Point p2 = new Point(13, 5);

        // when
        final Rectangle actual = new Rectangle(p1, p2);

        // then
        assertThat(actual.getLines()).containsOnly(
                new Line(new Point(8, 8), new Point(13,8)),
                new Line(new Point(8, 8), new Point(8,5)),
                new Line(new Point(13, 8), new Point(13,5)),
                new Line(new Point(8, 5), new Point(13,5))
        );
    }

    @Test
    public void should_return_points_for_3x5_rectangle() {
        // given
        Point p1 = new Point(8, 8);
        Point p2 = new Point(11, 3);

        // when
        final Rectangle actual = new Rectangle(p1, p2);

        // then
        assertThat(actual.getLines()).containsOnly(
                new Line(new Point(8, 8), new Point(11,8)),
                new Line(new Point(8, 8), new Point(8,3)),
                new Line(new Point(11, 8), new Point(11,3)),
                new Line(new Point(8, 3), new Point(11,3))
        );
    }

}