package baekjoon4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 22352 항체인식 (Gold5)
 * 백신을 놓기 전과 후의 데이터를 주고 과연 이 데이터만 보고 제대로된 백신인지 아닌지 판별하는 문제이다.
 * 변경전 데이터와 변경후 데이터를 비교하다가 숫자가 다른 영역이 나온다면 탐색을 돌려서
 * 변경전 데이터를 변경후 데이터로 바꿔준다. 한번 탐색을 실시했으면 그 이후에는 탐색을 진행하지 않는다.
 * 왜나햐면 백신을 한번만 놨기 때문이다. 그리고 내가 바꿔준 데이터와 변경후 데이터를 비교하여
 * 같으면 YES 다르면 NO 를 출력시켜주면 된다.
 */
public class Solution37 {
    static int N, M;
    static int[][] original;
    static int[][] update;
    static int[][] dir = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer  = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        original = new int[N][M];
        update = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < M; j++) {
                original[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < M; j++) {
                update[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        label:
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int ori = original[i][j];
                int up = update[i][j];

                if (ori != up) {
                    bfs(i, j, ori, up);
                    break label;
                }
            }
        }

        System.out.println(compare() ? "YES" : "NO");
    }

    static void bfs(int i, int j, int ori, int up) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {i, j});
        visited[i][j] = true;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int x = poll[0];
            int y = poll[1];

            for (int k = 0; k < 4; k++) {
                int nx = dir[k][0] + x;
                int ny = dir[k][1] + y;

                if (nx >= 0 && nx < N && ny >= 0 && ny < M && !visited[nx][ny] && original[nx][ny] == ori) {
                    visited[nx][ny] = true;
                    queue.add(new int[] {nx, ny});
                }
            }
        }

        change(up);
    }

    static void change(int up) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j]) {
                    original[i][j] = up;
                }
            }
        }
    }

    static boolean compare() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (original[i][j] != update[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
