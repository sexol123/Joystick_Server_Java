package handywheel.client;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {

    final String host = "192.168.137.1";
    final String localhost = "localhost";

    final Scanner scanner = new Scanner(System.in);

    JFrame jFrame = new JFrame();

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
        socket = new Socket(localhost, 4999);
        printWriter = new PrintWriter(socket.getOutputStream(), true);

        sendMessage("Hello from client!!!");

        jFrame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                sendMessage(Character.toString(e.getKeyCode()));
            }
        });
        jFrame.setVisible(true);

        //listening();
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

    void sendMessage(byte msg) {
        printWriter.println(msg);
    }

    void sendMessage(int msg) {
        printWriter.println(msg);
    }

    void listening() {



        String consoleInput;
        System.out.println("Listening... Press ENTER to EXIT");

        do {
            consoleInput = scanner.next();
            sendMessage(consoleInput);
        } while (true);

       // terminate();
    }

    void terminate() {
        if (printWriter != null) {
            printWriter.flush();
            printWriter = null;
        }
    }
}
