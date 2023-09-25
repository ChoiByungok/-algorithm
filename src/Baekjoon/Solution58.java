package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 2178 미로탐색
 */
public class Solution58 {
    static class Coordinate {
        int n;
        int k;
        int step;

        public Coordinate(int x, int y, int step) {
            this.n = x;
            this.k = y;
            this.step = step;
        }
    }
    static int answer;
    static Queue<Coordinate> queue = new LinkedList<>();
    static boolean[][] map;
    static int N;
    static int K;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] split = bufferedReader.readLine().split(" ");
        N = Integer.parseInt(split[0]);
        K = Integer.parseInt(split[1]);
        map = new boolean[N][K];

        for (int i = 0; i < N; i++) {
            String[] split1 = bufferedReader.readLine().split("");
            for (int j = 0; j < K; j++) {
                if (split1[j].equals("1")) {
                    map[i][j] = true;
                }
            }
        }

        search();
        System.out.println(answer);
    }

    static void search() {
        queue.add(new Coordinate(0, 0, 1));
        while (!queue.isEmpty()) {
            Coordinate now = queue.poll();
            int n = now.n;
            int k = now.k;
            int step = now.step;
            if (n < 0 || k > K - 1 || k < 0 || n > N - 1 || !map[n][k]) {
                continue;
            } else {
                map[n][k] = false;
            }
            if (n == N - 1 && k == K - 1) {
                answer = step;
                break;
            }

            queue.add(new Coordinate(n + 1, k, step + 1));
            queue.add(new Coordinate(n - 1, k, step + 1));
            queue.add(new Coordinate(n, k + 1, step + 1));
            queue.add(new Coordinate(n, k - 1, step + 1));

        }
    }

}
