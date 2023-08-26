package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 9935 문자열 폭발
 */
public class Solution30 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String readLine = bufferedReader.readLine();
        Stack<Character> stack = new Stack<>();
        StringBuilder stringBuilder = new StringBuilder();

        String bomb = bufferedReader.readLine();
        int length = bomb.length();

        for (int i = 0; i < readLine.length(); i++) {
            int count = 0;
            stack.push(readLine.charAt(i));
            if (stack.size() >= length) {
                for (int j = 0; j < length; j++) {
                    if (stack.get(stack.size() - length + j) == bomb.charAt(j)) {
                        count++;
                    }
                    if (count == length) {
                        for (int k = 0; k < length; k++) {
                            stack.pop();
                        }
                    }
                }
            }
        }
        if (stack.isEmpty()) {
            stringBuilder.append("FRULA");
        } else {
            for (Character character : stack) {
                stringBuilder.append(character);
            }
        }
        System.out.println(stringBuilder);
    }
}
