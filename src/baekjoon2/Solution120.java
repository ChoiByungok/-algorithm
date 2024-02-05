package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 16234 인구 이동 (Gold4)
 * 기존의 그래프 탐색과 비슷하나 조건이 하나 더 붙는다.
 * 해당 국가의 인구수의 값과 인접한 국가의 인구수의 값의 차가 L이상 R이하여야 한다.
 * 그렇게 탐색한 국가의 수와 각 국가의 인구수의 총합으로 평균을 구하고
 * 방금 이동했던 국가들의 인구수를 평균값으로 바꾸어준다.
 * 그렇게 계속 반복하다보면 더이상 국경선이 열리지 않게 되는데
 * 이때 반복문을 탈출 시켜주면 된다. 나같은 경우는 bfs() 메서드를 호출할 때
 * 카운트변수를 증가시켜주었고 이 값이 N*N이 되는순간 반복문을 탈출 시켜주었다.
 * 3*3 배열의 맵에 bfs 메서드가 9번 호출되었다는건 국경선이 열리지 않았다는 뜻이기 때문이다.
 */
public class Solution120 {
    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int N;
    static int L;
    static int R;
    static int[][] map;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] nlr = bufferedReader.readLine().split(" ");
        int answer = 0;
        N = Integer.parseInt(nlr[0]);
        L = Integer.parseInt(nlr[1]);
        R = Integer.parseInt(nlr[2]);
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        while (true) {
            int count = 0;
            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        bfs(i, j);
                        count++;
                    }
                }
            }
            if (count == N * N) {
                break;
            }
            answer++;
        }
        System.out.println(answer);
    }

    private static void bfs(int i, int j) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(i, j));
        int total = 0;
        int count = 0;
        List<int[]> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            Point poll = queue.poll();
            int x = poll.x;
            int y = poll.y;

            if (visited[x][y]) {
                continue;
            } else {
                visited[x][y] = true;
            }

            count++;
            total += map[x][y];
            list.add(new int[]{x, y});
            if (x + 1 < N
            && Math.abs(map[x + 1][y] - map[x][y]) >= L
            && Math.abs(map[x + 1][y] - map[x][y]) <= R) {
                queue.add(new Point(x + 1, y));
            }

            if (x - 1 >= 0
            && Math.abs(map[x - 1][y] - map[x][y]) >= L
            && Math.abs(map[x - 1][y] - map[x][y]) <= R) {
                queue.add(new Point(x - 1, y));
            }

            if (y + 1 < N
            && Math.abs(map[x][y + 1] - map[x][y]) >= L
            && Math.abs(map[x][y + 1] - map[x][y]) <= R) {
                queue.add(new Point(x, y + 1));
            }

            if (y - 1 >= 0
            && Math.abs(map[x][y - 1] - map[x][y]) >= L
            && Math.abs(map[x][y - 1] - map[x][y]) <= R) {
                queue.add(new Point(x, y - 1));
            }
        }
        int avg = total / count;
        for (int[] ints : list) {
            map[ints[0]][ints[1]] = avg;
        }
    }
}
