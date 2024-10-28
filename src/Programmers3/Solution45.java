package Programmers3;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 거리두기 확인하기 Lv.2
 * bfs 탐색문제 응용버전 처음에는 아무 P에서 부터 탐색을 시작하여 맨해튼거리 2 이하의 다른 P가 존재한다면 0을 반환하려고 했다.
 * 근데 처음 P 기준에는 맨해튼거리를 두고 있다고 해도 중간의 P 끼리는 서로 거리두기를 안지킬수 있기때문에 코드를 조금 수정하였다.
 * 모든 P에서 탐색을 진행하되 맨해튼 거리가 2를 넘어가면 더이상 탐색을 진행하지 않는 식으로 하고
 * 맨해튼거리가 2를 넘지않았는데 다른 P가 있다고 하면 0을 반환하도록 수정하였더니 통과되었다.
 */
public class Solution45 {
    static int[][] dir = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        for (int i = 0; i < places.length; i++) {
            int N = places[i].length;
            int M = places[i][0].length();
            boolean safety = true;
            label:
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    char c = places[i][j].charAt(k);
                    if (c == 'P') {
                        if (!bfs(j, k, N, M, places[i])) {
                            safety = false;
                            break label;
                        }
                    }
                }
            }
            answer[i] = safety ? 1 : 0;
        }
        return answer;
    }

    static boolean bfs(int i, int j, int N, int M, String[] places) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {i, j});
        boolean[][] visited = new boolean[N][M];
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int x = poll[0];
            int y = poll[1];
            if (x == i && y == j) {
                for (int k = 0; k < 4; k++) {
                    int nx = x + dir[k][0];
                    int ny = y + dir[k][1];
                    if (nx >= 0 && nx < N && ny >= 0 && ny < M && places[nx].charAt(ny) != 'X' && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        queue.add(new int[] {nx, ny});
                    }
                }
            } else {
                int manhattan = Math.abs(x - i) + Math.abs(y - j);
                if (manhattan > 2) {
                    continue;
                }
                if (places[x].charAt(y) == 'P') {
                    return false;
                }

                for (int k = 0; k < 4; k++) {
                    int nx = x + dir[k][0];
                    int ny = y + dir[k][1];
                    if (nx >= 0 && nx < N && ny >= 0 && ny < M && places[nx].charAt(ny) != 'X' && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        queue.add(new int[] {nx, ny});
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution45 solution45 = new Solution45();
        String[][] places = new String[][] {
                {"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"},
                {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"},
                {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"},
                {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"},
                {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}
        };
        System.out.println(Arrays.toString(solution45.solution(places)));
    }
}
