package Programmers;
/**
 * 최댓값과 최솟값
 */

import java.util.*;

public class Solution11 {
    public String solution(String s) {
        String[] s1 = s.split(" ");
        List<Integer> list = new ArrayList<>();
        for (String value : s1) {
            list.add(Integer.valueOf(value));
        }
        Integer max = Collections.max(list);
        Integer min = Collections.min(list);

        return min + " " + max;
    }

    public static void main(String[] args) {
        Solution11 solution11 = new Solution11();
        String s = "-1 -2 -3 -4";
        System.out.println(solution11.solution(s));
    }
}
