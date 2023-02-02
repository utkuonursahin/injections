import java.util.*;
import java.io.File;
import java.io.*;

public class OSCommandInjection {
    public static void main(String[] args) {
        String usr;
        Scanner input = new Scanner(System.in);
        System.out.print("Enter user name: ");
        usr = input.nextLine();
        String f_path = "C:\\Users\\Utku\\Desktop\\"+usr;
        File file = new File(f_path);
        try {
            String cmd = "cmd.exe /c dir "+f_path;
            Process process = Runtime.getRuntime().exec(cmd);
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String s = null;
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
            }
        }
        catch (IOException e) {
            System.out.println("Error executing command");
        }
    }
}
