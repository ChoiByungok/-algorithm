package baekjoon4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 2412 암벽등반 (Gold4) (실패 시간초과)
 */
public class Solution40 {
    static List<int[]> rockWall = new ArrayList<>();
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int T = Integer.parseInt(tokenizer.nextToken());

        visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            int x = Integer.parseInt(tokenizer.nextToken());
            int y = Integer.parseInt(tokenizer.nextToken());
            rockWall.add(new int[] {x, y});
        }
        rockWall.sort(Comparator.comparingInt(o -> o[1]));
        System.out.println(bfs(T));
    }

    static int bfs(int T) {
        int answer = -1;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {0, 0, 0});
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int x = poll[0];
            int y = poll[1];
            int step = poll[2];

            if (y == T) {
                answer = step;
                break;
            }

            for (int i = 0; i < rockWall.size(); i++) {
                int[] ints = rockWall.get(i);
                int rx = ints[0];
                int ry = ints[1];
                if (Math.abs(ry - y) <= 2 && Math.abs(rx - x) <= 2 && !visited[i]) {
                    visited[i] = true;
                    queue.add(new int[] {rx, ry, step + 1});
                }
            }
        }
        return answer;
    }
}
