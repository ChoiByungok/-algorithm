package Programmers3;

import java.util.Arrays;
import java.util.Stack;

/**
 * 배열 만들기 6
 */
public class Solution23 {
    public int[] solution(int[] arr) {
        Stack<Integer> stk = new Stack<>();
        int i = 0;
        while (arr.length > i) {
            if (stk.isEmpty()) {
                stk.add(arr[i]);
                i++;
            } else if (stk.peek() == arr[i]) {
                stk.pop();
                i++;
            } else {
                stk.add(arr[i]);
                i++;
            }

        }
        return stk.isEmpty() ? new int[]{-1} : stk.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        int[] arr1 = {0, 1, 1, 1, 0};
        int[] arr2 = {0, 1, 0, 1, 0};
        int[] arr3 = {0, 1, 1, 0};

        System.out.println(Arrays.toString(new Solution23().solution(arr1)));
    }
}
