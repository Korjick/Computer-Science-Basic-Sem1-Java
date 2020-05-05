package Piano;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Main extends JFrame implements KeyListener {

    public Main() {
        this.setSize(100, 100);
        this.addKeyListener(this);
    }

    public static void main(String[] args) {
        JFrame jFrame = new Main();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A) new Piano(Keys.get("a")).start();
        if (e.getKeyCode() == KeyEvent.VK_B) new Piano(Keys.get("b")).start();
        if (e.getKeyCode() == KeyEvent.VK_C) new Piano(Keys.get("c")).start();
        if (e.getKeyCode() == KeyEvent.VK_D) new Piano(Keys.get("d")).start();
        if (e.getKeyCode() == KeyEvent.VK_E) new Piano(Keys.get("e")).start();
        if (e.getKeyCode() == KeyEvent.VK_F) new Piano(Keys.get("f")).start();
        if (e.getKeyCode() == KeyEvent.VK_G) new Piano(Keys.get("g")).start();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
