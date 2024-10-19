package Programmers3;

import java.util.*;

/**
 * 배달 Lv.2
 * 처음엔 방문배열 하나 둔 bfs 로 해결해보려고 했는데 틀렸다고 나옴
 * 그래서 다익스트라 알고리즘을 사용하여 1번노드부터 모든 노드에 최단거리를 계산한뒤에
 * 그 배열 값이 K 이하인 것들만 카운트해서 반환함
 * 다익스트라 알고리즘으로만 풀어봤더니 문득 플로이드 워셜 알고리즘으로도 풀 수 있지 않을까 의문이 들었고
 * 플로이드 워셜 알고리즘으로도 풀어서 제출해보았다. 의외로 플로이드 워셜이 제출 시간이 더 빨랐다.
 */
public class Solution36 {
    static final int INF = 987654321;
    static class Node {
        int end;
        int cost;

        public Node(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }
    }
    public int dijkstra(int N, int[][] road, int K) {
        int answer = 0;
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        List<ArrayList<Node>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] ints : road) {
            int A = ints[0];
            int B = ints[1];
            int cost = ints[2];
            graph.get(A).add(new Node(B, cost));
            graph.get(B).add(new Node(A, cost));
        }

        Queue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));
        queue.add(new Node(1, 0));
        dist[1] = 0;
        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            int now = poll.end;
            int cost = poll.cost;
            for (Node node : graph.get(now)) {
                int next = node.end;
                int nc = node.cost;
                if (dist[next] > cost + nc) {
                    dist[next] = cost + nc;
                    queue.add(new Node(next, cost + nc));
                }
            }
        }
        for (int i = 1; i <= N; i++) {
            if (dist[i] <= K) {
                answer++;
            }
        }
        return answer;
    }

    public int floyd(int N, int[][] road, int K) {
        int answer = 0;
        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(map[i], INF);
            map[i][i] = 0;
        }

        for (int[] ints : road) {
            int a = ints[0] - 1;
            int b = ints[1] - 1;
            int c = ints[2];
            if (map[a][b] != INF) {
                map[a][b] = Math.min(map[a][b], c);
                map[b][a] = Math.min(map[b][a], c);
            } else {
                map[a][b] = c;
                map[b][a] = c;
            }
        }

        for (int mid = 0; mid < N; mid++) {
            for (int start = 0; start < N; start++) {
                for (int end = 0; end < N; end++) {
                    if (start == mid || start == end || mid == end) {
                        continue;
                    }
                    if (map[start][end] > map[start][mid] + map[mid][end]) {
                        map[start][end] = map[start][mid] + map[mid][end];
                    }
                }
            }
        }
        for (int i = 0; i < N; i++) {
            if (map[0][i] <= K) {
                answer++;
            }
        }
        return answer;
    }
    public static void main(String[] args) {
        Solution36 solution36 = new Solution36();
        System.out.println(solution36.dijkstra(5, new int[][] {{1,2,1}, {2,3,3}, {5,2,2}, {1,4,2}, {5,3,1}, {5,4,2}}, 3));
        System.out.println(solution36.dijkstra(6, new int[][] {{1,2,1}, {1,3,2}, {2,3,2}, {3,4,3}, {3,5,2}, {3,5,3}, {5,6,1}}, 4));
        System.out.println(solution36.floyd(5, new int[][] {{1,2,1}, {2,3,3}, {5,2,2}, {1,4,2}, {5,3,1}, {5,4,2}}, 3));
        System.out.println(solution36.floyd(6, new int[][] {{1,2,1}, {1,3,2}, {2,3,2}, {3,4,3}, {3,5,2}, {3,5,3}, {5,6,1}}, 4));
    }
}
