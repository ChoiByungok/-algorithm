package Programmers3;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 미로탈출 Lv.2
 * 백준에서 많이 풀어봤던 bfs 문제
 * 시작점과 레버위치 그리고 도착지점의 좌표가 주어진다.
 * 시작점에서 레버위치로 가고 그 이후에 도착점에 갈 수 있는 구조이다.
 * 즉 시작 -> 레버위치 bfs 한번실행
 * 레버위치 -> 도착점 bfs 두번실행
 * 그리하여 도착점에 도달한다면 총 이동횟수를 출력하고
 * 가지못한다면 -1을 출력하면 된다.
 */
public class Solution30 {
    static int N, M;
    static boolean[][] visited;
    static int[][] dir = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public int solution(String[] maps) {
        N = maps.length;
        M = maps[0].length();
        int startX = 0, startY = 0;
        int leverX = 0, leverY = 0;
        int endX = 0, endY = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                char c = maps[i].charAt(j);
                if (c == 'S') {
                    startX = i;
                    startY = j;
                }

                if (c == 'L') {
                    leverX = i;
                    leverY = j;
                }

                if (c == 'E') {
                    endX = i;
                    endY = j;
                }
            }
        }
        int step1 = bfs(startX, startY, leverX, leverY, maps);
        if (step1 == -1) {
            return -1;
        }
        int step2 = bfs(leverX, leverY, endX, endY, maps);
        if (step2 == -1) {
            return -1;
        }
        return step1 + step2;
    }

    static int bfs(int sx, int sy, int ex, int ey, String[] maps) {
        visited = new boolean[N][M];
        visited[sx][sy] = true;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {sx, sy, 0});
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int x = poll[0];
            int y = poll[1];
            int step = poll[2];
            if (x == ex && y == ey) {
                return step;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dir[i][0];
                int ny = y + dir[i][1];
                if (nx >= 0 && nx < N && ny >= 0 && ny < M && !visited[nx][ny] && maps[nx].charAt(ny) != 'X') {
                    visited[nx][ny] = true;
                    queue.add(new int[] {nx, ny, step + 1});
                }
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        String[] maps = new String[] {"SOOOL", "XXXXO", "OOOOO", "OXXXX", "OOOOE"};
        System.out.println(new Solution30().solution(maps));
    }
}
