package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 2493 탑
 */
public class Solution68 {
    static class Top {
        int index;
        int height;

        public Top(int index, int height) {
            this.index = index;
            this.height = height;
        }
    }

    static Stack<Top> stack = new Stack<>();

    public static void main(String[] args) throws IOException {
        StringBuilder answer = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());

        for (int i = 0; i < N; i++) {
            Top top = new Top(i + 1, Integer.parseInt(tokenizer.nextToken()));
            boolean exit = false;
            if (stack.isEmpty()) {
                answer.append("0").append(" ");
                stack.push(top);
            } else {
                if (stack.peek().height < top.height) {
                    while (stack.peek().height < top.height) {
                        stack.pop();
                        if (stack.isEmpty()) {
                            answer.append("0").append(" ");
                            stack.push(top);
                            exit = true;
                            break;
                        }
                    }
                    if (exit) {
                        continue;
                    }
                }
                answer.append(stack.peek().index).append(" ");
                stack.push(top);
            }
        }
        System.out.println(answer);
    }
}
