package Piano;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Keys {
    private static HashMap<String, String> keys;

    static {
        keys = new HashMap<>();
        keys.put("a", "A.mp3");
        keys.put("b", "B.mp3");
        keys.put("c", "C.mp3");
        keys.put("d", "D.mp3");
        keys.put("e", "E.mp3");
        keys.put("f", "F.mp3");
        keys.put("g", "G.mp3");
    }

    public static int size() {
        return keys.size();
    }

    public static boolean isEmpty() {
        return keys.isEmpty();
    }

    public static String get(Object key) {
        return keys.get(key);
    }

    public static boolean containsKey(Object key) {
        return keys.containsKey(key);
    }

    public static String put(String key, String value) {
        return keys.put(key, value);
    }

    public static void putAll(Map<? extends String, ? extends String> m) {
        keys.putAll(m);
    }

    public static String remove(Object key) {
        return keys.remove(key);
    }

    public static void clear() {
        keys.clear();
    }

    public static boolean containsValue(Object value) {
        return keys.containsValue(value);
    }

    public static Set<String> keySet() {
        return keys.keySet();
    }

    public static Collection<String> values() {
        return keys.values();
    }

    public static Set<Map.Entry<String, String>> entrySet() {
        return keys.entrySet();
    }

    public static String getOrDefault(Object key, String defaultValue) {
        return keys.getOrDefault(key, defaultValue);
    }

    public static String putIfAbsent(String key, String value) {
        return keys.putIfAbsent(key, value);
    }

    public static boolean remove(Object key, Object value) {
        return keys.remove(key, value);
    }

    public static boolean replace(String key, String oldValue, String newValue) {
        return keys.replace(key, oldValue, newValue);
    }

    public static String replace(String key, String value) {
        return keys.replace(key, value);
    }

    public static String computeIfAbsent(String key, Function<? super String, ? extends String> mappingFunction) {
        return keys.computeIfAbsent(key, mappingFunction);
    }

    public static String computeIfPresent(String key, BiFunction<? super String, ? super String, ? extends String> remappingFunction) {
        return keys.computeIfPresent(key, remappingFunction);
    }

    public static String compute(String key, BiFunction<? super String, ? super String, ? extends String> remappingFunction) {
        return keys.compute(key, remappingFunction);
    }

    public static String merge(String key, String value, BiFunction<? super String, ? super String, ? extends String> remappingFunction) {
        return keys.merge(key, value, remappingFunction);
    }

    public static void forEach(BiConsumer<? super String, ? super String> action) {
        keys.forEach(action);
    }

    public static void replaceAll(BiFunction<? super String, ? super String, ? extends String> function) {
        keys.replaceAll(function);
    }
}
