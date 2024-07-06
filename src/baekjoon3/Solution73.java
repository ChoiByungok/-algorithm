package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 7576 토마토 (Gold5)
 * 심화버전 bfs문제 반복문 탈출 조건문만 잘 세우면 무난하게 풀 수 있다.
 * 나는 방문배열을 하나 선언해서 그 배열이 모두 방문 되거나 토마토 큐가 비면 탈출하게 만들었는데
 * 방문배열이 모두 방문되어서 탈출되었으면 반복문 내에서 증가시켰던 count 변수를 반환하였고
 * 토마토 큐가 비어서 탈출하게 되면 -1 을 반환하였다.
 * 제출하고 보니깐 int 형 2차원배열도 선언할 필요가 없었을 듯?
 */
public class Solution73 {
    static class Tomato {
        int x;
        int y;

        public Tomato(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static boolean[][] visited;
    static int N;
    static int M;
    static Queue<Tomato> tomatoes = new LinkedList<>();
    static int[] dirX = new int[] {1, -1, 0, 0};
    static int[] dirY = new int[] {0, 0, 1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        M = Integer.parseInt(tokenizer.nextToken());
        N = Integer.parseInt(tokenizer.nextToken());
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < M; j++) {
                String token = tokenizer.nextToken();
                if (token.equals("1")) {
                    tomatoes.add(new Tomato(i, j));
                    visited[i][j] = true;
                } else if (token.equals("-1")) {
                    visited[i][j] = true;
                }
            }
        }
        System.out.println(bfs());
    }

    static int bfs() {
        int count = 0;
        while (check() && !tomatoes.isEmpty()) {
            Queue<Tomato> next = new LinkedList<>();
            while (!tomatoes.isEmpty()) {
                Tomato tomato = tomatoes.poll();

                for (int i = 0; i < 4; i++) {
                    int dx = tomato.x + dirX[i];
                    int dy = tomato.y + dirY[i];
                    if (dx < 0 || dx >= N || dy < 0 || dy >= M) {
                        continue;
                    }
                    if (!visited[dx][dy]) {
                        visited[dx][dy] = true;
                        next.add(new Tomato(dx, dy));
                    }
                }
            }
            tomatoes = next;
            count++;
        }


        return check() ? -1 : count;
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
}
