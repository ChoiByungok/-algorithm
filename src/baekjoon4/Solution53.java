package baekjoon4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 31946 죽음의 등굣길 (Silver1)
 * 일반적인 bfs 문제랑 같은데 맨해튼 거리만큼 이동해야 한다는 조건이 있음
 * 이 로직 짜는게 생각보다 많이 어려웠음 그것만 해결한다면 쉽게 해결됨
 */
public class Solution53 {
    static int N, M, X;
    static boolean[][] map;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());
        M = Integer.parseInt(bufferedReader.readLine());
        map = new boolean[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = tokenizer.nextToken().equals("1");
            }
        }
        X = Integer.parseInt(bufferedReader.readLine());
        if (map[0][0] != map[N - 1][M - 1]) {
            System.out.println("DEAD");
        } else {
            System.out.println(bfs() ? "ALIVE" : "DEAD");
        }
    }

    static boolean bfs() {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {0, 0});
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int x = poll[0];
            int y = poll[1];
            if (x == N - 1 && y == M - 1) {
                return true;
            }
            int sx = Math.max(0, x - X);
            int ex = Math.min(N - 1, x + X);
            int sy = Math.max(0, y - X);
            int ey = Math.min(M - 1, y + X);

            for (int i = sx; i <= ex; i++) {
                for (int j = sy; j <= ey; j++) {
                    if (Math.abs(i - x) + Math.abs(j - y) <= X) {
                        if (!visited[i][j] && map[i][j] == map[x][y]) {
                            visited[i][j] = true;
                            queue.add(new int[]{i, j});
                        }
                    }
                }
            }
        }
        return false;
    }
}
