package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 1261 알고스팟 (Gold4)
 * 이전에 풀었던 미로만들기 (2665) 와 같은 문제
 * bfs 를 사용하되 우선순위 큐를 사용하여 효율을 늘려주었다.
 */
public class Solution94 {
    static class Spot {
        int x;
        int y;
        int count;

        public Spot(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
    static int N;
    static int M;
    static boolean[][] map;
    static boolean[][] visited;
    static int[] dirX = new int[] {1, -1, 0, 0};
    static int[] dirY = new int[] {0, 0, 1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] split = bufferedReader.readLine().split(" ");
        M = Integer.parseInt(split[0]);
        N = Integer.parseInt(split[1]);
        map = new boolean[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String readLine = bufferedReader.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = readLine.charAt(j) == '0';
            }
        }
        System.out.println(bfs());
    }

    static int bfs() {
        int answer = 0;
        Queue<Spot> queue = new PriorityQueue<>(Comparator.comparingInt(s -> s.count));
        queue.add(new Spot(0, 0, 0));
        visited[0][0] = true;
        while (!queue.isEmpty()) {
            Spot poll = queue.poll();
            int x = poll.x;
            int y = poll.y;
            int count = poll.count;
            if (x == N - 1 && y == M - 1) {
                answer = count;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dirX[i];
                int ny = y + dirY[i];
                if (nx >= 0 && nx < N && ny >= 0 && ny < M && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    queue.add(map[nx][ny] ? new Spot(nx, ny, count) : new Spot(nx, ny, count + 1));
                }
            }
        }

        return answer;
    }
}
