package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 *  28279 덱2
 */
public class Solution43 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stringBuilder = new StringBuilder();
        Deque<String> deque = new ArrayDeque<>();

        int repeat = Integer.parseInt(bufferedReader.readLine());

        for (int i = 0; i < repeat; i++) {
            String[] split = bufferedReader.readLine().split(" ");
            switch (split[0]) {
                case "1" :
                    deque.addFirst(split[1]);
                    break;
                case "2" :
                    deque.addLast(split[1]);
                    break;
                case "3" :
                    if (deque.isEmpty()) {
                        stringBuilder.append("-1").append("\n");
                    } else {
                        stringBuilder.append(deque.pollFirst()).append("\n");
                    }
                    break;
                case "4" :
                    if (deque.isEmpty()) {
                        stringBuilder.append("-1").append("\n");
                    } else {
                        stringBuilder.append(deque.pollLast()).append("\n");
                    }
                    break;
                case "5" :
                    stringBuilder.append(deque.size()).append("\n");
                    break;
                case "6" :
                    if (deque.isEmpty()) {
                        stringBuilder.append("1").append("\n");
                    } else {
                        stringBuilder.append("0").append("\n");
                    }
                    break;
                case "7" :
                    if (deque.isEmpty()) {
                        stringBuilder.append("-1").append("\n");
                    } else {
                        stringBuilder.append(deque.peekFirst()).append("\n");
                    }
                    break;
                case "8" :
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
