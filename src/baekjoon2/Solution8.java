package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 1697 숨바꼭질
 */
public class Solution8 {
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
    static boolean[] visited = new boolean[100001];
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] split = bufferedReader.readLine().split(" ");

        N = Integer.parseInt(split[0]);
        K = Integer.parseInt(split[1]);

        System.out.println(bfs());
    }

    private static int bfs() {
        int answer = 0;
        Queue<Subin> queue = new LinkedList<>();
        queue.add(new Subin(N, 0));

        while (!queue.isEmpty()) {
            Subin subin = queue.poll();
            int now = subin.now;
            int sec = subin.sec;

            if (visited[now]) {
                continue;
            } else {
                visited[now] = true;
            }

            if (now == K) {
                answer = sec;
                break;
            }

            if (now + 1 <= 100000) {
                queue.add(new Subin(now + 1, sec + 1));
            }

            if (now - 1 >= 0) {
                queue.add(new Subin(now - 1, sec + 1));
            }

            if (now * 2 <= 100000) {
                queue.add(new Subin(now * 2, sec + 1));
            }
        }

        return answer;
    }
}
