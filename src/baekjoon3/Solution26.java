package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 1743 음식물 피하기 (Silver1)
 * 우선 입력받은 숫자만큼 사이즈의 2차원 boolean 배열을 하나 만든다
 * 그리고 입력받은 좌표를 true로 바꿔준다.
 * bfs 탐색을 하여 상하좌우 인접칸들의 갯수를 세어준다.
 * 이때 가장 큰 갯수가 곧 정답이다.
 */
public class Solution26 {
    static boolean[][] map;
    static int N;
    static int M;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        int answer = Integer.MIN_VALUE;
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        int K = Integer.parseInt(tokenizer.nextToken());
        map = new boolean[N][M];
        for (int i = 0; i < K; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            int r = Integer.parseInt(tokenizer.nextToken());
            int c = Integer.parseInt(tokenizer.nextToken());
            map[r - 1][c - 1] = true;
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j]) {
                    map[i][j] = false;
                    answer = Math.max(answer, bfs(i, j));
                }
            }
        }

        System.out.println(answer);
    }

    static int bfs(int i, int j) {
        int total = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {i, j});
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int x = poll[0];
            int y = poll[1];
            total++;
            if (x + 1 < N && map[x + 1][y]) {
                map[x + 1][y] = false;
                queue.add(new int[]{x + 1, y});
            }

            if (x - 1 >= 0 && map[x - 1][y]) {
                map[x - 1][y] = false;
                queue.add(new int[] {x - 1, y});
            }

            if (y + 1 < M && map[x][y + 1]) {
                map[x][y + 1] = false;
                queue.add(new int[] {x, y + 1});
            }

            if (y - 1 >= 0 && map[x][y - 1]) {
                map[x][y - 1] = false;
                queue.add(new int[] {x , y - 1});
            }
        }
        return total;
    }
}
