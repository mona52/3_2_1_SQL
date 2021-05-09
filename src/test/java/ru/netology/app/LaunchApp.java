package ru.netology.app;

import java.io.IOException;

public class LaunchApp {
    public static void launch() {

        String filePath = "./app-deadline.jar ";
        String param1 = " -P:jdbc.url=jdbc:mysql://localhost:3306/appdb ";
        String param2 = " -P:jdbc.user=user ";
        String param3 = " -P:jdbc.password=pass ";

        Runtime re = Runtime.getRuntime();
        try {
            re.exec("java -jar " + filePath + param1 + param2 + param3);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
