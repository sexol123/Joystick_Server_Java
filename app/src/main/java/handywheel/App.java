package handywheel;

import handywheel.client.Client;
import handywheel.core.Core;
import handywheel.server.Server;

import java.io.IOException;
import java.util.Scanner;

import static handywheel.Util.printlnWithTimestamp;

public class App {
    public static void main(String[] args) throws IOException {
        new Core().sayHi();

        Scanner scanner = new Scanner(System.in);

        printlnWithTimestamp("s - server, c - client");
        String consoleInput = "s";//scanner.nextLine();

        if (consoleInput.equals("s")) {
            printlnWithTimestamp("Server mode...");
            Server.serverRun();
        } else if (consoleInput.equals("c")) {
            printlnWithTimestamp("Client mode...");
            Client.clientRun();
        } else {
            printlnWithTimestamp("Ooops....");
        }
    }
}
