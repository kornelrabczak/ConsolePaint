package com.thecookiezen.infrastructure.command;

import com.google.common.collect.Lists;
import com.thecookiezen.bussiness.boundary.Printer;
import com.thecookiezen.bussiness.control.Canvas;
import com.thecookiezen.bussiness.control.UserInput;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

public class DrawLineTest {

    private Canvas canvas = mock(Canvas.class);

    private Printer printer = mock(Printer.class);

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_exception_for_wrong_coordinates_count() {
        new DrawLine(canvas, printer).execute(new UserInput(Character.MIN_VALUE, Lists.newArrayList(1, 2, 3), "c"));
    }

    @Test
    public void should_execute_command() {
        // given
        final UserInput userInput = new UserInput(Character.MIN_VALUE, Lists.newArrayList(1, 2, 1, 4), "c");

        // when
        new DrawLine(canvas, printer).execute(userInput);

        // then
        verify(canvas, only()).drawLine(any());
        verify(printer, only()).print(canvas);
    }
}