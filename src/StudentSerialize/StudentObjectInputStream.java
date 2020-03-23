package StudentSerialize;

import java.io.*;

public class StudentObjectInputStream extends InputStream {

    private ObjectInputStream in;

    public StudentObjectInputStream(InputStream in) throws IOException {
        this.in = new ObjectInputStream(in);
    }

    public Student readStudent() throws IOException, ClassNotFoundException {
        return (Student) readObject();
    }

    @Override
    public int read() throws IOException {
        return in.read();
    }

    public Object readObject() throws IOException, ClassNotFoundException {
        return in.readObject();
    }

    public Object readUnshared() throws IOException, ClassNotFoundException {
        return in.readUnshared();
    }

    public void defaultReadObject() throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }

    public ObjectInputStream.GetField readFields() throws IOException, ClassNotFoundException {
        return in.readFields();
    }

    public void registerValidation(ObjectInputValidation obj, int prio) throws NotActiveException, InvalidObjectException {
        in.registerValidation(obj, prio);
    }

    public boolean readBoolean() throws IOException {
        return in.readBoolean();
    }

    public byte readByte() throws IOException {
        return in.readByte();
    }

    public int readUnsignedByte() throws IOException {
        return in.readUnsignedByte();
    }

    public char readChar() throws IOException {
        return in.readChar();
    }

    public short readShort() throws IOException {
        return in.readShort();
    }

    public int readUnsignedShort() throws IOException {
        return in.readUnsignedShort();
    }

    public int readInt() throws IOException {
        return in.readInt();
    }

    public long readLong() throws IOException {
        return in.readLong();
    }

    public float readFloat() throws IOException {
        return in.readFloat();
    }

    public double readDouble() throws IOException {
        return in.readDouble();
    }

    public void readFully(byte[] buf) throws IOException {
        in.readFully(buf);
    }

    public void readFully(byte[] buf, int off, int len) throws IOException {
        in.readFully(buf, off, len);
    }

    public int skipBytes(int len) throws IOException {
        return in.skipBytes(len);
    }

    @Deprecated
    public String readLine() throws IOException {
        return in.readLine();
    }

    public String readUTF() throws IOException {
        return in.readUTF();
    }
}
