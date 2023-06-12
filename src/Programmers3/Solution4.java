package Programmers3;

import java.util.Arrays;

/**
 * 배열 조각하기
 */
public class Solution4 {
    public int[] solution(int[] arr, int[] query) {
        for (int i = 0; i < query.length; i++) {
            int number = query[i];
            if (i % 2 == 0) {
                arr = Arrays.copyOfRange(arr, 0, number + 1);
            } else {
                arr = Arrays.copyOfRange(arr, number, arr.length);
            }
        }

        return arr;
    }

    public static void main(String[] args) {
        int[] arr = {0, 1, 2, 3, 4, 5};
        int[] query = {4, 1, 2};

        System.out.println(Arrays.toString(new Solution4().solution(arr, query)));

    }
}
