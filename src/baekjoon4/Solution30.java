package baekjoon4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 14562 태권왕 (Silver1)
 * 조건에 맞춰 bfs를 실행하면 될거같지만 무한루프가 돌게 된다.
 * 어떻게 하면 무한루프를 돌지 않게 될까
 * 방법은 S가 T 보다 작을때만 탐색을 진행하면 된다.
 * 나는 큐에 배열을 담을 때 조건을 걸었더니 바로 통과되었다.
 */
public class Solution30 {
    public static void main(String[] args) throws IOException {
        StringBuilder answer = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int C = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < C; i++) {
            StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
            int S = Integer.parseInt(tokenizer.nextToken());
            int T = Integer.parseInt(tokenizer.nextToken());
            answer.append(bfs(S, T)).append("\n");
        }
        System.out.println(answer);
    }

    static int bfs(int S, int T) {
        int answer = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {S, T, 0});
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int s = poll[0];
            int t = poll[1];
            int kick = poll[2];
            if (s == t) {
                answer = kick;
                break;
            }

            if (s * 2 <= t + 3) {
                queue.add(new int[] {s * 2, t + 3, kick + 1});
            }

            if (s + 1 <= t) {
                queue.add(new int[] {s + 1, t, kick + 1});
            }
        }
        return answer;
    }
}
