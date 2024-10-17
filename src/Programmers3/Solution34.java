package Programmers3;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 무인도 여행 Lv.2
 * bfs 탐색을하여 숫자가 써있으며 아직 방문하지 않은 좌표를 시작점으로
 * 상하좌우 탐색하여 방문처리를 하고 해당칸에 쓰여저 있는 숫자들을 전부 더한 뒤 반환한다.
 * 반환값을 우선순위 큐 오름차순에 담아둔다.
 * 만약 우선순위큐에 아무값도 들어있지 않으면 배열에 -1 을 담아 반환하고
 * 담겨있으면 큐사이즈만한 배열을 하나 선언하여
 * 배열에 값을 담고 그 배열을 반환하면 된다.
 */
public class Solution34 {
    static int N, M;
    static boolean[][] visited;
    static int[][] dir = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public int[] solution(String[] maps) {
        Queue<Integer> queue = new PriorityQueue<>();
        N = maps.length;
        M = maps[0].length();
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                char c = maps[i].charAt(j);
                if (c == 'X') {
                    visited[i][j] = true;
                }
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j]) {
                    visited[i][j] = true;
                    queue.add(bfs(i, j, maps));
                }
            }
        }

        if (queue.isEmpty()) {
            return new int[] {-1};
        } else {
            int[] answer = new int[queue.size()];
            int index = 0;
            while (!queue.isEmpty()) {
                answer[index++] = queue.poll();
            }
            return answer;
        }
    }

    static int bfs(int i, int j, String[] maps) {
        int sum = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {i, j});
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int x = poll[0];
            int y = poll[1];
            sum += maps[x].charAt(y) - '0';

            for (int k = 0; k < 4; k++) {
                int nx = dir[k][0] + x;
                int ny = dir[k][1] + y;
                if (nx >= 0 && nx < N && ny >= 0 && ny < M && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    queue.add(new int[] {nx, ny});
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        Solution34 solution34 = new Solution34();
        System.out.println(Arrays.toString(solution34.solution(new String[]{"X591X", "X1X5X", "X231X", "1XXX1"})));
        System.out.println(Arrays.toString(solution34.solution(new String[]{"XXX","XXX","XXX"})));
    }
}
