package Programmers3;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 게임 맵 최단거리 Lv.2
 * 백준에서 질리도록 풀었던 bfs 문제 손쉽게 해결함
 */
public class Solution29 {
    public int solution(int[][] maps) {
        int N = maps.length;
        int M = maps[0].length;
        int[][] dir = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        boolean[][] visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (maps[i][j] == 0) {
                    visited[i][j] = true;
                }
            }
        }
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {0, 0, 1});
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int x = poll[0];
            int y = poll[1];
            int step = poll[2];

            if (x == N - 1 && y == M - 1) {
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

    public static void main(String[] args) {
        int [][] maps = new int[][] {{1,0,1,1,1}, {1,0,1,0,1}, {1,0,1,1,1}, {1,1,1,0,1}, {0,0,0,0,1}};
        System.out.println(new Solution29().solution(maps));
    }
}
