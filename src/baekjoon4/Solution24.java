package baekjoon4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 14923 미로탈출 (Gold4)
 * 얼마전에 풀었던 벽 뚫고 이동하기 같은 문제다
 * 이런 문제 특성상 벽을 뚫었을 때랑 안뚫었을때랑 구분을 지어서 방문처리를 해야 하므로
 * 3차원 배열을 사용해야 한다. 벽을 뚫고 방문처리를 한 경로가 막혀서 더이상 갈 수 없을 때
 * 벽을 뚫고 오지 않은 다른 경로가 후에 이 경로에 도달했을 때는 이미 방문처리가 되어있어 정확한 최단거리를 계산할 수 없기 때문이다.
 * 그래서 벽을 한번 뚫은 경로는 다른곳에 저장을 해서 서로 중복이 되지 않게 해야 한다.
 */
public class Solution24 {
    static class HongIk {
        int x;
        int y;
        int step;
        int wall;

        public HongIk(int x, int y, int step, int wall) {
            this.x = x;
            this.y = y;
            this.step = step;
            this.wall = wall;
        }
    }
    static int N, M;
    static int startX, startY;
    static int endX, endY;
    static boolean[][][] visited;
    static boolean[][] map;
    static int[] dirX = new int[] {1, -1, 0, 0};
    static int[] dirY = new int[] {0, 0, 1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        visited = new boolean[2][N][M];
        map = new boolean[N][M];
        tokenizer = new StringTokenizer(bufferedReader.readLine());
        startX = Integer.parseInt(tokenizer.nextToken()) - 1;
        startY = Integer.parseInt(tokenizer.nextToken()) - 1;
        tokenizer = new StringTokenizer(bufferedReader.readLine());
        endX = Integer.parseInt(tokenizer.nextToken()) - 1;
        endY = Integer.parseInt(tokenizer.nextToken()) - 1;
        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = tokenizer.nextToken().equals("1");
            }
        }
        System.out.println(bfs());
    }

    static int bfs() {
        int exit = -1;
        Queue<HongIk> queue = new LinkedList<>();
        queue.add(new HongIk(startX, startY, 0, 0));
        visited[0][0][0] = true;
        while (!queue.isEmpty()) {
            HongIk poll = queue.poll();
            int x = poll.x;
            int y = poll.y;
            int step = poll.step;
            int wall = poll.wall;

            if (x == endX && y == endY) {
                exit = step;
                break;
            }
            for (int i = 0; i < 4; i++) {
                int nx = x + dirX[i];
                int ny = y + dirY[i];
                if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
                    if (wall == 0 && !visited[wall][nx][ny]) { //지팡이를 사용하지 않았을 때
                        if (map[nx][ny]) { //벽이면
                            queue.add(new HongIk(nx, ny, step + 1, wall + 1));
                        } else { //아니면
                            queue.add(new HongIk(nx, ny, step + 1, wall));
                        }
                        visited[wall][nx][ny] = true;
                    } else { //지팡이를 이미사용한 경우
                        if (!map[nx][ny] && !visited[wall][nx][ny]) { //벽이 아닌곳만 이동 가능
                            visited[wall][nx][ny] = true;
                            queue.add(new HongIk(nx, ny, step + 1, wall));
                        }
                    }
                }
            }

        }


        return exit;
    }
}
