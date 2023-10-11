package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 17413 단어 뒤집기2
 */
public class Solution4 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String readLine = bufferedReader.readLine();
        StringBuilder answer = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        boolean bracket = false;

        for (int i = 0; i < readLine.length(); i++) {
            char c = readLine.charAt(i);
            if (c == '<') {
                bracket = true;
                while (!stack.isEmpty()) {
                    answer.append(stack.pop());
                }
            } else if (c == '>') {
                bracket = false;
                answer.append(c);
                continue;
            } else if (c == ' ') {
                while (!stack.isEmpty()) {
                    answer.append(stack.pop());
                }
                answer.append(c);
                continue;
            }

            if (bracket) {
                answer.append(c);
            } else {
                stack.push(c);
            }
        }
        while (!stack.isEmpty()) {
            answer.append(stack.pop());
        }

        System.out.println(answer);
    }
}
