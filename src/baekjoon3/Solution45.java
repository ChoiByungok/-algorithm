package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 1753 최단경로 (Gold4)
 * 이전에 배웠던 다익스트라 알고리즘을 사용하여 해결한 문제
 * 이전 문제랑 별반 다를게 없어서 어렵진 않았지만 아마 응용이 조금만 들어가게 된다면
 * 다시 못풀거같은 느낌이 들어서 몇문제 더 풀어보면서 감을 잡아야 할거같다.
 */
public class Solution45 {
    static class Node {
        int end;
        int cost;

        public Node(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }
    }

    static List<ArrayList<Node>> graph = new ArrayList<>();
    static boolean[] visited;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        int V = Integer.parseInt(tokenizer.nextToken());
        int E = Integer.parseInt(tokenizer.nextToken());
        int start = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
        }
        visited = new boolean[V + 1];
        arr = new int[V + 1];
        Arrays.fill(arr, Integer.MAX_VALUE);
        for (int i = 0; i < E; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            int u = Integer.parseInt(tokenizer.nextToken());
            int v = Integer.parseInt(tokenizer.nextToken());
            int w = Integer.parseInt(tokenizer.nextToken());

            graph.get(u).add(new Node(v, w));
        }

        System.out.println(dijkstra(start));

    }

    static StringBuilder dijkstra(int start) {
        Queue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));
        queue.add(new Node(start, 0));
        arr[start] = 0;
        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            int now = poll.end;
            if (!visited[now]) {
                visited[now] = true;
                for (Node node : graph.get(now)) {
                    int next = node.end;
                    int cost = node.cost;
                    if (arr[next] > arr[now] + cost) {
                        arr[next] = arr[now] + cost;
                        queue.add(new Node(next, arr[next]));
                    }
                }
            }
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == Integer.MAX_VALUE) {
                builder.append("INF").append("\n");
            } else {
                builder.append(arr[i]).append("\n");
            }
        }
        return builder;
    }
}
