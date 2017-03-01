package com.thecookiezen.bussiness.entity;

import com.google.common.base.Preconditions;

public class Point {

    private final int x;
    private final int y;

    public Point(int x, int y) {
        Preconditions.checkArgument(x > -1, "x must be non negative.");
        Preconditions.checkArgument(y > -1, "x must be non negative.");

        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (x != point.x) return false;
        return y == point.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return "[" + x + ", " + y + "]";
    }
}
