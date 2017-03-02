package com.thecookiezen.infrastructure;

import com.thecookiezen.bussiness.boundary.Printer;
import com.thecookiezen.bussiness.control.Canvas;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SystemOutPrinter implements Printer {

    private static final int HORIZONTAL_BOUNDARY_LENGTH = 2;

    @Override
    public void print(Canvas canvas) {
        final char[][] drawableArea = canvas.getDrawableArea();
        drawHorizontalBoundary(drawableArea.length);
        drawCanvas(drawableArea);
        drawHorizontalBoundary(drawableArea.length);
    }

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    private void drawCanvas(char[][] drawableArea) {
        for (int y = 0; y <= drawableArea[0].length - 1; y++) {
            System.out.print('|');
            for (char[] aDrawableArea : drawableArea) {
                System.out.print(aDrawableArea[y]);
            }
            System.out.println('|');
        }
    }

    private void drawHorizontalBoundary(int length) {
        System.out.println(IntStream.range(0, length + HORIZONTAL_BOUNDARY_LENGTH)
                .mapToObj(i -> "-")
                .collect(Collectors.joining()));
    }
}
