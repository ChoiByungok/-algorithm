package baekjoon4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 5567 결혼식 (Silver2)
 * 플로이드 워셜을 쓸까 다익스트라를 쓸까 고민하다가
 * 그냥 1번정점만 보면 되는 문제라 다익스트라를 사용하여 풀음
 * 다익스트라를 사용하여 1번노드와 연결되어 있는 노드의 가중치가 2이하인 녀석들만 카운트해서 출력해주면 됨
 */
public class Solution9 {
    static final int INF = 123456789;
    static int N;
    static List<ArrayList<Integer>> friends = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i <= N; i++) {
            friends.add(new ArrayList<>());
        }

        int M = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < M; i++) {
            StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            friends.get(a).add(b);
            friends.get(b).add(a);
        }
        System.out.println(dijkstra());
    }

    static int dijkstra() {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[1] = 0;
        Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(value -> value[1]));
        queue.add(new int[] {1, 0});
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int now = poll[0];
            int cost = poll[1];

            for (Integer friend : friends.get(now)) {
                if (dist[friend] > cost + 1) {
                    dist[friend] = cost + 1;
                    queue.add(new int[] {friend, dist[friend]});
                }
            }
        }
        return invite(dist);
    }
    static int invite(int[] dist) {
        int count = 0;
        for (int i = 2; i <= N; i++) {
            if (dist[i] <= 2) {
                count++;
            }
        }
        return count;
    }
}
