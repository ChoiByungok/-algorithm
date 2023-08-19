package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.EmptyStackException;
import java.util.Stack;

/**
 * 9012 괄호
 */
public class Solution24 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int repeat = Integer.parseInt(bufferedReader.readLine());
        Stack<Character> stack = new Stack<>();
        StringBuilder stringBuilder = new StringBuilder();
        boolean exception = false;

        for (int i = 0; i < repeat; i++) {
            String ps = bufferedReader.readLine();
            for (int j = 0; j < ps.length(); j++) {
                try {
                    char c = ps.charAt(j);
                    if (c == '(') {
                        stack.push(c);
                    } else {
                        stack.pop();
                    }
                } catch (EmptyStackException e) {
                    stringBuilder.append("NO").append("\n");
                    exception = true;
                    break;
                }
            }
            if (exception) {
                exception = false;
                stack.clear();
                continue;
            }
            if (!stack.isEmpty()) {
                stringBuilder.append("NO").append("\n");
            } else {
                stringBuilder.append("YES").append("\n");
            }
            stack.clear();
        }
        System.out.println(stringBuilder);
    }
}
