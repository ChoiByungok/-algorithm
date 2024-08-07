package baekjoon4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 22865 가장 먼 곳 (Gold4)
 * 마을에 있는 집들중에 친구집을 제외하고 모든집이 후보가 된다.
 * 그 후보들 중에서 친구들 집까지 최단거리를 구한다
 * 그렇게 계산된 각 후보들 위치에서 친구 ABC 중 가장 가까운 거리를 구한다.
 * 예를 들어 후보가 2개고 그 후보를 각 각 X Y 라고 했을 때
 * X 는 A B C 와 각 각 2 3 4 의 최단거리를 가진다 치고
 * Y 는 A B C 와 각 각 3 4 5 의 최단거리를 가진다 쳤을 때
 * X 에서 가장 가까운 친구의 집은 2 이고 Y 는 3 이다
 * 가장 가까운 친구와의 거리 중 가장 먼곳을 집 후보로 정하는 것이기 때문에 후보는 Y가 되게 되는 것이다.
 * 최단 거리 계산까지는 다익스트라를 사용하여 간단하게 구했는데 그 뒤 가장 먼 곳을 후보로 계산하는 로직이 조금 헷갈려서
 * 오래 걸렸지만 그래도 나쁘지 않은 성능에 풀었다는것에 의의를 두어야 겠다.
 */
public class Solution5 {
    static final int INF = 987654321;
    static class House {
        int num;
        int cost;

        public House(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }
    }
    static int N;
    static Set<Integer> set = new HashSet<>();
    static int[] friends = new int[3];

    static List<ArrayList<House>> map = new ArrayList<>();
    static int[][] dist;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());
        dist = new int[3][N + 1];
        for (int i = 0; i < 3; i++) {
            Arrays.fill(dist[i], INF);
        }
        for (int i = 0; i <= N; i++) {
            map.add(new ArrayList<>());
        }

        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < 3; i++) {
            int friend = Integer.parseInt(tokenizer.nextToken());
            set.add(friend);
            friends[i] = friend;
        }

        int M = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            int D = Integer.parseInt(tokenizer.nextToken());
            int E = Integer.parseInt(tokenizer.nextToken());
            int L = Integer.parseInt(tokenizer.nextToken());
            map.get(D).add(new House(E, L));
            map.get(E).add(new House(D, L));
        }

        for (int i = 0; i < 3; i++) {
            dijkstra(i);
        }
        System.out.println(distanceCalculation());
    }

    static void dijkstra(int friend) {
        Queue<House> queue = new PriorityQueue<>(Comparator.comparingInt(h -> h.cost));
        int start = friends[friend];
        dist[friend][start] = 0;
        queue.add(new House(start, 0));
        while (!queue.isEmpty()) {
            House poll = queue.poll();
            int num = poll.num;

            for (House house : map.get(num)) {
                int next = house.num;
                int cost = house.cost;
                if (dist[friend][next] > dist[friend][num] + cost) {
                    dist[friend][next] = dist[friend][num] + cost;
                    queue.add(new House(next, dist[friend][next]));
                }
            }
        }
    }

    static int distanceCalculation() {
        int answer = 0;
        int MAX = Integer.MIN_VALUE;
        List<int[]> calc = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            if (set.contains(i)) {
                continue;
            }
            int MIN = INF;
            for (int j = 0; j < 3; j++) {
                if (dist[j][i] < MIN) {
                    MIN = dist[j][i];
                }
            }
            calc.add(new int[] {i, MIN});
        }

        for (int[] ints : calc) {
            int num = ints[0];
            int dist = ints[1];
            if (dist > MAX) {
                MAX = dist;
                answer = num;
            }
        }

        return answer;
    }
}
