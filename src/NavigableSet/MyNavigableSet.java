package NavigableSet;

import java.util.*;

public class MyNavigableSet<T> extends AbstractSet<T> implements NavigableSet<T> {

    /**
     * Implementation class of NavigableSet. Set is sorted.
     * @autor Bulat Din
     * @version 1.0
     */

    /**
     * Field set comparator for comparing the input data.
     * @see MyNavigableSet#MyNavigableSet(Comparator)
     */
    private Comparator<T> comparator;
    private ArrayList<T> arr;

    /**Constructor - creating a new empty set.
     * @param comparator - comparator for comparing the input data*/
    public MyNavigableSet(Comparator<T> comparator) {
        this.comparator = comparator;
        arr = new ArrayList<>();
    }

    /**Constructor - creating a new empty set, but with specified length.
     * @param length the length of array
     * @param comparator - comparator for comparing the input data*/
    public MyNavigableSet(int length, Comparator<T> comparator) {
        this.comparator = comparator;
        arr = new ArrayList<>(length);
    }

    /**Adds a new element to the set.
     * @param t specific element*/
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

    /**Copies values from a specific collection to the current set.
     * @param c - collection where values will be copied from*/
    @Override
    public boolean addAll(Collection<? extends T> c) {
        for (T t : c) {
            add(t);
        }
        return true;
    }

    /**Removes element from set.
     * @param o - specific element*/
    @Override
    public boolean remove(Object o) {
        return arr.remove(o);
    }

    /**Deletes the elements that are contained in the transferred collection.
     * @param c - specific collection*/
    @Override
    public boolean removeAll(Collection<?> c) {
        return arr.removeAll(c);
    }

    /**Retains only the elements in this set that are contained in the specified collection.
     * @param c - specific collection*/
    @Override
    public boolean retainAll(Collection<?> c) {
        return arr.retainAll(c);
    }

    /** Creates a Spliterator over the elements in this set.*/
    @Override
    public Spliterator<T> spliterator() {
        return arr.spliterator();
    }

    /**Returns an array containing all of the elements in this set.*/
    @Override
    public Object[] toArray() {
        return arr.toArray();
    }

    /**Returns an array containing all of the elements in this set;
     * the runtime type of the returned array is that of the specified array.*/
    @Override
    public <T1> T1[] toArray(T1[] a) {
        return toArray(a);
    }

    /**Removes all of the elements from this set.*/
    @Override
    public void clear() {
        arr.clear();
    }

    /**Returns the greatest element in this set strictly less than the given element, or null if there is no such element.
     * @param t - specific element*/
    @Override
    public T lower(T t) {
        T el = floor(t);
        if(el == null || el == arr.get(0) && t == arr.get(0)) return null;
        return el;
    }

    /**Returns the greatest element in this set less than or equal to the given element, or null if there is no such element.
     * @param t - specific element*/
    @Override
    public T floor(T t) {
        if (comparator.compare(t, arr.get(0)) < 0) return null;
        int idx = arr.indexOf(t);
        if (idx == 0) return arr.get(0);
        if(idx != -1) return arr.get(idx - 1);

        for (int i = 0; i < arr.size(); i++) if(comparator.compare(arr.get(i), t) > 0) return arr.get(i - 1);
        return null;
    }

    /**Returns the least element in this set greater than or equal to the given element, or null if there is no such element.
     * @param t - specific element*/
    @Override
    public T ceiling(T t) {
        if (comparator.compare(t, arr.get(arr.size() - 1)) > 0) return null;
        int idx = arr.indexOf(t);

        if (idx == arr.size() - 1) return arr.get(idx);
        if(idx != -1) return arr.get(idx + 1);

        for (T value : arr) if (comparator.compare(value, t) > 0) return value;
        return null;
    }

    /**Returns the least element in this set strictly greater than the given element, or null if there is no such element.
     * @param t - specific element*/
    @Override
    public T higher(T t) {
        T el = ceiling(t);
        if(el == null || el == arr.get(arr.size() - 1) && t == arr.get(arr.size() - 1)) return null;
        return el;
    }

    /**Retrieves and removes the first element, or returns null if this set is empty.*/
    @Override
    public T pollFirst() {
        if (arr.size() == 0) return null;

        T t = arr.get(0);
        arr.remove(0);
        return t;
    }

