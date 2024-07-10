package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 4179 불! (Gold3)
 * 지훈이가 불을 피해 나갈수 있으면 나가는데 걸리는 시간을 출력하고 아니면 IMPOSSIBLE 을 출력하면 되는 문제
 * 처음에는 지훈이를 먼저 이동시킨 다음 불을 이동시켜서 계산 했더니 53퍼쯤에서 틀렸다고 나옴
 * 그래서 불을 먼저 이동시키고 그다음 지훈이를 이동시켰더니 통과됨
 * 근데 그게 당연히 맞는게 원래는 불과 지훈이 동시에 이동하는데
 * 동시에 같은 지점에 도달했을때 지훈이는 불을 통과 할 수 없으니
 * 불이 먼저 이동하는게 맞음
 */
public class Solution77 {
    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static boolean[][] map;
    static Queue<Point> jiHoon = new LinkedList<>();
    static Queue<Point> fire = new LinkedList<>();
    static int N;
    static int M;
    static int[] dirX = new int[] {1, -1, 0, 0};
    static int[] dirY = new int[] {0, 0, 1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] split = bufferedReader.readLine().split(" ");
        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);
        map = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String readLine = bufferedReader.readLine();
            for (int j = 0; j < M; j++) {
                char c = readLine.charAt(j);
                if (c == '.') {
                    map[i][j] = true;
                }

                if (c == 'F') {
                    fire.add(new Point(i, j));
                }

                if (c == 'J') {
                    jiHoon.add(new Point(i, j));
                    map[i][j] = true;
                }
            }
        }
        int second = 0;
        boolean possible = false;
        label :
        while (!jiHoon.isEmpty()) {
            Queue<Point> jiHoonNext = new LinkedList<>();
            Queue<Point> fireNext = new LinkedList<>();
            while (!fire.isEmpty()) {
                Point poll = fire.poll();

                for (int i = 0; i < 4; i++) {
                    int dX = poll.x + dirX[i];
                    int dY = poll.y + dirY[i];

                    if (dX < 0 || dX >= N || dY < 0 || dY >= M) {
                        continue;
                    }

                    if (map[dX][dY]) {
                        map[dX][dY] = false;
                        fireNext.add(new Point(dX, dY));
                    }
                }
            }
            while (!jiHoon.isEmpty()) {
                Point jihoon = jiHoon.poll();
                for (int i = 0; i < 4; i++) {
                    int dX = jihoon.x + dirX[i];
                    int dY = jihoon.y + dirY[i];
                    if (dX < 0 || dX >= N || dY < 0 || dY >= M) {
                        possible = true;
                        second++;
                        break label;
                    }
                    if (map[dX][dY]) {
                        map[dX][dY] = false;
                        jiHoonNext.add(new Point(dX, dY));
                    }
                }
            }

            jiHoon = jiHoonNext;
            fire = fireNext;
            second++;
        }

        System.out.println(possible ? second : "IMPOSSIBLE");
    }
}
