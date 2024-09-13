package baekjoon4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 21938 영상처리 (Silver2)
 * 처음에 문제가 이해가 가지않아 조금 해멨던 문제
 * 픽셀의 평균을 구하라는데 알고보니 한 칸에 3개의 색이 주어지는 거였고 그 3개의 값의 평균을 매기라는 소리였음
 * 문제를 이해하고 나서는 익숙한 bfs 문제라 손쉽게 풀었다.
 */
public class Solution42 {
    static int N, M, T;
    static int[][] map;
    static boolean[][] visited;
    static int[][] dir = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public static void main(String[] args) throws IOException {
        int answer = 0;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < M; j++) {
                int R = Integer.parseInt(tokenizer.nextToken());
                int G = Integer.parseInt(tokenizer.nextToken());
                int B = Integer.parseInt(tokenizer.nextToken());
                int average = (R + G + B) / 3;
                map[i][j] = average;
            }
        }

        T = Integer.parseInt(bufferedReader.readLine());

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] >= T) {
                    map[i][j] = 255;
                } else {
                    map[i][j] = 0;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j] && map[i][j] != 0) {
                    bfs(i, j);
                    answer++;
                }
            }
        }
        System.out.println(answer);
    }

    private static void bfs(int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {i, j});
        visited[i][j] = true;
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int x = poll[0];
            int y = poll[1];

            for (int k = 0; k < 4; k++) {
                int nx = x + dir[k][0];
                int ny = y + dir[k][1];

                if (nx >= 0 && nx < N && ny >= 0 && ny < M && !visited[nx][ny] && map[nx][ny] == 255) {
                    visited[nx][ny] = true;
                    queue.add(new int[] {nx, ny});
                }
            }
        }
    }
}
