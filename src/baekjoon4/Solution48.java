package baekjoon4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 25416 빠른 숫자 탐색 (Silver2)
 */
public class Solution48 {
    static boolean[][] map = new boolean[5][5];
    static int[][] dir = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static int endX, endY;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 5; i++) {
            StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < 5; j++) {
                String token = tokenizer.nextToken();
                map[i][j] = token.equals("-1");
                if (token.equals("1")) {
                    endX = i;
                    endY = j;
                }
            }
        }
        String[] split = bufferedReader.readLine().split(" ");
        System.out.println(bfs(Integer.parseInt(split[0]), Integer.parseInt(split[1])));
    }
    static int bfs(int sx, int sy) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {sx, sy, 0});
        map[sx][sy] = true;
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int x = poll[0];
            int y = poll[1];
            int step = poll[2];
            if (x == endX && y == endY) {
                return step;
            }

            for (int i = 0; i < 4; i++) {
                int nx = dir[i][0] + x;
                int ny = dir[i][1] + y;
                if (nx >= 0 && nx < 5 && ny >= 0 && ny < 5 && !map[nx][ny]) {
                    map[nx][ny] = true;
                    queue.add(new int[] {nx, ny, step + 1});
                }
            }
        }
        return -1;
    }
}
