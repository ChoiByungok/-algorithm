package baekjoon4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 24446 알고리즘 수업 - 너비 우선 탐색 3 (Silver2)
 */
public class Solution65 {
    static int N;
    static List<ArrayList<Integer>> graph = new ArrayList<>();
    static int[] depth;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());
        int start = Integer.parseInt(tokenizer.nextToken());
        depth = new int[N + 1];
        visited = new boolean[N + 1];
        Arrays.fill(depth, -1);
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            int u = Integer.parseInt(tokenizer.nextToken());
            int v = Integer.parseInt(tokenizer.nextToken());

            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        bfs(start);
        System.out.println(answer());
    }

    static void bfs(int start) {
        visited[start] = true;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {start, 0});
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int now = poll[0];
            int step = poll[1];
            depth[now] = step;

            for (Integer next : graph.get(now)) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.add(new int[] {next, step + 1});
                }
            }
        }
    }

    static String answer() {
        StringBuilder answer = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            answer.append(depth[i]).append("\n");
        }
        return answer.toString();
    }
}
