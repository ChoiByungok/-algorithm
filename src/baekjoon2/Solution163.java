package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 1835 카드 (Silver4)
 * 문제 이해하는게 조금 어려웠지만 이해만 하면 쉬운문제
 * 덱을 이용하여 문제에서 주어진 설명의 역순대로 하면 된다.
 */
public class Solution163 {
    public static void main(String[] args) throws IOException {
        StringBuilder answer = new StringBuilder();
        Deque<Integer> deque = new ArrayDeque<>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        deque.add(N);
        while (N-- > 1) {
            deque.addFirst(N);
            for (int i = 0; i < N; i++) {
                deque.addFirst(deque.pollLast());
            }
        }
        for (Integer integer : deque) {
            answer.append(integer).append(" ");
        }
        System.out.println(answer);
    }
}
