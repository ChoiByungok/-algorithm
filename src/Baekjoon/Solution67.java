package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 10799 쇠막대기
 */
public class Solution67 {
    static Stack<String> stack = new Stack<>();
    static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String bracket = bufferedReader.readLine();

        for (int i = 0; i < bracket.length(); i++) {
            String s = String.valueOf(bracket.charAt(i));
            logic(s);
        }
        System.out.println(answer);
    }

    private static void logic(String s) {
        int laser = 0;
        if (s.equals("(")) {
            stack.push(s);
        } else {
            if (stack.peek().equals("(")) {
                stack.pop();
                stack.push("1");
            } else {
                while (!stack.peek().equals("(")) {
                    int pop = Integer.parseInt(stack.pop());
                    laser += pop;
                }
                stack.pop();
                answer += laser + 1;
                stack.push(String.valueOf(laser));
            }
        }
    }
}
