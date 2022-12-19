package handywheel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Util {
    public static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

    public static void printlnWithTimestamp(String s){
        System.out.println("[" + timeFormatter.format(LocalDateTime.now()) + "] => " + s);
    }
}
