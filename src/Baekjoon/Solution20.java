package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 1316 그룹 단어 체커
 */
public class Solution20 {
    public static void main(String[] args) throws IOException {
        int answer = 0;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int repeat = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < repeat; i++) {
            String str = bufferedReader.readLine();
            if (groupWordChecker(str)) {
                answer++;
            }
        }
        System.out.println(answer);
    }
    private static boolean groupWordChecker(String str) {
        Stack<Character> stack = new Stack<>();
        stack.push(str.charAt(0));
        for (int i = 1; i < str.length(); i++) {
            if (stack.peek().equals(str.charAt(i))) {
                continue;
            }
            if (stack.contains(str.charAt(i))) {
                return false;
            }
            stack.push(str.charAt(i));
        }
        return true;
    }
}
