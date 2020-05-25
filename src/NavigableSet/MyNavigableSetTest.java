package NavigableSet;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

        Assert.assertEquals(actual, expected);
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

        Assert.assertEquals(expected, actual);
    }

    @Test
    void addTest() {
        String a = "Alina", b = "neAlina", c = "Абвгдеёж";
        expected.add("Alina");
        expected.add("neAlina");
        expected.add("Абвгдеёж");

        MyNavigableSet<String> actual = new MyNavigableSet<String>((o1, o2) -> o1.compareTo(o2));
        actual.add(a);
        actual.add(b);
        actual.add(c);

        expected.remove("Alina");
        actual.remove(a);
        expected.remove("Абвгдеёж");
        actual.remove(c);
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
    void addAllTest() {
        String a = "Alina", b = "neAlina", c = "Абвгдеёж";
        expected.add("Alina");
        expected.add("neAlina");
        expected.add("Абвгдеёж");

        MyNavigableSet<String> actual = new MyNavigableSet<String>((o1, o2) -> o1.compareTo(o2));
        actual.addAll(Stream.of(a, b, c).collect(Collectors.toList()));

        Assert.assertEquals(expected, actual);
    }

    @Test
    void addAllRemoveTest() {
        String a = "Alina", b = "neAlina", c = "Абвгдеёж";
        expected.add("Alina");
        expected.add("neAlina");
        expected.add("Абвгдеёж");

        MyNavigableSet<String> actual = new MyNavigableSet<String>((o1, o2) -> o1.compareTo(o2));
        ArrayList<String> list = new ArrayList<>();
        actual.addAll(Stream.of(a, b, c).collect(Collectors.toList()));

        expected.remove("Абвгдеёж");
        actual.remove(c);
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

        Assert.assertArrayEquals(exArr, acArr);
    }

    @Test
    void toArrayNotNull() {
        expected.add("Alina");
        expected.add("neAlina");
        expected.add("Абвгдеёж");

        Object[] exArr = expected.toArray();

        Assert.assertNotNull(exArr);
    }

    @Test
    void toArrayCollect() {
        String a = "Alina", b = "neAlina", c = "Абвгдеёж";
        expected.add("Alina");
        expected.add("neAlina");
        expected.add("Абвгдеёж");

        MyNavigableSet<String> actual = new MyNavigableSet<String>((o1, o2) -> o1.compareTo(o2));
        actual.addAll(Stream.of(a, b, c).collect(Collectors.toList()));
        Object[] exArr = expected.toArray();
        Object[] acArr = actual.toArray();

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

        Assert.assertEquals(expected.lower("Абвгдеёж"), actual.lower(c));
    }

    @Test
    void lowerBorder() {
        String a = "Alina", b = "neAlina", c = "Абвгдеёж";
        expected.add("Alina");
        expected.add("neAlina");
        expected.add("Абвгдеёж");

        MyNavigableSet<String> actual = new MyNavigableSet<String>((o1, o2) -> o1.compareTo(o2));
        actual.add(a);
        actual.add(b);
        actual.add(c);

        Assert.assertEquals(expected.lower("Alina"), actual.lower(a));
    }

    @Test
    void lowerBorderOutNotNull() {
        expected.add("Alina");
        expected.add("neAlina");
        expected.add("Абвгдеёж");

        Assert.assertNotNull(expected.lower("aa"));
    }

    @Test
    void lowerBorderOut() {
        String a = "Alina", b = "neAlina", c = "Абвгдеёж", d = "aa";
        expected.add("Alina");
        expected.add("neAlina");
        expected.add("Абвгдеёж");

        MyNavigableSet<String> actual = new MyNavigableSet<String>((o1, o2) -> o1.compareTo(o2));
        actual.addAll(Stream.of(a, b, c).collect(Collectors.toList()));

        Assert.assertEquals(expected.lower("aa"), actual.lower(d));
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

        Assert.assertEquals(expected.floor("neAlina"), actual.floor(b));
    }

    @Test
    void floorBorderOutNotNull() {
        expected.add("Alina");
        expected.add("neAlina");
        expected.add("Абвгдеёж");

        Assert.assertNotNull(expected.lower("aa"));
    }

    @Test
    void floorBorderOut() {
        String a = "Alina", b = "neAlina", c = "Абвгдеёж", d = "aa";
        expected.add("Alina");
        expected.add("neAlina");
        expected.add("Абвгдеёж");

        MyNavigableSet<String> actual = new MyNavigableSet<String>((o1, o2) -> o1.compareTo(o2));
        actual.addAll(Stream.of(a, b, c).collect(Collectors.toList()));

        Assert.assertEquals(expected.floor("aa"), actual.floor(d));
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
    void pollFirstRemove() {
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

        Assert.assertEquals(expected.pollFirst(), actual.pollFirst());
    }

    @Test
    void pollFirstRemoveAll() {
        String a = "Alina", b = "neAlina", c = "Абвгдеёж";
        expected.add("Alina");
        expected.add("neAlina");
        expected.add("Абвгдеёж");
        expected.remove("Alina");
        expected.removeAll(Stream.of(a, b, c).collect(Collectors.toList()));

        MyNavigableSet<String> actual = new MyNavigableSet<String>((o1, o2) -> o1.compareTo(o2));
        actual.add(a);
        actual.add(b);
        actual.add(c);
        actual.removeAll(Stream.of(a, b, c).collect(Collectors.toList()));

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
    void pollLastOut() {
        String a = "Alina", b = "neAlina", c = "Абвгдеёж", d = "cc";
        expected.add("Alina");
        expected.add("neAlina");
        expected.add("Абвгдеёж");
        expected.remove("cc");

        MyNavigableSet<String> actual = new MyNavigableSet<String>((o1, o2) -> o1.compareTo(o2));
        actual.add(a);
        actual.add(b);
        actual.add(c);
        actual.remove(d);

        Assert.assertEquals(expected.pollLast(), actual.pollLast());
    }

    @Test
    void pollLastBorder() {
        String a = "Alina", b = "neAlina", c = "Абвгдеёж";
        expected.add("Alina");
        expected.add("neAlina");
        expected.add("Абвгдеёж");
        expected.remove("Абвгдеёж");

        MyNavigableSet<String> actual = new MyNavigableSet<String>((o1, o2) -> o1.compareTo(o2));
        actual.add(a);
        actual.add(b);
        actual.add(c);
        actual.remove(c);

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

        Assert.assertEquals(expected.subSet("Alina", "Абвгдеёж"), actual.subSet(a, c));
    }

    @Test
    void subSetInclusive() {
        String a = "Alina", b = "neAlina", c = "Абвгдеёж";
        expected.add("Alina");
        expected.add("neAlina");
        expected.add("Абвгдеёж");

        MyNavigableSet<String> actual = new MyNavigableSet<String>((o1, o2) -> o1.compareTo(o2));
        actual.add(a);
        actual.add(b);
        actual.add(c);

        Assert.assertEquals(expected.subSet("Alina", true, "Абвгдеёж", true),
                actual.subSet(a, true, c, true));
    }

    @Test
    void subSetInclusiveExclusive() {
        String a = "Alina", b = "neAlina", c = "Абвгдеёж";
        expected.add("Alina");
        expected.add("neAlina");
        expected.add("Абвгдеёж");

        MyNavigableSet<String> actual = new MyNavigableSet<String>((o1, o2) -> o1.compareTo(o2));
        actual.add(a);
        actual.add(b);
        actual.add(c);

        Assert.assertEquals(expected.subSet("Alina", true, "Абвгдеёж", false),
                actual.subSet(a, true, c, false));
    }

    @Test
    void subSetOut() {
        String a = "Alina", b = "neAlina", c = "Абвгдеёж", d = "cc";
        expected.add("Alina");
        expected.add("neAlina");
        expected.add("Абвгдеёж");

        MyNavigableSet<String> actual = new MyNavigableSet<String>((o1, o2) -> o1.compareTo(o2));
        actual.add(a);
        actual.add(b);
        actual.add(c);

        Assert.assertEquals(expected.subSet("Alina", "cc"), actual.subSet(a, d));
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

        Assert.assertEquals(expected.headSet("Абвгдеёж"), actual.headSet(c));
    }

    @Test
    void headSetOut() {
        String a = "Alina", b = "neAlina", c = "Абвгдеёж", d = "aa";
        expected.add("Alina");
        expected.add("neAlina");
        expected.add("Абвгдеёж");

        MyNavigableSet<String> actual = new MyNavigableSet<String>((o1, o2) -> o1.compareTo(o2));
        actual.addAll(Stream.of(a, b, c).collect(Collectors.toList()));

        Assert.assertEquals(expected.headSet("aa"), actual.headSet(d));
    }

    @Test
    void headSetOutInclusive() {
        String a = "Alina", b = "neAlina", c = "Абвгдеёж", d = "aa";
        expected.add("Alina");
        expected.add("neAlina");
        expected.add("Абвгдеёж");

        MyNavigableSet<String> actual = new MyNavigableSet<String>((o1, o2) -> o1.compareTo(o2));
        actual.addAll(Stream.of(a, b, c).collect(Collectors.toList()));

        Assert.assertEquals(expected.headSet("aa", true), actual.headSet(d, true));
    }

    @Test
    void headSetInclusive() {
        String a = "Alina", b = "neAlina", c = "Абвгдеёж";
        expected.add("Alina");
        expected.add("neAlina");
        expected.add("Абвгдеёж");

        MyNavigableSet<String> actual = new MyNavigableSet<String>((o1, o2) -> o1.compareTo(o2));
        actual.addAll(Stream.of(a, b, c).collect(Collectors.toList()));

        Assert.assertEquals(expected.headSet("Alina", true), actual.headSet(a, true));
    }
}