package com.thecookiezen;

import com.thecookiezen.infrastructure.ConsolePrinter;
import com.thecookiezen.infrastructure.UserInputFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

public class ApplicationAcceptanceTests {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    private final PrintStream printStream = new PrintStream(outContent);

    @Before
    public void setUpStreams() {
        System.setOut(printStream);
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }

    private ApplicationRunner sut = new ApplicationRunner(new UserInputFactory(), new ConsolePrinter(printStream));

    @Test
    public void should_create_and_print_canvas_step() {
        // given
        String createCanvasRequest = "C 20 4";

        // when
        sut.start(new ByteArrayInputStream(createCanvasRequest.getBytes()));

        // then
        assertThat(outContent.toString()).contains(
                "----------------------" + System.lineSeparator() +
                "|                    |" + System.lineSeparator() +
                "|                    |" + System.lineSeparator() +
                "|                    |" + System.lineSeparator() +
                "|                    |" + System.lineSeparator() +
                "----------------------" + System.lineSeparator() );
    }

    @Test
    public void should_print_horizontal_line_step() {
        // given
        String drawLine = "C 20 4\n L 1 2 6 2";

        // when
        sut.start(new ByteArrayInputStream(drawLine.getBytes()));

        // then
        assertThat(outContent.toString()).contains(
                "----------------------" + System.lineSeparator() +
                "|                    |" + System.lineSeparator() +
                "|xxxxxx              |" + System.lineSeparator() +
                "|                    |" + System.lineSeparator() +
                "|                    |" + System.lineSeparator() +
                "----------------------" + System.lineSeparator() );
    }

    @Test
    public void should_print_vertical_line_step() {
        // given
        String drawLine = "C 20 4\n L 1 2 6 2\n L 6 3 6 4";

        // when
        sut.start(new ByteArrayInputStream(drawLine.getBytes()));

        // then
        assertThat(outContent.toString()).contains(
                "----------------------" + System.lineSeparator() +
                "|                    |" + System.lineSeparator() +
                "|xxxxxx              |" + System.lineSeparator() +
                "|     x              |" + System.lineSeparator() +
                "|     x              |" + System.lineSeparator() +
                "----------------------" + System.lineSeparator() );
    }

    @Test
    public void should_print_rectangle_step() {
        // given
        String drawRectangle = "C 20 4\n L 1 2 6 2\n L 6 3 6 4\n R 16 1 20 3";

        // when
        sut.start(new ByteArrayInputStream(drawRectangle.getBytes()));

        // then
        assertThat(outContent.toString()).contains(
                "----------------------" + System.lineSeparator() +
                "|               xxxxx|" + System.lineSeparator() +
                "|xxxxxx         x   x|" + System.lineSeparator() +
                "|     x         xxxxx|" + System.lineSeparator() +
                "|     x              |" + System.lineSeparator() +
                "----------------------" + System.lineSeparator() );
    }

    @Test
    public void should_bucket_fill_step() {
        // given
        String fill = "C 20 4\n L 1 2 6 2\n L 6 3 6 4\n R 16 1 20 3\n B 10 3 o";

        // when
        sut.start(new ByteArrayInputStream(fill.getBytes()));

        // then
        assertThat(outContent.toString()).contains(
                "----------------------" + System.lineSeparator() +
                "|oooooooooooooooxxxxx|" + System.lineSeparator() +
                "|xxxxxxooooooooox   x|" + System.lineSeparator() +
                "|     xoooooooooxxxxx|" + System.lineSeparator() +
                "|     xoooooooooooooo|" + System.lineSeparator() +
                "----------------------" + System.lineSeparator() );
    }

    @Test
    public void should_quit_after_first_line() {
        // given
        String drawLine = "C 20 4\n L 1 2 6 2\n Q\n L 6 3 6 4\n R 16 1 20 3\n B 10 3 o";

        // when
        sut.start(new ByteArrayInputStream(drawLine.getBytes()));

        // then
        assertThat(outContent.toString()).doesNotContain(
                "----------------------" + System.lineSeparator() +
                "|                    |" + System.lineSeparator() +
                "|xxxxxx              |" + System.lineSeparator() +
                "|     x              |" + System.lineSeparator() +
                "|     x              |" + System.lineSeparator() +
                "----------------------" + System.lineSeparator() );
    }

    @Test
    public void should_print_exception_message() {
        // given
        String drawLine = "C 20 4\n R 1 2 3 2";

        // when
        sut.start(new ByteArrayInputStream(drawLine.getBytes()));

        // then
        assertThat(outContent.toString()).contains("Points can't be in line.");
    }
}