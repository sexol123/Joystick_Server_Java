package handywheel.server;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;
import java.net.*;
import java.util.*;

import static handywheel.Util.printlnWithTimestamp;

public class Server {
    public static int KEY_ENTER = 13;

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
    private Robot robot;

    void setup() throws IOException {
        serverSocket = new ServerSocket(4999);

        printlnWithTimestamp("LocalHost -> " + InetAddress.getLocalHost().toString());
        printlnWithTimestamp("LocalPort -> " + serverSocket.getLocalPort());
        printlnWithTimestamp("Waiting client...");

        socket = serverSocket.accept();

        printlnWithTimestamp("Client connected");

        setupRobot();
        setupReader();
    }

    private void setupRobot() {
        try {
            robot = new Robot();
        } catch (AWTException exception) {
            printlnWithTimestamp(exception.getMessage());
        }
    }

    void setup(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        socket = serverSocket.accept();

        printlnWithTimestamp("Client connected");

        setupReader();
    }

    void setupReader() throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);


        int character = 0;
        int keyState = 0;

        printlnWithTimestamp( "Ready.");

        String msg = bufferedReader.readLine();
        printlnWithTimestamp("Client -> " + msg);

        do {
            try {
                msg = bufferedReader.readLine();

                if (msg == null){
                    Thread.sleep(1000);
                    msg = bufferedReader.readLine();
                    if (msg == null)
                        restart();
                }

                String[] tmp = msg.split("-");
                character = Integer.parseInt(tmp[0]);
                if (character == KEY_ENTER){
                    character = KeyEvent.VK_ENTER; //for Robot
                }

                keyState = Integer.parseInt(tmp[1]);

                doAction(character, keyState);

                //System.out.println("Ch: "+character + " Key: " + keyState);
                printlnWithTimestamp("Client -> " + msg);
                //key = socket.getInputStream().read();
            }catch (IOException ex) {
                printlnWithTimestamp("Client -> " + ex.getMessage());
                restart();
            } catch (Exception ex) {
                printlnWithTimestamp("Client -> " + ex.getMessage());
            }
        } while (true);
    }

    private void restart(){
        printlnWithTimestamp("Restarting...");
        try {
            socket.close();
            serverSocket.close();
            setup();
        }catch (Exception ex){
            printlnWithTimestamp("Client -> " + ex.getMessage());
        }
    }

    private final HashMap<Integer, Boolean> pressedButtons = new HashMap<>(5);

    void doAction(int ch, int isPressed) {
        if (isPressed == 0) {
            robot.keyPress(ch);
            pressedButtons.put(ch, true);
            //System.out.println("Robot press -> " + ch);
        } else if (isPressed == 1) {
            pressedButtons.put(ch, false);
            robot.keyRelease(ch);
            //System.out.println("Robot Release -> " + ch);
        } else if (isPressed == 2) {

            boolean wasPressed = pressedButtons.get(ch);

            if (wasPressed)
                robot.keyRelease(ch);
            else
                robot.keyPress(ch);

            pressedButtons.put(ch, !wasPressed);
        }
    }
}
