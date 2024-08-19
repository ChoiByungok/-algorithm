package baekjoon4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 6118 숨바꼭질 (Silver1)
 * 처음에는 문제설명이 많이 모호해서 햇갈렸는데
 * 그냥 단순하게 1번 노드서부터 가장 먼 노드를 찾고
 * 그 노드와의 거리를 출력하고 그 노드와 같은 거리의 노드들의 갯수를 출력하면 되는 문제였다.
 * 나는 다익스트라 알고리즘을 사용하여 1번노드와 다른 노드와의 거리를 구하고
 * 그 거리를 구한 배열을 이용하여 답을 출력해낼수 있었다.
 */
public class Solution17 {
    static final int INF = 123456789;
    static int N;
    static List<ArrayList<Integer>> barn = new ArrayList<>();
    static int[] distance;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        distance = new int[N + 1];
        Arrays.fill(distance, INF);
        distance[1] = 0;
        int M = Integer.parseInt(tokenizer.nextToken());
        for (int i = 0; i <= N; i++) {
            barn.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            int A = Integer.parseInt(tokenizer.nextToken());
            int B = Integer.parseInt(tokenizer.nextToken());
            barn.get(A).add(B);
            barn.get(B).add(A);
        }
        bfs();
        int MAX = 0;
        int count = 1;
        int node = 0;
        for (int i = 2; i <= N; i++) {
            if (distance[i] > MAX) {
                MAX = distance[i];
                count = 1;
                node = i;
            } else if (distance[i] == MAX) {
                count++;
            }
        }

        System.out.println(node + " " + MAX + " " + count);
    }

    static void bfs() {
        Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(i -> i[1]));
        queue.add(new int[] {1, 0});
        boolean[] visited = new boolean[N + 1];
        visited[1] = true;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int num = poll[0];
            int cost = poll[1];

            for (Integer next : barn.get(num)) {
                if (distance[next] > cost + 1 && !visited[next]) {
                    visited[next] = true;
                    distance[next] = cost + 1;
                    queue.add(new int[] {next, distance[next]});
                }
            }
        }
    }
}
