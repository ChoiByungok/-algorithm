package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 7569 토마토 (Gold5)
 * 이전에 풀었던 토마토(7576) 의 심화문제
 * 문제 내용은 같은데 축이 하나 늘었다 3차원 배열을 사용해야 함
 * 그래서 처음엔 굉장히 당황했는데 그냥 이전에 풀었던 문제에서
 * 위 아래로 갈 수 있는지 먼저 확인 한 후에 가능하면 큐에 넣고 아님 말면 되는 식이라
 * 생각보다 간단하게 풀렸다.
 */
public class Solution74 {
    static class Tomato {
        int h;
        int n;
        int m;

        public Tomato(int h, int n, int m) {
            this.h = h;
            this.n = n;
            this.m = m;
        }
    }
    static int[] dirN = new int[] {1, -1, 0, 0};
    static int[] dirM = new int[] {0, 0, 1, -1};
    static boolean[][][] visited;
    static int N;
    static int M;
    static int H;
    static Queue<Tomato> tomatoes = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        M = Integer.parseInt(tokenizer.nextToken());
        N = Integer.parseInt(tokenizer.nextToken());
        H = Integer.parseInt(tokenizer.nextToken());
        visited = new boolean[H][N][M];
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                tokenizer = new StringTokenizer(bufferedReader.readLine());
                for (int k = 0; k < M; k++) {
                    String token = tokenizer.nextToken();
                    if (token.equals("1")) {
                        tomatoes.add(new Tomato(i, j, k));
                        visited[i][j][k] = true;
                    }

                    if (token.equals("-1")) {
                        visited[i][j][k] = true;
                    }
                }
            }
        }
        System.out.println(bfs());
    }

    static int bfs() {
        int day = 0;
        while (check() && !tomatoes.isEmpty()) {
            Queue<Tomato> next = new LinkedList<>();
            while (!tomatoes.isEmpty()) {
                Tomato tomato = tomatoes.poll();
                int h = tomato.h;
                int n = tomato.n;
                int m = tomato.m;

                if (h - 1 >= 0 && !visited[h - 1][n][m]) {
                    visited[h - 1][n][m] = true;
                    next.add(new Tomato(h - 1, n, m));
                }

                if (h + 1 < H && !visited[h + 1][n][m]) {
                    visited[h + 1][n][m] = true;
                    next.add(new Tomato(h + 1, n, m));
                }

                for (int i = 0; i < 4; i++) {
                    int nN = dirN[i] + n;
                    int nM = dirM[i] + m;
                    if (nN < 0 || nN >= N || nM < 0 || nM >= M) {
                        continue;
                    }
                    if (!visited[h][nN][nM]) {
                        visited[h][nN][nM] = true;
                        next.add(new Tomato(h, nN, nM));
                    }
                }
            }
            tomatoes = next;
            day++;
        }

        return check() ? -1 : day;
    }

    static boolean check() {
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if (!visited[i][j][k]) {
                        return true;
                    }
                }

            }
        }
        return false;
    }
    private static void print() {
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    System.out.print(visited[i][j][k] ? "■ " : "□ ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }
}
