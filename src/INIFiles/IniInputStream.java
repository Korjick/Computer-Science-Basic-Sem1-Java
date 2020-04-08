package INIFiles;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class IniInputStream extends Reader {

    private BufferedReader in;

    public IniInputStream(Reader in){
        this.in = new BufferedReader(in);
    }

    public Map<String, String> readIni() throws IOException {
        String text;
        Map<String, String> ini = new HashMap<>();
        while(!(text = readLine()).isEmpty()){
            String[] incoming = text.split("=");
            ini.put(incoming[0], incoming[1]);
        }
        return ini;
    }

    @Override
    public int read() throws IOException {
        return in.read();
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        return in.read(cbuf, off, len);
    }

    public String readLine() throws IOException {
        return in.readLine();
    }

    @Override
    public long skip(long n) throws IOException {
        return in.skip(n);
    }

    @Override
    public boolean ready() throws IOException {
        return in.ready();
    }

    @Override
    public boolean markSupported() {
        return in.markSupported();
    }

    @Override
    public void mark(int readAheadLimit) throws IOException {
        in.mark(readAheadLimit);
    }

    @Override
    public void reset() throws IOException {
        in.reset();
    }

    @Override
    public void close() throws IOException {
        in.close();
    }

    public Stream<String> lines() {
        return in.lines();
    }
}
