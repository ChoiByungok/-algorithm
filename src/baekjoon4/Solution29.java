package baekjoon4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 17086 아기 상어 2 (Silver2)
 * 완전탐색과 bfs를 결합한 문제이다.
 * 나는 0인 부분을 전부탐색하여 1을 마주치는 순간 bfs를 나가게 했고 거기까지 도달하는 거리 수를 일일이 구해
 * 그중 최대값을 출력하는 식으로 풀었는데 유난히 시간이 짧은 사람들이 있어 제출코드를 살펴봤더니
 * 1인부분만 탐색하여 0까지 거리를 미리 구해 놓고 푸는것을 보았다.
 * 역시 생각의 전환이 있어야 효율이 좋다는것을 다시한번 상기시켜주는 코드였다.
 */
public class Solution29 {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[] dirX = new int[] {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dirY = new int[] {-1, 0, 1, -1, 1, -1, 0, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        int answer = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    answer = Math.max(answer, bfs(i, j));
                }
            }
        }
        System.out.println(answer);
    }

    static int bfs(int i, int j) {
        int answer = 0;
        visited = new boolean[N][M];
        visited[i][j] = true;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {i, j, 0});
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int x = poll[0];
            int y = poll[1];
            int step = poll[2];
            if (map[x][y] == 1) {
                answer = step;
                break;
            }

            for (int k = 0; k < 8; k++) {
                int nx = x + dirX[k];
                int ny = y + dirY[k];
                if (nx >= 0 && nx < N && ny >= 0 && ny < M && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    queue.add(new int[] {nx, ny, step + 1});
                }
            }
        }
        return answer;
    }
}
