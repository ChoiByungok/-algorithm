package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 5972 택배 배송 (Gold5)
 * 간단한 다익스트라 알고리즘 연습 문제
 * 양방향 그래프에서 1부터 시작하여 N까지 갔을때 최소 가중치를 출력시켜주면 되는 문제이다.
 */
public class Solution50 {
    static class Barn {
        int num;
        int cow;

        public Barn(int num, int cow) {
            this.num = num;
            this.cow = cow;
        }
    }
    static boolean[] visited;
    static int[] cows;
    static List<ArrayList<Barn>> map = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());

        visited = new boolean[N + 1];
        cows = new int[N + 1];
        Arrays.fill(cows, Integer.MAX_VALUE);
        for (int i = 0; i <= N; i++) {
            map.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            int c = Integer.parseInt(tokenizer.nextToken());

            map.get(a).add(new Barn(b, c));
            map.get(b).add(new Barn(a, c));
        }

        System.out.println(dijkstra(N));
    }

    static int dijkstra(int end) {
        Queue<Barn> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.cow));
        queue.add(new Barn(1, 0));
        cows[1] = 0;

        while (!queue.isEmpty()) {
            Barn now = queue.poll();
            int num = now.num;

            if (!visited[num]) {
                visited[num] = true;

                for (Barn barn : map.get(num)) {
                    int next = barn.num;
                    int cow = barn.cow;
                    if (cows[next] > cows[num] + cow) {
                        cows[next] = cows[num] + cow;
                        queue.add(new Barn(next, cows[next]));
                    }
                }
            }
        }
        return cows[end];
    }
}
