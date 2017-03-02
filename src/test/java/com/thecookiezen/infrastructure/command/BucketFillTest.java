package com.thecookiezen.infrastructure.command;

import com.google.common.collect.Lists;
import com.thecookiezen.bussiness.boundary.Printer;
import com.thecookiezen.bussiness.control.Canvas;
import com.thecookiezen.bussiness.control.UserInput;
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
        new BucketFill(canvas, printer).execute(new UserInput(Character.MIN_VALUE, Lists.newArrayList(1, 2, 3), "c"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_exception_for_empty_color_character() {
        new BucketFill(canvas, printer).execute(new UserInput(Character.MIN_VALUE, Lists.newArrayList(1, 2), " "));
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_exception_for_no_color_character() {
        new BucketFill(canvas, printer).execute(new UserInput(Character.MIN_VALUE, Lists.newArrayList(1, 2), ""));
    }

    @Test
    public void should_execute_command() {
        // given
        final UserInput userInput = new UserInput('B', Lists.newArrayList(1, 2), "c");

        // when
        new BucketFill(canvas, printer).execute(userInput);

        // then
        verify(canvas, only()).fill(eq(new PointOneBased(1, 2)), eq('c'));
        verify(printer, only()).print(canvas);
    }
}