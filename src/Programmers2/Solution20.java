package Programmers2;

import java.util.Arrays;

/**
 * 명예의 전당 (1)
 */
public class Solution20 {

    public int[] solution(int k, int[] score) {
        int[] answer = new int[score.length];
        for (int i = 0; i < score.length; i++) {
            int[] ints = Arrays.copyOfRange(score, 0, i + 1);
            answer[i] = rankTop3(ints, k);
        }
        return answer;
    }

    public int rankTop3(int[] arr, int k) {
        Arrays.sort(arr);
        if (arr.length < k) {
            return arr[0];
        }else {
            return arr[arr.length - k];
        }
    }

    public static void main(String[] args) {
        int k = 4;
        int[] score = {0, 300, 40, 300, 20, 70, 150, 50, 500, 1000};

        System.out.println(Arrays.toString(new Solution20().solution(k, score)));
    }
}
