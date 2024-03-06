package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 20058 마법사 상어와 파이어스톰 (Gold3)
 * 구현 시뮬레이션 문제에다가 넓이 우선탐색까지 사용해야 하는문제
 * 진짜 고비는 격자를 나눈 뒤 각각의 격자를 어떻게 90도 회전시키냐가 관건이다.
 * (0, 0) (0, 1)
 * (1, 0) (1, 1)
 * 의 좌표를
 * (1, 0) (0, 0)
 * (1, 1) (0 ,1)
 * 이렇게 회전을 시켜야 하는데
 * 이게 처음에 0 0 부터 시작하는 회전은 잘 되는데
 * (0, 2) (0, 3)
 * (1, 2) (1, 3)
 * 부터는 내가 원하는 대로 잘 회전이 되지않아
 * 내가 원하는건
 * (1, 2) (0, 2)
 * (1, 3) (0, 3)
 * 인데 이 값이 들어오지않고 계속
 * (1, 0) (0, 0)
 * (1, 1) (0 ,1)
 * 의 값이 계속 들어오길래 계속 고민하다가 그냥
 * 새로운 변수를 만들어 그 변수의 값을 조절하는 방식으로 해결하였다.
 * 그 이후엔 어느 얼음을 녹여야 하는지 찾아내어 녹이고
 * 반복문이 끝난 뒤에는 남아있는 얼음의 합을 출력하고
 * 넓이 우선탐색을 진행하여 얼음덩어리의 최대 사이즈를 출력하면 된다.
 */
public class Solution150 {
    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int N;
    static int[][] map;
    static int[][] newMap;
    static int max = 0;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        StringBuilder answer = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        N = (int) Math.pow(2, Integer.parseInt(tokenizer.nextToken()));
        int Q = Integer.parseInt(tokenizer.nextToken());
        map = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        tokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < Q; i++) {
            int L = (int) Math.pow(2, Integer.parseInt(tokenizer.nextToken()));
            if (L >= 2) {
                grid(L);
                map = newMap;
            }
            melt();
        }

        bfs();
        answer.append(sum()).append("\n").append(max);
        System.out.println(answer);
    }

    private static void grid(int L) {
        newMap = new int[N][N];
        for (int i = 0; i < N; i += L) {
            for (int j = 0; j < N; j += L) {
                spin(i, j, i + L, j + L);
            }
        }
    }

    private static void spin(int si, int sj, int ei, int ej) {
        int indexJ = sj;
        for (int i = si; i < ei; i++) {
            int indexI = ei - 1;
            for (int j = sj; j < ej; j++) {
                newMap[i][j] = map[indexI--][indexJ];
            }
            indexJ++;
        }
    }

    private static void melt() {
        boolean[][] melt = new boolean[N][N];
        for (int j = 0; j < N; j++) {
            for (int k = 0; k < N; k++) {
                if (map[j][k] > 0) {
                    int count = 0;
                    if (j - 1 >= 0 && map[j - 1][k] != 0) {
                        count++;
                    }

                    if (j + 1 < N && map[j + 1][k] != 0) {
                        count++;
                    }

                    if (k - 1 >= 0 && map[j][k - 1] != 0) {
                        count++;
                    }

                    if (k + 1 < N && map[j][k + 1] != 0) {
                        count++;
                    }
                    if (count < 3) {
                        melt[j][k] = true;
                    }
                }
            }
        }

        for (int j = 0; j < N; j++) {
            for (int k = 0; k < N; k++) {
                if (melt[j][k]) {
                    map[j][k]--;
                }
            }
        }
    }

    private static int sum() {
        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sum += map[i][j];
            }
        }

        return sum;
    }

    private static void bfs() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] > 0 && !visited[i][j]) {
                    Queue<Point> queue = new LinkedList<>();
                    queue.add(new Point(i, j));
                    visited[i][j] = true;
                    int count  = 0;
                    while (!queue.isEmpty()) {
                        count++;
                        Point poll = queue.poll();
                        int x = poll.x;
                        int y = poll.y;

                        if (x + 1 < N && !visited[x + 1][y] && map[x + 1][y] > 0) {
                            visited[x + 1][y] = true;
                            queue.add(new Point(x + 1, y));
                        }

                        if (x - 1 >= 0 && !visited[x - 1][y] && map[x - 1][y] > 0) {
                            visited[x - 1][y] = true;
                            queue.add(new Point(x - 1, y));
                        }

                        if (y + 1 < N && !visited[x][y + 1] && map[x][y + 1] > 0) {
                            visited[x][y + 1] = true;
                            queue.add(new Point(x, y + 1));
                        }

                        if (y - 1 >= 0 && !visited[x][y - 1] && map[x][y - 1] > 0) {
                            visited[x][y - 1] = true;
                            queue.add(new Point(x, y - 1));
                        }
                    }
                    max = Math.max(max, count);
                }
            }
        }
    }

    static void print(int[][] map) {
        System.out.println();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
