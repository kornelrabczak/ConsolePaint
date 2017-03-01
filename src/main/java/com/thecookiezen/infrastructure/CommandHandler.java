package com.thecookiezen.infrastructure;

import com.google.common.collect.ImmutableMap;
import com.thecookiezen.bussiness.boundary.Printer;
import com.thecookiezen.bussiness.control.Canvas;
import com.thecookiezen.bussiness.control.Request;
import com.thecookiezen.infrastructure.command.CreateCanvas;
import com.thecookiezen.infrastructure.command.DrawLine;
import com.thecookiezen.infrastructure.command.DrawRectangle;

import java.util.Map;
import java.util.function.Consumer;

public class CommandHandler {

    private final Map<Character, Consumer<Request>> executables;

    private final static Consumer<Request> NO_OP = req -> {};

    private Canvas canvas;

    public CommandHandler(Printer printer) {
        executables = ImmutableMap.<Character, Consumer<Request>>builder()
                .put(CreateCanvas.COMMAND_KEY, request -> new CreateCanvas(this, request).execute(canvas, printer))
                .put(DrawLine.COMMAND_KEY, request -> new DrawLine(request).execute(canvas, printer))
                .put(DrawRectangle.COMMAND_KEY, request -> new DrawRectangle(request).execute(canvas, printer))
                .put(Quit.COMMAND_KEY, request -> {
                    throw new Quit();
                })
                .build();
    }

    public void handle(Request request) {
        if (request.getCommandKey() != CreateCanvas.COMMAND_KEY && canvas == null) {
            throw new IllegalStateException("Canvas must be created first. Use C command.");
        }

        executables.getOrDefault(request.getCommandKey(), NO_OP).accept(request);
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }
}
