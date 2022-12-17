package handywheel;

import handywheel.client.Client;
import handywheel.core.Core;
import handywheel.server.Server;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        new Core().sayHi();

        System.out.println("Ready...");
        String consoleInput = System.console().readLine();

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
