package baekjoon4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 11123 양 한마리... 양 두마리... (Silver2)
 */
public class Solution47 {
    static int N, M;
    static boolean[][] map;
    static int[][] dir = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public static void main(String[] args) throws IOException {
        StringBuilder answer = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bufferedReader.readLine());

        for (int i = 0; i < T; i++) {
            int count = 0;
            String[] split = bufferedReader.readLine().split(" ");
            N = Integer.parseInt(split[0]);
            M = Integer.parseInt(split[1]);
            map = new boolean[N][M];
            for (int j = 0; j < N; j++) {
                String readLine = bufferedReader.readLine();
                for (int k = 0; k < M; k++) {
                    char c = readLine.charAt(k);
                    map[j][k] = c == '.';
                }
            }
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if (!map[j][k]) {
                        bfs(j, k);
                        count++;
                    }
                }
            }
            answer.append(count).append("\n");
        }
        System.out.println(answer);
    }

    static void bfs(int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {i, j});
        map[i][j] = true;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int x = poll[0];
            int y = poll[1];

            for (int k = 0; k < 4; k++) {
                int nx = x + dir[k][0];
                int ny = y + dir[k][1];
                if (nx >= 0 && nx < N && ny >= 0 && ny < M && !map[nx][ny]) {
                    map[nx][ny] = true;
                    queue.add(new int[] {nx, ny});
                }
            }
        }
    }
}
