package baekjoon4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 18404 현명한 나이트 (Silver1)
 * 도달해야 하는 좌표를 입력 받을 때마다 bfs 를 실행시켜 이동횟수를 출력하였는데 시간초과가 발생하였다.
 * 어차피 나이트가 이동할 수 있는 좌표는 정해져있기때문에 bfs 를 실행하여 각 좌표마다 나이트가 몇번 이동해서 도착할 수 있는지
 * 미리 계산해놓은 뒤 해당 좌표를 입력받으면 그 좌표의 이동 값만 출력하였더니 통과되었다.
 * 고로 중복연산이 많았기때문에 시간초과가 났던것 말그대로 현명하게 이동을 해야 주어진 시간내에 통과되는 알고리즘이었다.
 */
public class Solution27 {
    static class Knight {
        int x;
        int y;
        int step;

        public Knight(int x, int y, int step) {
            this.x = x;
            this.y = y;
            this.step = step;
        }


    }
    static int N, M;
    static boolean[][] visited;
    static int[][] map;
    static int[] dirX = new int[] {-2, -2, -1, -1, 1, 1, 2, 2};
    static int[] dirY = new int[] {-1, 1, -2, 2, -2, 2, -1, 1};
    public static void main(String[] args) throws IOException {
        StringBuilder answer = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        map = new int[N][N];
        tokenizer = new StringTokenizer(bufferedReader.readLine());
        int X = Integer.parseInt(tokenizer.nextToken()) - 1;
        int Y = Integer.parseInt(tokenizer.nextToken()) - 1;
        bfs(X, Y);
        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            int A = Integer.parseInt(tokenizer.nextToken()) - 1;
            int B = Integer.parseInt(tokenizer.nextToken()) - 1;
            answer.append(map[A][B]).append(" ");
        }

        System.out.println(answer);
    }

    static void bfs(int startX, int startY) {
        Queue<Knight> queue = new LinkedList<>();
        queue.add(new Knight(startX, startY, 0));
        visited = new boolean[N][N];
        visited[startX][startX] = true;
        while (!queue.isEmpty()) {
            Knight poll = queue.poll();
            int x = poll.x;
            int y = poll.y;
            int step = poll.step;
            map[x][y] = step;

            for (int i = 0; i < 8; i++) {
                int nx = x + dirX[i];
                int ny = y + dirY[i];
                if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    queue.add(new Knight(nx, ny, step + 1));
                }
            }
        }
    }
}
