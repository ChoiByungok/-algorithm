package baekjoon4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * 14950 정복자 (Gold4)
 * MST 문제인데 지금까지는 익숙한 프림알고리즘을 사용하여 문제를 풀었다면
 * 이번에는 크루스칼 알고리즘을 사용해 풀어보았다.
 * 사실은 간선을 가중치순으로 정렬한다는 거 까지만 알고 그 뒤에는 자세하게 기억이 나질 않았는데
 * 보니 예전에 사용해봤던 유니온 파인드 알고리즘을 사용한다.
 * 파인드 메서드로 두 정점의 부모를 탐색하여
 * 부모가 같지않으면 유니온 메서드로 두 정점을 연결하고
 * 부모가 같다면 그 두 정점사이에는 사이클이 존재한다는 뜻인것이다.
 * 다른 사람들은 MST 문제를 풀면 열에 아홉은 이런 크루스칼 알고리즘을 사용하던데
 * 나는 아직 프림알고리즘이 더 직관적이고 쉬운거같다.....
 */
public class Solution15 {
    static class Edge {
        int start;
        int end;
        int cost;

        public Edge(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }
    static int N;
    static Edge[] edges;
    static int[] parents;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());
        int T = Integer.parseInt(tokenizer.nextToken());
        int increase = 0;
        int weight = 0;
        for (int i = 1; i < N - 1; i++) {
            increase += T * i;
        }
        edges = new Edge[M];
        parents = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }
        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            int A = Integer.parseInt(tokenizer.nextToken());
            int B = Integer.parseInt(tokenizer.nextToken());
            int C = Integer.parseInt(tokenizer.nextToken());
            edges[i] = new Edge(A, B, C);
        }
        Arrays.sort(edges, Comparator.comparingInt(e -> e.cost));
        for (int i = 0; i < M; i++) {
            int start = edges[i].start;
            int end = edges[i].end;
            int cost = edges[i].cost;
            if (find(start) != find(end)) {
                union(start, end);
                weight += cost;
            }
        }
        System.out.println(weight + increase);
    }

    static int find(int x) {
        if (parents[x] == x) {
            return x;
        } else {
            return find(parents[x]);
        }
    }

    static void union(int start, int end) {
        start = find(start);
        end = find(end);

        if (start > end) {
            parents[start] = end;
        } else {
            parents[end] = start;
        }
    }
}
