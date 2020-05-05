package Piano;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;

class Piano extends Thread{

    private String track;

    public Piano(String track) {
        this.track = track;
    }

    @Override
    public void run() {
        play();
    }

    public void play() {
        try {
            FileInputStream is = new FileInputStream(Paths.get("").toAbsolutePath().resolve(track).toString());
            Player player = new Player(is);
            player.play();
            player.close();
        } catch (IOException | JavaLayerException e) {
            System.out.println(e.getMessage());
        }
    }
}