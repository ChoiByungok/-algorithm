package Programmers;
/**
 * 올바른 괄호
 */

import java.util.*;

public class Solution15 {
    boolean solution(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '('){
                stack.push(s.charAt(i));
            }
            try {
                if (s.charAt(i) == ')') {
                    stack.pop();
                }
            }catch (EmptyStackException e){
                return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        Solution15 solution15 = new Solution15();

        System.out.println(solution15.solution(")()("));
    }
}
