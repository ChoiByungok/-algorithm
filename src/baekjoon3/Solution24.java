package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 3055 탈출 (Gold4)
 * 고슴도치는 다음시간에 물이 찰 위치로 이동하지 못한다.
 * 그래서 나는 미리 물을 채운 뒤 고슴도치가 이동할 위치를 설정하였다.
 * 물을 채운뒤 고슴도치가 이동할수 있는 좌표를 큐에 담는다
 * 이때 이미 방문한 좌표면 넣지않는다 왜냐하면 이미 방문한 위치에 다시 간다는 것은 최단경로가 아니라는 뜻이기 때문이다.
 * 그렇게 고슴도치가 갈 수 있는 위치를 큐에 담고나서
 * 큐가 비어있으면 고슴도치는 비버굴로 갈수없다는 뜻이기에 반복문을 탈출시킨다.
 * 아니면 다시 물을 채우고 고슴도치가 갈 수 있는 좌표를 계산한다.
 * 그렇게 고슴도치가 비버굴 좌표에 도달하면 이동한 거리를 출력해주면 된다.
 */
public class Solution24 {
    static class Point {
        int x;
        int y;

        public Point() {
        }

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void set(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int R;
    static int C;
    static char[][] map;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int answer = 0;
        boolean KAKTUS = true;
        String[] split = bufferedReader.readLine().split(" ");
        R = Integer.parseInt(split[0]);
        C = Integer.parseInt(split[1]);
        map = new char[R][C];
        visited = new boolean[R][C];
        Point beaverCave = new Point();
        Point hedgehog = new Point();
        Queue<Point> queue = new LinkedList<>();
        for (int i = 0; i < R; i++) {
            String readLine = bufferedReader.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = readLine.charAt(j);
                if (map[i][j] == '.') {
                    visited[i][j] = true;
                }
                if (map[i][j] == 'D') {
                    visited[i][j] = true;
                    beaverCave.set(i, j);
                }
                if (map[i][j] =='S') {
                    map[i][j] = '.';
                    hedgehog.set(i, j);
                }
            }
        }
        queue.add(hedgehog);
        label:
        while (!queue.isEmpty()) {
            flood();
            Queue<Point> points = new LinkedList<>();
            while (!queue.isEmpty()) {
                Point poll = queue.poll();
                int x = poll.x;
                int y = poll.y;
                if (x == beaverCave.x && y == beaverCave.y) {
                    KAKTUS = false;
                    break label;
                }

                if (x + 1 < R && visited[x + 1][y]) {
                    visited[x + 1][y] = false;
                    points.add(new Point(x + 1, y));
                }

                if (x - 1 >= 0 && visited[x - 1][y]) {
                    visited[x - 1][y] = false;
                    points.add(new Point(x - 1, y));
                }

                if (y + 1 < C && visited[x][y + 1]) {
                    visited[x][y + 1] = false;
                    points.add(new Point(x, y + 1));
                }

                if (y - 1 >= 0 && visited[x][y - 1]) {
                    visited[x][y - 1] = false;
                    points.add(new Point(x, y - 1));
                }
            }
            queue = points;
            answer++;
        }

        System.out.println(KAKTUS ? "KAKTUS" : answer);
    }

    static void flood() {
        List<Point> list = new ArrayList<>();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == '*') {
                    if (i - 1 >= 0 && map[i - 1][j] == '.' && map[i - 1][j] != 'D') {
                        list.add(new Point(i - 1, j));
                    }
                    if (i + 1 < R && map[i + 1][j] == '.' && map[i + 1][j] != 'D') {
                        list.add(new Point(i + 1, j));
                    }
                    if (j - 1 >= 0 && map[i][j - 1] == '.'&& map[i][j - 1] != 'D') {
                        list.add(new Point(i, j - 1));
                    }
                    if (j + 1 < C && map[i][j + 1] == '.' && map[i][j + 1] != 'D') {
                        list.add(new Point(i, j + 1));
                    }
                }
            }
        }
        for (Point point : list) {
            map[point.x][point.y] = '*';
            visited[point.x][point.y] = false;
        }
    }
}
