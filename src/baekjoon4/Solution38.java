package baekjoon4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 17836 공주님을 구해라! (Gold5)
 * 용사가 가는길에 명검을 주으면 앞으로 가는길에 벽이있어도 그냥 뚫고 갈 수 있음
 * 이문제 또한 검을 주웠을 떄랑 안주웠을때랑 따로 방문배열을 선언하여 탐색을 진행해야 하는 문제임
 * 생각해보니 검을 주웠을 떄랑 안주웠을때랑 같은 방문배열을 사용하여 해결한다면 최적의 경로를 찾을 수 없다.
 * 검을 주운 뒤 최적의 경로를 찾으려는데 안주웠을경우 경로가 이미 방문 처리를 해버렸으면
 * 그 길이 최적의 경로임에도 탐색을 더 이상 진행하지 않기 떄문이다.
 */
public class Solution38 {
    static class Hero {
        int x;
        int y;
        int step;
        boolean gram;

        public Hero(int x, int y, int step, boolean gram) {
            this.x = x;
            this.y = y;
            this.step = step;
            this.gram = gram;
        }
    }
    static int N, M, T;

    static boolean[][][] visited;
    static int[][] map;
    static int[][] dir = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        T = Integer.parseInt(tokenizer.nextToken());
        visited = new boolean[2][N][M];
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        int step = bfs();
        System.out.println(step == -1 ? "Fail" : step);
    }

    static int bfs() {
        int answer = -1;
        Queue<Hero> queue = new LinkedList<>();
        queue.add(new Hero(0, 0, 0, false));
        visited[0][0][0] = true;
        while (!queue.isEmpty()) {
            Hero hero = queue.poll();
            int x = hero.x;
            int y = hero.y;
            int step = hero.step;
            boolean gram = hero.gram;
            int num = map[x][y];
            if (num == 2) {
                gram = true;
            }
            if (step > T) {
                continue;
            }
            if (x == N - 1 && y == M - 1) {
                answer = step;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dir[i][0];
                int ny = y + dir[i][1];
                if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
                    if (gram) {
                        if (!visited[1][nx][ny]) {
                            visited[1][nx][ny] = true;
                            queue.add(new Hero(nx, ny, step + 1, true));
                        }
                    } else {
                        if (!visited[0][nx][ny] && map[nx][ny] != 1) {
                            visited[0][nx][ny] = true;
                            queue.add(new Hero(nx, ny, step + 1, false));
                        }
                    }
                }
            }
        }

        return answer;
    }
}
