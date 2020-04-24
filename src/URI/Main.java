package URI;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            ArrayList<Integer> divOpen = new ArrayList<>();
            ArrayList<Integer> divClose = new ArrayList<>();

            Scanner in = new Scanner(System.in);
            String text = in.next();

            URI uri = new URI(text);
            URLConnection connection = uri.toURL().openConnection();

            String mime = connection.getContentType().split(";")[0];
            System.out.println(mime);
            MimeMap map = new MimeMap();
            for (Map.Map.Entry<String, String> node : map.entrySet()) {
                if (mime.equals(node.getValue())) {
                    mime = node.getKey();
                    break;
                }
            }

            BufferedInputStream is = new BufferedInputStream(connection.getInputStream());
            Files.copy(is, new File(System.getProperty("user.home") + "/downloaded." + mime).toPath());

            if (mime.equals("html") || mime.equals("htm")) {
                BufferedReader reader = new BufferedReader(new FileReader(System.getProperty("user.home") + "/downloaded." + mime));
                while ((text = reader.readLine()) != null) {
                    for (int i = 0; i < text.length(); i++) {
                        if (text.charAt(i) == '<') {
                            if (text.charAt(i + 1) != '/') {
                                if (text.substring(i + 1, i + 4).equals("div")) divOpen.add(1);
                            } else {
                                if (text.substring(i + 2, i + 5).equals("div")) divClose.add(1);
                            }
                        }
                    }
                }
                System.out.println(Math.min(divOpen.size(), divClose.size()));
            }
        } catch (URISyntaxException uriex) {
            System.out.println(uriex.getMessage());
        } catch (IOException ioex) {
            System.out.println(ioex.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}