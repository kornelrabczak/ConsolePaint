package com.thecookiezen.bussiness.entity;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LineTest {

    @Test(expected = NullPointerException.class)
    public void should_throw_nullpointer_for_null_point() {
        // given
        new Line(null, new Point(1, 2));
    }

    @Test
    public void should_find_points_between_two_points_horizontal() {
        // given
        Point p1 = new Point(3, 10);
        Point p2 = new Point(7, 10);

        // when
        final Line actual = new Line(p1, p2);

        // then
        assertThat(actual.getPoints()).hasSize(5);
        assertThat(actual.getPoints()).containsOnly(
                new Point(3, 10), new Point(4, 10), new Point(5, 10), new Point(6, 10), new Point(7, 10)
        );
    }

    @Test
    public void should_find_points_between_two_points_vertical() {
        // given
        Point p1 = new Point(3, 2);
        Point p2 = new Point(3, 4);

        // when
        final Line actual = new Line(p1, p2);

        // then
        assertThat(actual.getPoints()).hasSize(3);
        assertThat(actual.getPoints()).containsOnly(new Point(3, 2), new Point(3, 3), new Point(3, 4));
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_exception_for_two_points_crosswise() {
        // given
        Point p1 = new Point(3, 2);
        Point p2 = new Point(5, 6);

        // when && then
        new Line(p1, p2);
    }

    @Test
    public void should_find_points_between_two_same_points() {
        // given
        Point p1 = new Point(3, 2);
        Point p2 = new Point(3, 2);

        // when
        final Line actual = new Line(p1, p2);

        // then
        assertThat(actual.getPoints()).hasSize(1);
        assertThat(actual.getPoints()).containsOnly(p1);
    }

    @Test
    public void should_find_points_between_two_nearby_points() {
        // given
        Point p1 = new Point(3, 2);
        Point p2 = new Point(4, 2);
        Point p3 = new Point(2, 2);
        Point p4 = new Point(3, 1);
        Point p5 = new Point(3, 3);

        // when
        final Line actual1 = new Line(p1, p2);
        final Line actual2 = new Line(p1, p3);
        final Line actual3 = new Line(p1, p4);
        final Line actual4 = new Line(p1, p5);

        // then
        assertThat(actual1.getPoints()).containsOnly(p1, p2);
        assertThat(actual2.getPoints()).containsOnly(p1, p3);
        assertThat(actual3.getPoints()).containsOnly(p1, p4);
        assertThat(actual4.getPoints()).containsOnly(p1, p5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_exception_when_one_of_the_point_is_negative() {
        // given
        Point p1 = new Point(-3, 2);
        Point p2 = new Point(3, 2);

        // when && then
        new Line(p1, p2);
    }
}