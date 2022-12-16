package Programmers;

import java.util.*;

/**
 * 괄호 회전하기
 */
public class Solution31 {
    public int solution(String s) {
        int answer = 0;
        for (int i = 0; i < s.length(); i++) {
            StringBuilder sb = new StringBuilder(s);
            String subString = sb.substring(0, i);
            sb.delete(0,i);
            sb.append(subString);
            if(rightParenthesisString(sb)){
                answer++;
            }
        }
        return answer;
    }
    public boolean rightParenthesisString(StringBuilder sb){
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < sb.length(); i++) {
            try {
                if(stack.isEmpty()){
                    stack.push(sb.charAt(i));
                }else {
                    if(sb.charAt(i) == ']'){
                        if(stack.peek() == '['){
                            stack.pop();
                        }else {
                            stack.push(sb.charAt(i));
                        }
                    } else if (sb.charAt(i) == '}') {
                        if(stack.peek() == '{'){
                            stack.pop();
                        }else {
                            stack.push(sb.charAt(i));
                        }
                    } else if (sb.charAt(i) == ')') {
                        if(stack.peek() == '('){
                            stack.pop();
                        }else {
                            stack.push(sb.charAt(i));
                        }
                    }else {
                        stack.push(sb.charAt(i));
                    }
                }

            }catch (EmptyStackException e){
                return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String s1 = "[](){}";
        String s2 = "}]()[{";
        String s3 = "[)(]";
        String s4 = "}}}";

        System.out.println(new Solution31().solution(s1));
    }
}
