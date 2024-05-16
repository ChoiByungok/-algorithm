package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 2589 보물섬 (Gold5)
 * 오랜만에 풀어본 넓이우선탐색 근데 완전탐색을 곁들였다.
 * 모든 땅 위치에서 최대한 멀리떨어진 땅을 찾으면 된다.
 * 맵을 입력받아서 초기화한다음 그 맵을 반복문을 돌리면서
 * 땅이 나오면 그때마다 맵을 복사한 방문배열을 만든 뒤
 * 갈 수 있는 땅의 최대 거리를 구해준다.
 */
public class Solution22 {
    static class Point {
        int x;
        int y;
        int hour;

        public Point(int x, int y, int hour) {
            this.x = x;
            this.y = y;
            this.hour = hour;
        }
    }
    static int N;
    static int M;
    static boolean[][] map;
    static boolean[][] visited;
    static int answer = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] split = bufferedReader.readLine().split(" ");
        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);
        map = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String readLine = bufferedReader.readLine();
            for (int j = 0; j < readLine.length(); j++) {
                map[i][j] = readLine.charAt(j) == 'L';
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j]) {
                    visited = copyMap();
                    visited[i][j] = false;
                    answer = Math.max(answer, max(i, j));
                }
            }
        }
        System.out.println(answer);
    }

    static int max(int i, int j) {
        int max = 0;
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(i, j, 0));
        while (!queue.isEmpty()) {
            Point poll = queue.poll();
            int x = poll.x;
            int y = poll.y;
            int hour = poll.hour;
            max = Math.max(max, hour);

            if (x + 1 < N && visited[x + 1][y]) {
                visited[x + 1][y] = false;
                queue.add(new Point(x + 1, y, hour + 1));
            }

            if (x - 1 >= 0 && visited[x - 1][y]) {
                visited[x - 1][y] = false;
                queue.add(new Point(x - 1, y, hour + 1));
            }

            if (y + 1 < M && visited[x][y + 1]) {
                visited[x][y + 1] = false;
                queue.add(new Point(x, y + 1, hour + 1));
            }

            if (y - 1 >= 0 && visited[x][y - 1]) {
                visited[x][y - 1] = false;
                queue.add(new Point(x, y - 1, hour + 1));
            }
        }

        return max;
    }

    static boolean[][] copyMap() {
        boolean[][] copy = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            if (M >= 0) {
                System.arraycopy(map[i], 0, copy[i], 0, M);
            }
        }

        return copy;
    }
}
