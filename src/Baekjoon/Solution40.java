package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 12789 도키도키 간식드리미
 */
public class Solution40 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        Queue<Integer> queue = new ArrayDeque<>(N);
        Stack<Integer> stack = new Stack<>();
        List<Integer> list = new ArrayList<>();
        int start = 1;

        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        while (tokenizer.hasMoreTokens()) {
            int ticket = Integer.parseInt(tokenizer.nextToken());
            queue.add(ticket);
        }

        while (!queue.isEmpty()) {
            if (queue.peek() == start) {
                list.add(queue.poll());
                start++;
            } else {
                stack.add(queue.poll());
            }
            while (!stack.isEmpty()) {
                if (stack.peek() == start) {
                    list.add(stack.pop());
                    start++;
                } else {
                    break;
                }
            }
        }

        System.out.println(list.size() == N ? "Nice" : "Sad");
    }
}