    /**Retrieves and removes the last element, or returns null if this set is empty.*/
    @Override
    public T pollLast() {
        if (arr.size() == 0) return null;

        T t = arr.get(arr.size() - 1);
        arr.remove(arr.size() - 1);
        return t;
    }

    /**Returns an iterator over the elements in this set, in ascending order.*/
    @Override
    public Iterator<T> iterator() {
        return arr.iterator();
    }

    /**Returns a reverse order view of the elements contained in this set*/
    @Override
    public NavigableSet<T> descendingSet() {
        NavigableSet<T> returnArr = new MyNavigableSet<>(comparator);
        for (int i = arr.size() - 1; i > 0; i--) {
            returnArr.add(arr.get(i));
        }
        return returnArr;
    }

    /**Returns an iterator over the elements in this set, in descending order.*/
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

    /**Returns a view of the portion of this set whose elements range from fromElement to toElement.
     * The inclusion of elements in the list depends on the values 'fromInclusive' and 'toInclusive'
     *
     * @param fromElement - lowest element
     * @param fromInclusive - is lowest element include in new set
     * @param toElement - highest element
     * @param toInclusive - is highest element include in new set
     *
     * @throws IllegalArgumentException when the 'fromElement' is larger than the 'toElement'.
     */
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

    /**
     * Returns a view of the portion of this set whose elements are less than (or equal to, if inclusive is true) toElement.
     *
     * @param toElement - highest element
     * @param inclusive - is highest element include in new set
     */
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

    /**
     * Returns a view of the portion of this set whose elements are greater than (or equal to, if inclusive is true) fromElement.
     *
     * @param fromElement - lowest element
     * @param inclusive - is lowest element include in new set
     */
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

    /**
     * Returns comparator of the set
     */
    @Override
    public Comparator<? super T> comparator() {
        return comparator;
    }

    /**
     * Returns a view of the portion of this set whose elements range from fromElement to toElement.
     *
     * @param fromElement - lowest element
     * @param toElement - is lowest element include in new set
     *
     * @throws IllegalArgumentException when the 'fromElement' is larger than the 'toElement'.
     */
    @Override
    public SortedSet<T> subSet(T fromElement, T toElement) throws IllegalArgumentException {
        SortedSet<T> returnArr = new MyNavigableSet<>(comparator);

        if(comparator.compare(toElement, fromElement) < 0) throw new IllegalArgumentException();
        for (T el: arr){
            if(comparator.compare(el, fromElement) >= 0 && comparator.compare(el, toElement) < 0) returnArr.add(el);
        }
        return returnArr;
    }

    /**
     * Returns a view of the portion of this set whose elements are strictly less than toElement.
     * @param toElement - highest element
     *
     */
    @Override
    public SortedSet<T> headSet(T toElement){
        SortedSet<T> returnArr = new MyNavigableSet<>(comparator);

        for (int i = 0; i < arr.size() - 1; i++)
            if(comparator.compare(toElement, arr.get(i)) > 0) returnArr.add(arr.get(i));
            else break;
        return returnArr;
    }

    /**
     * Returns a view of the portion of this set whose elements are greater than or equal to fromElement.
     * @param fromElement - lowest element
     *
     */
    @Override
    public SortedSet<T> tailSet(T fromElement){
        SortedSet<T> returnArr = new MyNavigableSet<>(comparator);

        for (int i = 0; i < arr.size() - 1; i++)
            if(comparator.compare(fromElement, arr.get(i)) < 0) returnArr.add(arr.get(i));
            else break;
        return returnArr;
    }

    /**
     * Returns first element
     */
    @Override
    public T first() {
        return arr.get(0);
    }

    /**
     * Returns last element
     */
    @Override
    public T last() {
        return arr.get(arr.size() - 1);
    }

    /**
     * Return size of set(size of array) {@link MyNavigableSet#arr}
     */
    @Override
    public int size() {
        return arr.size();
    }

    /**
     * Compares the specified object with this set for equality.
     * @param o - specific object
     */
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

    /**
     * Returns the hash code value for this set.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        for (int i = 0; i < arr.size(); i++) {
            hash = hash + 13 * (49 * arr.get(i).hashCode() + 8);
        }
        return hash;
    }

    /**
     * Checks if the set is empty
     */
    @Override
    public boolean isEmpty() {
        return arr.isEmpty();
    }
}
