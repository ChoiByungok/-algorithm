package Programmers2;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * 괄호 회전하기
 */
public class Solution41 {
    public int solution(String s) {
        StringBuilder sb = new StringBuilder(s);
        int answer = 0;
        for (int i = 0; i < sb.length(); i++) {
            if (rotationCheck(sb)) {
                answer++;
            }
            char c = sb.charAt(0);
            sb.deleteCharAt(0);
            sb.append(c);
        }
        return answer;
    }

    public boolean rotationCheck(StringBuilder sb) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            try {
                if (c == '[') {
                    stack.push(c);
                } else if (c == ']') {
                    if (stack.peek() != '[') {
                        return false;
                    }
                    stack.pop();
                } else if (c == '{') {
                    stack.push(c);
                } else if (c == '}') {
                    if (stack.peek() != '{') {
                        return false;
                    }
                    stack.pop();
                } else if (c == '(') {
                    stack.push(c);
                } else if (c == ')') {
                    if (stack.peek() != '(') {
                        return false;
                    }
                    stack.pop();
                }
            } catch (EmptyStackException e) {
                return false;
            }
        }
        return stack.isEmpty();
    }


    public static void main(String[] args) {
        String[] s = {"[](){}", "}]()[{", "[)(]", "}}}"};
        for (String s1 : s) {
            System.out.println(new Solution41().solution(s1));
        }
    }
}
