package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 *  10773번 제로
 */
public class Solution16 {
    public static void main(String[] args) throws IOException {
        int result = 0;
        Stack<String> stack = new Stack<>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int repeat = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < repeat; i++) {
            String readLine = bufferedReader.readLine();
            if (readLine.equals("0")) {
                stack.pop();
            } else {
                stack.push(readLine);
            }
        }
        for (String s : stack) {
            result += Integer.parseInt(s);
        }
        System.out.println(result);
    }
}
