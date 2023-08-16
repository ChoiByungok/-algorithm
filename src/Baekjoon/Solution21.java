package Baekjoon;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * 11279 최대 힙
 */
public class Solution21 {
    public static void main(String[] args) throws IOException {
        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int repeat = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < repeat; i++) {
            String readLine = bufferedReader.readLine();
            if (readLine.equals("0")) {
                if (queue.isEmpty()) {
                    stringBuilder.append("0").append("\n");
                } else {
                    stringBuilder.append(queue.poll()).append("\n");
                }
            } else {
                queue.add(Integer.parseInt(readLine));
            }
        }
        System.out.println(stringBuilder);
    }
}
