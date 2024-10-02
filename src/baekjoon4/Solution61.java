package baekjoon4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 30024 옥수수밭 (Gold4)
 * 문제를 처음 접했을땐 어떻게 접근해야 할지 가늠이 가지않았지만
 * 알고리즘 분류를 보고 바로 어떻게 해야할지 감이 잡혔다.
 * 우선순위 큐를 이용하면 쉽게 풀 수있다.
 * 하지만 이런 힌트 없이 풀수있어야 진정한 실력이라 생각하기에 아쉽다고 생각한다.
 */
public class Solution61 {
    static class Corn {
        int x;
        int y;
        int value;

        public Corn(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }
    }
    static int N, M, K;
    static int[][] map;
    static boolean[][] visited;
    static int[][] dir = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static Queue<Corn> queue = new PriorityQueue<>((o1, o2) -> o2.value - o1.value);
    static StringBuilder answer = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
                if (i == 0 || j == 0 || i == N - 1 || j == M - 1) {
                    visited[i][j] = true;
                    queue.add(new Corn(i, j, map[i][j]));
                }
            }
        }
        K = Integer.parseInt(bufferedReader.readLine());
        bfs();
        System.out.println(answer);
    }

    static void bfs() {
        int count = 0;
        while (!queue.isEmpty()) {
            Corn poll = queue.poll();
            count++;
            int x = poll.x;
            int y = poll.y;
            answer.append(x + 1).append(" ").append(y + 1).append("\n");
            if (count >= K) {
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dir[i][0];
                int ny = y + dir[i][1];
                if (nx >= 0 && nx < N && ny >= 0 && ny < M && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    queue.add(new Corn(nx, ny, map[nx][ny]));
                }
            }
        }
    }
}
