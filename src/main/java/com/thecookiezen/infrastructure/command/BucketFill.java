package com.thecookiezen.infrastructure.command;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.thecookiezen.bussiness.boundary.Printable;
import com.thecookiezen.bussiness.boundary.Printer;
import com.thecookiezen.bussiness.control.Canvas;
import com.thecookiezen.bussiness.control.Request;
import com.thecookiezen.bussiness.entity.PointOneBased;

public class BucketFill implements Printable {

    public static final char COMMAND_KEY = 'B';

    private final Request request;

    public BucketFill(Request request) {
        Preconditions.checkArgument(request.getCoordinates().size() == 2, "Bucket fill command must have 2 coordinates.");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(request.getAdditionalParameter()), "Fill color command be single character.");
        Preconditions.checkArgument(!request.getAdditionalParameter().trim().isEmpty(), "Fill color can't be empty.");
        this.request = request;
    }

    @Override
    public void execute(Canvas canvas, Printer printer) {
        canvas.fill(new PointOneBased(request.getCoordinates().get(0), request.getCoordinates().get(1)),
                request.getAdditionalParameter().charAt(0));
        printer.print(canvas);
    }
}
