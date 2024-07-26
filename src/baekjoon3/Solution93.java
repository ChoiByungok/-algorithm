package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 12834 주간 미팅 (Gold4)
 * 다익스트라 알고리즘을 사용하여 (집 - A) + (집 - B) 의 접체 합을 출력해주면 된다.
 * 이때 경로가 존재하지 않으면 -1 을 더해주면 된다.
 */
public class Solution93 {
    static final int INF = 99999999;
    static class Node {
        int end;
        int length;

        public Node(int end, int length) {
            this.end = end;
            this.length = length;
        }
    }

    static List<ArrayList<Node>> graph = new ArrayList<>();
    static int[] knights;
    public static void main(String[] args) throws IOException {
        int answer = 0;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        int N = Integer.parseInt(tokenizer.nextToken()); //팀원의 수
        int V = Integer.parseInt(tokenizer.nextToken()); //장소의 수
        int E = Integer.parseInt(tokenizer.nextToken()); //도로의 수
        knights = new int[N];
        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
        }
        tokenizer = new StringTokenizer(bufferedReader.readLine());
        int A = Integer.parseInt(tokenizer.nextToken());
        int B = Integer.parseInt(tokenizer.nextToken());

        tokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < N; i++) {
            knights[i] = Integer.parseInt(tokenizer.nextToken());
        }

        for (int i = 0; i < E; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            int l = Integer.parseInt(tokenizer.nextToken());
            graph.get(a).add(new Node(b, l));
            graph.get(b).add(new Node(a, l));
        }

        for (int i = 0; i < N; i++) {
            answer += dijkstra(knights[i], A, V);
            answer += dijkstra(knights[i], B, V);
        }

        System.out.println(answer);
    }

    static int dijkstra(int start, int destination, int V) {
        int[] dist = new int[V + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;
        Queue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(n -> n.length));
        queue.add(new Node(start, 0));

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int now = node.end;
            for (Node next : graph.get(now)) {
                int end = next.end;
                int length = next.length;
                if (dist[end] > node.length + length) {
                    dist[end] = node.length + length;
                    queue.add(new Node(end, dist[end]));
                }
            }
        }

        return dist[destination] != INF ? dist[destination] : -1;
    }
}
