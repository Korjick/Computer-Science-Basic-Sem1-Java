package INIFiles;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;

public class IniWriter extends Writer {

    private BufferedWriter writer;

    public IniWriter(Writer writer){
        this.writer = new BufferedWriter(writer);
    }

    public void writeIni(Map<String, String> map) throws IOException {
        for(Map.Entry<String, String> node : map.entrySet()){
            write(node.getKey());
            write(" = ");
            write(node.getValue());
            flush();
        }
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        writer.write(cbuf, off, len);
    }

    @Override
    public void write(String s, int off, int len) throws IOException {
        writer.write(s, off, len);
    }

    public void newLine() throws IOException {
        writer.newLine();
    }

    @Override
    public void flush() throws IOException {
        writer.flush();
    }

    @Override
    public void close() throws IOException {
        writer.close();
    }

    @Override
    public void write(char[] cbuf) throws IOException {
        writer.write(cbuf);
    }

    @Override
    public void write(String str) throws IOException {
        writer.write(str);
    }

    @Override
    public Writer append(CharSequence csq) throws IOException {
        return writer.append(csq);
    }

    @Override
    public Writer append(CharSequence csq, int start, int end) throws IOException {
        return writer.append(csq, start, end);
    }

    @Override
    public Writer append(char c) throws IOException {
        return writer.append(c);
    }

    @Override
    public void write(int c) throws IOException {
        writer.write(c);
    }
}
