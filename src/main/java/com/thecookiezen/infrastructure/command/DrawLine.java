package com.thecookiezen.infrastructure.command;

import com.google.common.base.Preconditions;
import com.thecookiezen.bussiness.boundary.PrintableCommand;
import com.thecookiezen.bussiness.boundary.Printer;
import com.thecookiezen.bussiness.control.Canvas;
import com.thecookiezen.bussiness.control.UserInput;
import com.thecookiezen.bussiness.entity.Line;
import com.thecookiezen.bussiness.entity.PointOneBased;

public class DrawLine extends PrintableCommand {

    public static final char COMMAND_KEY = 'L';

    public DrawLine(Canvas canvas, Printer printer) {
        super(canvas, printer);
    }

    @Override
    public void execute(UserInput userInput) {
        Preconditions.checkArgument(userInput.getCoordinates().size() == 4, "Draw line command must have 4 coordinates.");
        canvas.drawLine(new Line(
                new PointOneBased(userInput.getCoordinates().get(0), userInput.getCoordinates().get(1)),
                new PointOneBased(userInput.getCoordinates().get(2), userInput.getCoordinates().get(3))
        ));
        printer.print(canvas);
    }
}
