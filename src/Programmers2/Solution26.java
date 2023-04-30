package Programmers2;

import java.util.*;

/**
 * 더 맵게
 * 섞은 음식의 스코빌 지수 = 가장 맵지 않은 음식의 스코빌 지수 + (두 번째로 맵지 않은 음식의 스코빌 지수 * 2)
 */

public class Solution26 {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        Queue<Integer> heap = new PriorityQueue<>();
        for (int i : scoville) {
            heap.add(i);
        }
        while (heap.peek() < K) {
            if (heap.size() == 1) {
                return -1;
            }
            Integer fns = heap.poll();
            Integer sns = heap.poll();
            int result = fns + (sns * 2);
            heap.offer(result);
            answer++;
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] scoville1 = {1, 2, 3, 9, 10, 12};
        int K = 7;
        System.out.println(new Solution26().solution(scoville1, K));
    }
}
