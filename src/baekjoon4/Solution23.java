package baekjoon4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 21736 헌내기는 친구가 필요해 (Silver2)
 * 매일 한문제씩 풀어야 하는데 너무 어려운거 풀면 시간 오래걸리기때문에 쉬운거로 하나 때움
 * 다음에 시간 여유 있으면 어려운거 풀 예정
 */
public class Solution23 {
    static int N;
    static int M;
    static boolean[][] visited;
    static char[][] map;
    static int[] dirX = new int[] {1, -1, 0, 0};
    static int[] dirY = new int[] {0, 0, 1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] split = bufferedReader.readLine().split(" ");
        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);

        visited = new boolean[N][M];
        map = new char[N][M];
        int[] start = new int[0];

        for (int i = 0; i < N; i++) {
            String readLine = bufferedReader.readLine();
            for (int j = 0; j < M; j++) {
                char c = readLine.charAt(j);
                map[i][j] = c;
                visited[i][j] = c == 'X';

                if (map[i][j] == 'I') {
                    visited[i][j] = true;
                    start = new int[] {i, j};
                }
            }
        }
        bfs(start);
    }

    static void bfs(int[] start) {
        int count = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int x = poll[0];
            int y = poll[1];

            if (map[x][y] == 'P') {
                count++;
            }

            for (int i = 0; i < 4; i++) {
                int nx = dirX[i] + x;
                int ny = dirY[i] + y;

                if (nx >= 0 && nx < N && ny >= 0 && ny < M && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    queue.add(new int[] {nx, ny});
                }
            }
        }
        if (count > 0) {
            System.out.println(count);
        } else {
            System.out.println("TT");
        }
    }
}
