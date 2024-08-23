package baekjoon4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 5427 불 (Gold4)
 * 12 퍼센트에서 틀렸다고 나옴 게시판에 있는 모든 12퍼센트 반례 다통과함
 * 근데 제출하면 12퍼센트에서 틀렸다고 나옴 도저히 뭐가 틀렸는지 모르겠음
 * 방문처리를 하는 2차원 배열을 하나로 두고 처리했을때는 12퍼센트에서 걸렸는데
 * 불과 상근이의 방문처리 배열을 따로 두어 처리하니깐 통과 됨
 */
public class Solution21 {
    static int N;
    static int M;
    static char[][] map;
    static List<int[]> fire;
    static List<int[]> sangeun;
    static boolean[][] sangeunVisited;
    static boolean[][] fireVisited;
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
            sangeunVisited = new boolean[N][M];
            fireVisited = new boolean[N][M];
            fire = new ArrayList<>();
            sangeun = new ArrayList<>();
            for (int j = 0; j < N; j++) {
                String readLine = bufferedReader.readLine();
                for (int k = 0; k < M; k++) {
                    map[j][k] = readLine.charAt(k);
                    if (map[j][k] == '@') {
                        sangeun.add(new int[] {j, k});
                        sangeunVisited[j][k] = true;
                    }

                    if (map[j][k] == '*') {
                        fire.add(new int[] {j, k});
                        fireVisited[j][k] = true;
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
                    if (nx >= 0 && nx < N && ny >= 0 && ny < M && !fireVisited[nx][ny] && map[nx][ny] == '.') {
                        fireVisited[nx][ny] = true;
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
                    if (nx >= 0 && nx < N && ny >= 0 && ny < M && !sangeunVisited[nx][ny] && map[nx][ny] == '.' && !fireVisited[nx][ny]) {
                        sangeunVisited[nx][ny] = true;
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
