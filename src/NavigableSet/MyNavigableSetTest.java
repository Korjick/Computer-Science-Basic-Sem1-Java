package NavigableSet;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class MyNavigableSetTest {

    MyNavigableSet<String> expected = new MyNavigableSet<String>((o1, o2) -> o1.compareTo(o2));

    @Test
    void add() {
        String a = "Alina", b = "neAlina", c = "Абвгдеёж";
        expected.add("Alina");
        expected.add("neAlina");
        expected.add("Абвгдеёж");

        MyNavigableSet<String> actual = new MyNavigableSet<String>((o1, o2) -> o1.compareTo(o2));
        actual.add(a);
        actual.add(b);
        actual.add(c);

        Assert.assertNotNull(expected);
        Assert.assertEquals(actual, expected);
    }

    @Test
    void addAll() {
        String a = "Alina", b = "neAlina", c = "Абвгдеёж";
        expected.add("Alina");
        expected.add("neAlina");
        expected.add("Абвгдеёж");

        MyNavigableSet<String> actual = new MyNavigableSet<String>((o1, o2) -> o1.compareTo(o2));
        ArrayList<String> list = new ArrayList<>();
        list.add(a);
        list.add(b);
        list.add(c);
        actual.addAll(list);

        Assert.assertEquals(expected, actual);
    }

    @Test
    void remove() {
        String a = "Alina", b = "neAlina", c = "Абвгдеёж";
        expected.add("Alina");
        expected.add("neAlina");
        expected.add("Абвгдеёж");

        expected.remove("Alina");
        MyNavigableSet<String> actual = new MyNavigableSet<String>((o1, o2) -> o1.compareTo(o2));
        actual.add(a);
        actual.add(b);
        actual.add(c);
        actual.remove(a);

        Assert.assertNotNull(expected);
        Assert.assertEquals(expected, actual);
    }

    @Test
    void toArray() {
        String a = "Alina", b = "neAlina", c = "Абвгдеёж";
        expected.add("Alina");
        expected.add("neAlina");
        expected.add("Абвгдеёж");

        MyNavigableSet<String> actual = new MyNavigableSet<String>((o1, o2) -> o1.compareTo(o2));
        actual.add(a);
        actual.add(b);
        actual.add(c);

        Object[] exArr = expected.toArray();
        Object[] acArr = actual.toArray();

        Assert.assertNotNull(exArr);
        Assert.assertArrayEquals(exArr, acArr);
    }

    @Test
    void lower() {
        String a = "Alina", b = "neAlina", c = "Абвгдеёж";
        expected.add("Alina");
        expected.add("neAlina");
        expected.add("Абвгдеёж");

        MyNavigableSet<String> actual = new MyNavigableSet<String>((o1, o2) -> o1.compareTo(o2));
        actual.add(a);
        actual.add(b);
        actual.add(c);

        Assert.assertNotNull(expected.lower("Абвгдеёж"));
        Assert.assertEquals(expected.lower("Абвгдеёж"), actual.lower(c));
    }

    @Test
    void floor() {
        String a = "Alina", b = "neAlina", c = "Абвгдеёж";
        expected.add("Alina");
        expected.add("neAlina");
        expected.add("Абвгдеёж");

        MyNavigableSet<String> actual = new MyNavigableSet<String>((o1, o2) -> o1.compareTo(o2));
        actual.add(a);
        actual.add(b);
        actual.add(c);

        Assert.assertNotNull(expected.floor("neAlina"));
        Assert.assertEquals(expected.floor("neAlina"), actual.floor(b));
    }

    @Test
    void pollFirst() {
        String a = "Alina", b = "neAlina", c = "Абвгдеёж";
        expected.add("Alina");
        expected.add("neAlina");
        expected.add("Абвгдеёж");

        MyNavigableSet<String> actual = new MyNavigableSet<String>((o1, o2) -> o1.compareTo(o2));
        actual.add(a);
        actual.add(b);
        actual.add(c);

        Assert.assertEquals(expected.pollFirst(), actual.pollFirst());
    }

    @Test
    void pollLast() {
        String a = "Alina", b = "neAlina", c = "Абвгдеёж";
        expected.add("Alina");
        expected.add("neAlina");
        expected.add("Абвгдеёж");

        MyNavigableSet<String> actual = new MyNavigableSet<String>((o1, o2) -> o1.compareTo(o2));
        actual.add(a);
        actual.add(b);
        actual.add(c);

        Assert.assertEquals(expected.pollLast(), actual.pollLast());
    }

    @Test
    void subSet() {
        String a = "Alina", b = "neAlina", c = "Абвгдеёж";
        expected.add("Alina");
        expected.add("neAlina");
        expected.add("Абвгдеёж");

        MyNavigableSet<String> actual = new MyNavigableSet<String>((o1, o2) -> o1.compareTo(o2));
        actual.add(a);
        actual.add(b);
        actual.add(c);

        Assert.assertNotNull(expected.subSet("Alina", "Абвгдеёж"));
        Assert.assertEquals(expected.subSet("Alina", "Абвгдеёж"), actual.subSet(a, c));
    }

    @Test
    void headSet() {
        String a = "Alina", b = "neAlina", c = "Абвгдеёж";
        expected.add("Alina");
        expected.add("neAlina");
        expected.add("Абвгдеёж");

        MyNavigableSet<String> actual = new MyNavigableSet<String>((o1, o2) -> o1.compareTo(o2));
        actual.add(a);
        actual.add(b);
        actual.add(c);

        Assert.assertNotNull(expected.headSet("Абвгдеёж"));
        Assert.assertEquals(expected.headSet("Абвгдеёж"), actual.headSet(c));
    }
}