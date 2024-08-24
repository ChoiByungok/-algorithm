package baekjoon4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 6593 상범 빌딩 (Gold5)
 * 일반적인 bfs 문제였지만 차원이 하나 더있어서 생각보다 많은고민을 해야했던문제
 * 그거 빼고는 풀만했다.
 */
public class Solution22 {
    static boolean[][][] map;
    static int[] start;
    static int[] end;
    static int L;
    static int R;
    static int C;
    static int[] dirH = new int[] {1, -1, 0, 0, 0, 0};
    static int[] dirX = new int[] {0, 0, 1, -1, 0, 0};
    static int[] dirY = new int[] {0, 0, 0, 0, 1, -1};
    static StringBuilder answer = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            int[] LRC = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            L = LRC[0];
            R = LRC[1];
            C = LRC[2];
            if (L == 0 && R == 0 && C == 0) {
                break;
            }
            map = new boolean[L][R][C];

            for (int i = 0; i < L; i++) {
                for (int j = 0; j < R; j++) {
                    String readLine  = bufferedReader.readLine();
                    for (int k = 0; k < C; k++) {
                        char c = readLine.charAt(k);
                        if (c == 'S') {
                            start = new int[] {i, j, k};
                        }

                        if (c == 'E') {
                            end = new int[] {i, j, k};
                        }

                        map[i][j][k] = c == '#';
                    }
                }
                bufferedReader.readLine();
            }
            bfs();
        }
        System.out.println(answer);
    }

    static void bfs() {
        int exit = -1;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {start[0], start[1], start[2], 0});
        map[start[0]][start[1]][start[2]] = true;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int h = poll[0];
            int x = poll[1];
            int y = poll[2];
            int step = poll[3];

            if (h == end[0] && x == end[1] && y == end[2]) {
                exit = step;
                break;
            }

            for (int i = 0; i < 6; i++) {
                int nh = h + dirH[i];
                int nx = x + dirX[i];
                int ny = y + dirY[i];
                if (nh >= 0 && nh < L && nx >= 0 && nx < R && ny >= 0 && ny < C && !map[nh][nx][ny]) {
                    map[nh][nx][ny] = true;
                    queue.add(new int[] {nh, nx, ny, step + 1});
                }
            }
        }
        if (exit == -1) {
            answer.append("Trapped!").append("\n");
        } else {
            answer.append("Escaped in ").append(exit).append(" minute(s).").append("\n");
        }
    }
}
