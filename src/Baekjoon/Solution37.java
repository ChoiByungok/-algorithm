package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.EmptyStackException;
import java.util.Stack;

/**
 * 4949 균형잡힌 세상
 */
public class Solution37 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stringBuilder = new StringBuilder();

        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine.equals(".")) {
                break;
            }
            String s = readLine.replaceAll("[A-Za-z.]", "").trim().replace(" ", "");
            if (balanceCheck(s)) {
                stringBuilder.append("yes").append("\n");
            } else {
                stringBuilder.append("no").append("\n");
            }
        }
        System.out.println(stringBuilder);
    }

    private static boolean balanceCheck(String s) {
        Stack<Character> stack = new Stack<>();
        try {
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(' || s.charAt(i) == '[') {
                    stack.push(s.charAt(i));
                } else {
                    if (s.charAt(i) == ')') {
                        if (stack.peek() == '(') {
                            stack.pop();
                        } else {
                            return false;
                        }
                    } else {
                        if (stack.peek() == '[') {
                            stack.pop();
                        } else {
                            return false;
                        }
                    }
                }
            }
        } catch (EmptyStackException e) {
            return false;
        }
        return stack.isEmpty();
    }
}