package StudentSerialize;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Student> arr = new ArrayList<>();
        arr.add(new Student("Bulat", "11-902", 'M', 4, 9, 2001));
        arr.add(new Student("Olga", "11-905", 'F', 12, 3, 2001));
        arr.add(new Student("Alexandr", "11-903", 'M', 6, 6, 2001));
        Student.serialize(arr, "students.txt");
        ArrayList<Student> arr1 = (ArrayList<Student>) Student.deserialize("students.txt");
        for (Student student : arr1) {
            System.out.println(student);
        }

        try (StudentDataOutputStream outer = new StudentDataOutputStream(new FileOutputStream("student.txt"));
             StudentDataInputStream inner = new StudentDataInputStream(new FileInputStream("student.txt"))) {
            Student s1 = new Student("a", "b", 'c', 1,2,3);
            outer.writeStudent(s1);
            Student s2 = inner.readStudent();
            System.out.println(s2.getName() + "-" + s2.getGroup() + "-" + s2.getSex() + "-" +
                    s2.getBirthDate() + "." + s2.getBirthMonth() + "." + s2.getBirthYear());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
