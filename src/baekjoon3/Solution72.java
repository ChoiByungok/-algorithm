package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 11060 점프 점프 (Silver2)
 * 분위기 전환용으로 풀어본 간단한 bfs문제
 */
public class Solution72 {
    static class JaeHwan {
        int cur;
        int step;

        public JaeHwan(int cur, int step) {
            this.cur = cur;
            this.step = step;
        }
    }
    static int[] maze;
    static boolean[] visited;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());
        maze = new int[N];
        visited = new boolean[N];
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < N; i++) {
            maze[i] = Integer.parseInt(tokenizer.nextToken());
        }
        System.out.println(bfs());
    }

    static int bfs() {
        int answer = -1;
        Queue<JaeHwan> queue = new LinkedList<>();
        queue.add(new JaeHwan(0, 0));
        visited[0] = true;
        while (!queue.isEmpty()) {
            JaeHwan jaeHwan = queue.poll();
            int cur = jaeHwan.cur;
            int step = jaeHwan.step;
            if (cur == N - 1) {
                answer = step;
                break;
            }

            int repeat = maze[cur];
            for (int i = 1; i <= repeat; i++) {
                int next = cur + i;
                if (next < N && !visited[next]) {
                    visited[next] = true;
                    queue.add(new JaeHwan(next, step + 1));
                }
            }
        }

        return answer;
    }
}
