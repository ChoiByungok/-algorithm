package baekjoon4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 21924 도시건설 (Gold4)
 * 프림알고리즘을 사용하여 최소신장 트리의 가중치를 구한 뒤
 * 모든 길의 가중치에서 그 값을 빼면 된다.
 * 모든 빌딩을 이을 수 없으면 -1을 출력하면 된다.
 */
public class Solution26 {
    static class Building {
        int num;
        int cost;

        public Building(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }
    }
    static int N;
    static List<ArrayList<Building>> buildings = new ArrayList<>();
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());
        visited = new boolean[N + 1];
        for (int i = 0; i <= N; i++) {
            buildings.add(new ArrayList<>());
        }

        long total = 0;
        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            int c = Integer.parseInt(tokenizer.nextToken());
            buildings.get(a).add(new Building(b, c));
            buildings.get(b).add(new Building(a, c));
            total += c;
        }

        long prim = prim();
        if (prim == -1) {
            System.out.println(prim);
        } else {
            System.out.println(total - prim);
        }
    }

    static long prim() {
        long answer = 0;
        Queue<Building> queue = new PriorityQueue<>(Comparator.comparingInt(b -> b.cost));
        queue.add(new Building(1, 0));
        while (!queue.isEmpty()) {
            Building poll = queue.poll();
            int now = poll.num;
            int cost = poll.cost;
            if (visited[now]) {
                continue;
            }
            visited[now] = true;
            answer += cost;

            if (check()) {
                return answer;
            }

            for (Building building : buildings.get(now)) {
                int next = building.num;
                if (!visited[next]) {
                    queue.add(building);
                }
            }
        }

        return -1;
    }

    static boolean check () {
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                return false;
            }
        }
        return true;
    }
}
