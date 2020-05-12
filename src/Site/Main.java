package Site;

import URI.MimeMap;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {
    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(System.in);
            URI link = new URI(in.nextLine());

            URLConnection connection = link.toURL().openConnection();
            InputStream inputStream = connection.getInputStream();

            Path html = Paths.get("").toAbsolutePath().resolve("site.html");
            if (Files.exists(html)) Files.delete(html);
            Files.copy(inputStream, html);

            String text = new String(Files.readAllBytes(html));
            ArrayList<String> result = new ArrayList<>();
            String regex = "(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";

            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(text);
            while (m.find()) result.add(m.group());

            MimeMap map = new MimeMap();

            Path save = Paths.get("").toAbsolutePath().resolve("save");
            if (Files.exists(save))
                Files.walk(save).sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);

            save.toFile().mkdir();
            int i = 1;
            for (String s : result) {
                link = new URI(s);
                connection = link.toURL().openConnection();
                connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
                try{
                    String mime = connection.getContentType().split(";")[0];
                    for (Map.Entry<String, String> node : map.entrySet()) {
                        if (node.getValue().equals(mime)) {
                            Files.copy(connection.getInputStream(), save.resolve("file" + i + "." + node.getKey()));
                            i++;
                        }
                    }

                    Thread.sleep(1000);
                } catch (IOException | InterruptedException | NullPointerException e){
                    System.out.println("Miss data");
                }
            }
        } catch (URISyntaxException | IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
