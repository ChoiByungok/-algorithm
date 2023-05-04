package Programmers2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 추억점수
 */
public class Solution27 {
    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        int[] answer = new int[photo.length];
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < yearning.length; i++) {
            map.put(name[i], yearning[i]);
        }
        for (int i = 0; i < photo.length; i++) {
            for (int j = 0; j <photo[i].length; j++) {
                for (String n : map.keySet()) {
                    if (n.equals(photo[i][j])) {
                        answer[i] += map.get(n);
                    } else {
                        answer[i] += 0;
                    }
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        String[] name = {"may", "kein", "kain", "radi"};
        int[] yearning = {5, 10, 1, 3};
        String[][] photo = {{"may", "kein", "kain", "radi"},
                            {"may", "kein", "brin", "deny"},
                            {"kon", "kain", "may", "coni"}};
        System.out.println(Arrays.toString(new Solution27().solution(name, yearning, photo)));
    }
}
