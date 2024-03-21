package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 1113 수영장 만들기 (Gold1)
 * 해당 칸이 물이 고일 수 있는 칸인지 아닌지 이거 구분하는거부터 기준을 못세우겠어서
 * 결국 구글링을 하여 어떻게 문제에 접근해야 하는지 알아내었다.
 * 우선 입력받으면서 가장 높은 칸의 숫자를 찾아 낸다 그 위로는 볼 필요가 없기 때문이다.
 * 그리고 3중반복문을 실행한다. 가장 외부의 반복문은 1부터 가장 높은 칸 까지 반복문을돌리는 것인데
 * 이것은 물의 높이라고 생각하면 된다. 수심이 1일때부터 가장 높은 칸 까지 수심을 1씩 높이는데 이때
 * 각각의 수심에 따라 고일 수 있는 물의 양을 계산하는 것이다. 이것을 다 합친것이 정답이 된다.
 * 이때 중요한건 해당 칸이 고일 수 있는 칸인지 아닌지 구분 하는 것인데
 * bfs를 돌다가 현재 좌표가 맵의 범위를 벗어나게 될 때 noCount 라는 boolean 변수를 true 로 주어서
 * 마지막에 noCount 가 true 이면 0을 반환하고 아니면 count 를 반환하는 식으로 해결하였다.
 * bfs 탐색을 하면서 해당 좌표가 한번이라도 맵 밖으로 벗어나지 않았다면 그 칸들은 물이 고일 수 있는 칸이고
 * 아니면 고일 수 없는 칸인 것이다. 어떻게 다른 사람들은 이 문제를 이렇게 접근할 수 있었는지 대단하다.
 */
public class Solution166 {
    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int N;
    static int M;
    static int[][] map;
    static int max = 0;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        int answer = 0;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] split = bufferedReader.readLine().split(" ");
        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String readLine = bufferedReader.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = readLine.charAt(j) - '0';
                if (max < map[i][j]) {
                    max = map[i][j];
                }
            }
        }

        for (int h = 1; h <= max; h++) {
            visited = new boolean[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] <= h && !visited[i][j]) {
                        visited[i][j] = true;
                        answer += bfs(i, j, h);
                    }
                }
            }
        }
        System.out.println(answer);
    }

    private static int bfs(int i, int j, int h) {
        int count = 0;
        boolean noCount = false;
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(i, j));
        while (!queue.isEmpty()) {
            Point poll = queue.poll();
            int x = poll.x;
            int y = poll.y;
            count++;

            if (x - 1 >= 0) {
                if (!visited[x - 1][y] && map[x - 1][y] <= h) {
                    visited[x - 1][y] = true;
                    queue.add(new Point(x - 1, y));
                }
            } else {
                noCount = true;
            }

            if (x + 1 < N) {
                if (!visited[x + 1][y] && map[x + 1][y] <= h) {
                    visited[x + 1][y] = true;
                    queue.add(new Point(x + 1, y));
                }
            } else {
                noCount = true;
            }

            if (y - 1 >= 0) {
                if (!visited[x][y - 1] && map[x][y - 1] <= h) {
                    visited[x][y - 1] = true;
                    queue.add(new Point(x, y - 1));
                }
            } else {
                noCount = true;
            }

            if (y + 1 < M) {
                if (!visited[x][y + 1] && map[x][y + 1] <= h) {
                    visited[x][y + 1] = true;
                    queue.add(new Point(x, y + 1));
                }
            } else {
                noCount = true;
            }
        }

        return noCount ? 0 : count;
    }
}
