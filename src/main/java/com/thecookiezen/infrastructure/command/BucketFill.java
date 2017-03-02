package com.thecookiezen.infrastructure.command;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.thecookiezen.bussiness.boundary.PrintableCommand;
import com.thecookiezen.bussiness.boundary.Printer;
import com.thecookiezen.bussiness.control.Canvas;
import com.thecookiezen.bussiness.control.UserInput;
import com.thecookiezen.bussiness.entity.PointOneBased;

public class BucketFill extends PrintableCommand {

    public static final char COMMAND_KEY = 'B';

    public BucketFill(Canvas canvas, Printer printer) {
        super(canvas, printer);
    }

    @Override
    public void execute(UserInput userInput) {
        Preconditions.checkArgument(userInput.getCoordinates().size() == 2, "Bucket fill command must have 2 coordinates.");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(userInput.getAdditionalParameter()), "Fill color command be single character.");
        Preconditions.checkArgument(!userInput.getAdditionalParameter().trim().isEmpty(), "Fill color can't be empty.");

        canvas.fill(new PointOneBased(userInput.getCoordinates().get(0), userInput.getCoordinates().get(1)),
                userInput.getAdditionalParameter().charAt(0));
        printer.print(canvas);
    }
}
