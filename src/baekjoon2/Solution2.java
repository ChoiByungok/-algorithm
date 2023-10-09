package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 2206 벽 부수고 이동하기
 */
public class Solution2 {
    static class Pos {
        int x;
        int y;
        int step;
        boolean breakWall;

        public Pos(int x, int y, int step, boolean breakWall) {
            this.x = x;
            this.y = y;
            this.step = step;
            this.breakWall = breakWall;
        }
    }
    static boolean[][] map;
    static boolean[][][] visited;
    static int N;
    static int M;
    static int answer = -1;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] split = bufferedReader.readLine().split(" ");
        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);
        map = new boolean[N][M];
        visited = new boolean[2][N][M];

        for (int i = 0; i < N; i++) {
            String readLine = bufferedReader.readLine();
            for (int j = 0; j < M; j++) {
                if (readLine.charAt(j) == '0') {
                    map[i][j] = true;
                }
            }
        }
        bfs();
        System.out.println(answer);
    }

    private static void bfs() {
        Queue<Pos> queue = new LinkedList<>();
        queue.add(new Pos(0, 0, 1, false));
        while (!queue.isEmpty()) {
            Pos now = queue.poll();
            int x = now.x;
            int y = now.y;
            int step = now.step;
            boolean breakWall = now.breakWall;

            if (!breakWall) {
                if (visited[0][x][y]) {
                    continue;
                } else {
                    visited[0][x][y] = true;
                }
            } else {
                if (visited[1][x][y]) {
                    continue;
                } else {
                    visited[1][x][y] = true;
                }
            }

            if (x == N - 1 && y == M - 1) {
                answer = step;
                break;
            }

            if (x + 1 < N) {
                if (map[x + 1][y]) { //벽이 아닐 시
                    if (breakWall && !visited[1][x + 1][y]) { //한번 벽을 부신적이 있어도 길이기 때문에 가능
                        queue.add(new Pos(x + 1, y, step + 1, true));
                    } else if (!breakWall && !visited[0][x + 1][y]) { //부신적이 없으면 그냥 가능
                        queue.add(new Pos(x + 1, y, step + 1, false));
                    }
                } else { //벽일 때
                    if (!breakWall && !visited[1][x + 1][y]) { //벽을 부순적이 없다면 가능
                        queue.add(new Pos(x + 1, y, step + 1, true));
                    }
                }
            }
            if (x - 1 >= 0) {
                if (map[x - 1][y]) {
                    if (breakWall && !visited[1][x - 1][y]) {
                        queue.add(new Pos(x - 1, y, step + 1, true));
                    } else if (!breakWall && !visited[0][x - 1][y]) {
                        queue.add(new Pos(x - 1, y, step + 1, false));
                    }
                } else {
                    if (!breakWall && !visited[1][x - 1][y]) {
                        queue.add(new Pos(x - 1, y, step + 1, true));
                    }
                }
            }

            if (y + 1 < M) {
                if (map[x][y + 1]) {
                    if (breakWall && !visited[1][x][y + 1]) {
                        queue.add(new Pos(x, y + 1, step + 1, true));
                    } else if (!breakWall && !visited[0][x][y + 1]) {
                        queue.add(new Pos(x, y + 1, step + 1, false));
                    }
                } else {
                    if (!breakWall && !visited[1][x][y + 1]) {
                        queue.add(new Pos(x, y + 1, step + 1, true));
                    }
                }
            }

            if (y - 1 >= 0) {
                if (map[x][y - 1]) {
                    if (breakWall && !visited[1][x][y - 1]) {
                        queue.add(new Pos(x, y - 1, step + 1, true));
                    } else if (!breakWall && !visited[0][x][y - 1]) {
                        queue.add(new Pos(x, y - 1, step + 1, false));
                    }
                } else {
                    if (!breakWall && !visited[1][x][y - 1]) {
                        queue.add(new Pos(x, y - 1, step + 1, true));
                    }
                }
            }
        }
    }
}
