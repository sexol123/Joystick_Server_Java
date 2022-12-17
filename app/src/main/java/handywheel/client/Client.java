package handywheel.client;

import java.io.*;
import java.net.*;

public class Client {

    public static void clientRun() {
        Client client = new Client();
        try {
            client.setup();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private Socket socket;
    private PrintWriter printWriter;

    void setup() throws IOException {
        socket = new Socket("localhost", 4999);
        printWriter = new PrintWriter(socket.getOutputStream(), true);

        sendMessage("Hello from client!!!");
        listening();
    }

    void setup(String address, int port) throws IOException {
        socket = new Socket(address, port);
        printWriter = new PrintWriter(socket.getOutputStream());

        sendMessage("Hello from client!!!");
    }

    void sendMessage(String msg) {
        if (printWriter == null) {
            System.out.println("ERORR: Can't send " + msg);
            return;
        }
        printWriter.println(msg);
    }

    void listening() {
        String consoleInput;
        System.out.println("Listening... Press ENTER to EXIT");

        do {
            consoleInput = System.console().readLine();
            sendMessage(consoleInput);
        } while (!consoleInput.equals(""));

        terminate();
    }

    void terminate() {
        if (printWriter != null) {
            //printWriter.flush();
            printWriter = null;
        }
    }
}
