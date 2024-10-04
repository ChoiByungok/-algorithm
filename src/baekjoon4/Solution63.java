package baekjoon4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 27211 도넛 행성 (Gold5)
 * 상하좌우 탐색하여 맵 내에 총 몇개의 그룹이 존재하는지 파악하는문제이다.
 * 하지만 이 문제가 일반적인 bfs 탐색 문제와 다른점은
 * 토러스형태의 맵 구조를 띄고있기때문이다.
 * 즉 좌표가 맵을 벗어나면 반대쪽으로 가게 해야한다.
 * 좌표가 맵을 벗어났을때 의도와 맞게 잘 조정한다면 막힘없이 풀 수있는 문제이다.
 */
public class Solution63 {
    static int N, M;
    static boolean[][] map;
    static int[][] dir = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        map = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = tokenizer.nextToken().equals("1");
            }
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!map[i][j]) {
                    map[i][j] = true;
                    bfs(i, j);
                    answer++;
                }
            }
        }
        System.out.println(answer);
    }

    private static void bfs(int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {i, j});
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int x = poll[0];
            int y = poll[1];

            for (int k = 0; k < 4; k++) {
                int nx = x + dir[k][0];
                int ny = y + dir[k][1];

                if (nx == N) {
                    nx = 0;
                }

                if (nx == -1) {
                    nx = N - 1;
                }

                if (ny == M) {
                    ny = 0;
                }

                if (ny == -1) {
                    ny = M - 1;
                }

                if (!map[nx][ny]) {
                    map[nx][ny] = true;
                    queue.add(new int[] {nx, ny});
                }
            }
        }
    }
}
