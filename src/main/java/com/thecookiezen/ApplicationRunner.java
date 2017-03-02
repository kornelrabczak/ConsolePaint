package com.thecookiezen;

import com.thecookiezen.bussiness.boundary.Printer;
import com.thecookiezen.bussiness.control.UserInput;
import com.thecookiezen.infrastructure.CommandHandler;
import com.thecookiezen.infrastructure.Quit;
import com.thecookiezen.infrastructure.UserInputFactory;
import com.thecookiezen.infrastructure.ConsolePrinter;

import java.io.InputStream;
import java.util.Scanner;

public class ApplicationRunner {

    private final UserInputFactory userInputFactory;

    private final Printer printer;

    ApplicationRunner(UserInputFactory userInputFactory, Printer printer) {
        this.userInputFactory = userInputFactory;
        this.printer = printer;
    }

    public static void main(String[] args) {
        new ApplicationRunner(new UserInputFactory(), new ConsolePrinter(System.out)).start(System.in);
    }

    void start(InputStream in) {
        final CommandHandler commandHandler = new CommandHandler(printer);

        Scanner scanner = new Scanner(in);

        printer.print("enter command: ");
        while (scanner.hasNextLine()) {
            final UserInput userInput = userInputFactory.createUserInputFromString(scanner.nextLine());

            try {
                commandHandler.handle(userInput);
            } catch (Quit q) {
                break;
            } catch (Exception e) {
                printer.println(e.getMessage());
            }
            printer.print("enter command: ");
        }
    }
}
