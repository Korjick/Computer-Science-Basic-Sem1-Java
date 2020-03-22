package StudentSerialize;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Student {
    private String name, group;
    private char sex;
    private int birthDate, birthMonth, birthYear;

    public Student(String name, String group, char sex, int birthDate, int birthMonth, int birthYear) {
        this.name = name;
        this.group = group;
        this.sex = sex;
        this.birthDate = birthDate;
        this.birthMonth = birthMonth;
        this.birthYear = birthYear;
    }

    public String getName() {
        return name;
    }

    public String getGroup() {
        return group;
    }

    public char getSex() {
        return sex;
    }

    public int getBirthDate() {
        return birthDate;
    }

    public int getBirthMonth() {
        return birthMonth;
    }

    public int getBirthYear() {
        return birthYear;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\n"
                        + "Group:" + group + "\n"
                        + "Sex: " + sex + "\n"
                        + "Birthday: " + birthDate + "." + birthMonth + "." + birthYear + "\n";
    }

    public static void serialize(Collection<Student> students, String path) {
        try (FileOutputStream out = new FileOutputStream(path)) {
            ByteBuffer buffer = ByteBuffer.allocate(4);
            buffer.putInt(students.size());
            out.write(buffer.array());

            for (Student student : students) {

                int nameLen = student.getName().getBytes().length;
                int groupLen = student.getGroup().getBytes().length;
                buffer = ByteBuffer.allocate(8);
                buffer.putInt(nameLen);
                buffer.putInt(groupLen);
                out.write(buffer.array());

                buffer = ByteBuffer.allocate(14 + nameLen + groupLen);
                buffer.put(student.getName().getBytes());
                buffer.put(student.getGroup().getBytes());
                buffer.putChar(student.getSex());
                buffer.putInt(student.getBirthDate());
                buffer.putInt(student.getBirthMonth());
                buffer.putInt(student.getBirthYear());
                out.write(buffer.array());
            }
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Collection<Student> deserialize(String path) {
        try (FileInputStream in = new FileInputStream(path)) {
            ByteBuffer buffer = ByteBuffer.allocate(4);
            in.read(buffer.array());
            int size = buffer.getInt();

            Collection<Student> students = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                buffer = ByteBuffer.allocate(4);
                in.read(buffer.array());
                byte[] name = new byte[buffer.getInt()];

                buffer = ByteBuffer.allocate(4);
                in.read(buffer.array());
                byte[] group = new byte[buffer.getInt()];

                buffer = ByteBuffer.allocate(14);
                in.read(name);
                in.read(group);
                in.read(buffer.array());
                students.add(new Student(
                        new String(name),
                        new String(group),
                        buffer.getChar(),
                        buffer.getInt(),
                        buffer.getInt(),
                        buffer.getInt()));
            }
            return students;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
