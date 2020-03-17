package SimpleCollection;

import java.util.*;

public class SimpleCollection<T> extends AbstractCollection<T> {

    private T[] arr;
    private int elementCount;

    private final float ARRAY_EXPANSION = 1.5f;

    public SimpleCollection() {
        arr = (T[]) new Object[0];
        elementCount = 0;
    }

    public SimpleCollection(Collection<? extends T> collection) {
        arr = (T[]) new Object[collection.size()];
        elementCount = 0;
        for (T t : collection) {
            arr[elementCount] = t;
            elementCount++;
        }
    }

    @Override
    public boolean add(T t) {
        if(elementCount == arr.length) arr = positiveArrayResize();
        arr[elementCount] = t;
        elementCount++;
        return true;
    }

    public int size() {
        return elementCount;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            int HEAD = 0;

            @Override
            public boolean hasNext() {
                return HEAD < elementCount;
            }

            @Override
            public T next() {
                if (HEAD == elementCount) throw new NoSuchElementException();
                HEAD++;
                return arr[HEAD - 1];
            }

            @Override
            public void remove() {
                if(HEAD == 0) throw new IllegalStateException();
                T[] returnArray = (T[]) new Object[arr.length-1];
                System.arraycopy(arr, 0, returnArray, 0, HEAD - 1);
                System.arraycopy(arr, HEAD, returnArray, HEAD - 1, elementCount - HEAD);
                arr = returnArray;
                elementCount--;
            }
        };
    }

    private T[] positiveArrayResize() {
        T[] returnArray = (T[]) new Object[(int)(1 + arr.length * ARRAY_EXPANSION)];
        System.arraycopy(arr, 0, returnArray, 0, size());
        return returnArray;
    }
}
