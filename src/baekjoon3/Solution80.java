package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 11967 불켜기 (Gold2)
 * 그래프 탐색을 진행하면서 해당 위치에서 다른방에 불을 킬 수 있으면 불을 키고
 * 인접한 방이 불이 켜저 있으면 이동가능하다
 * 그래프 탐색과 불을 키는 행위를 같이 해야 하는데
 * 우선 현재 위치에서 불을 킬 수 있는 곳이 있는지 찾고 작업을 마치면 탐색을 진행한다.
 * 그렇게 전부 불을 키면 될거같은데 문제는 그 이후
 * 더이상 불을 킬 곳이 없으면 계속 방과 방 사이를 돌아다니며 무한루프에 빠지게 될거같음
 * 이때 반복문 탈출 조건을 걸어줘야 할거같은데 어떻게 해야할까
 * 한번이라도 어떠한 방에서 불을 킨 행위를 했다면 방문배열을 초기화 시키는 방법으로 무한루프를 해결하였다.
 * 어찌 어찌 억지로 코드짜서 제출했더니 통과는 됐지만 지상 최악의 성능을 가진 정답을 제출할 수 있었다.
 */
public class Solution80 {
    static boolean[][] map;
    static boolean[][] visited;
    static boolean[][] isLight;
    static int N;
    static int M;
    static List<int[]> list = new ArrayList<>();
    static int[] dirX = new int[] {1, -1, 0, 0};
    static int[] dirY = new int[] {0, 0, 1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        map = new boolean[N][N];
        visited = new boolean[N][N];
        isLight = new boolean[N][N];

        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            int x = Integer.parseInt(tokenizer.nextToken()) - 1;
            int y = Integer.parseInt(tokenizer.nextToken()) - 1;
            int a = Integer.parseInt(tokenizer.nextToken()) - 1;
            int b = Integer.parseInt(tokenizer.nextToken()) - 1;
            list.add(new int[] {x, y, a, b});
        }

        bfs();
        System.out.println(count());
    }

    static void bfs() {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {0, 0});
        map[0][0] = true;
        visited[0][0] = true;
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int x = now[0];
            int y = now[1];


            if (!isLight[x][y]) {
                for (int[] ints : list) {
                    if (ints[0] == x && ints[1] == y) {
                        isLight[x][y] = true;
                        map[ints[2]][ints[3]] = true;
                        visited = new boolean[N][N];
                    }
                }
            }

            for (int i = 0; i < 4; i++) {
                int dX = x + dirX[i];
                int dY = y + dirY[i];
                if (dX < 0 || dX >= N || dY < 0 || dY >= N) {
                    continue;
                }

                if (map[dX][dY] && !visited[dX][dY]) {
                    visited[dX][dY] = true;
                    queue.add(new int[] {dX, dY});
                }
            }
        }
    }

    static int count() {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }
}
