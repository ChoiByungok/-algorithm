package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 2665 미로만들기 (Gold4)
 * 2차원 배열로 구성된 맵에서 (0,0) 시작점에 있는 사람이 (N-1,N-1)까지 가는데
 * 최소 몇개의 벽만 부셔야 하나 구하는 문제
 * 지금으로선 어떻게 접근을 해야하는지 가늠조차 잡히지 않는다.
 * 결국 다른사람의 풀이를 살짝 참조 하였다.
 * 평소처럼 bfs 탐색은 하되 다음 칸이 벽이면 벽 카운트를 1증가시켜 큐에 넣어주고
 * 아니면 그대로 큐에 넣어주었다.
 * 이때 약간의 최적화를 위해 카운트순서대로 오름차순으로 정렬되는 우선순위큐를 사용하였고
 * 우선순위 큐를 사용했으므로 좌표가 목적지에 도착했을때 가장 적은 벽을 부수고 온 좌표가 될것이므로 그 카운트를 출력시켜주었다.
 */
public class Solution91 {
    static class Point {
        int x;
        int y;
        int breakCount;

        public Point(int x, int y, int breakCount) {
            this.x = x;
            this.y = y;
            this.breakCount = breakCount;
        }
    }
    static int N;
    static boolean[][] map;
    static boolean[][] visited;
    static int[] dirX = new int[] {1, -1, 0, 0};
    static int[] dirY = new int[] {0, 0, 1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());
        map = new boolean[N][N];
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            String readLine = bufferedReader.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = readLine.charAt(j) == '1';
            }
        }

        System.out.println(bfs());
    }

    static int bfs() {
        Queue<Point> queue = new PriorityQueue<>(Comparator.comparingInt(p -> p.breakCount));
        queue.add(new Point(0, 0, 0));
        visited[0][0] = true;
        int answer = 0;
        while (!queue.isEmpty()) {
            Point point = queue.poll();
            int breakCount = point.breakCount;

            if (point.x == N - 1 && point.y == N - 1) {
                answer = breakCount;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nextX = point.x + dirX[i];
                int nextY = point.y + dirY[i];

                if (nextX >= 0 && nextX < N && nextY >= 0 && nextY < N && !visited[nextX][nextY]) {
                    visited[nextX][nextY] = true;
                    queue.add(map[nextX][nextY] ? new Point(nextX, nextY, breakCount) : new Point(nextX, nextY, breakCount + 1));
                }
            }
        }
        return answer;
    }
}
