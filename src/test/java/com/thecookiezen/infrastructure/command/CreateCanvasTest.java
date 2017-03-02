package com.thecookiezen.infrastructure.command;

import com.google.common.collect.Lists;
import com.thecookiezen.bussiness.boundary.Printer;
import com.thecookiezen.bussiness.control.Canvas;
import com.thecookiezen.bussiness.control.UserInput;
import com.thecookiezen.infrastructure.CommandHandler;
import org.junit.Test;

import java.util.ArrayList;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

public class CreateCanvasTest {

    private Canvas canvas = mock(Canvas.class);

    private Printer printer = mock(Printer.class);

    private CommandHandler commandHandler = mock(CommandHandler.class);

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_exception_for_wrong_coordinates_count() {
        final ArrayList<Integer> coordinates = Lists.newArrayList(1, 2, 3);
        new CreateCanvas(commandHandler, coordinates, printer).execute(new UserInput(Character.MIN_VALUE, coordinates, "c"));
    }

    @Test
    public void should_execute_command() {
        // given
        final UserInput userInput = new UserInput(Character.MIN_VALUE, Lists.newArrayList(2, 4), "c");

        // when
        new CreateCanvas(commandHandler, userInput.getCoordinates(), printer).execute(userInput);

        // then
        verifyZeroInteractions(canvas);
        verify(commandHandler, only()).setCanvas(any());
        verify(printer, only()).print(any(Canvas.class));
    }

}