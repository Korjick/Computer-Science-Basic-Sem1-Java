package Download;

import URI.MimeMap;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class DownloadThread extends Thread {

    private URI uri;
    private InputStream inputStream;
    private URLConnection connection;
    private MimeMap map;
    private Path download;
    private int size;


    public DownloadThread(String uri) {
        try {
            this.uri = new URI(uri);
            connection = this.uri.toURL().openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");

            inputStream = connection.getInputStream();
            map = new MimeMap();

            size = connection.getContentLength();
        } catch (URISyntaxException | MalformedURLException e) {
            System.out.println("Неверно указан путь");
        } catch (IOException e) {
            System.out.println("Не удалось получить данные");
        }
    }

    @Override
    public void run() {
        try {
            String mime = connection.getContentType().split(";")[0];
            System.out.println("Start of downloading: " + mime);
            for (Map.Entry<String, String> node : map.entrySet()) {
                if (node.getValue().equals(mime)) {
                    mime = node.getKey();
                    break;
                }
            }

            if (mime == null) return;
            download = Paths.get("").toAbsolutePath().resolve("file." + mime).normalize();

            if (Files.exists(download)) Files.delete(download);
            Files.copy(inputStream, download);
            inputStream.close();
        } catch (IOException e) {
            System.out.println("Не удалось скачать файл");
        }
    }

    public void percentOfDownloading() {
        if (Thread.currentThread().getState().equals(State.TERMINATED)) {
            System.out.println("100%");
            return;
        }

        try {
            System.out.println((Files.size(download) * 1.0f / size) * 100 + "%");
        } catch (IOException e) {
            System.out.println("Ошибка в получении данных из файла");
        }
    }
}
