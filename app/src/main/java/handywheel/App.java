package handywheel;

import handywheel.client.Client;
import handywheel.core.Core;
import handywheel.server.Server;

import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws IOException {
        new Core().sayHi();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Ready...");
        String consoleInput = scanner.nextLine();

        if (consoleInput.equals("s")) {
            System.out.println("Server mode...");
            Server.serverRun();
        } else if (consoleInput.equals("c")) {
            System.out.println("Client mode...");
            Client.clientRun();
        } else {
            System.out.println("Ooops....");
        }
    }
}
