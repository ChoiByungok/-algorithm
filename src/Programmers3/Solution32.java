package Programmers3;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 숫자 변환하기 Lv.2
 * 간단한 bfs 문제
 */
public class Solution32 {
    static final int INF = 1000000;
    static boolean[] visited;
    public int solution(int x, int y, int n) {
        visited = new boolean[INF + 1];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {x, 0});
        visited[x] = true;
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int now = poll[0];
            int step = poll[1];
            if (now == y) {
                return step;
            }

            if (now + n <= INF && !visited[now + n]) {
                visited[now + n] = true;
                queue.add(new int[] {now + n, step + 1});
            }

            if (now * 2 <= INF && !visited[now * 2]) {
                visited[now * 2] = true;
                queue.add(new int[] {now * 2, step + 1});
            }

            if (now * 3 <= INF && !visited[now * 3]) {
                visited[now * 3] = true;
                queue.add(new int[] {now * 3, step + 1});
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Solution32 solution32 = new Solution32();
        System.out.println(solution32.solution(10, 40, 5));
        System.out.println(solution32.solution(10, 40, 30));
        System.out.println(solution32.solution(2, 5, 4));

    }
}
