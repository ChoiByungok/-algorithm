package baekjoon4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 3187 양치기 꿍 (Silver1)
 * bfs 탐색을 하여 울타리 안쪽의 양과 늑대의 수를 카운트해서
 * 그 울타리 안쪽에 늑대의 수가 양과 같거나 많으면 전체 늑대의 수만 증가시켜주고
 * 아니면 전체 양의 수를 증가시켜주면 된다.
 */
public class Solution44 {
    static int N, M;
    static char[][] map;
    static boolean[][] visited;
    static int sheep = 0;
    static int wolves = 0;
    static int[][] dir = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        map = new char[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String readLine = bufferedReader.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = readLine.charAt(j);
                if (map[i][j] == '#') {
                    visited[i][j] = true;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j]) {
                    bfs(i, j);
                }
            }
        }

        System.out.println(sheep + " " + wolves);
    }

    private static void bfs(int i, int j) {
        int s = 0;
        int w = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {i, j});
        visited[i][j] = true;
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int x = poll[0];
            int y = poll[1];

            if (map[x][y] == 'v') {
                w++;
            } else if (map[x][y] == 'k') {
                s++;
            }

            for (int k = 0; k < 4; k++) {
                int nx = x + dir[k][0];
                int ny = y + dir[k][1];
                if (nx >= 0 && nx < N && ny >= 0 && ny < M && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    queue.add(new int[] {nx, ny});
                }
            }
        }

        if (s > w) {
            sheep += s;
        } else {
            wolves += w;
        }
    }
}
