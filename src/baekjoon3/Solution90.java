package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 18223 민준이와 마산 그리고 건우 (Gold4)
 * 민준이가 마산을 가는데 중간에 건우를 데리고 가는 경로의 가중치가 바로 마산으로 가는 경로의 가중치보다
 * 작거나 같으면 데려가고 아니면 그냥 마산으로 가는 문제이다.
 * 바로 시작점에서 마산으로 가는 가중치의 최소치를 구하고
 * 시작점 -> 건우위치 -> 마산 의 가중치의 최소치를 구한 뒤
 * 둘의 값을 비교하여 출력하면 된다.
 */
public class Solution90 {
    static final int INF = 99999999;
    static class Vertex {
        int next;
        int weight;

        public Vertex(int next, int weight) {
            this.next = next;
            this.weight = weight;
        }
    }
    static List<ArrayList<Vertex>> graph = new ArrayList<>();
    static int V;
    static int P;
    static int[] dist;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        V = Integer.parseInt(tokenizer.nextToken());
        int E = Integer.parseInt(tokenizer.nextToken());
        P = Integer.parseInt(tokenizer.nextToken());

        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            int c = Integer.parseInt(tokenizer.nextToken());

            graph.get(a).add(new Vertex(b, c));
            graph.get(b).add(new Vertex(a, c));
        }

        int saveHim = 0;
        int goodbye = 0;

        dijkstra(1, V);
        goodbye = dist[V];
        saveHim = dist[P];
        dijkstra(P, V);
        saveHim += dist[V];

        System.out.println(saveHim > goodbye ? "GOOD BYE" : "SAVE HIM");
    }

    static void dijkstra(int start, int end) {
        Queue<Vertex> queue = new PriorityQueue<>(Comparator.comparingInt(v -> v.weight));
        queue.add(new Vertex(start, 0));
        dist = new int[V + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;
        while (!queue.isEmpty()) {
            Vertex vertex = queue.poll();
            int now = vertex.next;

            for (Vertex next : graph.get(now)) {
                if (dist[next.next] > vertex.weight + next.weight) {
                    dist[next.next] = vertex.weight + next.weight;
                    queue.add(new Vertex(next.next, dist[next.next]));
                }
            }
        }
    }
}
