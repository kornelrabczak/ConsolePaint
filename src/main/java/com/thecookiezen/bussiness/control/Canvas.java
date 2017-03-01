package com.thecookiezen.bussiness.control;

import com.google.common.base.Preconditions;
import com.thecookiezen.bussiness.entity.Line;
import com.thecookiezen.bussiness.entity.Point;
import com.thecookiezen.bussiness.entity.Rectangle;

import java.util.Arrays;

public class Canvas {

    private static final char EMPTY_SPACE = ' ';

    private final char[][] drawableArea;

    public Canvas(final int width, final int height) {
        Preconditions.checkArgument(width > 1, "Canvas width must be greater than 1");
        Preconditions.checkArgument(height > 1, "Canvas height must be greater than 1");

        this.drawableArea = new char[width][height];

        for (char[] row : this.drawableArea) {
            Arrays.fill(row, EMPTY_SPACE);
        }
    }

    public void drawLine(Line line) {
        Arrays.stream(line.getPoints())
                .filter(this::isPointWithinBoundary)
                .forEach(p -> this.drawableArea[p.getX()][p.getY()] = Line.FILL_CHARACTER);
    }

    private boolean isPointWithinBoundary(Point p) {
        return p.getX() >= 0 && p.getX() < drawableArea.length
                && p.getY() >= 0 && p.getY() < drawableArea[0].length;
    }

    public char[][] getDrawableArea() {
        char[][] copyOf = new char[drawableArea.length][];
        for (int x = 0; x < drawableArea.length; x++) {
            copyOf[x] = new char[drawableArea[x].length];
            for (int y = 0; y < drawableArea[x].length; y++) {
                copyOf[x][y] = drawableArea[x][y];
            }
        }
        return copyOf;
    }

    public void drawRectangle(Rectangle rectangle) {
        for (Line line : rectangle.getLines()) {
            drawLine(line);
        }
    }
}
