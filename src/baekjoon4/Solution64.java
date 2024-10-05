package baekjoon4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 28471 W키가 빠진 성원이 (Silver1)
 * 8방향으로 움직일수 있는 게임이 있는데 주인공은 w 키가 빠져서 위로 갈 수 없다.
 * 그래서 위로 갈 수 있는 방향을 제외한 7방향으로 움직일수 있다.
 * 목적지 F 가 주어졌을 때 목적지까지 갈 수 있는 칸이 몇개가 존재할까
 * 일반적으로 생각한다면 벽이아닌 모든칸에서 bfs 탐색을 하여 칸수를 세겠지만
 * 그렇게되면 효율적이지 않다. 목적지에서 시작하여 단 한번의 bfs 탐색을 진행하면 굉장히 효율적이다.
 * 그렇게 되면 갈 수 있는 방향을 조정해줘야 한다. 목적지부터 탐색을 시작한다면 아래로 가는 방향을 막으면 되는것이다.
 * 그렇게 목적지부터 아래방향을 제외한 7방향 탐색을 실시하여 칸수를 세면된다.
 */
public class Solution64 {
    static int N, startX, startY;
    static boolean[][] visited;
    static int[][] dir = new int[][] {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, -1}, {0, -1}, {-1, -1}};
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            String readLine = bufferedReader.readLine();
            for (int j = 0; j < N; j++) {
                char c = readLine.charAt(j);
                if (c == '#') {
                    visited[i][j] = true;
                }

                if (c == 'F') {
                    startX = i;
                    startY = j;
                }
            }
        }

        System.out.println(bfs());
    }

    static int bfs() {
        int count = -1;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {startX, startY});
        visited[startX][startY] = true;
        while (!queue.isEmpty()) {
            count++;
            int[] poll = queue.poll();
            int x = poll[0];
            int y = poll[1];

            for (int i = 0; i < 7; i++) {
                int nx = x + dir[i][0];
                int ny = y + dir[i][1];
                if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    queue.add(new int[] {nx, ny});
                }
            }
        }
        return count;
    }
}
