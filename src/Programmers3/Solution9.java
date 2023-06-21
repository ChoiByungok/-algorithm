package Programmers3;

import java.util.*;

/**
 * 문자열 묶기
 */
public class Solution9 {
    public int solution(String[] strArr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (String str : strArr) {
            int length = str.length();
            map.put(length, map.getOrDefault(length, 0) + 1);
        }
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort((o1, o2) -> {
            if (o1.getValue().equals(o2.getValue())) {
                return o1.getKey() - o2.getKey();
            } else {
                return o1.getValue() - o2.getValue();
            }
        });

        return list.get(list.size() - 1).getValue();
    }

    public static void main(String[] args) {
        String[] strArr = {"a","bc","d","efg","hi"};
        System.out.println(new Solution9().solution(strArr));
    }
}
