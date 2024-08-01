package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 14497 주난의 난(難) (Gold4)
 * 문제를 처음 봤을땐 이게 왜 다익스트라이며 파동의 규칙도 파악이 되질않았다.
 * 그래서 다른사람의 해석을 참고했는데 맵 상에 1은 사람 0은 빈공간이라고 했을 때
 * 파동이 사람을 만나면 점프가 1증가하는 위치라고 생각하면 되고 0이면 그냥 진행하면 되는
 * 예전에 최소한의 벽 뚫고 목적지 도착하는 그 알고리즘과 같은 문제였던 것이다.
 */
public class Solution99 {
    static class Point {
        int x;
        int y;
        int step;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point(int x, int y, int step) {
            this.x = x;
            this.y = y;
            this.step = step;
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
        String[] NM = bufferedReader.readLine().split(" ");
        N = Integer.parseInt(NM[0]);
        M = Integer.parseInt(NM[1]);
        map = new boolean[N][M];
        visited = new boolean[N][M];
        int[] points = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Point junan = new Point(points[0] - 1, points[1] - 1, 0);
        Point criminal = new Point(points[2] - 1, points[3] - 1);

        for (int i = 0; i < N; i++) {
            String readLine = bufferedReader.readLine();
            for (int j = 0; j < M; j++) {
                char c = readLine.charAt(j);
                if (c == '1' || c == '#') {
                    map[i][j] = true;
                }
            }
        }
        System.out.println(bfs(junan, criminal));
    }

    static int bfs(Point start, Point criminal) {
        int answer = 0;
        Queue<Point> queue = new PriorityQueue<>(Comparator.comparingInt(p -> p.step));
        queue.add(start);
        visited[start.x][start.y] = true;
        while (!queue.isEmpty()) {
            Point now = queue.poll();
            int x = now.x;
            int y = now.y;
            int step = now.step;

            if (x == criminal.x && y == criminal.y) {
                answer = step;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = dirX[i] + x;
                int ny = dirY[i] + y;
                if (nx >= 0 && nx < N && ny >= 0 && ny < M && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    queue.add(map[nx][ny] ? new Point(nx, ny, step + 1) : new Point(nx, ny, step));
                }
            }
        }
        return answer;
    }
}
