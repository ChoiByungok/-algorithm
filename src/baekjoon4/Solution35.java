package baekjoon4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 13565 침투 (Silver2)
 * 기본적인 bfs 문제 첫번째 줄만 탐색하여 거기서 마지막 줄까지 갈 수 있다면 yes 를 출력하고
 * 아니면 no를 출력하면 된다.
 */
public class Solution35 {
    static int N, M;
    static boolean[][] map;
    static int[] dirX = new int[] {1, -1, 0, 0};
    static int[] dirY = new int[] {0, 0, 1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] split = bufferedReader.readLine().split(" ");
        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);
        map = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String readLine = bufferedReader.readLine();
            for (int j = 0; j < M; j++) {
                char c = readLine.charAt(j);
                map[i][j] = c == '1';
            }
        }

        boolean yes = false;
        for (int i = 0; i < M; i++) {
            if (!map[0][i]) {
                if (bfs(i)) {
                    yes = true;
                    break;
                }
            }
        }

        System.out.println(yes ? "YES" : "NO");
    }

    private static boolean bfs(int i) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {0, i});
        map[0][i] = true;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int x = poll[0];
            int y = poll[1];

            if (x == N - 1) {
                return true;
            }

            for (int j = 0; j < 4; j++) {
                int nx = x + dirX[j];
                int ny = y + dirY[j];

                if (nx >= 0 && nx < N && ny >= 0 && ny < M && !map[nx][ny]) {
                    map[nx][ny] = true;
                    queue.add(new int[] {nx, ny});
                }
            }
        }

        return false;
    }
}
