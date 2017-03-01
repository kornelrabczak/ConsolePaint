package com.thecookiezen.bussiness.entity;

import com.google.common.base.Preconditions;

public class Rectangle {

    private final Line[] lines;

    public Rectangle(Point p1, Point p2) {
        Preconditions.checkNotNull(p1, "Point p1 can't be null.");
        Preconditions.checkNotNull(p2, "Point p2 can't be null.");
        Preconditions.checkArgument(!p1.equals(p2), "Points can't be the same.");
        Preconditions.checkArgument(p1.getX() != p2.getX(), "Points can't be in line.");
        Preconditions.checkArgument(p1.getY() != p2.getY(), "Points can't be in line.");

        this.lines = new Line[4];
        this.lines[0] = new Line(new Point(p1.getX(), p1.getY()), new Point(p2.getX(), p1.getY()));
        this.lines[1] = new Line(new Point(p1.getX(), p1.getY()), new Point(p1.getX(), p2.getY()));
        this.lines[2] = new Line(new Point(p1.getX(), p2.getY()), new Point(p2.getX(), p2.getY()));
        this.lines[3] = new Line(new Point(p2.getX(), p2.getY()), new Point(p2.getX(), p1.getY()));
    }

    public Line[] getLines() {
        return lines;
    }
}
