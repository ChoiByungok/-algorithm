package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 18405 경쟁적 전염 (Gold5)
 * 간만에 깔끔하게 풀린 문제 생각한대로 그대로 구현해서
 * 한번에 통과된 몇 안되는 문제
 * 경쟁이라길래 바로 우선순위 큐를 이용해야겠다고 생각함
 * 그렇게 먼저 나온 바이러스가 먼저 칸을 점령하면 그 이후에는 칸이 점령되어있나 아닌가만 판단하면 된다.
 */
public class Solution78 {
    static class Virus {
        int num;
        int x;
        int y;

        public Virus(int num, int x, int y) {
            this.num = num;
            this.x = x;
            this.y = y;
        }
    }
    static int[][] map;
    static int N;
    static int K;
    static int[] dirX = new int[] {1, -1, 0, 0};
    static int[] dirY = new int[] {0, 0, 1, -1};
    static Queue<Virus> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.num));
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        K = Integer.parseInt(tokenizer.nextToken());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
                if (map[i][j] != 0) {
                    queue.add(new Virus(map[i][j], i, j));
                }
            }
        }
        tokenizer = new StringTokenizer(bufferedReader.readLine());
        int S = Integer.parseInt(tokenizer.nextToken());
        int X = Integer.parseInt(tokenizer.nextToken());
        int Y = Integer.parseInt(tokenizer.nextToken());

        while (S-- > 0) {
            Queue<Virus> next = new PriorityQueue<>(Comparator.comparingInt(o -> o.num));
            while (!queue.isEmpty()) {
                Virus virus = queue.poll();

                for (int i = 0; i < 4; i++) {
                    int dX = virus.x + dirX[i];
                    int dY = virus.y + dirY[i];
                    if (dX < 0 || dX >= N || dY < 0 || dY >= N) {
                        continue;
                    }
                    if (map[dX][dY] == 0) {
                        map[dX][dY] = virus.num;
                        next.add(new Virus(virus.num, dX, dY));
                    }
                }
            }
            queue = next;
        }

        System.out.println(map[X - 1][Y - 1]);
    }

    static void print() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
