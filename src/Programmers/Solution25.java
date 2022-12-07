package Programmers;

import java.util.Stack;

/**
 * 짝지어 제거하기
 * 효율성 성공
 */
public class Solution25 {
    public int solution(String s) {

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            if(stack.isEmpty() || stack.peek() != s.charAt(i)){
                stack.push(s.charAt(i));
            } else if (stack.peek() == s.charAt(i)) {
                stack.pop();
            }
        }
        return stack.isEmpty() ? 1 : 0;
    }

    public static void main(String[] args) {

        String s = "cdcd";
        System.out.println(new Solution25().solution(s));
    }
}
