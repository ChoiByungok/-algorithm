package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 1918 후위 표기식
 */
public class Solution1 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        String readLine = bufferedReader.readLine();

        for (int i = 0; i < readLine.length(); i++) {
            char c = readLine.charAt(i);
            if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!(stack.peek() == '(')) {
                    answer.append(stack.pop());
                }
                stack.pop();
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                if (stack.isEmpty()) {
                    stack.push(c);
                } else {
                    while (!stack.isEmpty() && priority(stack.peek()) >= priority(c)) {
                        answer.append(stack.pop());
                    }
                    stack.push(c);
                }
            } else {
                answer.append(c);
            }
        }
        while (!stack.isEmpty()) {
            Character pop = stack.pop();
            if (pop == '(' || pop == ')') {
                continue;
            }
            answer.append(pop);
        }
        System.out.println(answer);
    }
    private static int priority (char c) {
        if (c == '*' || c == '/') {
            return 2;
        } else if (c == '+' || c == '-') {
            return 1;
        } else {
            return 0;
        }
    }
}
