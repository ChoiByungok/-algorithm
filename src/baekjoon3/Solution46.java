package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 4485 녹색 옷 입은 애가 젤다지? (Gold4)
 * 얼핏보면 bfs 문제같지만 해당 문제는 다익스트라 알고리즘을 사용해야 하며
 * dp와 우선순위 큐를 사용하여 문제를 해결하였다.
 * 우선순위 큐는 누적값의 오름차순으로 정렬되어있다. 그러므로 큐에서 요소를 하나 빼면
 * 누적값이 가장 작은 좌표가 큐에서 나올것이며 그 좌표의 상하좌우를 계산하여
 * 현재 배열에 저장되어있는 값보다 작을시에 갱신해주고 큐에 담아준다.
 * 그렇게 탐색을 진행하다가 목적지에 도착하면 그 값이 최소값이며 반복문을 나가주면 된다.
 */
public class Solution46 {
    static class Point {
        int accumulate;
        int x;
        int y;

        public Point(int accumulate, int x, int y) {
            this.accumulate = accumulate;
            this.x = x;
            this.y = y;
        }
    }
    static int [][] cave;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int testCase = 1;
        StringBuilder answer = new StringBuilder();
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine.equals("0")) {
                break;
            }

            N = Integer.parseInt(readLine);
            cave = new int[N][N];
            for (int i = 0; i < N; i++) {
                StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
                for (int j = 0; j < N; j++) {
                    cave[i][j] = Integer.parseInt(tokenizer.nextToken());
                }
            }
            answer.append("Problem ").append(testCase++).append(": ").append(dijkstra()).append("\n");
        }
        System.out.println(answer);
    }

    static int dijkstra() {
        Queue<Point> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.accumulate));
        int[][] route = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(route[i], Integer.MAX_VALUE);
        }
        queue.add(new Point(cave[0][0], 0, 0));
        route[0][0] = cave[0][0];

        while (!queue.isEmpty()) {
            Point poll = queue.poll();
            int accumulate = poll.accumulate;
            int x = poll.x;
            int y = poll.y;

            if (x == N - 1 && y == N - 1) {
                break;
            }

            if (x + 1 < N && route[x + 1][y] > accumulate + cave[x + 1][y]) {
                queue.add(new Point(accumulate + cave[x + 1][y], x + 1, y));
                route[x + 1][y] = accumulate + cave[x + 1][y];
            }

            if (x - 1 >= 0 && route[x - 1][y] > accumulate + cave[x - 1][y]) {
                queue.add(new Point(accumulate + cave[x - 1][y], x - 1, y));
                route[x - 1][y] = accumulate + cave[x - 1][y];
            }

            if (y + 1 < N && route[x][y + 1] > accumulate + cave[x][y + 1]) {
                queue.add(new Point(accumulate + cave[x][y + 1], x, y + 1));
                route[x][y + 1] = accumulate + cave[x][y + 1];
            }

            if (y - 1 >= 0 && route[x][y - 1] > accumulate + cave[x][y - 1]) {
                queue.add(new Point(accumulate + cave[x][y - 1], x, y - 1));
                route[x][y - 1] = accumulate + cave[x][y - 1];
            }
        }

        return route[N - 1][N - 1];
    }
}
