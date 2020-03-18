package NavigableSet;

import java.util.*;

public class MyNavigableSet<T> extends AbstractSet<T> implements NavigableSet<T> {

    private Comparator<T> comparator;
    private ArrayList<T> arr;

    public MyNavigableSet(Comparator<T> comparator) {
        this.comparator = comparator;
        arr = new ArrayList<>();
    }

    public MyNavigableSet(int length, Comparator<T> comparator) {
        this.comparator = comparator;
        arr = new ArrayList<>(length);
    }

    @Override
    public boolean add(T t) {
        if (arr.indexOf(t) != -1) return false;
        for (int i = 0; i < arr.size(); i++) {
            if (comparator.compare(t, arr.get(i)) <= 0) {
                arr.add(i, t);
                return true;
            }
        }
        arr.add(t);
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        for (T t : c) {
            add(t);
        }
        return true;
    }

    @Override
    public boolean remove(Object o) {
        return arr.remove(o);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return arr.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return arr.retainAll(c);
    }

    @Override
    public Spliterator<T> spliterator() {
        return arr.spliterator();
    }

    @Override
    public Object[] toArray() {
        return arr.toArray();
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return toArray(a);
    }

    @Override
    public void clear() {
        arr.clear();
    }

    @Override
    public T lower(T t) {
        T el = floor(t);
        if(el == null || el == arr.get(0) && t == arr.get(0)) return null;
        return el;
    }

    @Override
    public T floor(T t) {
        if (comparator.compare(t, arr.get(0)) < 0) return null;
        int idx = arr.indexOf(t);
        if (idx == 0) return arr.get(0);
        if(idx != -1) return arr.get(idx - 1);

        for (int i = 0; i < arr.size(); i++) if(comparator.compare(arr.get(i), t) > 0) return arr.get(i - 1);
        return null;
    }

    @Override
    public T ceiling(T t) {
        if (comparator.compare(t, arr.get(arr.size() - 1)) > 0) return null;
        int idx = arr.indexOf(t);

        if (idx == arr.size() - 1) return arr.get(idx);
        if(idx != -1) return arr.get(idx + 1);

        for (T value : arr) if (comparator.compare(value, t) > 0) return value;
        return null;
    }

    @Override
    public T higher(T t) {
        T el = ceiling(t);
        if(el == null || el == arr.get(arr.size() - 1) && t == arr.get(arr.size() - 1)) return null;
        return el;
    }

    @Override
    public T pollFirst() {
        if (arr.size() == 0) return null;

        T t = arr.get(0);
        arr.remove(0);
        return t;
    }

    @Override
    public T pollLast() {
        if (arr.size() == 0) return null;

        T t = arr.get(arr.size() - 1);
        arr.remove(arr.size() - 1);
        return t;
    }

    @Override
    public Iterator<T> iterator() {
        return arr.iterator();
    }

    @Override
    public NavigableSet<T> descendingSet() {
        NavigableSet<T> returnArr = new MyNavigableSet<>(comparator);
        for (int i = arr.size() - 1; i > 0; i--) {
            returnArr.add(arr.get(i));
        }
        return returnArr;
    }

    @Override
    public Iterator<T> descendingIterator() {
        return new Iterator<T>() {

            int first = arr.size() - 1;

            @Override
            public boolean hasNext() {
                return first > 0;
            }

            @Override
            public T next() {
                if (first == 0) throw new NoSuchElementException();

                first--;
                return arr.get(first + 1);
            }
        };
    }

    @Override
    public NavigableSet<T> subSet(T fromElement, boolean fromInclusive, T toElement, boolean toInclusive)
            throws IllegalArgumentException {

        NavigableSet<T> returnArr = new MyNavigableSet<>(comparator);
        if(comparator.compare(toElement, fromElement) < 0) throw new IllegalArgumentException();

        if(fromInclusive && toInclusive)
            for (T el: arr) if(comparator.compare(el, fromElement) >= 0 && comparator.compare(el, toElement) <= 0)
                returnArr.add(el);

        if(!fromInclusive && toInclusive)
            for (T el: arr) if(comparator.compare(el, fromElement) > 0 && comparator.compare(el, toElement) <= 0)
                returnArr.add(el);

        if(fromInclusive && !toInclusive)
            for (T el: arr) if(comparator.compare(el, fromElement) >= 0 && comparator.compare(el, toElement) < 0)
                returnArr.add(el);

        if(!fromInclusive && !toInclusive)
            for (T el: arr) if(comparator.compare(el, fromElement) > 0 && comparator.compare(el, toElement) < 0)
                returnArr.add(el);
        return returnArr;
    }

    @Override
    public NavigableSet<T> headSet(T toElement, boolean inclusive){
        NavigableSet<T> returnArr = new MyNavigableSet<>(comparator);

        if(inclusive) for (int i = 0; i < arr.size() - 1; i++)
            if(comparator.compare(toElement, arr.get(i)) >= 0) returnArr.add(arr.get(i));
            else break;

        if(!inclusive) for (int i = 0; i < arr.size() - 1; i++)
            if(comparator.compare(toElement, arr.get(i)) > 0) returnArr.add(arr.get(i));
            else break;
        return returnArr;
    }

    @Override
    public NavigableSet<T> tailSet(T fromElement, boolean inclusive){
        NavigableSet<T> returnArr = new MyNavigableSet<>(comparator);

        if(inclusive) for (int i = 0; i < arr.size() - 1; i++)
            if(comparator.compare(fromElement, arr.get(i)) <= 0) returnArr.add(arr.get(i));
            else break;

        if(!inclusive) for (int i = 0; i < arr.size() - 1; i++)
            if(comparator.compare(fromElement, arr.get(i)) < 0) returnArr.add(arr.get(i));
            else break;

        return returnArr;
    }

    @Override
    public Comparator<? super T> comparator() {
        return comparator;
    }

    @Override
    public SortedSet<T> subSet(T fromElement, T toElement) throws IllegalArgumentException {
        SortedSet<T> returnArr = new MyNavigableSet<>(comparator);

        if(comparator.compare(toElement, fromElement) < 0) throw new IllegalArgumentException();
        for (T el: arr){
            if(comparator.compare(el, fromElement) >= 0 && comparator.compare(el, toElement) < 0) returnArr.add(el);
        }
        return returnArr;
    }

    @Override
    public SortedSet<T> headSet(T toElement){
        SortedSet<T> returnArr = new MyNavigableSet<>(comparator);

        for (int i = 0; i < arr.size() - 1; i++)
            if(comparator.compare(toElement, arr.get(i)) > 0) returnArr.add(arr.get(i));
            else break;
        return returnArr;
    }

    @Override
    public SortedSet<T> tailSet(T fromElement){
        SortedSet<T> returnArr = new MyNavigableSet<>(comparator);

        for (int i = 0; i < arr.size() - 1; i++)
            if(comparator.compare(fromElement, arr.get(i)) < 0) returnArr.add(arr.get(i));
            else break;
        return returnArr;
    }

    @Override
    public T first() {
        return arr.get(0);
    }

    @Override
    public T last() {
        return arr.get(arr.size() - 1);
    }

    @Override
    public int size() {
        return arr.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyNavigableSet<T> that = (MyNavigableSet<T>) o;
        if (that.size() != size()) return false;
        for (int i = 0; i < size(); i++) {
            if (comparator.compare(arr.get(i), that.arr.get(i)) != 0) return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        for (int i = 0; i < arr.size(); i++) {
            hash = hash + 13 * (49 * arr.get(i).hashCode() + 8);
        }
        return hash;
    }

    @Override
    public boolean isEmpty() {
        return arr.isEmpty();
    }
}
