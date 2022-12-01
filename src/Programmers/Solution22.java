package Programmers;

import java.util.*;

/**
 * 푸드 파이트 대회
 */
public class Solution22 {
    public String solution(int[] food) {
        StringBuilder answer = new StringBuilder();
        Stack<Integer> stack = new Stack<>();
        for (int i = 1; i < food.length; i++) {
            for (int j = 0; j < food[i]/2; j++) {
                answer.append(i);
                stack.push(i);
            }
        }
        answer.append("0");
        int size = stack.size();

        for (int i = 0; i < size; i++) {
            answer.append(stack.pop());
        }
        return answer.toString();
    }

    public static void main(String[] args) {
        int[] food = {1, 3, 4, 6};
        System.out.println(new Solution22().solution(food));
    }
}
