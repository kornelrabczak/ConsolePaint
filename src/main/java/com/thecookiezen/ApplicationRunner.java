package com.thecookiezen;

import com.thecookiezen.bussiness.boundary.Printer;
import com.thecookiezen.bussiness.control.UserInput;
import com.thecookiezen.infrastructure.CommandHandler;
import com.thecookiezen.infrastructure.Quit;
import com.thecookiezen.infrastructure.UserInputFactory;
import com.thecookiezen.infrastructure.SystemOutPrinter;

import java.util.Scanner;

public class ApplicationRunner {

    public static void main(String[] args) {
        final UserInputFactory userInputFactory = new UserInputFactory();
        final Printer printer = new SystemOutPrinter();
        final CommandHandler commandHandler = new CommandHandler(printer);

        Scanner scanner = new Scanner(System.in);

        System.out.print("enter command: ");
        while (scanner.hasNextLine()) {
            final UserInput userInput = userInputFactory.createUserInputFromString(scanner.nextLine());

            try {
                commandHandler.handle(userInput);
            } catch (Quit q) {
                break;
            } catch (Exception e) {
                printer.print(e.getMessage());
            }
            System.out.print("enter command: ");
        }
    }
}
