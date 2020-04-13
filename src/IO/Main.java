package IO;

import java.io.*;
import java.nio.charset.Charset;

public class Main {
    public static void main(String[] args) {
        int value = 1988777666;
        char ch = '是';

        try (FileOutputStream out = new FileOutputStream(System.getProperty("user.home") + "\\Desktop\\text.txt")){
            for (int i = 3; i >= 0; i--) {
                out.write(value >> i * 8);
            }
            for (int i = 1; i >= 0; i--) {
                out.write(ch >> i * 8);
            }
            out.flush();
        } catch (IOException e){
            System.err.println(e.getMessage());
        }

        try (FileInputStream in = new FileInputStream(System.getProperty("user.home") + "\\Desktop\\text.txt")){
            int b = 0, d = 0;
            char c;
            for (int i = 3; i >= 0; i--) {
                b = in.read() << i * 8 | b;
            }
            for (int i = 1; i >= 0; i--) {
                d = (in.read() << i * 8) | d;
            }
            c = (char) d;
            System.out.println(b);
            System.out.println(c);
            System.out.println(System.getProperty("file.encoding"));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
