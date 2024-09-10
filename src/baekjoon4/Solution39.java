package baekjoon4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 16973 직사각형 탈출 (Gold4)
 * 직사각형 범위가 벽에 닿지 않고 목표 지점까지 도달할 수 있는가 탐색해야 하는문제다
 * 도달하지 못하면 -1을 출력하고 도달하면 이동 거리를 출력하면된다.
 * 좌측상단 좌표를 이용하여 작사각형의 범위를 계산한 뒤
 * 직사각형 범위를 2중반복문 돌려 그 직사각형 범위가 전체 범위를 벗어나거나 혹은 직사각형 범위 내 벽이 존재한다면 이동을 못하는식으로 탐색을 진행했다.
 * 예제도 통과되길래 제출했더니 시간초과가 발생하였다. 아마 직사각형 범위가 커지게 되면 2중반복문이 기하급수적으로 늘어나기에 효율이 안좋은 코드였을것이다.
 * 그래서 이번엔 벽의 좌표를 따로 입력 받아서
 * 벽의 좌표를 반복문 돌린 뒤 직사각형 범위내에 좌표가 포함 된다면 이동을 못하는식으로 구현하여 제출했더니 통과가 되었다.
 * 이 문제를 극한으로 효율적으로 해결하려면 누적합을 이용하여 벽의 갯수를 미리 파악한 뒤 하면 된다는데
 * 코드를 봐도 이해가 안가서 그냥 내 방식대로 풀었다.
 */
public class Solution39 {
    static class Rectangle {
        int x;
        int y;
        int step;

        public Rectangle(int x, int y, int step) {
            this.x = x;
            this.y = y;
            this.step = step;
        }
    }
    static int N, M;
    static int H, W;
    static int endX, endY;
    static boolean[][] visited;
    static List<int[]> walls = new ArrayList<>();
    static int[][] dir = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < M; j++) {
                String token = tokenizer.nextToken();
                if (token.equals("1")) {
                    walls.add(new int[] {i, j});
                }
            }
        }
        tokenizer = new StringTokenizer(bufferedReader.readLine());
        H = Integer.parseInt(tokenizer.nextToken());
        W = Integer.parseInt(tokenizer.nextToken());
        int startX = Integer.parseInt(tokenizer.nextToken()) - 1;
        int startY = Integer.parseInt(tokenizer.nextToken()) - 1;
        endX = Integer.parseInt(tokenizer.nextToken()) - 1;
        endY = Integer.parseInt(tokenizer.nextToken()) - 1;

        Rectangle rectangle = new Rectangle(startX, startY, 0);
        System.out.println(bfs(rectangle));
    }
    static int bfs(Rectangle rectangle) {
        int answer = -1;
        Queue<Rectangle> queue = new LinkedList<>();
        queue.add(rectangle);
        visited[rectangle.x][rectangle.y] = true;
        while (!queue.isEmpty()) {
            Rectangle now = queue.poll();
            int x = now.x;
            int y = now.y;
            int step = now.step;
            if (x == endX && y == endY) {
                answer = step;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = dir[i][0] + x;
                int ny = dir[i][1] + y;
                if (rangeCheck(nx, ny) && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    queue.add(new Rectangle(nx, ny, step + 1));
                }
            }
        }

        return answer;
    }

    static boolean rangeCheck(int x, int y) {
        if (x < 0 || x >= N || y < 0 || y >= M) {
            return false;
        }

        if (x + H -1 >= N || y + W -1 >= M) {
            return false;
        }

        for (int[] wall : walls) {
            int wx = wall[0];
            int wy = wall[1];
            if ((wx >= x && wx <= x + H -1) && (wy >= y && wy <= y + W -1)) {
                return false;
            }
        }

        return true;
    }

}
