package com.thecookiezen.infrastructure;

import com.google.common.collect.ImmutableMap;
import com.thecookiezen.bussiness.boundary.Printer;
import com.thecookiezen.bussiness.control.Canvas;
import com.thecookiezen.bussiness.control.UserInput;
import com.thecookiezen.infrastructure.command.BucketFill;
import com.thecookiezen.infrastructure.command.CreateCanvas;
import com.thecookiezen.infrastructure.command.DrawLine;
import com.thecookiezen.infrastructure.command.DrawRectangle;

import java.util.Map;
import java.util.function.Consumer;

public class CommandHandler {

    private final Map<Character, Consumer<UserInput>> executables;

    private final static Consumer<UserInput> NO_OP = req -> {};

    private Canvas canvas;

    public CommandHandler(Printer printer) {
        executables = ImmutableMap.<Character, Consumer<UserInput>>builder()
                .put(CreateCanvas.COMMAND_KEY, request -> new CreateCanvas(this, request.getCoordinates(), printer).execute(request))
                .put(DrawLine.COMMAND_KEY, request -> new DrawLine(canvas, printer).execute(request))
                .put(DrawRectangle.COMMAND_KEY, request -> new DrawRectangle(canvas, printer).execute(request))
                .put(BucketFill.COMMAND_KEY, request -> new BucketFill(canvas, printer).execute(request))
                .put(Quit.COMMAND_KEY, request -> {
                    throw new Quit();
                })
                .build();
    }

    public void handle(UserInput userInput) {
        executables.getOrDefault(userInput.getCommandKey(), NO_OP).accept(userInput);
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }
}
