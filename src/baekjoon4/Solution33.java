package baekjoon4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 24513 좀비 바이러스 (Gold3) 실패
 * 1번 바이러스와 2번바이러스가 존재하고 동시에 상하좌우로 퍼지는데
 * 두 바이러스가 겹치는곳에서 3번바이러스가 생성된다. 3번바이러스는 퍼지지는 않는다.
 * 결국 1 2 3번바이러스가 온 지역에 다 퍼졌을 때 각 각 몇 칸을 차지할것인가 구하는문제인데
 * 3번바이러스를 처리하는 과정이 굉장히 번거롭고 어렵다 어떻게 로직을 처리해야할지 모르겠다.
 * 해당 코드는 예제는 통과하지만 메모리초과가 발생한다. 나중에 실력을 가다듬고 재도전할예정
 */
public class Solution33 {
    static int N;
    static int M;
    static int[][] map;
    static boolean[][] visited;
    static int[] dirX = new int[] {1, -1, 0, 0};
    static int[] dirY = new int[] {0, 0, 1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        Queue<int[]> one = new LinkedList<>();
        Queue<int[]> two = new LinkedList<>();
        Queue<int[]> nextOne = new LinkedList<>();
        Queue<int[]> nextTwo = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
                if (map[i][j] == 1) {
                    visited[i][j] = true;
                    one.add(new int[] {i, j});
                }

                if (map[i][j] == 2) {
                    visited[i][j] = true;
                    two.add(new int[] {i, j});
                }

                if (map[i][j] == -1) {
                    visited[i][j] = true;
                }
            }
        }

        while (check()) {
            while (!one.isEmpty()) {
                int[] poll = one.poll();
                int x = poll[0];
                int y = poll[1];
                for (int i = 0; i < 4; i++) {
                    int nx = x + dirX[i];
                    int ny = y + dirY[i];
                    if (nx >= 0 && nx < N && ny >= 0 && ny < M && !visited[nx][ny]) {
                        nextOne.add(new int[] {nx, ny});
                    }
                }
            }

            while (!two.isEmpty()) {
                int[] poll = two.poll();
                int x = poll[0];
                int y = poll[1];
                for (int i = 0; i < 4; i++) {
                    int nx = x + dirX[i];
                    int ny = y + dirY[i];
                    if (nx >= 0 && nx < N && ny >= 0 && ny < M && !visited[nx][ny]) {
                        nextTwo.add(new int[] {nx, ny});
                    }
                }
            }
            while (!nextOne.isEmpty()) {
                int[] poll = nextOne.poll();
                one.add(poll);
                int x = poll[0];
                int y = poll[1];
                if (!visited[x][y]) {
                    visited[x][y] = true;
                    map[x][y] += 1;
                }
            }

            while (!nextTwo.isEmpty()) {
                int[] poll = nextTwo.poll();
                int x = poll[0];
                int y = poll[1];
                if (map[x][y] == 1) {
                    map[x][y] += 2;
                } else {
                    if (!visited[x][y]) {
                        visited[x][y] = true;
                        map[x][y] += 2;
                        two.add(poll);
                    }
                }
            }
        }
        count();
    }

    static boolean check() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j]) {
                    return true;
                }
            }
        }
        return false;
    }

    static void print() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    static void count() {
        StringBuilder answer = new StringBuilder();
        int one = 0;
        int two = 0;
        int three = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) {
                    one++;
                }
                if (map[i][j] == 2) {
                    two++;
                }
                if (map[i][j] == 3) {
                    three++;
                }
            }
        }
        answer.append(one).append(" ").append(two).append(" ").append(three);
        System.out.println(answer);
    }
}
