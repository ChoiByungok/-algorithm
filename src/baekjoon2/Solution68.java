package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

/**
 * 20301 반전 요세푸스
 * 카운트를 증가시켜 해당 카운트가 k 의 배수가 되면 boolean 타입의 변수를 반전시켜
 * 정방향인지 역방향인지 구분해준다. 나는 true일때 정방향 false일때 역방향으로 구현하였다.
 */
public class Solution68 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] nmk = bufferedReader.readLine().split(" ");
        int n = Integer.parseInt(nmk[0]);
        int m = Integer.parseInt(nmk[1]);
        int k = Integer.parseInt(nmk[2]);
        boolean reverse = true;
        int count = 0;
        StringBuilder answer = new StringBuilder();
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            deque.add(i);
        }

        while (!deque.isEmpty()) {
            if (reverse) {
                for (int i = 0; i < m - 1; i++) {
                    deque.offerLast(deque.pollFirst());
                }
                answer.append(deque.pollFirst()).append("\n");
            } else {
                for (int i = 0; i < m; i++) {
                    deque.offerFirst(deque.pollLast());
                }
                answer.append(deque.pollFirst()).append("\n");
            }
            count++;
            if (count % k == 0) {
                reverse = !reverse;
            }
        }

        System.out.println(answer);
    }
}
