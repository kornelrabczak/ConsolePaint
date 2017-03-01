package com.thecookiezen.infrastructure;

import com.thecookiezen.bussiness.boundary.Printer;
import com.thecookiezen.bussiness.control.Canvas;
import com.thecookiezen.bussiness.entity.Line;
import com.thecookiezen.bussiness.entity.Point;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

public class SystemOutPrinterTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    private Printer printer = new SystemOutPrinter();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }

    @Test
    public void should_print_canvas_with_vertical_and_horizontal_line() {
        final Canvas canvas = new Canvas(10, 5);

        final Line vertical = new Line(new Point(7, 1), new Point(7, 7));
        final Line vertical2 = new Line(new Point(3, 0), new Point(3, 4));
        final Line horizontal = new Line(new Point(0, 1), new Point(20, 1));

        canvas.drawLine(vertical);
        canvas.drawLine(vertical2);
        canvas.drawLine(horizontal);

        // when
        printer.print(canvas);

        // then
        assertThat(outContent.toString()).isEqualTo(
                "------------" + System.lineSeparator() +
                "|   x   x  |" + System.lineSeparator() +
                "|   x   x  |" + System.lineSeparator() +
                "|   x   x  |" + System.lineSeparator() +
                "|xxxxxxxxxx|" + System.lineSeparator() +
                "|   x      |" + System.lineSeparator()  +
                "------------" + System.lineSeparator() );
    }
}