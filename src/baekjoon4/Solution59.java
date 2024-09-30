package baekjoon4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 10917 Your life (Silver2)
 */
public class Solution59 {
    static int N;
    static boolean[] visited;
    static List<ArrayList<Integer>> dreams = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());
        visited = new boolean[N + 1];
        for (int i = 0; i <= N; i++) {
            dreams.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            int x = Integer.parseInt(tokenizer.nextToken());
            int y = Integer.parseInt(tokenizer.nextToken());
            dreams.get(x).add(y);
            dreams.get(y).add(x);
        }
        System.out.println(bfs());
    }

    static int bfs() {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {1, 0});
        visited[1] = true;
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int now = poll[0];
            int step = poll[1];

            if (now == N) {
                return step;
            }

            for (Integer next : dreams.get(now)) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.add(new int[] {next, step + 1});
                }
            }
        }
        return -1;
    }
}
