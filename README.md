# ConsolePaint

Simple console based application for drawing lines and rectangle on created canvas.

## Commands

* 'C w h' - creates new canvas of width w and height h
* 'L x1 y1 x2 y2' - creates a new line from (x1,y1) to (x2,y2). Only horizontal or vertical lines are supported.
* 'R x1 y1 x2 y2' - creates a new rectangle, with upper left corner (x1,y1) and lower right corner (x2,y2).
* 'B x y c' - fill the area connected to point (x,y) with colour 'c'.
* 'Quit' - exit program

## Running application

    mvn clean package
    java -jar target/console-paint-1.0-SNAPSHOT-jar-with-dependencies.jar

## Used additional libraries

* Guava - utility library for most common APIs
* Mockito - mocking framework for unit testing
* AssertJ - fluent API for assertions
