package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 14716 현수막 (Silver1)
 * 이전 문제랑 똑같은 bfs탐색문제 근데 이 문제는 방향이 8방향이다.
 * 하지만 미리 정해놓은 배열을 이용하여 반복문을 사용한다면 코드가 더이상 길어지지 않는다.
 * 로직은 간단하다 bfs탐색으로 인접한 1을 방문처리해놓으면 문자 갯수만큼만 bfs탐색을 진행하기 때문에
 * bfs 메서드 호출한 횟수만큼 카운트를 센 뒤 카운트를 출력해주면 된다.
 */
public class Solution56 {
    static int[] dirX = {-1, -1, -1, 0, 1, 1, 1, 0};
    static int[] dirY = {-1, 0, 1, 1, 1, 0, -1, -1};
    static int M;
    static int N;
    static int[][] banner;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        M = Integer.parseInt(tokenizer.nextToken());
        N = Integer.parseInt(tokenizer.nextToken());
        banner = new int[M][N];
        visited = new boolean[M][N];

        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < N; j++) {
                banner[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        int count = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (banner[i][j] == 1 && !visited[i][j]) {
                    visited[i][j] = true;
                    count++;
                    bfs(i, j);
                }
            }
        }

        System.out.println(count);
    }

    static void bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {x, y});
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();

            for (int i = 0; i < 8; i++) {
                int nx = poll[0] + dirX[i];
                int ny = poll[1] + dirY[i];

                if (nx >= M || nx < 0 || ny >= N || ny < 0) {
                    continue;
                }

                if (!visited[nx][ny] && banner[nx][ny] == 1) {
                    visited[nx][ny] = true;
                    queue.add(new int[] {nx, ny});
                }
            }
        }
    }
}
