package baekjoon4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 14621 나만 안되는 연애 (Gold3)
 * 최소신장 트리를 구현하는 문제인데 연결된 노드가 같은 속성의 대학이면 안된다.
 * 그래서 처음부터 간선이 입력으로 주어졌을 때 같은 속성의 정점 사이에 연결된 간선은 연결조차도 하지 않았다.
 * 그리고 프림 알고리즘을 사용하여 문제를 해결하였는데
 * if (visited[num]) continue; << 이 로직을 넣지 않아서 계속 틀렸다.
 */
public class Solution12 {
    static class School {
        int num;
        int cost;

        public School(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }
    }
    static List<ArrayList<School>> graph = new ArrayList<>();
    static int N;
    static String[] gender;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }

        gender = new String[N];
        visited = new boolean[N];
        tokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < N; i++) {
            gender[i] = tokenizer.nextToken();
        }

        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            int u = Integer.parseInt(tokenizer.nextToken()) - 1;
            int v = Integer.parseInt(tokenizer.nextToken()) - 1;
            int d = Integer.parseInt(tokenizer.nextToken());
            if (!gender[u].equals(gender[v])) {
                graph.get(u).add(new School(v, d));
                graph.get(v).add(new School(u, d));
            }
        }
        System.out.println(prim());
    }

    static int prim () {
        int answer = 0;
        int count = 0;
        Queue<School> queue = new PriorityQueue<>(Comparator.comparingInt(s -> s.cost));
        queue.add(new School(0, 0));
        while (!queue.isEmpty()) {
            School poll = queue.poll();
            int num = poll.num;
            if (visited[num]) { // 이 로직을 무조건 넣어야 함
                continue;
            }
            visited[num] = true;
            answer += poll.cost;
            count++;
            if (count == N) {
                break;
            }
            for (School school : graph.get(num)) {
                int next = school.num;
                if (!visited[next]) {
                    queue.add(new School(next, school.cost));
                }
            }
        }
        return check() ? answer : -1;
    }

    static boolean check() {
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                return false;
            }
        }
        return true;
    }
}
