package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 1874 스택 수열
 */
public class Solution66 {
    static Stack<Integer> stack = new Stack<>();
    static int max = 1;
    static StringBuilder answer = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(bufferedReader.readLine());
            if (!logic(num)) {
                answer = new StringBuilder();
                answer.append("NO");
                break;
            }
        }

        System.out.println(answer);
    }

    private static boolean logic(int num) {
        if (num > max) {
            for (;max <= num; max++) {
                stack.add(max);
                answer.append("+").append("\n");
            }
            stack.pop();
            answer.append("-").append("\n");
            return true;
        } else if (num == max) {
            max++;
            answer.append("+").append("\n");
            answer.append("-").append("\n");
            return true;
        } else {
            if (stack.isEmpty()) {
                return false;
            } else {
                while (stack.peek() != num) {
                    stack.pop();
                    answer.append("-").append("\n");
                    if (stack.isEmpty()) {
                        return false;
                    }
                }
                stack.pop();
                answer.append("-").append("\n");
                return true;
            }
        }
    }
}
