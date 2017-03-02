package com.thecookiezen.infrastructure.command;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.thecookiezen.bussiness.boundary.Printable;
import com.thecookiezen.bussiness.boundary.Printer;
import com.thecookiezen.bussiness.control.Canvas;
import com.thecookiezen.bussiness.control.UserInput;
import com.thecookiezen.bussiness.entity.PointOneBased;

public class BucketFill implements Printable {

    public static final char COMMAND_KEY = 'B';

    private final UserInput userInput;

    public BucketFill(UserInput userInput) {
        Preconditions.checkArgument(userInput.getCoordinates().size() == 2, "Bucket fill command must have 2 coordinates.");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(userInput.getAdditionalParameter()), "Fill color command be single character.");
        Preconditions.checkArgument(!userInput.getAdditionalParameter().trim().isEmpty(), "Fill color can't be empty.");
        this.userInput = userInput;
    }

    @Override
    public void execute(Canvas canvas, Printer printer) {
        canvas.fill(new PointOneBased(userInput.getCoordinates().get(0), userInput.getCoordinates().get(1)),
                userInput.getAdditionalParameter().charAt(0));
        printer.print(canvas);
    }
}
