package Programmers3;

import java.util.*;

/**
 * 전국 대회 선발 고사
 */
public class Solution11 {
    public int solution(int[] rank, boolean[] attendance) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < rank.length; i++) {
            if (attendance[i]) {
                map.put(rank[i], i);
            }
        }
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort(Comparator.comparingInt(Map.Entry::getKey));

        return list.get(0).getValue() * 10000 + list.get(1).getValue() * 100 + list.get(2).getValue();
    }

    public static void main(String[] args) {
        int[] rank1 = {3, 7, 2, 5, 4, 6, 1};
        boolean[] attendance1 = {false, true, true, true, true, false, false};

        int[] rank2 = {1, 2, 3};
        boolean[] attendance2 = {true, true, true};

        int[] rank3 = {6, 1, 5, 2, 3, 4};
        boolean[] attendance3 = {true, false, true, false, false, true};

        System.out.println(new Solution11().solution(rank3, attendance3));
    }
}
