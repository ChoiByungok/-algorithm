package baekjoon4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 2146 다리 만들기 (Gold3)
 * 내 기준 난이도가 많이 높은 bfs 문제
 * 섬들간에 다리를 놓았을 때 가장 짧은 경로를 구해야 한다.
 * 그래서 일단 같은 섬끼리 다리를 놓으면 안되므로 같은 섬들끼리 번호를 매겨 구분을 지어주었다.
 * 그다음 서로 다른 섬 끼리 최단 거리를 구해서 제출했더니 3퍼센트에서 틀렸다고 나왔다.
 * 그래서 반례를 막 찾아보던 중 탐색을 했는데도 불구하고 섬을 찾지 못했을 떄 거리가 0으로 초기화 되는 반례를 찾아서
 * 그 부분을 예외처리 해주었더니 통과되었다.
 */
public class Solution20 {
    static class Point {
        int x;
        int y;
        int length;

        public Point(int x, int y, int length) {
            this.x = x;
            this.y = y;
            this.length = length;
        }
    }
    static int N;
    static boolean[][] map;
    static boolean[][] visited;
    static int[][] division;
    static int[] dirX = new int[] {1, -1, 0, 0};
    static int[] dirY = new int[] {0, 0, 1, -1};
    static int answer = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());
        map = new boolean[N][N];
        visited = new boolean[N][N];
        division = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = tokenizer.nextToken().equals("1");
            }
        }

        int div = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && map[i][j]) {
                    visited[i][j] = true;
                    divisionBFS(i, j, div);
                    div++;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                boolean search = false;
                if (map[i][j]) {
                    for (int k = 0; k < 4; k++) {
                        int nx = i + dirX[k];
                        int ny = j + dirY[k];
                        if (nx >= 0 && nx < N && ny >= 0 && ny < N && !map[nx][ny]) {
                            search = true;
                            break;
                        }
                    }
                    if (search) {
                        searchBFS(i, j, division[i][j]);
                    }
                }
            }
        }

        System.out.println(answer);
    }

    static void divisionBFS(int i, int j, int div) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {i, j});
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int x = poll[0];
            int y = poll[1];
            division[x][y] = div;

            for (int k = 0; k < 4; k++) {
                int nx = x + dirX[k];
                int ny = y + dirY[k];
                if (nx >= 0 && nx < N && ny >= 0 && ny < N && map[nx][ny] && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    queue.add(new int[] {nx, ny});
                }
            }
        }
    }

    static void searchBFS(int i, int j, int div) {
        int shortestPath = 0;
        boolean find = false;
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(i, j, 0));
        visited = new boolean[N][N];
        visited[i][j] = true;
        while (!queue.isEmpty()) {
            Point poll = queue.poll();
            int x = poll.x;
            int y = poll.y;
            int length = poll.length;
            if (division[x][y] != 0 && division[x][y] != div) {
                shortestPath = length - 1;
                find = true;
                break;
            }

            for (int k = 0; k < 4; k++) {
                int nx = x + dirX[k];
                int ny = y + dirY[k];
                if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny] && division[nx][ny] != div) {
                    visited[nx][ny] = true;
                    queue.add(new Point(nx, ny, length + 1));
                }
            }
        }

        if (find) {
            answer = Math.min(answer, shortestPath);
        }
    }
}
