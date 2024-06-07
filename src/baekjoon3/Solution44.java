package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 1916 최소비용 구하기 (Gold5)
 * 해당 문제는 다익스트라 알고리즘을 사용하여 풀어야 하는 문제이다.
 * 이전에 학습했던 플로이드 와샬 알고리즘은 모든 정점에서 최소 가중치를 구하는 것이었다면
 * 다익스트라 알고리즘은 임의의 정점에서 목적지까지 갈 수 있는 최소 가중치를 구하는 알고리즘이다.
 * 다익스트라 알고리즘은 방문배열과 우선순위 큐를 사용하여 해당 정점의 최소 가중치를 갱신해가며 해결하는 알고리즘이다.
 * 우선 시작 정점에서 직접적으로 이어진 정점들을 제외하고 나머지 정점들은 최댓값으로 초기화 해놓는다.
 * 그리고 시작정점에서 갈 수 있는 정점중 가중치가 가장 적은 정점이 다음 정점이 된다.(우선순위 큐 사용)
 * 다음 정점에서 만약 시작 정점에서 갈 수 없는 노드가 이어져 있는 정점이 있거나 기존에 이어져있던 정점보다 더 적은 가중치로 이어져 있다면
 * 가중치의 갱신이 일어난다. 예를 들어 1에서 2를 가는 가중치가 5이고 1에서 3을 거쳐 2로 가는 가중치가 4이면
 * 1->2 는 5에서 4로 갱신이 된다. 마찬가지로 1에서 4로 직접가는 간선은 없었지만 1에서 3을 거쳐 4로 갈 수 있다면 이때 1 3 4 간선의 가중치의
 * 합이 1에서 4로 가는 가중치가 되는것이다. (1->4 Max 에서 1->3->4 가중치의 합) 해당 로직을 모든 정점을 방문할때까지 반복해주면
 * 임의의 정점에서 갈 수 있는 정점의 가중치의 최소값들이 배열에 저장될 것이다.
 * 다익스트라 알고리즘 몇문제를 더 풀어보면서 가닥을 잡아야 할거같다.
 */
public class Solution44 {
    static class Node {
        int node;
        int cost;

        public Node(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
    }

    static boolean[] visited;
    static int[] arr;
    static List<ArrayList<Node>> graph = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        int M = Integer.parseInt(bufferedReader.readLine());

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        visited = new boolean[N + 1];
        arr = new int[N + 1];
        Arrays.fill(arr, Integer.MAX_VALUE);

        for (int i = 0; i < M; i++) {
            String[] split = bufferedReader.readLine().split(" ");
            int a = Integer.parseInt(split[0]);
            int b = Integer.parseInt(split[1]);
            int cost = Integer.parseInt(split[2]);

            graph.get(a).add(new Node(b, cost));
        }

        String[] split = bufferedReader.readLine().split(" ");
        int start = Integer.parseInt(split[0]);
        int end = Integer.parseInt(split[1]);
        System.out.println(dijkstra(start, end));

    }

    static int dijkstra(int start, int end) {
        Queue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));
        queue.add(new Node(start, 0));
        arr[start] = 0;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int now = node.node;
            int cost = node.cost;

            if (!visited[now]) {
                visited[now] = true;
                for (Node next : graph.get(now)) {
                    int nextNode = next.node;
                    int nextNodeCost = next.cost;
                    if (!visited[nextNode] && arr[nextNode] > arr[now] + nextNodeCost) {
                        arr[nextNode] = arr[now] + nextNodeCost;
                        queue.add(new Node(nextNode, arr[nextNode]));
                    }
                }
            }
        }
        return arr[end];
    }
}
