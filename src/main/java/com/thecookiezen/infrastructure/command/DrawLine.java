package com.thecookiezen.infrastructure.command;

import com.google.common.base.Preconditions;
import com.thecookiezen.bussiness.boundary.Printable;
import com.thecookiezen.bussiness.boundary.Printer;
import com.thecookiezen.bussiness.control.Canvas;
import com.thecookiezen.bussiness.control.Request;
import com.thecookiezen.bussiness.entity.Line;
import com.thecookiezen.bussiness.entity.PointOneBased;

public class DrawLine implements Printable {

    public static final char COMMAND_KEY = 'L';

    private final Request request;

    public DrawLine(Request request) {
        Preconditions.checkArgument(request.getCoordinates().size() == 4, "Draw line command must have 4 coordinates.");
        this.request = request;
    }

    @Override
    public void execute(Canvas canvas, Printer printer) {
        canvas.drawLine(new Line(
                new PointOneBased(request.getCoordinates().get(0), request.getCoordinates().get(1)),
                new PointOneBased(request.getCoordinates().get(2), request.getCoordinates().get(3))
        ));
        printer.print(canvas);
    }
}
