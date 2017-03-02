package com.thecookiezen.infrastructure;

import com.thecookiezen.bussiness.boundary.Printer;
import com.thecookiezen.bussiness.control.Canvas;

import java.io.PrintStream;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ConsolePrinter implements Printer {

    private static final int HORIZONTAL_BOUNDARY_LENGTH = 2;

    private final PrintStream out;

    public ConsolePrinter(PrintStream out) {
        this.out = out;
    }

    @Override
    public void print(Canvas canvas) {
        final char[][] drawableArea = canvas.getDrawableArea();
        drawHorizontalBoundary(drawableArea.length);
        drawCanvas(drawableArea);
        drawHorizontalBoundary(drawableArea.length);
    }

    @Override
    public void print(String message) {
        out.print(message);
    }

    @Override
    public void println(String message) {
        out.println(message);
    }

    private void drawCanvas(char[][] drawableArea) {
        for (int y = 0; y <= drawableArea[0].length - 1; y++) {
            out.print('|');
            for (char[] aDrawableArea : drawableArea) {
                out.print(aDrawableArea[y]);
            }
            out.println('|');
        }
    }

    private void drawHorizontalBoundary(int length) {
        out.println(IntStream.range(0, length + HORIZONTAL_BOUNDARY_LENGTH)
                .mapToObj(i -> "-")
                .collect(Collectors.joining()));
    }
}
