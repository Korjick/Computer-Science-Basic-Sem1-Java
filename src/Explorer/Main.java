package Explorer;

import java.awt.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

    static String text;
    static Scanner in = new Scanner(System.in);
    static Path path = Paths.get(".").normalize().toAbsolutePath();

    public static void main(String[] args) {
        System.out.print("<" + path + ">: ");

        while (!(text = in.nextLine()).equals("quit")) {
            parser(text);
            System.out.print("<" + path + ">: ");
        }
    }

    public static void parser(String text) {
        try {
            if (text.length() > 2) {
                if (text.trim().toLowerCase().substring(0,2).equals("cd")) {
                    text = text.substring(2).trim();

                    if (text.equals("..")) {
                        if (!path.equals(path.getRoot())) path = path.getParent();
                        return;
                    }

                    if(text.contains("/")){
                        Path abs = Paths.get(text);
                        if(Files.exists(abs)) path = Paths.get(abs.toString());
                    } else{
                        path = Paths.get(path.toString() + "/" + text).normalize().toAbsolutePath();
                        if (!Files.exists(path)) path = path.getParent();
                    }
                    return;
                }

                if(text.trim().toLowerCase().substring(0,3).equals("dir")){
                    File fileSystem = new File(path.toString());
                    System.out.println("[ <--------");
                    for(File file : fileSystem.listFiles()) System.out.println(file);
                    System.out.println("--------> ]");
                    return;
                }

                if(text.trim().toLowerCase().substring(0,4).equals("open")){
                    text = text.substring(4).trim();
                    File run = new File(path.toString() + "/" + text);
                    if(Files.exists(run.toPath()) && !Files.isDirectory(run.toPath())){
                        System.out.println("Starting " + run + "...");
                        Desktop.getDesktop().open(run);
                    }
                    return;
                }
            }
        } catch (InvalidPathException e) {
            System.out.println("Путь введен некорректно");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
