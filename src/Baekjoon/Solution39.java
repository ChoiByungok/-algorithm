package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 18258 큐 2
 */
public class Solution39 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stringBuilder = new StringBuilder();
        Deque<String> deque = new ArrayDeque<>();

        int repeat = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < repeat; i++) {
            String[] split = bufferedReader.readLine().split(" ");
            switch (split[0]) {
                case "push" :
                    deque.add(split[1]);
                    break;
                case "pop" :
                    if (deque.isEmpty()) {
                        stringBuilder.append("-1").append("\n");
                    } else {
                        stringBuilder.append(deque.pollFirst()).append("\n");
                    }
                    break;
                case "size" :
                    stringBuilder.append(deque.size()).append("\n");
                    break;
                case "empty" :
                    if (deque.isEmpty()) {
                        stringBuilder.append("1").append("\n");
                    } else {
                        stringBuilder.append("0").append("\n");
                    }
                    break;
                case "front" :
                    if (deque.isEmpty()) {
                        stringBuilder.append("-1").append("\n");
                    } else {
                        stringBuilder.append(deque.peekFirst()).append("\n");
                    }
                    break;
                case "back" :
                    if (deque.isEmpty()) {
                        stringBuilder.append("-1").append("\n");
                    } else {
                        stringBuilder.append(deque.peekLast()).append("\n");
                    }
                    break;
            }
        }
        System.out.println(stringBuilder);
    }
}
