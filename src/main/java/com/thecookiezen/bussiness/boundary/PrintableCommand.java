package com.thecookiezen.bussiness.boundary;

import com.google.common.base.Preconditions;
import com.thecookiezen.bussiness.control.Canvas;
import com.thecookiezen.bussiness.control.UserInput;

public abstract class PrintableCommand {

    protected final Canvas canvas;

    protected final Printer printer;

    public PrintableCommand(Canvas canvas, Printer printer) {
        Preconditions.checkNotNull(canvas, "Canvas must be created first. Use C command.");
        this.canvas = canvas;
        this.printer = printer;
    }

    public abstract void execute(UserInput userInput);
}
