package Programmers3;

import java.util.*;

/**
 * 가장 먼 노드 Lv.3
 * 간선의 길이는 1이라 정하고 양방향으로 그래프를 만든다
 * 1번 노드부터 다익스트라 알고리즘을 실행하여 1번노드부터 연결된 노드들의 최단거리를 담은 배열을 만든다
 * 그중 가장 먼 노드들의 갯수를 센다.
 */
public class Solution42 {
    static final int INF = 50001;
    static List<ArrayList<Integer>> graph = new ArrayList<>();
    static int[] dist;
    public int solution(int n, int[][] edge) {
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] ints : edge) {
            int a = ints[0];
            int b = ints[1];
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        return dijkstra(n);
    }

    static int dijkstra(int n) {
        dist = new int[n + 1];
        Arrays.fill(dist, INF);
        dist[1] = 0;
        Queue<Integer> queue = new PriorityQueue<>();
        queue.add(1);
        while (!queue.isEmpty()) {
            Integer now = queue.poll();

            for (Integer next : graph.get(now)) {
                if (dist[next] > dist[now] + 1) {
                    dist[next] = dist[now] + 1;
                    queue.add(next);
                }
            }
        }
        return maxDistanceCount(dist);
    }

    static int maxDistanceCount(int[] dist) {
        int count = 0;
        int max = 0;
        for (int i = 2; i < dist.length; i++) {
            if (dist[i] > max) {
                max = dist[i];
                count = 1;
            } else if (dist[i] == max) {
                count++;
            }
        }

        return count;
    }
    public static void main(String[] args) {
        Solution42 solution42 = new Solution42();
        System.out.println(solution42.solution(6, new int[][] {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}}));
    }
}
