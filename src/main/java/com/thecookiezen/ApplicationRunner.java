package com.thecookiezen;

import com.thecookiezen.bussiness.boundary.Printer;
import com.thecookiezen.bussiness.control.Request;
import com.thecookiezen.infrastructure.CommandHandler;
import com.thecookiezen.infrastructure.Quit;
import com.thecookiezen.infrastructure.RequestFactory;
import com.thecookiezen.infrastructure.SystemOutPrinter;

import java.util.Scanner;

public class ApplicationRunner {

    public static void main(String[] args) {
        final RequestFactory requestFactory = new RequestFactory();
        final Printer printer = new SystemOutPrinter();
        final CommandHandler commandHandler = new CommandHandler(printer);

        Scanner scanner = new Scanner(System.in);

        System.out.print("enter command: ");
        while (scanner.hasNextLine()) {
            final Request request = requestFactory.createRequestFromString(scanner.nextLine());

            try {
                commandHandler.handle(request);
            } catch (Quit q) {
                break;
            } catch (Exception e) {
                printer.print(e.getMessage());
            }
            System.out.print("enter command: ");
        }
    }
}
