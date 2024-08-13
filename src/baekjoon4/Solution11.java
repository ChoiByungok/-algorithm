package baekjoon4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 1303 전쟁 - 전투 (Silver1)
 * bfs 탐색을 통해 각 각 b 와 w 의 면적을 구한 뒤
 * 그 면적들의 제곱의 값을 출력해주면 된다.
 */
public class Solution11 {
    static int N;
    static int M;
    static int[] dirX = new int[] {1, -1, 0, 0};
    static int[] dirY = new int[] {0, 0, 1, -1};
    static char[][] map;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] split = bufferedReader.readLine().split(" ");
        int blue = 0;
        int white = 0;
        M = Integer.parseInt(split[0]);
        N = Integer.parseInt(split[1]);
        map = new char[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String readLine = bufferedReader.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = readLine.charAt(j);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j]) {
                    visited[i][j] = true;
                    if (map[i][j] == 'W') {
                        white += bfs(i, j, map[i][j]);
                    } else {
                        blue += bfs(i, j, map[i][j]);
                    }
                }
            }
        }

        System.out.println(white + " " + blue);
    }

    static int bfs(int i, int j, char c) {
        int area = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {i, j});
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int x = poll[0];
            int y = poll[1];
            area++;

            for (int k = 0; k < 4; k++) {
                int nx = dirX[k] + x;
                int ny = dirY[k] + y;

                if (nx >= 0 && nx < N && ny >= 0 && ny < M && !visited[nx][ny] && map[nx][ny] == c) {
                    visited[nx][ny] = true;
                    queue.add(new int[] {nx, ny});
                }
            }
        }
        return area * area;
    }
}
