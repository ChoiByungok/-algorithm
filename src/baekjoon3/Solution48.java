package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 1238 파티 (Gold3)
 * 다익스트라 알고리즘을 사용하여 n->x로 가는 가중치의 최소값을 각각 구해서 배열에 넣어주었고
 * 반대로 x->n으로 오는 가중치의 최소값을 또 각각 구하여 다른 배열에 넣어주었다.
 * 이제 각 배열의 각 인덱스 값을 더해주면 그 값이 n->x->n 의 값이 되고
 * 그중 가장 큰 값을 출력해주면 된다.
 */
public class Solution48 {
    static class Node {
        int end;
        int cost;

        public Node(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }
    }
    static List<ArrayList<Node>> graph = new ArrayList<>();
    static int[] nTox;
    static int[] xTon;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        int answer = 0;
        N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());
        int X = Integer.parseInt(tokenizer.nextToken());
        nTox = new int[N + 1];
        Arrays.fill(nTox, Integer.MAX_VALUE);
        nTox[X] = 0;
        xTon = new int[N + 1];
        Arrays.fill(xTon, Integer.MAX_VALUE);
        xTon[X] = 0;
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            int A = Integer.parseInt(tokenizer.nextToken());
            int B = Integer.parseInt(tokenizer.nextToken());
            int cost = Integer.parseInt(tokenizer.nextToken());
            graph.get(A).add(new Node(B, cost));
        }

        for (int i = 1; i <= N; i++) {
            if (i != X) {
                nTox[i] = dijkstra(i, X);
            }
        }

        for (int i = 1; i <= N; i++) {
            if (i != X) {
                xTon[i] = dijkstra(X, i);
            }
        }

        for (int i = 1; i <= N; i++) {
            answer = Math.max(answer, nTox[i] + xTon[i]);
        }

        System.out.println(answer);
    }

    static int dijkstra(int start, int end) {
        int[] temp = new int[N + 1];
        boolean[] visited = new boolean[N + 1];
        Arrays.fill(temp, Integer.MAX_VALUE);
        temp[start] = 0;
        Queue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));
        queue.add(new Node(start, 0));
        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            int now = poll.end;

            if (!visited[now]) {
                visited[now] = true;
                for (Node node : graph.get(now)) {
                    int next = node.end;
                    int nextCost = node.cost;
                    if (temp[next] > temp[now] + nextCost) {
                        temp[next] = temp[now] + nextCost;
                        queue.add(new Node(next, temp[next]));
                    }
                }

            }
        }
        return temp[end];
    }
}
