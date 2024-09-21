package baekjoon4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 6146 신아를 만나러 (Silver1)
 * 문제 자체는 그저 단순한 bfs 문제같지만 일반 2차원 배열로는 도달할 수 없는 음의 좌표가 존재하는 문제이다.
 * 음의 좌표를 방문처리 할 수 없으므로 무식하게 범위만큼의 2차원방문배열을 선언하여 해결하였다.
 * -500 ~ 500 까지가 좌표 범위랬으니 [1001][1001] 크기의 배열을 만들고 0,0 좌표를 500, 500이라 치고 해결한 것이다.
 * 입력받은 물응덩이가 존재하는 좌표는 입력받으면서 500을 더해주면 알아서 음의 좌표도 처리할 수 있다.
 * 그렇게 500,500 을 0,0 이라 치고 해결하였다. 나만 이렇게 무식하게 푼줄알았는데
 * 대다수의 사람이 이렇게 푼거 보고 사람 생각하는건 거기서 거기구나 알게되었다.
 */
public class Solution50 {
    static int endX, endY;
    static int[][] dir = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static boolean[][] visited = new boolean[1001][1001];
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        endX = Integer.parseInt(tokenizer.nextToken()) + 500;
        endY = Integer.parseInt(tokenizer.nextToken()) + 500;
        int N = Integer.parseInt(tokenizer.nextToken());
        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            int A = Integer.parseInt(tokenizer.nextToken()) + 500;
            int B = Integer.parseInt(tokenizer.nextToken()) + 500;
            visited[A][B] = true;
        }
        System.out.println(bfs());
    }
    static int bfs() {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {500, 500, 0});
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int x = poll[0];
            int y = poll[1];
            int step = poll[2];

            if (x == endX && y == endY) {
                return step;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dir[i][0];
                int ny = y + dir[i][1];
                if (nx >= 0 && nx <= 1000 && ny >= 0 && ny <= 1000 && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    queue.add(new int[] {nx, ny, step + 1});
                }
            }
        }
        return -1;
    }
}
