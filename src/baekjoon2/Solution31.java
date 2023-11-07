package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 1389 케빈 베이컨의 6단계 법칙
 */
public class Solution31 {
    static class Step {
        int start;
        int step;

        public Step(int start, int step) {
            this.start = start;
            this.step = step;
        }
    }
    static class Person {
        int index;
        int bacon;

        public Person(int index) {
            this.index = index;
        }

        public Person(int index, int bacon) {
            this.index = index;
            this.bacon = bacon;
        }
    }
    static int n;
    static int m;
    static boolean[] visited;
    static List<ArrayList<Integer>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = bufferedReader.readLine().split(" ");
        n = Integer.parseInt(nm[0]);
        m = Integer.parseInt(nm[1]);
        List<Person> people = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
            people.add(new Person(i));
        }

        for (int i = 0; i < m; i++) {
            String[] split = bufferedReader.readLine().split(" ");
            int a = Integer.parseInt(split[0]);
            int b = Integer.parseInt(split[1]);
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n ; j++) {
                if (i < j) {
                    visited = new boolean[n + 1];
                    int value = bfs(i, j);
                    people.get(i).bacon += value;
                    people.get(j).bacon += value;
                }
            }
        }

        people.sort((o1, o2) -> {
            if (o1.bacon == o2.bacon) {
                return o1.index - o2.index;
            }
            return o1.bacon - o2.bacon;
        });
        System.out.println(people.get(1).index);
    }

    private static int bfs(int i, int j) {
        int answer = 0;
        visited[i] = true;
        Queue<Step> queue = new LinkedList<>();
        queue.add(new Step(i, 0));
        while (!queue.isEmpty()) {
            Step poll = queue.poll();
            int start = poll.start;
            int step = poll.step;

            if (start == j) {
                answer = step;
                break;
            }

            for (Integer integer : graph.get(start)) {
                if (!visited[integer]) {
                    visited[integer] = true;
                    queue.add(new Step(integer, step + 1));
                }
            }
        }
        return answer;
    }
}
