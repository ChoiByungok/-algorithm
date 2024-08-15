package baekjoon4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 16398 행성 연결 (Gold4)
 * 최소신장트리 구하는 문제
 * 익숙한 프림 알고리즘을 사용하여 풀었는데 62퍼센트에서 틀렸다고 나옴
 * 알고보니 정답이 int형 범위를 벗어나기 때문
 * 그래서 long 으로 바꾸고 제출했더니 통과했다.
 * 다음에는 크루스칼 알고리즘을 사용해서 풀어봐야겠다.
 */
public class Solution13 {
    static class Planet {
        int num;
        int cost;

        public Planet(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }
    }

    static List<ArrayList<Planet>> flow = new ArrayList<>();
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());

        for (int i = 0; i < N; i++) {
            flow.add(new ArrayList<>());
        }

        for (int i = 0; i < N; i++) {
            StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < N; j++) {
                int cost = Integer.parseInt(tokenizer.nextToken());
                if (i == j) {
                    continue;
                }
                flow.get(i).add(new Planet(j, cost));
            }
        }
        System.out.println(prim());
    }

    static long prim() {
        long answer = 0;
        int count = 0;
        boolean[] visited = new boolean[N];
        Queue<Planet> queue = new PriorityQueue<>(Comparator.comparingInt(p -> p.cost));
        queue.add(new Planet(0, 0));
        while (!queue.isEmpty()) {
            Planet poll = queue.poll();
            int num = poll.num;
            int cost = poll.cost;

            if (visited[num]) {
                continue;
            }
            visited[num] = true;
            answer += cost;
            count++;
            if (count == N) {
                break;
            }
            for (Planet planet : flow.get(num)) {
                queue.add(new Planet(planet.num, planet.cost));
            }
        }

        return answer;
    }
}
