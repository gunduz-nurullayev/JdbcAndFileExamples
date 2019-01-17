package PathFile;

import java.io.Console;

public class ConsoleDemo {
    public static void main(String[] args) {
        Console console = System.console();
        if (console != null) {
            console.printf("Zehmet olmasa username daxil edin: ");
            String user = console.readLine();
            console.printf("Parolu daxil edin: ");
            char[] password=console.readPassword();
            console.printf("Yeni user %s yaradildi, parolu %s",user,password);

        }
        else {
            System.out.println("Console yaranmadi!");
        }
    }
}
