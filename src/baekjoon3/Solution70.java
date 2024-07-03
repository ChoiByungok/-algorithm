package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 14940 쉬운 최단거리 (Silver1)
 * 시작 지점부터 갈 수 있는 지점까지 거리를 출력하면 되는 문제
 * 근데 출력에서 조심해야 하는 부분이 있다.
 * 원래 갈 수 없는 길이라면 0을 출력하고 갈 수는 있지만 못가게 되는 곳은 -1을 출력해야 한다.
 * 이게 무슨 소리냐면 애초에 입력부터 0으로 주어진 곳은 0을 출력하면 되는데
 * 0으로 둘러싸여서 갈 수 없게 된 곳은 -1을 출력하라는 소리이다.
 * 처음에 이 조건을 보지 못하여 틀렸었다.
 */
public class Solution70 {
    static class Point {
        int x;
        int y;
        int step;
        public Point() {
        }

        public Point(int x, int y, int step) {
            this.x = x;
            this.y = y;
            this.step = step;
        }
    }

    static int[] dirX = new int[] {1, -1, 0, 0};
    static int[] dirY = new int[] {0 ,0, 1, -1};
    static int[][] map;
    static int[][] dist;
    static boolean[][] visited;
    static int n;
    static int m;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        map = new int[n][m];
        dist = new int[n][m];
        visited = new boolean[n][m];
        StringBuilder answer = new StringBuilder();
        Point start = new Point();
        for (int i = 0; i < n; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
                if (map[i][j] == 2) {
                    start.x = i;
                    start.y = j;
                    start.step = 0;
                }
            }
        }
        bfs(start);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0 && dist[i][j] == 0) {
                    answer.append(0).append(" ");
                } else if (map[i][j] == 1 && dist[i][j] == 0) {
                    answer.append(-1).append(" ");
                } else {
                    answer.append(dist[i][j]).append(" ");
                }
            }
            answer.append("\n");
        }

        System.out.println(answer);
    }

    static void bfs(Point start) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(start);
        visited[start.x][start.y] = true;
        while (!queue.isEmpty()) {
            Point now = queue.poll();
            int x = now.x;
            int y = now.y;
            int step = now.step;
            dist[x][y] = step;

            for (int i = 0; i < 4; i++) {
                int dx = dirX[i] + x;
                int dy = dirY[i] + y;
                if (dx < 0 || dx >= n || dy < 0 || dy >= m) {
                    continue;
                }
                if (!visited[dx][dy] && map[dx][dy] != 0) {
                    visited[dx][dy] = true;
                    queue.add(new Point(dx, dy, step + 1));
                }
            }
        }
    }
}
