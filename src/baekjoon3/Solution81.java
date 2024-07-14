package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 18352 특정 거리의 도시 찾기 (Silver2)
 * bfs나 dfs 처럼 계속 풀어보면서 감각을 유지해야 하는데
 * 다익스트라 문제를 오랜만에 풀어봤던지라 개념이 많이 날아갔다.
 * 앞으로 2~3 문제 더 풀어보면서 완전히 익혀야 할듯
 */
public class Solution81 {
    static int N;
    static int K;
    static int X;
    static int[] distance;
    static List<ArrayList<Integer>> graph = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        StringBuilder answer = new StringBuilder();
        N = Integer.parseInt(tokenizer.nextToken()); //도시의 개수
        int M = Integer.parseInt(tokenizer.nextToken()); // 도로의 개수
        K = Integer.parseInt(tokenizer.nextToken()); // 거리정보
        X = Integer.parseInt(tokenizer.nextToken()); // 출발 도시의 번호

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        distance = new int[N + 1];
        Arrays.fill(distance, 987654321);
        distance[X] = 0;

        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            int A = Integer.parseInt(tokenizer.nextToken());
            int B = Integer.parseInt(tokenizer.nextToken());
            graph.get(A).add(B);
        }

        dijkstra(X);

        boolean exist = false;
        for (int i = 1; i <= N; i++) {
            if (distance[i] == K) {
                answer.append(i).append("\n");
                exist = true;
            }
        }
        System.out.println(exist ? answer : -1);
    }

    static void dijkstra(int start) {
        boolean[] visited = new boolean[N + 1];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;
        while (!queue.isEmpty()) {
            Integer now = queue.poll();

            int dis = distance[now];
            for (Integer integer : graph.get(now)) {
                if (!visited[integer]) {
                    visited[integer] = true;
                    distance[integer] = Math.min(distance[integer], dis + 1);
                    queue.add(integer);
                }
            }
        }
    }
}
