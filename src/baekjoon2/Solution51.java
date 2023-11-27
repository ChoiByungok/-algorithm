package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 1939 중량제한
 * 기존에 풀어왔던 bfs 문제를 이분탐색이랑 섞어서 푸는 문제임
 * 이 문제는 답을 보고 풀었는데도 불구하고 정확한 메커니즘을 이해하지 못하겠음
 * 다음에 한번 더 풀어봐야 할 문제일듯
 */
public class Solution51 {
    static class Island {
        int num;
        int value;

        public Island(int num, int value) {
            this.num = num;
            this.value = value;
        }
    }
    static int n;
    static int start;
    static int end;

    static List<List<Island>> graph = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = bufferedReader.readLine().split(" ");
        n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);
        int low = Integer.MAX_VALUE;
        int high = Integer.MIN_VALUE;
        int answer = 0;
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            String[] split = bufferedReader.readLine().split(" ");
            int x = Integer.parseInt(split[0]);
            int y = Integer.parseInt(split[1]);
            int weight = Integer.parseInt(split[2]);
            graph.get(x).add(new Island(y, weight));
            graph.get(y).add(new Island(x, weight));
            low = Math.min(low, weight);
            high = Math.max(high, weight);
        }
        String[] split = bufferedReader.readLine().split(" ");
        start = Integer.parseInt(split[0]);
        end = Integer.parseInt(split[1]);

        while (low <= high) {
            int middle = (high + low) / 2;
            if (bfs(middle)) {
                low = middle + 1;
                answer = middle;
            } else {
                high = middle - 1;
            }
        }
        System.out.println(answer);
    }

    private static boolean bfs(int weight) {
        Queue<Island> queue = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];
        visited[start] = true;
        queue.add(new Island(start, 0));
        while (!queue.isEmpty()) {
            Island now = queue.poll();
            int num = now.num;
            if (num == end) {
                return true;
            }
            for (Island island : graph.get(num)) {
                if (weight <= island.value && !visited[island.num]) {
                    visited[island.num] = true;
                    queue.add(island);
                }
            }
        }
        return false;
    }
}
