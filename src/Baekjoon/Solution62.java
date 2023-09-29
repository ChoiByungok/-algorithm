package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 2468 안전 영역
 */
public class Solution62 {
    static class Land {
        int x;
        int y;

        public Land(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static Queue<Land> queue = new LinkedList<>();
    static int[][] height;
    static boolean[][] visited;
    static boolean[][] waterLevel;
    static int N;
    static int answer = 1;
    static int count = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());
        height = new int[N][N];
        waterLevel = new boolean[N][N];
        int max = 0;
        int min = 101;

        for (int i = 0; i < N; i++) {
            String[] split = bufferedReader.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                int parseInt = Integer.parseInt(split[j]);
                if (parseInt > max) {
                    max = parseInt;
                }
                if (parseInt < min) {
                    min = parseInt;
                }
                height[i][j] = parseInt;
            }
        }
        rainySeason(min, max);
        System.out.println(answer);
    }

    private static void rainySeason(int min, int max) {
        for (int i = min - 1; i < max; i++) {
            count = 0;
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    if (i < height[j][k]) {
                        waterLevel[j][k] = true;
                    }
                }
            }
            visited = waterLevel;
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    if (visited[j][k]) {
                        bfs(j, k);
                    }
                }
            }
            answer = Math.max(answer, count);
        }
    }

    private static void bfs(int x, int y) {
        count++;
        queue.add(new Land(x, y));
        while (!queue.isEmpty()) {
            Land now = queue.poll();
            int nowX = now.x;
            int nowY = now.y;

            if (!visited[nowX][nowY]) {
                continue;
            } else {
                visited[nowX][nowY] = false;
            }

            if (nowX + 1 < N) {
                queue.add(new Land(nowX + 1, nowY));
            }
            if (nowX - 1 >= 0) {
                queue.add(new Land(nowX - 1, nowY));
            }
            if (nowY + 1 < N) {
                queue.add(new Land(nowX, nowY + 1));
            }
            if (nowY - 1 >= 0) {
                queue.add(new Land(nowX, nowY - 1));
            }
        }
    }
}
