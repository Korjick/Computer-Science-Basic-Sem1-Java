package Download;

import java.util.Scanner;

public class Main {

    static DownloadThread thread;

    public static void main(String[] args) {
        String text;
        Scanner in = new Scanner(System.in);
        while (!(text = in.nextLine()).equals("quit")) {
            parser(text.trim().toLowerCase());
        }
    }

    public static void parser(String command) {
        try {
            String[] param = command.split(" ", 2);

            if (param.length > 1) {
                if (param[0].trim().equals("download")) {
                    if (thread == null || thread.getState().equals(Thread.State.TERMINATED)) {
                        thread = new DownloadThread(param[1].trim());
                        thread.start();
                    }
                }
            } else if (param.length > 0) {
                if (param[0].trim().equals("status")) {
                    if (thread == null) System.out.println("Поток для скачивания свободен");
                    else thread.percentOfDownloading();
                }

                if (param[0].trim().equals("stop")) {
                    if (thread != null) {
                        thread.interrupt();
                        System.out.println("Скачивание было прервано");
                    } else System.out.println("Поток не возможно прекратить, так как он еще не был открыт");
                }
            }
        } catch (NullPointerException | IllegalArgumentException e){
            System.out.println("Ошибка при вводе данных");
        }
    }
}
