package com.thecookiezen.infrastructure.command;

import com.google.common.collect.Lists;
import com.thecookiezen.bussiness.boundary.Printer;
import com.thecookiezen.bussiness.control.Canvas;
import com.thecookiezen.bussiness.control.Request;
import com.thecookiezen.bussiness.entity.PointOneBased;
import org.junit.Test;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

public class BucketFillTest {

    private Canvas canvas = mock(Canvas.class);

    private Printer printer = mock(Printer.class);

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_exception_for_wrong_coordinates_count() {
        new BucketFill(new Request(Character.MIN_VALUE, Lists.newArrayList(1, 2, 3), "c"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_exception_for_empty_color_character() {
        new BucketFill(new Request(Character.MIN_VALUE, Lists.newArrayList(1, 2), " "));
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_exception_for_no_color_character() {
        new BucketFill(new Request(Character.MIN_VALUE, Lists.newArrayList(1, 2), ""));
    }

    @Test
    public void should_execute_command() {
        // given
        final Request c = new Request('B', Lists.newArrayList(1, 2), "c");

        // when
        new BucketFill(c).execute(canvas, printer);

        // then
        verify(canvas, only()).fill(eq(new PointOneBased(1, 2)), eq('c'));
        verify(printer, only()).print(canvas);
    }
}