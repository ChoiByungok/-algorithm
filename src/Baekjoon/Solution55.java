package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.EmptyStackException;
import java.util.Stack;

/**
 * 2504 괄호의 값
 */
public class Solution55 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String parenthesis = bufferedReader.readLine();
        Stack<String> stack = new Stack<>();
        int total;
        int answer = 0;
        boolean wrong = false;
        boolean repeat = false;
        for (int i = 0; i < parenthesis.length(); i++) {
            try {
                total = 0;
                char c = parenthesis.charAt(i);
                if (c == '(' || c == '[') {
                    stack.push(String.valueOf(c));
                } else if (c == ')') {
                    while (!stack.peek().equals("(")) {
                        repeat = true;
                        if (stack.peek().equals("[")) {
                            wrong = true;
                            break;
                        }
                        int num = Integer.parseInt(stack.pop());
                        total += num;
                    }
                    if (wrong) {
                        break;
                    }
                    stack.pop();
                    if (repeat) {
                        repeat = false;
                        stack.push(String.valueOf(total * 2));
                    } else {
                        stack.push("2");
                    }
                } else if (c == ']') {
                    while (!stack.peek().equals("[")) {
                        repeat = true;
                        if (stack.peek().equals("(")) {
                            wrong = true;
                            break;
                        }
                        int num = Integer.parseInt(stack.pop());
                        total += num;
                    }
                    if (wrong) {
                        break;
                    }
                    stack.pop();
                    if (repeat) {
                        repeat = false;
                        stack.push(String.valueOf(total * 3));
                    } else {
                        stack.push("3");
                    }
                }
            } catch (EmptyStackException e) {
                wrong = true;
                break;
            }
        }

        if (wrong) {
            System.out.println(answer);
        } else {
            while (!stack.isEmpty()) {
                String peek = stack.peek();
                if (peek.equals("(") || peek.equals(")") || peek.equals("[") || peek.equals("]")) {
                    answer = 0;
                    break;
                }
                int num = Integer.parseInt(stack.pop());
                answer += num;
            }
            System.out.println(answer);
        }
    }
}
