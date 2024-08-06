package baekjoon4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 20046 Road Reconstruction (Gold4)
 * 이전에 풀어봤던 0 1 bfs문제 이렇게 가중치를 두고 우선순위 큐를 사용하는 bfs는 일종의 다익스트라로 치는 듯?
 * 문제 푸는데 어려움은 없었으며 주의해야 할 점은 시작점이 -1 일시 -1을 출력해야 한다는 점과
 * 시작점부터 길의 가중치가 1 혹은 2 이다 그러면 가중치를 1 혹은 2를 넣은 상태로 시작해야 한다.
 */
public class Solution4 {
    static class Road {
        int x;
        int y;
        int cost;

        public Road(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }

    static int N;
    static int M;
    static int[][] map;
    static boolean[][] visited;
    static int[] dirX = new int[]{1, -1, 0, 0};
    static int[] dirY = new int[]{0, 0, 1, -1};

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
            }
        }

        System.out.println(bfs());
    }


    static int bfs() {
        if (map[0][0] == -1) {
            return -1;
        }
        Queue<Road> queue = new PriorityQueue<>(Comparator.comparingInt(r -> r.cost));
        queue.add(new Road(0, 0, map[0][0]));
        while (!queue.isEmpty()) {
            Road poll = queue.poll();
            int x = poll.x;
            int y = poll.y;
            int cost = poll.cost;

            if (x == N - 1 && y == M - 1) {
                return cost;
            }

            for (int i = 0; i < 4; i++) {
                int nx = dirX[i] + x;
                int ny = dirY[i] + y;
                if (nx >= 0 && nx < N && ny >= 0 && ny < M && !visited[nx][ny] && map[nx][ny] != -1) {
                    visited[nx][ny] = true;
                    int num = map[nx][ny];
                    switch (num) {
                        case 0:
                            queue.add(new Road(nx, ny, cost));
                            break;
                        case 1:
                            queue.add(new Road(nx, ny, cost + 1));
                            break;
                        case 2:
                            queue.add(new Road(nx, ny, cost + 2));
                            break;
                    }
                }
            }
        }
        return -1;
    }
}

