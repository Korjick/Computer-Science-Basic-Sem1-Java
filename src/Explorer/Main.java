package Explorer;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    static String text;
    static Scanner in = new Scanner(System.in);
    static Path path = Paths.get(".").normalize().toAbsolutePath();

    public static void main(String[] args) throws IOException {
        System.out.print("<" + path + ">: ");
        while (!(text = in.nextLine()).toLowerCase().trim().equals("quit")) {
            parser(text.toLowerCase().trim());
            System.out.print("<" + path + ">: ");
        }
    }

    public static void parser(String text) {
        try {
            String[] line = text.split(" ", 2);
            String cmd = line[0];
            String param = path.toString();

            if (line.length > 1) param = line[1];
            Path abs = path.resolve(param).toAbsolutePath().normalize();

            if (cmd.equals("cd")) {
                if (param.equals("..") && !path.equals(path.getRoot())) {
                    path = path.getParent();
                    return;
                }

                if (Files.exists(abs) && Files.isDirectory(abs)) path = Paths.get(abs.toString());
            }

            if (cmd.equals("dir")) {
                File fileList = new File(path.toString());
                BasicFileAttributes attr;

                System.out.println("[ <--------");
                for (File file : Objects.requireNonNull(fileList.listFiles())) {
                    attr = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
                    System.out.println(file.getName() + ":");
                    System.out.println("    creationTime: " + attr.creationTime());
                    System.out.println("    lastAccessTime: " + attr.lastAccessTime());
                    System.out.println("    lastModifiedTime: " + attr.lastModifiedTime());
                    System.out.println("    isDirectory: " + attr.isDirectory());
                    System.out.println("    isOther: " + attr.isOther());
                    System.out.println("    isRegularFile: " + attr.isRegularFile());
                    System.out.println("    isSymbolicLink: " + attr.isSymbolicLink());
                    System.out.println("    size: " + attr.size());
                }
                System.out.println("--------> ]");
            }

            if (cmd.equals("run")) {
                if (Files.exists(abs) && !Files.isDirectory(abs)) {
                    Desktop.getDesktop().open(abs.toFile());
                }
            }
        } catch (InvalidPathException e) {
            System.out.println("Путь введен некорректно");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
