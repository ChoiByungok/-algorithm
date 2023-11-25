package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 1158 요세푸스 문제
 * 이전에 풀었던 풍선 터뜨리기랑 유사한 문제인데 두 방향으로 움직이지 않고 한 방향으로만 움직이기 때문에 훨씬 간단한 문제
 */
public class Solution49 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] split = bufferedReader.readLine().split(" ");
        Queue<Integer> queue = new LinkedList<>();
        StringBuilder answer  = new StringBuilder();
        int N = Integer.parseInt(split[0]);
        int K = Integer.parseInt(split[1]);

        for (int i = 1; i <= N; i++) {
            queue.offer(i);
        }
        answer.append("<");
        while (queue.size() > 1) {
            for (int i = 0; i < K - 1; i++) {
                queue.offer(queue.poll());
            }
            answer.append(queue.poll()).append(", ");
        }
        answer.append(queue.poll()).append(">");
        System.out.println(answer);
    }
}
