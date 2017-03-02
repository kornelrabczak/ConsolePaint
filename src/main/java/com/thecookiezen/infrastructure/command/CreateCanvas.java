package com.thecookiezen.infrastructure.command;

import com.thecookiezen.bussiness.boundary.PrintableCommand;
import com.thecookiezen.bussiness.boundary.Printer;
import com.thecookiezen.bussiness.control.Canvas;
import com.thecookiezen.bussiness.control.UserInput;
import com.thecookiezen.infrastructure.CommandHandler;

import java.util.List;

public class CreateCanvas extends PrintableCommand {

    public static final char COMMAND_KEY = 'C';

    public CreateCanvas(CommandHandler commandHandler, List<Integer> coordinates, Printer printer) {
        super(new Canvas(coordinates.get(0), coordinates.get(1)), printer);
        commandHandler.setCanvas(canvas);
    }

    @Override
    public void execute(UserInput userInput) {
        printer.print(canvas);
    }
}
