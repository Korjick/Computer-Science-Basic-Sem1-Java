package Explorer;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    static String text;
    static Scanner in = new Scanner(System.in);
    static Path path = Paths.get(".").normalize().toAbsolutePath();

    public static void main(String[] args) {
        System.out.print("<" + path + ">: ");
        while (!(text = in.nextLine()).toLowerCase().trim().equals("quit")) {
            parser(text.toLowerCase().trim());
            System.out.print("<" + path + ">: ");
        }
    }

    public static void recursiveDelete(Path delete) throws IOException {
        if (Files.isDirectory(delete))
            for (File f : Objects.requireNonNull(delete.toFile().listFiles()))
                recursiveDelete(delete);

        Files.delete(delete);
    }

    public static void recursiveCopy(Path from, Path to) throws IOException {
        if (Files.isDirectory(from)) {
            Files.copy(from, to);
            for (File f : Objects.requireNonNull(from.toFile().listFiles()))
                recursiveCopy(f.toPath().toAbsolutePath().normalize(),
                        to.resolve(f.getName()));
        }

        Files.copy(from, to);
    }

    public static void parser(String text) {
        try {
            String[] line = text.split(" ", 2);
            String cmd = line[0];

            if (cmd.equals("cd")) {
                if (line.length > 1) {
                    String param = line[1].trim().toLowerCase();
                    if (param.equals("..") && !path.equals(path.getRoot())) {
                        path = path.getParent();
                        return;
                    }

                    Path absolute = path.resolve(param).toAbsolutePath().normalize();
                    if (Files.exists(absolute) && Files.isDirectory(absolute)) path = Paths.get(absolute.toString());
                } else {
                    System.out.println("Введите путь к папке");
                }
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
                if (line.length > 1) {
                    String param = line[1].trim().toLowerCase();
                    Path run = path.resolve(param).toAbsolutePath().normalize();

                    if (Files.exists(run) && !Files.isDirectory(run)) {
                        System.out.println("Запуск программы...");
                        Desktop.getDesktop().open(run.toFile());
                    }
                } else {
                    System.out.println("Введите путь к файлу для исполнения");
                }
            }

            if (cmd.equals("copy")) {
                if (line.length > 1) {
                    ArrayList<String> fromTo = new ArrayList<>();
                    String param = line[1].trim().toLowerCase();

                    if (param.contains("\"")) {
                        Pattern pattern = Pattern.compile("\"([a-zA-Z\\s0-9/\\\\.]+)\"");
                        Matcher matcher = pattern.matcher(param);

                        while (matcher.find()) {
                            String add = matcher.group();
                            add = add.substring(add.indexOf("\"") + 1, add.lastIndexOf("\""));
                            fromTo.add(add);
                            System.out.println(add);
                        }
                    } else fromTo.addAll(Arrays.asList(line[1].trim().split(" ", 2)));

                    if (fromTo.size() > 1) {
                        Path from = path.resolve(fromTo.get(0).toLowerCase().trim()).toAbsolutePath().normalize();
                        Path to = path.resolve(fromTo.get(1).toLowerCase().trim()).toAbsolutePath().normalize();

                        if (Files.exists(from) && Files.exists(to.getParent())) {
                            System.out.println("Копирование файла...");
                            recursiveCopy(from, to);
                        }
                    }
                } else {
                    System.out.println("Укажите сначала путь к файлу(включая имя файла) или папке, который собираетесь копировать, " +
                            "а потом путь к месту назначения(включая имя нового файла)");
                }
            }

            if (cmd.equals("delete")) {
                if (line.length > 1) {
                    String param = line[1].trim().toLowerCase();
                    Path delete = path.resolve(param).toAbsolutePath().normalize();

                    if (Files.exists(delete)) {
                        System.out.println("Удаление файла...");
                        recursiveDelete(delete);
                    }
                } else {
                    System.out.println("Введите путь к файлу для удаления");
                }
            }
        } catch (InvalidPathException e) {
            System.out.println("Путь введен некорректно");
        } catch (DirectoryNotEmptyException e) {
            System.out.println("Невозможно удалить каталог, так как он содержит файлы");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
