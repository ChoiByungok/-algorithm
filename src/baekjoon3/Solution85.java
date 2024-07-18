package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 1504 특정한 최단 경로 (Gold4)
 * 최단 경로를 찾는 문제인데 이 문제는 1 -> N 을 가는데 입력받은 두 정점을 무조건 거쳐서 N으로 가야한다.
 * 그렇다면 경우의 수가 2가지가 나오는데
 * 1 -> v1 -> v2 -> N 혹은
 * 1 -> v2 -> v1 -> N 이다.
 * 그 이후에는 노가다를 해서 최단 경로를 구해준다.
 * 근데 지문에서 경로가 없는 경우엔 -1을 출력하랬으니
 * 중간에 경로가 없다 싶으면 연산을 그만 진행하면 된다.
 * 그리하여 나온 두가지 경우의수 최단거리중
 * 더 짧은것을 출력하면 된다.
 */
public class Solution85 {
    static class Vertex {
        int num;
        int weight;

        public Vertex(int num, int weight) {
            this.num = num;
            this.weight = weight;
        }
    }
    static List<ArrayList<Vertex>> graph = new ArrayList<>();
    static int N;
    static final int INF = 987654321;
    static int[] distance;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            int c = Integer.parseInt(tokenizer.nextToken());

            graph.get(a).add(new Vertex(b, c));
            graph.get(b).add(new Vertex(a, c));
        }
        tokenizer = new StringTokenizer(bufferedReader.readLine());
        int v1 = Integer.parseInt(tokenizer.nextToken());
        int v2 = Integer.parseInt(tokenizer.nextToken());

        int answer1 = 0;
        boolean impossible1 = false;

        dijkstra(1);
        if (distance[v1] != INF) {
            answer1 += distance[v1];
        } else {
            impossible1 = true;
        }

        if (!impossible1) {
            dijkstra(v1);
            if (distance[v2] != INF) {
                answer1 += distance[v2];
            } else {
                impossible1 = true;
            }
        }

        if (!impossible1) {
            dijkstra(v2);
            if (distance[N] != INF) {
                answer1 += distance[N];
            } else {
                impossible1 = true;
            }
        }

        int answer2 = 0;
        boolean impossible2 = false;
        dijkstra(1);
        if (distance[v2] != INF) {
            answer2 += distance[v2];
        } else {
            impossible2 = true;
        }

        if (!impossible2) {
            dijkstra(v2);
            if (distance[v1] != INF) {
                answer2 += distance[v1];
            } else {
                impossible2 = true;
            }
        }

        if (!impossible2) {
            dijkstra(v1);
            if (distance[N] != INF) {
                answer2 += distance[N];
            } else {
                impossible2 = true;
            }
        }

        if (impossible1 && impossible2) {
            System.out.println(-1);
        } else if (impossible1) {
            System.out.println(answer2);
        } else if (impossible2) {
            System.out.println(answer1);
        } else {
            System.out.println(Math.min(answer1, answer2));
        }
    }

    static void dijkstra(int start) {
        distance = new int[N + 1];
        Arrays.fill(distance, INF);
        distance[start] = 0;
        Queue<Vertex> queue = new PriorityQueue<>(Comparator.comparingInt(v -> v.weight));
        queue.add(new Vertex(start, 0));
        
        while (!queue.isEmpty()) {
            Vertex poll = queue.poll();
            int now = poll.num;

            for (Vertex vertex : graph.get(now)) {
                int next = vertex.num;
                int weight = vertex.weight;
                if (distance[next] > distance[now] + weight) {
                    distance[next] = distance[now] + weight;
                    queue.add(new Vertex(next, distance[next]));
                }
            }
        }
    }
}
