package baekjoon4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 16948 데스나이트 (Silver1)
 * 전형적인 bfs 문제
 */
public class Solution16 {
    static class Point {
        int x;
        int y;
        int step;

        public Point(int x, int y, int step) {
            this.x = x;
            this.y = y;
            this.step = step;
        }
    }
    static int[] dirX = new int[] {-2, -2, 0, 0, 2, 2};
    static int[] dirY = new int[] {-1, 1, -2, 2, -1, 1};
    static int N;
    static int r1, c1, r2, c2;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());
        visited = new boolean[N][N];
        String[] split = bufferedReader.readLine().split(" ");
        r1 = Integer.parseInt(split[0]);
        c1 = Integer.parseInt(split[1]);
        r2 = Integer.parseInt(split[2]);
        c2 = Integer.parseInt(split[3]);

        System.out.println(bfs());
    }

    static int bfs() {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(r1, c1, 0));
        visited[r1][c1] = true;
        while (!queue.isEmpty()) {
            Point poll = queue.poll();
            int x = poll.x;
            int y = poll.y;
            int step = poll.step;
            if (x == r2 && y == c2) {
                return step;
            }

            for (int i = 0; i < 6; i++) {
                int nx = x + dirX[i];
                int ny = y + dirY[i];

                if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    queue.add(new Point(nx, ny, step + 1));
                }
            }
        }
        return -1;
    }
}
