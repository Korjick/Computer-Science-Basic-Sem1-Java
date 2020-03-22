package StreamAPI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        /*<--- 4 --->*/

        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(3);
        arr.add(1);
        arr.add(5);
        arr.sort(((o1, o2) -> o1 - o2));

        /*<--- 5 --->*/

        ArrayList<Integer> list1 = new ArrayList<>();
        list1.add(7);
        list1.add(9);
        list1.add(3);
        list1.stream().filter(x -> x > arr.get(arr.size() - 1)).forEach(System.out::println);

        /*<--- 6 --->*/

        HashSet<String> set = new HashSet<>();
        set.add("Hello");
        set.add("Alexandra");
        long k = set.stream().filter(x ->
        {
            x = x.toLowerCase();
            int count = 0;
            for (int i = 0; i < x.length(); i++) {
                if(x.charAt(i) == 'a' || x.charAt(i) == 'e' ||
                        x.charAt(i) == 'i' || x.charAt(i) == 'o' || x.charAt(i) == 'u') count++;
            }
            return count > 3;
        }).count();
        System.out.println(k);

        /*<--- 7 --->*/

        HashMap<String, String> map = new HashMap<>();
        map.put("a", "a");
        map.put("b", "b");
        String s = map.keySet().stream().collect(Collectors.joining());
        System.out.println(s);

        /*<--- 8 --->*/
        ArrayList<String> list2 = new ArrayList<>();
        list2.add("Hello Dear");
        list2.add("Mama I`m Sorry");
        k = list2.stream().filter(x -> x.length() > 5).map(x -> x.length()).count();
        System.out.println(k);
    }
}
