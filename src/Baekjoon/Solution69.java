package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 4963 섬의 개수
 */
public class Solution69 {
    static class Land {
        int x;
        int y;

        public Land(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static Queue<Land> queue = new LinkedList<>();
    static boolean[][] map;
    static StringBuilder answer = new StringBuilder();
    static int w;
    static int h;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            int count = 0;
            String readLine = bufferedReader.readLine();
            if (readLine.equals("0 0")) {
                break;
            }
            String[] split = readLine.split(" ");
            w = Integer.parseInt(split[0]);
            h = Integer.parseInt(split[1]);
            map = new boolean[h][w];
            for (int i = 0; i < h; i++) {
                String[] split1 = bufferedReader.readLine().split(" ");
                for (int j = 0; j < w; j++) {
                    if (split1[j].equals("1")) {
                        map[i][j] = true;
                    }
                }
            }
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (map[i][j]) {
                        count++;
                        bfs(i,j);
                    }
                }
            }
            answer.append(count).append("\n");
        }
        System.out.println(answer);
    }

    private static void bfs(int i, int j) {
        queue.add(new Land(i, j));
        while (!queue.isEmpty()) {
            Land poll = queue.poll();
            int x = poll.x;
            int y = poll.y;

            if (!map[x][y]) {
                continue;
            } else {
                map[x][y] = false;
            }

            if (x + 1 < h && y + 1 < w) {
                queue.add(new Land(x + 1, y + 1)); // 오른쪽 아래 대각선
            }
            if (x - 1 >= 0 && y - 1 >= 0) {
                queue.add(new Land(x - 1, y - 1)); //왼쪽 위 대각선
            }
            if (x - 1 >= 0 && y + 1 < w) {
                queue.add(new Land(x - 1, y + 1)); //오른쪽 위 대각선
            }
            if (x + 1 < h && y - 1 >= 0) {
                queue.add(new Land(x + 1, y - 1)); // 왼쪽 아래 대각선
            }
            if (x + 1 < h) {
                queue.add(new Land(x + 1, y));
            }
            if (x - 1 >= 0) {
                queue.add(new Land(x - 1 ,y));
            }
            if (y + 1 < w) {
                queue.add(new Land(x, y + 1));
            }
            if (y - 1 >= 0) {
                queue.add(new Land(x, y - 1));
            }
        }
    }
}
