package Programmers3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 문자 개수 세기
 */
public class Solution26 {
    public int[] solution(String my_string) {
        Map<Character, Integer> map = new HashMap<>();
        char A = 'A';
        for (int i = 0; i < 26; i++) {
            map.put(A++, 0);
        }
        char a = 'a';
        for (int i = 0; i < 26; i++) {
            map.put(a++, 0);
        }
        for (int i = 0; i < my_string.length(); i++) {
            map.put(my_string.charAt(i), map.getOrDefault(my_string.charAt(i), 0) + 1);
        }
        return map.values().stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        String my_string = "Programmers";
        System.out.println(Arrays.toString(new Solution26().solution(my_string)));
    }
}
