package Piano;

import java.io.*;
import java.nio.file.Paths;
import java.util.Scanner;

public class Text {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String play = in.nextLine().trim().toLowerCase();

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(Paths.get("").resolve("play.txt").toString()));
            BufferedReader reader = new BufferedReader(new FileReader(Paths.get("").resolve("play.txt").toString()))){
            writer.write(play);
            writer.flush();

            String read = reader.readLine();
            for (char k : read.toCharArray()) {
                if(Keys.containsKey(String.valueOf(k))) new Piano(Keys.get(String.valueOf(k))).start();
                Thread.sleep(1000);
            }
        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
