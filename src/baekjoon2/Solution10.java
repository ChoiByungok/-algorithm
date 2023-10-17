package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 12851 숨바꼭질2
 */
public class Solution10 {
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
    static int[] time = new int[100001];
    static boolean[] visited = new boolean[100001];
    static StringBuilder answer = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] split = bufferedReader.readLine().split(" ");

        N = Integer.parseInt(split[0]);
        K = Integer.parseInt(split[1]);

        bfs();
        System.out.println(answer);
    }

    private static void bfs() {
        int count = 0;
        int minTime = Integer.MAX_VALUE;
        Queue<Subin> queue = new LinkedList<>();
        queue.add(new Subin(N, 0));
        while (!queue.isEmpty()) {
            Subin subin = queue.poll();
            int now = subin.now;
            int sec = subin.sec;
            time[now] = sec;
            visited[now] = true;

            if (sec > minTime) {
                continue;
            }
            if (now == K) {
                minTime = sec;
                count++;
            }

            if (now + 1 <= 100000) {
                int next = now + 1;
                if (!visited[next]) {
                    queue.add(new Subin(next, sec + 1));
                } else {
                    if (time[next] == sec + 1) {
                        queue.add(new Subin(next, sec + 1));
                    }
                }
            }

            if (now - 1 >= 0) {
                int next = now - 1;
                if (!visited[next]) {
                    queue.add(new Subin(next, sec + 1));
                } else {
                    if (time[next] == sec + 1) {
                        queue.add(new Subin(next, sec + 1));
                    }
                }
            }

            if (now * 2 <= 100000) {
                int next = now * 2;
                if (!visited[next]) {
                    queue.add(new Subin(next, sec + 1));
                } else {
                    if (time[next] == sec + 1) {
                        queue.add(new Subin(next, sec + 1));
                    }
                }
            }
        }
        answer.append(minTime).append("\n").append(count);
    }
}
