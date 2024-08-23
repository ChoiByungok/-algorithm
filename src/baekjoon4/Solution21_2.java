package baekjoon4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 5427 불 (Gold4)
 * 맨처음 불의 위치도 방문처리를 해야 하는데 그걸 하지 못해서 12퍼센트의 벽을 넘지 못했던 것 
 */
public class Solution21_2 {
    static int N;
    static int M;
    static char[][] map;
    static boolean[][] visited;
    static List<int[]> fire;
    static List<int[]> sangeun;
    static int[] dirX = new int[] {1, -1, 0 ,0};
    static int[] dirY = new int[] {0, 0, 1, -1};
    static StringBuilder answer = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < T; i++) {
            StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
            M = Integer.parseInt(tokenizer.nextToken());
            N = Integer.parseInt(tokenizer.nextToken());
            map = new char[N][M];
            visited = new boolean[N][M];
            fire = new ArrayList<>();
            sangeun = new ArrayList<>();
            for (int j = 0; j < N; j++) {
                String readLine = bufferedReader.readLine();
                for (int k = 0; k < M; k++) {
                    map[j][k] = readLine.charAt(k);
                    if (map[j][k] == '@') {
                        visited[j][k] = true;
                        sangeun.add(new int[] {j, k});
                    }

                    if (map[j][k] == '*') {
                        visited[j][k] = true;
                        fire.add(new int[] {j, k});
                    }

                    if (map[j][k] == '#') {
                        visited[j][k] = true;
                    }
                }
            }
            bfs();
        }
        System.out.println(answer);
    }

    static void bfs() {
        int second = 0;
        boolean exit = false;
        label:
        while (!sangeun.isEmpty()) {
            List<int[]> nextFire = new ArrayList<>();
            List<int[]> nextSangeun = new ArrayList<>();
            for (int[] now : fire) {
                int x = now[0];
                int y = now[1];
                for (int i = 0; i < 4; i++) {
                    int nx = x + dirX[i];
                    int ny = y + dirY[i];
                    if (nx >= 0 && nx < N && ny >= 0 && ny < M && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        nextFire.add(new int[] {nx, ny});
                    }
                }
            }
            fire = nextFire;
            for (int[] now : sangeun) {
                int x = now[0];
                int y = now[1];
                if (x == 0 || x == N - 1 || y == 0 || y == M - 1) {
                    second++;
                    exit = true;
                    break label;
                }

                for (int i = 0; i < 4; i++) {
                    int nx = x + dirX[i];
                    int ny = y + dirY[i];
                    if (nx >= 0 && nx < N && ny >= 0 && ny < M && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        nextSangeun.add(new int[] {nx, ny});
                    }
                }
            }
            sangeun = nextSangeun;
            second++;
        }

        answer.append(exit ? second : "IMPOSSIBLE").append("\n");
    }
}
