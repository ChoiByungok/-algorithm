package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 12761 돌다리
 */
public class Solution56 {
    static class Path {
        public int cur;
        public int step;

        public Path(int cur, int step) {
            this.cur = cur;
            this.step = step;
        }
    }
    static boolean[] visited;
    static int A;
    static int B;
    static int M;
    static Queue<Path> queue = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] split = bufferedReader.readLine().split(" ");
        A = Integer.parseInt(split[0]);
        B = Integer.parseInt(split[1]);
        int N = Integer.parseInt(split[2]);
        M = Integer.parseInt(split[3]);

        visited = new boolean[100001];
        bfs(N);
    }
    static void bfs(int N) {
        int count = 0;
        int answer = 0;
        queue.add(new Path(N, count));
        while (!queue.isEmpty()) {
            Path now = queue.poll();
            if (now.cur < 0 || now.cur > 100000) {
                continue;
            }

            if (visited[now.cur]) {
                continue;
            } else {
                visited[now.cur] = true;
            }

            if (now.cur == M) {
                answer = now.step;
                break;
            }
            queue.add(new Path(now.cur + 1, now.step + 1));
            queue.add(new Path(now.cur - 1, now.step + 1));
            queue.add(new Path(now.cur + A, now.step + 1));
            queue.add(new Path(now.cur - A, now.step + 1));
            queue.add(new Path(now.cur + B, now.step + 1));
            queue.add(new Path(now.cur - B, now.step + 1));
            queue.add(new Path(now.cur * A, now.step + 1));
            queue.add(new Path(now.cur * B, now.step + 1));
        }
        System.out.println(answer);
    }
}
