package Programmers;

import java.util.Stack;

/**
 * A로 B 만들기
 */
public class Solution38 {
    public int solution(String before, String after) {
        Stack<Character> stack = new Stack<>();
        StringBuilder afterBuilder = new StringBuilder(after);

        for (int i = 0; i < before.length(); i++) {
            stack.push(before.charAt(i));
        }
        for (int i = 0; i < after.length(); i++) {
            for (int j = 0; j < afterBuilder.length(); j++) {
                if(stack.peek() == afterBuilder.charAt(j)){
                    stack.pop();
                    afterBuilder.delete(j,j+1);
                }
            }
        }


        return stack.isEmpty()? 1 : 0;
    }

    public static void main(String[] args) {
        String before = "olleh";
        String after = "hello";

        System.out.println(new Solution38().solution(before,after));
    }
}
