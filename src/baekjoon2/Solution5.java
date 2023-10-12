package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 13700 완전 범죄
 */
public class Solution5 {
    static class Now {
        int now;
        int step;

        public Now(int now, int step) {
            this.now = now;
            this.step = step;
        }
    }
    static int N;
    static int S;
    static int D;
    static int F;
    static int B;
    static int K;
    static boolean[] building;
    static boolean[] visited;
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] split = bufferedReader.readLine().split(" ");
        N = Integer.parseInt(split[0]); //건물의 개수
        S = Integer.parseInt(split[1]); //털린 금은방
        D = Integer.parseInt(split[2]); //대도의 집
        F = Integer.parseInt(split[3]); //앞으로 갈 수 있는 건물 수
        B = Integer.parseInt(split[4]); //뒤로 갈 수 있는 건물 수
        K = Integer.parseInt(split[5]); //경찰서의 개수
        
        building = new boolean[N + 1];
        visited = new boolean[N + 1];

        if (K != 0) {
            StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
            while (tokenizer.hasMoreTokens()) {
                int police = Integer.parseInt(tokenizer.nextToken());
                building[police] = true;
            }
        }

        if (bfs(S)) {
            System.out.println(count);
        } else {
            System.out.println("BUG FOUND");
        }
    }

    private static boolean bfs(int s) {
        Queue<Now> queue = new LinkedList<>();
        queue.add(new Now(s, 0));

        while (!queue.isEmpty()) {
            Now poll = queue.poll();
            int now = poll.now;
            int step = poll.step;

            if (visited[now]) {
                continue;
            } else {
                visited[now] = true;
            }

            if (now == D) {
                count = step;
                return true;
            }

            if (now + F <= N && !building[now + F]) {
                queue.add(new Now(now + F, step + 1));
            }

            if (now - B >= 1 && !building[now - B]) {
                queue.add(new Now(now - B, step + 1));
            }
        }
        return false;
    }
}
