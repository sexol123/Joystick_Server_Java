package handywheel.server;

import handywheel.Constant;

import java.io.*;
import java.net.*;

public class Server {

    public static void serverRun() {
        Server server = new Server();
        try {
            server.setup();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private ServerSocket serverSocket;
    private Socket socket;

    void setup() throws IOException {
        serverSocket = new ServerSocket(4999);
        socket = serverSocket.accept();

        System.out.println("Client connected");

        setupReader();
    }

    void setup(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        socket = serverSocket.accept();

        System.out.println("Client connected");

        setupReader();
    }

    void setupReader() throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String msg;
        do {
            msg = bufferedReader.readLine();
            System.out.println("Client -> " + msg);
        } while (!msg.equals(""));
    }
}
