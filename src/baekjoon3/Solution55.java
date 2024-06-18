package baekjoon3;

import java.io.*;
import java.util.*;

/**
 * 1926 그림 (Silver1)
 * 흔한 bfs문제 근데 이번엔 남들이 하는것처럼 코드를 짜봤다.
 * 항상 if문 4개를 이용하여 로직을 수행했는데
 * 이번엔 낟들이 하는거처럼 배열을 하나 만들어서 반복문으로 처리해보았다.
 * 확실히 코드도 짧아지고 가독성도 좋은듯
 */
public class Solution55 {
    static int[] dirX = {1, -1, 0, 0};
    static int[] dirY = {0, 0, 1, -1};
    static int N;
    static int M;
    static int[][] map;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        int area = 0;
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    visited[i][j] = true;
                    count++;
                    area = Math.max(area, bfs(i, j));
                }
            }
        }
        System.out.println(count + "\n" + area);
    }
    static int bfs(int x, int y) {
        int area = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {x, y});
        while (!queue.isEmpty()) {
            area++;
            int[] poll = queue.poll();
            int nowX = poll[0];
            int nowY = poll[1];
            for (int i = 0; i < 4; i++) {
                int dx = nowX + dirX[i];
                int dy = nowY + dirY[i];

                if (dx >= N || dx < 0 || dy >= M || dy < 0) {
                    continue;
                }

                if (map[dx][dy] == 1 && !visited[dx][dy]) {
                    visited[dx][dy] = true;
                    queue.add(new int[] {dx, dy});
                }
            }
        }
        return area;
    }
}
