package SimpleCollection;

import org.junit.Assert;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class SimpleCollectionTest {

    @org.junit.jupiter.api.Test
    void add() {
        int a = 3, b = 4, c = 5;
        SimpleCollection<Integer> expected = new SimpleCollection<Integer>();
        expected.add(3);
        expected.add(4);
        expected.add(5);

        SimpleCollection<Integer> actual = new SimpleCollection<Integer>();
        actual.add(a);
        actual.add(b);
        actual.add(c);
        Assert.assertEquals(expected, actual);
        Assert.assertNotNull(expected);
    }

    @org.junit.jupiter.api.Test
    void size() {
        int a = 3, b = 4, c = 5;
        SimpleCollection<Integer> expected = new SimpleCollection<Integer>();
        expected.add(3);
        expected.add(4);
        expected.add(5);

        SimpleCollection<Integer> actual = new SimpleCollection<Integer>();
        actual.add(a);
        actual.add(b);
        actual.add(c);
        Assert.assertEquals(expected.size(), actual.size());
        Assert.assertNotNull(expected);
    }

    @org.junit.jupiter.api.Test
    void iterator() {
        int a = 3, b = 4, c = 5;
        SimpleCollection<Integer> expected = new SimpleCollection<Integer>();
        expected.add(3);
        expected.add(4);
        expected.add(5);
        Iterator<Integer> exIter = expected.iterator();

        SimpleCollection<Integer> actual = new SimpleCollection<Integer>();
        actual.add(a);
        actual.add(b);
        actual.add(c);
        Iterator<Integer> acIter = actual.iterator();

        Assert.assertEquals(exIter.next(), acIter.next());
        Assert.assertNotNull(exIter);
    }
}