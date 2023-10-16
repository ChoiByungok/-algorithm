package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 13913 숨바꼭질4
 */
public class Solution9 {
    static class Subin {
        int now;
        int sec;

        public Subin(int now, int sec) {
            this.now = now;
            this.sec = sec;
        }

    }
    static int N;
    static int K;
    static StringBuilder answer = new StringBuilder();
    static boolean[] visited = new boolean[100001];
    static int[] parent = new int[100001];

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] split = bufferedReader.readLine().split(" ");

        N = Integer.parseInt(split[0]);
        K = Integer.parseInt(split[1]);

        bfs();
        System.out.println(answer);
    }

    private static void bfs() {
        Queue<Subin> queue = new LinkedList<>();
        queue.add(new Subin(N, 0));

        while (!queue.isEmpty()) {
            Subin subin = queue.poll();
            int now = subin.now;
            int sec = subin.sec;

            if (now == K) {
                answer.append(sec).append("\n");
                trace(now);
                break;
            }


            if (now + 1 <= 100000 && !visited[now + 1]) {
                queue.add(new Subin(now + 1, sec + 1));
                parent[now + 1] = now;
                visited[now + 1] = true;
            }

            if (now - 1 >= 0 && !visited[now - 1]) {
                queue.add(new Subin(now - 1, sec + 1));
                parent[now - 1] = now;
                visited[now - 1] = true;
            }

            if (now * 2 <= 100000 && !visited[now * 2]) {
                queue.add(new Subin(now * 2, sec + 1));
                parent[now * 2] = now;
                visited[now * 2] = true;
            }
        }
    }

    private static void trace(int trace) {
        Stack<Integer> stack = new Stack<>();
        while (trace != N) {
            stack.add(trace);
            trace = parent[trace];
        }
        stack.add(N);
        while (!stack.isEmpty()) {
            answer.append(stack.pop()).append(" ");
        }
    }
}
