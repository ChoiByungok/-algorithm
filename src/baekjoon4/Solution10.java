package baekjoon4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 3184 양 (Silver1)
 * 전형적인 bfs 문제
 * 울타리(#)가 아닌곳을 탐색을 진행햐여 그 안에 양과 늑대의 수를 카운트함
 * 그래서 양이 크면 양의 숫자와 0을 반환하고 그 외에는 0과 늑대의 수를 반환해서
 * 반환값들을 전부 더한 뒤 출력해주면 됨
 */
public class Solution10 {
    static int N;
    static int M;
    static char[][] map;
    static boolean[][] visited;
    static int[] dirX = new int[] {1, -1, 0, 0};
    static int[] dirY = new int[] {0, 0, 1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] split = bufferedReader.readLine().split(" ");
        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);
        map = new char[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String readLine = bufferedReader.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = readLine.charAt(j);
            }
        }
        int sheep = 0;
        int wolf = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != '#' && !visited[i][j]) {
                    visited[i][j] = true;
                    int[] dfs = dfs(i, j);
                    sheep += dfs[0];
                    wolf += dfs[1];
                }
            }
        }

        System.out.println(sheep + " " + wolf);
    }

    static int[] dfs(int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {i, j});
        int sheep = 0;
        int wolf = 0;
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int x = poll[0];
            int y = poll[1];
            if (map[x][y] == 'o') {
                sheep++;
            }
            if (map[x][y] == 'v') {
                wolf++;
            }

            for (int k = 0; k < 4; k++) {
                int nx = dirX[k] + x;
                int ny = dirY[k] + y;
                if (nx >= 0 && nx < N && ny >= 0 && ny < M && !visited[nx][ny] && map[nx][ny] != '#') {
                    visited[nx][ny] = true;
                    queue.add(new int[] {nx, ny});
                }
            }
        }
        return sheep > wolf ? new int[] {sheep, 0} : new int[] {0, wolf};
    }
}
