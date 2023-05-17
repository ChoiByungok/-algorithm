package Programmers2;

import java.util.*;

/**
 * 귤 고르기
 */
public class Solution36 {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        Map<Integer, Integer> size = new HashMap<>();
        for (int i : tangerine) {
            size.put(i, size.getOrDefault(i, 0) + 1);
        }
        List<Map.Entry<Integer, Integer>> sizeList = new LinkedList<>(size.entrySet());

        sizeList.sort((o1, o2) -> o2.getValue() - o1.getValue());
        for (Map.Entry<Integer, Integer> entry : sizeList) {
                k = k - entry.getValue();
                answer++;
                if (k <= 0) {
                    break;
                }
        }
        return answer;
    }

    public static void main(String[] args) {
        int k1 = 6;
        int[] tangerine1 = {1, 3, 2, 5, 4, 5, 2, 3};

        int k2 = 4;
        int[] tangerine2 = {1, 3, 2, 5, 4, 5, 2, 3};

        int k3 = 2;
        int[] tangerine3 = {1, 1, 1, 1, 2, 2, 2, 3};
        System.out.println(new Solution36().solution(k3, tangerine3));
    }
}
