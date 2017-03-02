package com.thecookiezen.infrastructure.command;

import com.google.common.base.Preconditions;
import com.thecookiezen.bussiness.boundary.Printable;
import com.thecookiezen.bussiness.boundary.Printer;
import com.thecookiezen.bussiness.control.Canvas;
import com.thecookiezen.bussiness.control.UserInput;
import com.thecookiezen.bussiness.entity.PointOneBased;
import com.thecookiezen.bussiness.entity.Rectangle;

public class DrawRectangle implements Printable {

    public static final char COMMAND_KEY = 'R';

    private final UserInput userInput;

    public DrawRectangle(UserInput userInput) {
        Preconditions.checkArgument(userInput.getCoordinates().size() == 4, "Draw rectangle command must have 4 coordinates.");
        this.userInput = userInput;
    }

    @Override
    public void execute(Canvas canvas, Printer printer) {
        canvas.drawRectangle(new Rectangle(
                new PointOneBased(userInput.getCoordinates().get(0), userInput.getCoordinates().get(1)),
                new PointOneBased(userInput.getCoordinates().get(2), userInput.getCoordinates().get(3))
        ));
        printer.print(canvas);
    }
}
