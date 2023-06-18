package Programmers3;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 무작위로 k개의 수 뽑기
 */
public class Solution8 {
    public int[] solution(int[] arr, int k) {
        int[] answer = new int[k];
        Arrays.fill(answer, -1);
        Set<Integer> set = new HashSet<>();
        int index = 0;
        for (int num : arr) {
            if (!set.contains(num)) {
                answer[index++] = num;
                set.add(num);
            }
            if (index >= k) {
                break;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        int[] arr1 = {0, 1, 1, 2, 2, 3};
        int k1 = 3;

        int[] arr2 = {0, 1, 1, 1, 1};
        int k2 = 4;

        System.out.println(Arrays.toString(new Solution8().solution(arr1, k1)));
    }
}
