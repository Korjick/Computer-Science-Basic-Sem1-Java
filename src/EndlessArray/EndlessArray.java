package EndlessArray;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EndlessArray<T> implements Iterable<T>{

    /**
     * Class copies functionality of ArrayList.
     * @autor Bulat Din
     * @version 2.0
     */

    /**
     * Field set default size of array
     * @see EndlessArray#EndlessArray()
     */
    private final int BASE_CAPACITY = 10;
    /**The field sets the degree of array increase
     * @see EndlessArray#negativeArrayResize(int)
     */
    private final float ARRAY_EXPANSION = 1.5f;
    private T[] arr;
    /** Field control the count of array elements*/
    private int elementCount;

    public EndlessArray() {
        arr = (T[]) new Object[BASE_CAPACITY];
        elementCount = 0;
    }

    /**Constructor - creating a new object with specific values
     * @param length the length of array*/
    public EndlessArray(int length) {
        arr = (T[]) new Object[length];
        elementCount = 0;
    }

    /**The function adds a new number to the end of the array
     * @param num specific element*/
    public void add(T num) {
        if (arr.length == elementCount) arr = positiveArrayResize();
        arr[elementCount] = num;
        elementCount++;
    }

    /**The function removes a number from an array at a specific index
     * @param index index of element*/
    public void remove(int index) throws EndlessArrayDeleteItemIndexOutOfBoundsException {
        if (index < 0 || index >= elementCount) throw new EndlessArrayDeleteItemIndexOutOfBoundsException();
        arr = negativeArrayResize(index);
        elementCount--;
    }

    /**The function gets specific number from array
     * @return returns an element at a specific index*/
    public T get(int index) throws EndlessArrayIndexOutOfBoundsException {
        if (index < 0 || index >= elementCount) throw new EndlessArrayIndexOutOfBoundsException();
        return arr[index];
    }

    /**The function gets the value of the field {@link EndlessArray#elementCount}
     * @return return the dummy size of the array that the user expects to see*/
    public int size() {
        return elementCount;
    }

    /**The function gets full size of array
     *  @return return the real size of the array*/
    public int capacity() {
        return arr.length;
    }

    /**The function gets index of specific number
     * @return return the first index of a specific element. If there is no such element, then returns -1*/
    public int indexOf(T num) {
        for (int i = 0; i < size(); i++) {
            if (num.equals(arr[i])) return i;
        }
        return -1;
    }

    /**The function gets last index of specific number
     * @return return the last index of a specific element. If there is no such element, then returns -1*/
    public int lastIndexOf(T num){
        for (int i = size() - 1; i >= 0; i--) {
            if (num.equals(arr[i])) return i;
        }
        return -1;
    }

    /**The function checks if specific number contains in array
     * @return return true if specific element contain in array, else return false */
    public boolean contains(T num) {
        return indexOf(num) >= 0;
    }

    /**The function gets the field {@link EndlessArray#arr}
     * @return return array*/
    public T[] getArray(){
        T[] returnArray = (T[]) new Object[size()];
        System.arraycopy(arr, 0, returnArray, 0, size());
        return returnArray;
    }

    /**The private function expands the array
     * @return new extended array*/
    private T[] positiveArrayResize() {
        T[] returnArray = (T[]) new Object[(int)(arr.length * ARRAY_EXPANSION)];
        System.arraycopy(arr, 0, returnArray, 0, size());
        return returnArray;
    }

    /**The private function deletes number at the specific index
     * @return new truncated array without specific element*/
    private T[] negativeArrayResize(int index) {
        T[] returnArray = (T[]) new Object[capacity() - 1];
        System.arraycopy(arr, 0, returnArray, 0, index);
        System.arraycopy(arr, size() - (index - 1), returnArray, size() - index, size() - (index - 1));
        return returnArray;
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
                if(HEAD == elementCount) throw new NoSuchElementException();
                HEAD++;
                return arr[HEAD-1];
            }
        };
    }
}

