package Programmers3;

import java.util.*;

/**
 * 섬 연결하기 Lv.3
 * 알고리즘 분류는 그리디 알고리즘이었다. 그리디 알고리즘이 정확히는 뭔지 모르겠지만
 * 나는 이 문제를 프림알고리즘을 이용하여 해결하였다.
 * 프림알고리즘도 그리디의 일종인듯 싶다.
 */
public class Solution43 {
    static class Island {
        int num;
        int cost;

        public Island(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }
    }
    static List<ArrayList<Island>> bridges = new ArrayList<>();
    static boolean[] visited;
    public int solution(int n, int[][] costs) {
        for (int i = 0; i < n; i++) {
            bridges.add(new ArrayList<>());
        }
        for (int[] cost : costs) {
            int a = cost[0];
            int b = cost[1];
            int c = cost[2];
            bridges.get(a).add(new Island(b, c));
            bridges.get(b).add(new Island(a, c));
        }
        return prim(n);
    }

    static int prim(int n) {
        int min = 0;
        visited = new boolean[n];
        Queue<Island> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));
        queue.add(new Island(0, 0));
        while (!queue.isEmpty()) {
            Island poll = queue.poll();
            int now = poll.num;
            int cost = poll.cost;
            if (!visited[now]) {
                visited[now] = true;
                min += cost;
                for (Island island : bridges.get(now)) {
                    int next = island.num;
                    if (!visited[next]) {
                        queue.add(island);
                    }
                }
            }

        }
        return min;
    }

    public static void main(String[] args) {
        System.out.println(new Solution43().solution(4, new int[][] {{0,1,1}, {0,2,2}, {1,2,5}, {1,3,1}, {2,3,8}}));
    }
}
