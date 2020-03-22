package StudentSerialize;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Student> arr = new ArrayList<>();
        arr.add(new Student("Bulat", "11-902", 'M', 4, 9, 2001));
        arr.add(new Student("Olga", "11-905", 'F', 12, 3, 2001));
        arr.add(new Student("Alexandr", "11-903", 'M', 6, 6, 2001));
        Student.serialize(arr, "students.txt");
        ArrayList<Student> arr1 = (ArrayList<Student>) Student.deserialize("students.txt");
        for(Student student : arr1){
            System.out.println(student);
        }
    }

}
