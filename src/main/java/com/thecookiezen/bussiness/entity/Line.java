package com.thecookiezen.bussiness.entity;

import com.google.common.base.Preconditions;

import java.util.Arrays;

public class Line {

    public static final char FILL_CHARACTER = 'x';

    private final Point[] points;

    public Line(final Point start, final Point end) {
        Preconditions.checkNotNull(start, "Point can't be null.");
        Preconditions.checkNotNull(end, "Point can't be null.");

        int steps = 1;
        int xIncrement = 0;
        int yIncrement = 0;

        int dx = end.getX() - start.getX();
        int dy = end.getY() - start.getY();

        checkIsCurve(dx, dy);

        if (Math.abs(dx) > Math.abs(dy)) {
            steps = Math.abs(dx) + 1;
            xIncrement = 1;
        } else if (Math.abs(dx) < Math.abs(dy)) {
            steps = Math.abs(dy) + 1;
            yIncrement = 1;
        }

        this.points = new Point[steps];

        for(int step = 0; step < steps; step++) {
            points[step] = new Point(Math.min(start.getX(), end.getX()) + step * xIncrement,
                    Math.min(start.getY(), end.getY()) + step * yIncrement);
        }
    }

    private void checkIsCurve(int dx, int dy) {
        if (dx != 0 && dy != 0) {
            throw new IllegalArgumentException("2 points in a curve.");
        }
    }

    public Point[] getPoints() {
        return points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Line line = (Line) o;
        return Arrays.equals(points, line.points);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(points);
    }

    @Override
    public String toString() {
        return "Line{" + "points=" + Arrays.toString(points) + "}";
    }
}
