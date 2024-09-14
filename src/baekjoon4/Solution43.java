package baekjoon4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 13903 출근 (Silver1)
 * 첫번째 줄 세로블록 즉 1로 시작하는 부분에서 시작해 주어진 이동경로를 통하여 마지막 줄에 도착했을 때
 * 최소 걸음수를 출력해야 하는문제이며 도달하지 못했을때는 -1을 출력해야 한다.
 * 나는 첫번째줄에 여러개의 출발지점이 있기에 첫번째줄을 반복문 돌려서 각각의 출발지점에서 bfs 탐색을 진행하였다
 * 그때마다 방문배열을 새로 생성하였고 새로 탐색했기에 시간초과가 발생하였다.
 * 이후 이 문제는 한번의 탐색에서 모든것을 끝내야 한다고 판단하여 첫째줄 모든 출발지점을 한번에 큐에 담아
 * 한번의 탐색만 진행하였고 그 결과 시간초과 없이 통과되었다.
 */
public class Solution43 {
    static int R, C, N;
    static int[][] map;
    static boolean[][] visited;
    static int[][] dir;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        R = Integer.parseInt(tokenizer.nextToken());
        C = Integer.parseInt(tokenizer.nextToken());
        map = new int[R][C];
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        N = Integer.parseInt(bufferedReader.readLine());
        dir = new int[N][2];
        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            int r = Integer.parseInt(tokenizer.nextToken());
            int c = Integer.parseInt(tokenizer.nextToken());
            dir[i][0] = r;
            dir[i][1] = c;
        }
        System.out.println(bfs());
    }

    static int bfs() {
        int answer = -1;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < C; i++) {
            if (map[0][i] == 1) {
                queue.add(new int[] {0, i, 0});
                visited[0][i] = true;
            }
        }
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int x = poll[0];
            int y = poll[1];
            int step = poll[2];

            if (x == R - 1) {
                return step;
            }

            for (int j = 0; j < N; j++) {
                int nx = x + dir[j][0];
                int ny = y + dir[j][1];

                if (nx >= 0 && nx < R && ny >= 0 && ny < C && !visited[nx][ny] && map[nx][ny] == 1) {
                    visited[nx][ny] = true;
                    queue.add(new int[] {nx, ny, step + 1});
                }
            }
        }
        return answer;
    }
}
