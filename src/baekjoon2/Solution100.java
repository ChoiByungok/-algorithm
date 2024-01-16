package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 28066 타노스는 요세푸스가 밉다
 * 덱을 쓰면 되는 문제이다.
 * 덱의 사이즈가 K 보다 작아지면 반복문을 탈출 시키고
 * 그 반복문 동안 첫번쨰 요소는 맨 마지막에 보내고
 * K-1번 동안 덱의 맨 앞에 요소들을 삭제시키면 된다.
 * 반복문이 끝난 뒤에 덱의 맨 앞에 요소를 출력시켜주면 된다.
 */
public class Solution100 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] nk = bufferedReader.readLine().split(" ");
        Deque<Integer> deque = new ArrayDeque<>();
        int N = Integer.parseInt(nk[0]);
        int K = Integer.parseInt(nk[1]);
        for (int i = 1; i <= N; i++) {
            deque.offerLast(i);
        }
        while (deque.size() >= K) {
            deque.offerLast(deque.pollFirst());
            for (int i = 0; i < K - 1; i++) {
                deque.pollFirst();
            }
        }
        System.out.println(deque.peekFirst());
    }
}
