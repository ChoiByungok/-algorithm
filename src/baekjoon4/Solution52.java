package baekjoon4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 17129 윌리암슨수액빨이딱따구리가 정보섬에 올라온 이유 (Silver1)
 * bfs 탐색을 하면서 현재좌표가 3 4 5 중 하나면 여기까지 오는데 최단거리를 출력하면되고
 * 도달하지 못하면 -1 을 출력하면 된다.
 */
public class Solution52 {
    static int N, M;
    static int startX, startY;
    static char[][] map;
    static boolean[][] visited;
    static int[][] dir = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
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
                char c = readLine.charAt(j);
                if (c == '1') {
                    visited[i][j] = true;
                    map[i][j] = '1';
                } else if (c == '2') {
                    startX = i;
                    startY = j;
                    map[i][j] = '0';
                    visited[i][j] = true;
                } else {
                    map[i][j] = c;
                }
            }
        }
        int answer = bfs();
        System.out.println(answer == -1 ? "NIE" : "TAK" + "\n" + answer);
    }

    static int bfs() {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {startX, startY, 0});
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int x = poll[0];
            int y = poll[1];
            int step = poll[2];
            if (map[x][y] >= '3') {
                return step;
            }

            for (int i = 0; i < 4; i++) {
                int nx = dir[i][0] + x;
                int ny = dir[i][1] + y;
                if (nx >= 0 && nx < N && ny >= 0 && ny < M && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    queue.add(new int[] {nx, ny, step + 1});
                }
            }
        }

        return -1;
    }
}
