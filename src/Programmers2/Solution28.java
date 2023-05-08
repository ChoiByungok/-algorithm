package Programmers2;

import java.util.Arrays;
import java.util.Collections;

/**
 * 과일 장수
 */

public class Solution28 {
    public int solution(int k, int m, int[] score) {
        int answer = 0;
        int box = score.length / m;
        int[] reverseOrder = Arrays
                .stream(score)
                .boxed()
                .sorted(Collections.reverseOrder())
                .mapToInt(Integer::intValue)
                .toArray();


        int start = 0;
        int end = m;
        for (int i = 0; i < box; i++) {
            int[] boxed = Arrays.stream(reverseOrder, start, end).toArray();
            answer += boxed[m - 1] * m;
            start = end;
            end = end + m;
        }
        return answer;
    }

    public static void main(String[] args) {
        int k1 = 3;
        int m1 = 4;
        int[] score1 = {1, 2, 3, 1, 2, 3, 1};

        int k2 = 4;
        int m2 = 3;
        int[] score2 = {4, 1, 2, 2, 4, 4, 4, 4, 1, 2, 4, 2};

        System.out.println(new Solution28().solution(k1, m1 , score1));
    }
}
