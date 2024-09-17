package baekjoon4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 1600 말이 되고픈 원숭이 (Gold3)
 * 원숭이가 K 번만큼만 말처럼 움직일 수 있고 그 외에는 상하좌우로밖에 못움직인다
 * K번 점프하는 경우의 수를 모두 고려해야 하니 점프 할 수 있는 수만큼 3차원 배열로 방문처리를 해야 한다.
 * 해당 위치에서 말처럼 점프가 가능하고 아직 카운트가 가능하면 말처럼 이동을 한다.
 * 그리고 새로운 차원의 배열로 이동시켜 새로 방문처리를 해준다.
 * 그렇게 목적지에 도착하면 최소 이동거리를 제출하고
 * 목적지에 도착하지 못하면 -1을 출력한다.
 */
public class Solution46 {
    static class Monkey {
        int x;
        int y;
        int step;
        int jump;

        public Monkey(int x, int y, int step, int jump) {
            this.x = x;
            this.y = y;
            this.step = step;
            this.jump = jump;
        }
    }
    static int N, M, K;
    static boolean[][][] visited;
    static int[][] sDir = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static int[][] jDir = new int[][] {{-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}};
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(bufferedReader.readLine()) + 1;
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        M = Integer.parseInt(tokenizer.nextToken());
        N = Integer.parseInt(tokenizer.nextToken());
        visited = new boolean[K][N][M];

        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < M; j++) {
                String token = tokenizer.nextToken();
                for (int k = 0; k < K; k++) {
                    visited[k][i][j] = token.equals("1");
                }
            }
        }
        System.out.println(bfs());
    }

    static int bfs() {
        Queue<Monkey> queue = new LinkedList<>();
        queue.add(new Monkey(0, 0, 0, 0));
        visited[0][0][0] = true;
        while (!queue.isEmpty()) {
            Monkey monkey = queue.poll();
            int x = monkey.x;
            int y = monkey.y;
            int step = monkey.step;
            int jump = monkey.jump;

            if (x == N - 1 && y == M - 1) {
                return step;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + sDir[i][0];
                int ny = y + sDir[i][1];

                if (nx >= 0 && nx < N && ny >= 0 && ny < M && !visited[jump][nx][ny]) {
                    visited[jump][nx][ny] = true;
                    queue.add(new Monkey(nx, ny, step + 1, jump));
                }
            }

            if (jump < K - 1) {
                for (int i = 0; i < 8; i++) {
                    int nx = x + jDir[i][0];
                    int ny = y + jDir[i][1];
                    if (nx >= 0 && nx < N && ny >= 0 && ny < M && !visited[jump + 1][nx][ny]) {
                        visited[jump + 1][nx][ny] = true;
                        queue.add(new Monkey(nx, ny, step + 1, jump + 1));
                    }
                }
            }
        }

        return -1;
    }
}
