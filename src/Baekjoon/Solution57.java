package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 13549 숨바꼭질3
 */
public class Solution57 {
    static class Location {
        public int cur;
        public int sec;

        public Location(int cur, int sec) {
            this.cur = cur;
            this.sec = sec;
        }

    }
    static boolean[] visited = new boolean[100001];
    static int K;
    static int answer;
    static Queue<Location> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.sec));
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] split = bufferedReader.readLine().split(" ");
        int N = Integer.parseInt(split[0]);
        K = Integer.parseInt(split[1]);
        bfs(N);
        System.out.println(answer);
    }

    static void bfs(int start) {
        queue.add(new Location(start, 0));
        while (!queue.isEmpty()) {
            Location now = queue.poll();
            int cur = now.cur;
            int sec = now.sec;
            if (cur < 0 || cur > 100000 || visited[cur]) {
                continue;
            } else {
                visited[cur] = true;
            }
            if (cur == K) {
                answer = sec;
                break;
            }
            queue.add(new Location(cur + 1, sec + 1));
            queue.add(new Location(cur - 1, sec + 1));
            queue.add(new Location(cur * 2, sec));
        }
    }
}
