package baekjoon4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 16174 점프왕 쩰리 (Large) (Silver1)
 * 오른쪽과 아래쪽으로만 이동 가능하다길래 중복되는 경로가 없다고 판단해서 방문배열을 선언하지 않고 제출했더니
 * 메모리초과가 발생하였다. 다시 곰곰히 생각해보니 중복경로가 있어서 방문배열을 선언했더니 바로 통과되었다.
 * 역시 이런 탐색알고리즘종류들은 조건을 적당히 세울줄 알아야 한다는것을 다시한번 알게되었다.
 */
public class Solution31 {
    static int N;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }
        System.out.println(bfs() ? "HaruHaru" : "Hing");
    }

    static boolean bfs() {
        boolean[][] visited = new boolean[N][N];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {0, 0});
        visited[0][0] = true;
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int x = poll[0];
            int y = poll[1];
            if (x == N - 1 && y == N - 1) {
                return true;
            }

            int jump = map[x][y];
            int nx = jump + x;
            int ny = jump + y;
            if (nx < N && !visited[nx][y]) {
                visited[nx][y] = true;
                queue.add(new int[] {nx, y});
            }

            if (ny < N && !visited[x][ny]) {
                visited[x][ny] = true;
                queue.add(new int[] {x, ny});
            }
        }
        
        return false;
    }
}
