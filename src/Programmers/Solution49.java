package Programmers;

import java.util.Stack;

/**
 * 문자열 계산하기
 */

public class Solution49 {
    public int solution(String my_string) {
        Stack<Integer> stack = new Stack<>();
        String[] split = my_string.split(" ");
        stack.push(Integer.parseInt(split[0]));
        for (int i = 1; i < split.length - 1; i++) {
            if (split[i].equals("+")) {
                Integer pop = stack.pop();
                stack.push(pop + Integer.parseInt(split[i+1]));
            } else if (split[i].equals("-")) {
                Integer pop = stack.pop();
                stack.push(pop - Integer.parseInt(split[i+1]));
            }
        }
        return stack.peek();
    }

    public static void main(String[] args) {
        String my_string = "3 + 4 - 5 + 6 - 10000";
        System.out.println(new Solution49().solution(my_string));
    }
}
