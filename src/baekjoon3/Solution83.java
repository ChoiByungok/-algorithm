package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 14284 간선 이어가기 2 (Gold5)
 * 문제에서는 그래프를 만들다가 주어진 노드 둘이 연결되었을때 최소 가중치를 출력하시오 라고 나왔는데
 * 생각해보니 그냥 그래프에서 두 정점사이의 최단거리를 구하시오 라는 말과 같아서
 * 입력받은 값으로 그래프를 만들고 다익스트라 알고리즘을 구현하여 값을 구했다.
 */
public class Solution83 {
    static class Vertex {
        int end;
        int weight;

        public Vertex(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }
    }
    static int[] distance;
    static List<ArrayList<Vertex>> graph = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());

        distance = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            int from = Integer.parseInt(tokenizer.nextToken());
            int to = Integer.parseInt(tokenizer.nextToken());
            int weight = Integer.parseInt(tokenizer.nextToken());
            graph.get(from).add(new Vertex(to, weight));
            graph.get(to).add(new Vertex(from, weight));
        }

        tokenizer = new StringTokenizer(bufferedReader.readLine());
        int start = Integer.parseInt(tokenizer.nextToken());
        int end = Integer.parseInt(tokenizer.nextToken());

        Arrays.fill(distance, 987654321);
        distance[start] = 0;
        dijkstra(start);
        System.out.println(distance[end]);
    }

    static void dijkstra(int start) {
        Queue<Vertex> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight));
        queue.add(new Vertex(start, 0));
        while (!queue.isEmpty()) {
            Vertex poll = queue.poll();
            int now = poll.end;

            for (Vertex vertex : graph.get(now)) {
                if (distance[vertex.end] > distance[now] + vertex.weight) {
                    distance[vertex.end] = distance[now] + vertex.weight;
                    queue.add(new Vertex(vertex.end, distance[vertex.end]));
                }
            }
        }
    }
}
