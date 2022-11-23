package Programmers;
/**
 * 같은 숫자는 싫어
 */

import java.util.*;

public class Solution5 {
    public int[] solution(int []arr) {
        Stack<Integer> stack = new Stack<>();
        stack.push(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            if(stack.peek() != arr[i]){
                stack.push(arr[i]);
            }
        }
        int[] answer = new int[stack.size()];
        for (int i = answer.length; i > 0; i--) {
            answer[i-1] = stack.pop();
        }
        return answer;
    }

    public static void main(String[] args) {
        Solution5 solution5 = new Solution5();
        int[] arr1 = {4,4,4,3,3};
        int[] arr2 = {1,1,3,3,0,1,1};
        int[] arr3 = {3,3,3,3};
        System.out.println(Arrays.toString(solution5.solution(arr2)));
    }
}
