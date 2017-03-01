package com.thecookiezen.infrastructure.command;

import com.google.common.base.Preconditions;
import com.thecookiezen.bussiness.boundary.Printable;
import com.thecookiezen.bussiness.boundary.Printer;
import com.thecookiezen.bussiness.control.Canvas;
import com.thecookiezen.bussiness.control.Request;
import com.thecookiezen.bussiness.entity.PointOneBased;
import com.thecookiezen.bussiness.entity.Rectangle;

public class DrawRectangle implements Printable {

    public static final char COMMAND_KEY = 'R';

    private final Request request;

    public DrawRectangle(Request request) {
        Preconditions.checkArgument(request.getCoordinates().size() == 4, "Draw rectangle command must have 4 coordinates.");
        this.request = request;
    }

    @Override
    public void execute(Canvas canvas, Printer printer) {
        canvas.drawRectangle(new Rectangle(
                new PointOneBased(request.getCoordinates().get(0), request.getCoordinates().get(1)),
                new PointOneBased(request.getCoordinates().get(2), request.getCoordinates().get(3))
        ));
        printer.print(canvas);
    }
}
