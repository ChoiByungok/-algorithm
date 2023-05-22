package Programmers2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 2019 카카오 개발자 겨울 인턴십
 * 튜플
 */
public class Solution40 {
    public int[] solution(String s) {
        List<Integer> list = new ArrayList<>();
        String substring = s.substring(2);
        String substring1 = substring.substring(0, substring.length() - 2);

        String replace = substring1.replace("},{", "-");

        String[] split = replace.split("-");
        Arrays.sort(split, Comparator.comparingInt(String::length));
        for (String s1 : split) {
            String[] temp = s1.split(",");
            for (int i = 0; i < temp.length; i++) {
                int n = Integer.parseInt(temp[i]);
                if (!list.contains(n)) {
                    list.add(n);
                }
            }
        }
        return list.stream().mapToInt(integer -> integer).toArray();
    }

    public static void main(String[] args) {
        String s1 = "{{2},{2,1},{2,1,3},{2,1,3,4}}";
        String s2 = "{{4,2,3},{3},{2,3,4,1},{2,3}}";
        System.out.println(Arrays.toString(new Solution40().solution(s2)));
    }
}
