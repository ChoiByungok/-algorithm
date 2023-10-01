package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 2583 영역 구하기
 */
public class Solution64 {
    static class Location {
        int x;
        int y;

        public Location(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int M;
    static int N;
    static List<Integer> area = new ArrayList<>();
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        init();
        logic();
        answer();
    }
    private static void init() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        M = Integer.parseInt(tokenizer.nextToken());
        N = Integer.parseInt(tokenizer.nextToken());
        int k = Integer.parseInt(tokenizer.nextToken());

        visited = new boolean[M][N];
        for (int i = 0; i < k; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            int c = Integer.parseInt(tokenizer.nextToken());
            int d = Integer.parseInt(tokenizer.nextToken());
            for (int j = b; j < d; j++) {
                for (int l = a; l < c; l++) {
                    visited[j][l] = true;
                }
            }
        }
    }

    private static void logic() {
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    bfs(i, j);
                }
            }
        }
    }

    private static void bfs(int i, int j) {
        int count = 0;
        Queue<Location> queue = new LinkedList<>();
        queue.add(new Location(i, j));
        while (!queue.isEmpty()) {
            Location now = queue.poll();
            int x = now.x;
            int y = now.y;

            if (visited[x][y]) {
                continue;
            } else {
                visited[x][y] = true;
            }

            if (x + 1 < M && !visited[x + 1][y]) {
                queue.add(new Location(x + 1, y));
            }
            if (x - 1 >= 0 && !visited[x - 1][y]) {
                queue.add(new Location(x - 1, y));
            }
            if (y + 1 < N && !visited[x][y + 1]) {
                queue.add(new Location(x, y + 1));
            }
            if (y - 1 >= 0 && !visited[x][y - 1]) {
                queue.add(new Location(x, y - 1));
            }
            count++;
        }
        area.add(count);
    }

    private static void answer() {
        StringBuilder answer = new StringBuilder();
        Collections.sort(area);
        answer.append(area.size()).append("\n");
        for (Integer integer : area) {
            answer.append(integer).append(" ");
        }
        System.out.println(answer);
    }
}
