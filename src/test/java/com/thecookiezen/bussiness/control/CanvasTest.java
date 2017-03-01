package com.thecookiezen.bussiness.control;

import com.thecookiezen.bussiness.entity.Line;
import com.thecookiezen.bussiness.entity.Point;
import com.thecookiezen.bussiness.entity.Rectangle;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CanvasTest {

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_exception_for_invalid_width() {
        new Canvas(0, 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_exception_for_invalid_height() {
        new Canvas(5, 1);
    }

    @Test
    public void should_return_empty_canvas_2x2() {
        // given & when
        final Canvas canvas = new Canvas(2, 2);

        // then
        assertThat(canvas.getDrawableArea()).containsExactly(new char[][]{{' ', ' '}, {' ', ' '}});
    }

    @Test
    public void should_draw_vertical_line_on_canvas_5x5() {
        // given
        final Canvas canvas = new Canvas(5, 5);

        // when
        canvas.drawLine(new Line(new Point(1, 2), new Point(1, 4)));

        // then
        assertThat(canvas.getDrawableArea()).containsExactly(new char[][]{
                // y ->
           /*x*/{' ', ' ', ' ', ' ', ' '},
                {' ', ' ', 'x', 'x', 'x'},
                {' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' '}
        });
    }

    @Test
    public void should_draw_horizontal_line_on_canvas_5x5() {
        // given
        final Canvas canvas = new Canvas(5, 5);

        // when
        canvas.drawLine(new Line(new Point(1, 2), new Point(4, 2)));

        // then
        assertThat(canvas.getDrawableArea()).containsExactly(new char[][]{
                // y ->
           /*x*/{' ', ' ', ' ', ' ', ' '},
                {' ', ' ', 'x', ' ', ' '},
                {' ', ' ', 'x', ' ', ' '},
                {' ', ' ', 'x', ' ', ' '},
                {' ', ' ', 'x', ' ', ' '}
        });
    }

    @Test
    public void should_draw_horizontal_line_beyond_canvas_5x5() {
        // given
        final Canvas canvas = new Canvas(5, 5);

        // when
        canvas.drawLine(new Line(new Point(2, 2), new Point(10, 2)));

        // then
        assertThat(canvas.getDrawableArea()).containsExactly(new char[][]{
                // y ->
           /*x*/{' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' '},
                {' ', ' ', 'x', ' ', ' '},
                {' ', ' ', 'x', ' ', ' '},
                {' ', ' ', 'x', ' ', ' '}
        });
    }

    @Test
    public void should_draw_rectangle() {
        // given
        final Canvas canvas = new Canvas(10, 5);

        // when
        canvas.drawRectangle(new Rectangle(new Point(1, 3), new Point(7, 1)));

        // then
        assertThat(canvas.getDrawableArea()).containsExactly(new char[][]{
                // y ->
           /*x*/{' ', ' ', ' ', ' ', ' '},
                {' ', 'x', 'x', 'x', ' '},
                {' ', 'x', ' ', 'x', ' '},
                {' ', 'x', ' ', 'x', ' '},
                {' ', 'x', ' ', 'x', ' '},
                {' ', 'x', ' ', 'x', ' '},
                {' ', 'x', ' ', 'x', ' '},
                {' ', 'x', 'x', 'x', ' '},
                {' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' '}
        });
    }

    @Test
    public void should_draw_rectangle_beyond_canvas() {
        // given
        final Canvas canvas = new Canvas(10, 5);

        // when
        canvas.drawRectangle(new Rectangle(new Point(1, 6), new Point(7, 1)));

        // then
        assertThat(canvas.getDrawableArea()).containsExactly(new char[][]{
                // y ->
           /*x*/{' ', ' ', ' ', ' ', ' '},
                {' ', 'x', 'x', 'x', 'x'},
                {' ', 'x', ' ', ' ', ' '},
                {' ', 'x', ' ', ' ', ' '},
                {' ', 'x', ' ', ' ', ' '},
                {' ', 'x', ' ', ' ', ' '},
                {' ', 'x', ' ', ' ', ' '},
                {' ', 'x', 'x', 'x', 'x'},
                {' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' '}
        });
    }

    @Test
    public void should_draw_rectangle_in_corner() {
        // given
        final Canvas canvas = new Canvas(10, 5);

        // when
        canvas.drawRectangle(new Rectangle(new Point(5, 7), new Point(20, 2)));

        // then
        assertThat(canvas.getDrawableArea()).containsExactly(new char[][]{
                // y ->
           /*x*/{' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' '},
                {' ', ' ', 'x', 'x', 'x'},
                {' ', ' ', 'x', ' ', ' '},
                {' ', ' ', 'x', ' ', ' '},
                {' ', ' ', 'x', ' ', ' '},
                {' ', ' ', 'x', ' ', ' '}
        });
    }

    @Test
    public void should_draw_rectangle_in_corner_with_line() {
        // given
        final Canvas canvas = new Canvas(10, 5);

        // when
        canvas.drawRectangle(new Rectangle(new Point(5, 7), new Point(20, 2)));
        canvas.drawLine(new Line(new Point(3, 3), new Point(6, 3)));

        // then
        assertThat(canvas.getDrawableArea()).containsExactly(new char[][]{
                // y ->
           /*x*/{' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', 'x', ' '},
                {' ', ' ', ' ', 'x', ' '},
                {' ', ' ', 'x', 'x', 'x'},
                {' ', ' ', 'x', 'x', ' '},
                {' ', ' ', 'x', ' ', ' '},
                {' ', ' ', 'x', ' ', ' '},
                {' ', ' ', 'x', ' ', ' '}
        });
    }

    @Test
    public void should_fill_canvas() {
        // given
        final Canvas canvas = new Canvas(5, 5);

        // when
        canvas.fill(new Point(1, 2), 't');

        // then
        assertThat(canvas.getDrawableArea()).containsExactly(new char[][]{
                // y ->
           /*x*/{'t', 't', 't', 't', 't'},
                {'t', 't', 't', 't', 't'},
                {'t', 't', 't', 't', 't'},
                {'t', 't', 't', 't', 't'},
                {'t', 't', 't', 't', 't'}
        });
    }

    @Test
    public void should_fill_canvas_only_on_one_side() {
        // given
        final Canvas canvas = new Canvas(5, 5);

        // when
        canvas.drawLine(new Line(new Point(1, 0), new Point(1, 4)));
        canvas.fill(new Point(3, 3), 'q');

        // then
        assertThat(canvas.getDrawableArea()).containsExactly(new char[][]{
                // y ->
           /*x*/{' ', ' ', ' ', ' ', ' '},
                {'x', 'x', 'x', 'x', 'x'},
                {'q', 'q', 'q', 'q', 'q'},
                {'q', 'q', 'q', 'q', 'q'},
                {'q', 'q', 'q', 'q', 'q'}
        });
    }

    @Test
    public void should_fill_4_parts_of_canvas() {
        // given
        final Canvas canvas = new Canvas(5, 5);

        // when
        canvas.drawLine(new Line(new Point(2, 0), new Point(2, 4)));
        canvas.drawLine(new Line(new Point(0, 2), new Point(4, 2)));
        canvas.fill(new Point(0, 0), 'w');
        canvas.fill(new Point(1, 4), 'e');
        canvas.fill(new Point(3, 1), 'q');
        canvas.fill(new Point(4, 4), 'z');

        // then
        assertThat(canvas.getDrawableArea()).containsExactly(new char[][]{
                // y ->
           /*x*/{'w', 'w', 'x', 'e', 'e'},
                {'w', 'w', 'x', 'e', 'e'},
                {'x', 'x', 'x', 'x', 'x'},
                {'q', 'q', 'x', 'z', 'z'},
                {'q', 'q', 'x', 'z', 'z'}
        });
    }

    @Test
    public void should_fill_rectangle_inside() {
        // given
        final Canvas canvas = new Canvas(5, 5);

        // when
        canvas.drawRectangle(new Rectangle(new Point(1, 1), new Point(4, 4)));
        canvas.fill(new Point(2, 2), 'q');

        // then
        assertThat(canvas.getDrawableArea()).containsExactly(new char[][]{
                // y ->
           /*x*/{' ', ' ', ' ', ' ', ' '},
                {' ', 'x', 'x', 'x', 'x'},
                {' ', 'x', 'q', 'q', 'x'},
                {' ', 'x', 'q', 'q', 'x'},
                {' ', 'x', 'x', 'x', 'x'}
        });
    }

    @Test
    public void should_fill_all_space_between_lines() {
        // given
        final Canvas canvas = new Canvas(5, 5);

        // when
        canvas.drawLine(new Line(new Point(1, 1), new Point(3, 1)));
        canvas.drawLine(new Line(new Point(3, 1), new Point(3, 3)));
        canvas.drawLine(new Line(new Point(0, 3), new Point(3, 3)));
        canvas.fill(new Point(0, 0), 'z');

        // then
        assertThat(canvas.getDrawableArea()).containsExactly(new char[][]{
                // y ->
           /*x*/{'z', 'z', 'z', 'x', 'z'},
                {'z', 'x', 'z', 'x', 'z'},
                {'z', 'x', 'z', 'x', 'z'},
                {'z', 'x', 'x', 'x', 'z'},
                {'z', 'z', 'z', 'z', 'z'}
        });
    }

    @Test
    public void should_fill_outside_rectangle() {
        // given
        final Canvas canvas = new Canvas(5, 5);

        // when
        canvas.drawRectangle(new Rectangle(new Point(1, 2), new Point(2, 3)));
        canvas.fill(new Point(0, 2), 'o');

        // then
        assertThat(canvas.getDrawableArea()).containsExactly(new char[][]{
                // y ->
           /*x*/{'o', 'o', 'o', 'o', 'o'},
                {'o', 'o', 'x', 'x', 'o'},
                {'o', 'o', 'x', 'x', 'o'},
                {'o', 'o', 'o', 'o', 'o'},
                {'o', 'o', 'o', 'o', 'o'}
        });
    }

    @Test
    public void should_change_fill_color() {
        // given
        final Canvas canvas = new Canvas(5, 5);

        // when
        canvas.drawLine(new Line(new Point(1, 0), new Point(1, 4)));
        canvas.fill(new Point(0, 2), 'c');
        canvas.fill(new Point(0, 4), 'e');

        // then
        assertThat(canvas.getDrawableArea()).containsExactly(new char[][]{
                // y ->
           /*x*/{'e', 'e', 'e', 'e', 'e'},
                {'x', 'x', 'x', 'x', 'x'},
                {' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' '}
        });
    }

}