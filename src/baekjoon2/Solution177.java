package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 10828 스택 (Silver4)
 * 스택 자료구조 특성만 알고 있으면 쉽게 풀수있는 문제
 */
public class Solution177 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        int N = Integer.parseInt(bufferedReader.readLine());
        Stack<String> stack = new Stack<>();
        while (N-- > 0) {
            String[] split = bufferedReader.readLine().split(" ");
            String command = split[0];
            switch (command) {
                case "push":
                    stack.push(split[1]);
                    break;
                case "pop":
                    if (stack.isEmpty()) {
                        answer.append("-1").append("\n");
                    } else {
                        answer.append(stack.pop()).append("\n");
                    }
                    break;
                case "size":
                    answer.append(stack.size()).append("\n");
                    break;
                case "empty":
                    answer.append(stack.isEmpty() ? 1 : 0).append("\n");
                    break;
                case "top":
                    if (stack.isEmpty()) {
                        answer.append("-1").append("\n");
                    } else {
                        answer.append(stack.peek()).append("\n");
                    }
                    break;
            }
        }
        System.out.println(answer);
    }
}
