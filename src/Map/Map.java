package Map;

import java.util.*;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Map<K, V> extends AbstractMap<K, V> {

    static class Node<K,V> implements Entry<K,V> {
        final K key;
        V value;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public final K getKey()        { return key; }
        public final V getValue()      { return value; }
        public final String toString() { return key + "=" + value; }

        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        public final V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        public final boolean equals(Object o) {
            if (o == this)
                return true;
            if (o instanceof java.util.Map.Entry) {
                Entry<?,?> e = (Entry<?,?>)o;
                if (Objects.equals(key, e.getKey()) &&
                        Objects.equals(value, e.getValue()))
                    return true;
            }
            return false;
        }
    }

    private ArrayList<K> keys;
    private ArrayList<V> values;

    public Map(){
        keys = new ArrayList<>();
        values = new ArrayList<>();
    }

    public Map(Map<K, V> map){
        this.keys = map.keys;
        this.values = map.values;
    }

    @Override
    public void clear() {
        keys = null;
        values = null;
    }

    @Override
    public Collection<V> values() {
        Collection<V> returnArr = new ArrayList<>(values);
        return returnArr;
    }

    @Override
    public int size() {
        return keys.size();
    }

    @Override
    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
        if (function == null) throw new NullPointerException();

        for (int i = 0; i < values.size(); i++) {
            values.set(i, function.apply(keys.get(i), values.get(i)));
        }
    }

    @Override
    public boolean replace(K key, V oldValue, V newValue) {
        if(keys.contains(key) && values.get(keys.indexOf(key)).equals(oldValue)){
            values.set(keys.indexOf(key), newValue);
            return true;
        } else{
            return false;
        }
    }

    @Override
    public V replace(K key, V value) {
        if(keys.contains(key)){
            V returnValue = values.get(keys.indexOf(key));
            values.set(keys.indexOf(key), value);
            return returnValue;
        } else{
            throw new NoSuchElementException();
        }
    }

    @Override
    public boolean remove(Object key, Object value) {
        if(keys.contains(key) && values.get(keys.indexOf(key)).equals(value)){
            values.remove(keys.indexOf(key));
            return true;
        } else{
            return false;
        }
    }

    @Override
    public V putIfAbsent(K key, V value) {
        if(keys.contains(key) && values.get(keys.indexOf(key)).equals(value)){
            return values.get(keys.indexOf(key));
        } else{
            keys.add(key);
            values.add(value);
            return null;
        }
    }

    @Override
    public V put(K key, V value) {
        if(keys.contains(key) && values.get(keys.indexOf(key)).equals(value)){
            return values.get(keys.indexOf(key));
        } else if(keys.contains(key) && !values.get(keys.indexOf(key)).equals(value)){
            V returnValue = values.get(keys.indexOf(key));
            values.set(keys.indexOf(key), value);
            return returnValue;
        } else{
            keys.add(key);
            values.add(value);
            return null;
        }
    }

    @Override
    public Set<K> keySet() {
        return new HashSet<>(keys);
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> arr = new HashSet<>();

        for (int i = 0; i < keys.size(); i++) {
            arr.add(new Node<>(keys.get(i), values.get(i)));
        }
        return arr;
    }

    @Override
    public boolean containsKey(Object key) {
        return keys.contains(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return values.contains(value);
    }

    @Override
    public boolean isEmpty() {
        return keys.isEmpty();
    }

    @Override
    public V get(Object key) {
        if(keys.contains(key) && values.get(keys.indexOf(key)) != null){
            return values.get(keys.indexOf(key));
        } else{
            return null;
        }
    }

    @Override
    public V getOrDefault(Object key, V defaultValue) {
        if(keys.contains(key) && values.get(keys.indexOf(key)) != null){
            return values.get(keys.indexOf(key));
        } else {
            return defaultValue;
        }
    }

    @Override
    public void forEach(BiConsumer<? super K, ? super V> action) {
        for (int i = 0; i < values.size(); i++) {
            action.accept(keys.get(i), values.get(i));
        }
    }

    @Override
    public V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction) {
        if(!keys.contains(key) || values.get(keys.indexOf(key)) == null){
            V returnValue = values.get(keys.indexOf(key));
            V setValue = mappingFunction.apply(keys.get(keys.indexOf(key)));

            if(returnValue == null) values.set(keys.indexOf(key), setValue);
            return setValue;
        } else {
            return null;
        }
    }

    @Override
    public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        if(keys.contains(key) && values.get(keys.indexOf(key)) != null){
            return remappingFunction.apply(keys.get(keys.indexOf(key)), values.get(keys.indexOf(key)));
        } else {
            return null;
        }
    }

    @Override
    public V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        if(keys.contains(key) && values.get(keys.indexOf(key)) != null){
            V returnValue = values.get(keys.indexOf(key));
            values.set(keys.indexOf(key), remappingFunction.apply(keys.get(keys.indexOf(key)), values.get(keys.indexOf(key))));
            return returnValue;
        } else {
            return null;
        }
    }

    @Override
    public V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
        if(!keys.contains(key) || values.get(keys.indexOf(key)) == null) {
            V returnValue = values.get(keys.indexOf(key));
            values.set(keys.indexOf(key), remappingFunction.apply(values.get(keys.indexOf(key)), value));
            return returnValue;
        }

        return null;
    }

    @Override
    public int hashCode() {
        int returned = 0;
        for (int i = 0; i < keys.size(); i++) {
            returned = returned + keys.get(i).hashCode() + values.get(i).hashCode();
        }
        return returned;
    }
}
