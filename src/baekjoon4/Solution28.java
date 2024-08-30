package baekjoon4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 5558 チーズ (Cheese) (Gold5)
 * 간만에 재미있게 풀었던 그래프 탐색문제
 * 치즈 수 만큼 반복탐색해서 해당 치즈위치에 도착하면 거기까지 이동한 걸음수를 누적합 해준다.
 * 중요한점은 치즈를 찾을때마다 방문배열을 초기화시켜줘야 한다.
 */
public class Solution28 {
    static class Mouse {
        int x;
        int y;
        int step;

        public Mouse(int x, int y, int step) {
            this.x = x;
            this.y = y;
            this.step = step;
        }
    }
    static int N, M;
    static char[][] map;
    static boolean[][] visited;
    static int cheese;
    static int startX, startY;
    static int[] dirX = new int[] {1, -1, 0, 0};
    static int[] dirY = new int[] {0, 0, 1, -1};
    public static void main(String[] args) throws IOException {
        int answer = 0;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] split = bufferedReader.readLine().split(" ");
        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);
        map = new char[N][M];
        cheese = Integer.parseInt(split[2]);

        for (int i = 0; i < N; i++) {
            String readLine = bufferedReader.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = readLine.charAt(j);
                if (map[i][j] == 'S') {
                    startX = i;
                    startY = j;
                }
            }
        }

        for (int i = 1; i <= cheese; i++) {
            visited = renewal(startX, startY);
            answer += bfs(i);
        }

        System.out.println(answer);
    }

    static int bfs(int level) {
        int answer = 0;
        Queue<Mouse> queue = new LinkedList<>();
        queue.add(new Mouse(startX, startY, 0));
        while (!queue.isEmpty()) {
            Mouse poll = queue.poll();
            int x = poll.x;
            int y = poll.y;
            int step = poll.step;
            if (map[x][y] - '0' == level) {
                startX = x;
                startY = y;
                answer = step;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dirX[i];
                int ny = y + dirY[i];
                if (nx >= 0 && nx < N && ny >= 0 && ny < M && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    queue.add(new Mouse(nx, ny, step + 1));
                }
            }
        }
        return answer;
    }

    static boolean[][] renewal(int nowX, int nowY) {
        boolean[][] renewal = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                renewal[i][j] = map[i][j] == 'X';
            }
        }
        renewal[nowX][nowY] = true;
        return renewal;
    }
}
