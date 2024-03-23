package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 2161 카드1 (Silver5)
 * 나는 덱을 이용해서 풀었는데 다시보니깐 큐를 이용해도 될듯
 */
public class Solution168 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        int N = Integer.parseInt(bufferedReader.readLine());
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            deque.add(i);
        }

        while (deque.size() != 1) {
            answer.append(deque.pollFirst()).append(" ");
            deque.addLast(deque.pollFirst());
        }
        answer.append(deque.pollFirst());
        System.out.println(answer);
    }
}
