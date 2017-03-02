package com.thecookiezen.infrastructure.command;

import com.google.common.base.Preconditions;
import com.thecookiezen.bussiness.boundary.Printable;
import com.thecookiezen.bussiness.boundary.Printer;
import com.thecookiezen.bussiness.control.Canvas;
import com.thecookiezen.bussiness.control.UserInput;
import com.thecookiezen.infrastructure.CommandHandler;

public class CreateCanvas implements Printable {

    public static final char COMMAND_KEY = 'C';

    private final CommandHandler handler;
    private final UserInput userInput;

    public CreateCanvas(CommandHandler handler, UserInput userInput) {
        Preconditions.checkArgument(userInput.getCoordinates().size() == 2, "Create canvas command must have 2 coordinates.");
        this.handler = handler;
        this.userInput = userInput;
    }

    @Override
    public void execute(Canvas ignore, Printer printer) {
        final Canvas createdCanvas = new Canvas(userInput.getCoordinates().get(0), userInput.getCoordinates().get(1));
        handler.setCanvas(createdCanvas);
        printer.print(createdCanvas);
    }
}
