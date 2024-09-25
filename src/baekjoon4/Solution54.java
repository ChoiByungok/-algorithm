package baekjoon4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 30702 국기 색칠하기 (Silver1)
 * 기존의 깃발을 bfs 탐색하여 비교할 깃발의 색깔로 바꿔준다
 * 다 바꾼뒤 바꾼 깃발과 비교할 깃발을 비교하여 같으면 YES 다르면 NO 를 출력해준다.
 */
public class Solution54 {
    static int N, M;
    static char[][] A;
    static char[][] B;
    static int[][] dir = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] split = bufferedReader.readLine().split(" ");
        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);

        A = new char[N][M];
        B = new char[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String readLine = bufferedReader.readLine();
            for (int j = 0; j < M; j++) {
                A[i][j] = readLine.charAt(j);
            }
        }

        for (int i = 0; i < N; i++) {
            String readLine = bufferedReader.readLine();
            for (int j = 0; j < M; j++) {
                B[i][j] = readLine.charAt(j);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j]) {
                    bfs(i, j);
                }
            }
        }
        System.out.println(compare() ? "YES" : "NO");
    }

    private static void bfs(int i, int j) {
        char original = A[i][j];
        char change = B[i][j];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {i, j});
        visited[i][j] = true;
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int x = poll[0];
            int y = poll[1];

            A[x][y] = change;

            for (int k = 0; k < 4; k++) {
                int nx = x + dir[k][0];
                int ny = y + dir[k][1];

                if (nx >= 0 && nx < N && ny >= 0 && ny < M && !visited[nx][ny] && A[nx][ny] == original) {
                    visited[nx][ny] = true;
                    queue.add(new int[] {nx, ny});
                }
            }
        }
    }

    static boolean compare() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (A[i][j] != B[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
