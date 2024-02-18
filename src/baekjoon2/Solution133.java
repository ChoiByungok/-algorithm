package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 11559 Puyo Puyo (Gold4)
 * 1. 같은색의 블록이 상하좌우 인접한 곳에 4개이상 있는지 확인 할것 하나라도 없으면 반복문 탈출
 * 2. 반복문을 반복할때마다 answer 값을 1씩 증가시켜준다.
 * 3. 같은 색의 블록이 상하좌우 인접한 곳에 4개이상 있을 시 해당 블록들을 모두 터트림
 * 4. 터진 블록에 의해 빈공간이 된곳을 위에서 내리면서 메워준다.
 * 5. 그렇게 맵을 재정의 해주고 다시 1번으로 돌아간다.
 * 6. 반복문을 나간뒤 answer 값을 출력시켜주면 된다.
 * 다 풀고나니 간단해보이는데 막상 풀때는 되게 생각할게 많은 문제
 */
public class Solution133 {
    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static final int N = 12;
    static final int M = 6;
    static char[][] map = new char[N][M];
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        int answer = 0;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < N; i++) {
            String readLine = bufferedReader.readLine();
            for (int j = 0; j < readLine.length(); j++) {
                map[i][j] = readLine.charAt(j);
            }
        }
        while (count() != 0) {
            answer++;
            redefine();
            print();
        }
        System.out.println(answer);
    }

    static void print() {
        System.out.println();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }

    static int count() {
        int count = 0;
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                char c = map[i][j];
                if (c != '.' && !visited[i][j]) {
                    if (bfs(i, j, c) >= 4) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    private static int bfs(int x, int y, char c) {
        Queue<Point> queue = new LinkedList<>();
        List<Point> list = new ArrayList<>();
        queue.add(new Point(x, y));
        visited[x][y] = true;
        while (!queue.isEmpty()) {
            Point poll = queue.poll();
            int nowX = poll.x;
            int nowY = poll.y;
            list.add(new Point(nowX, nowY));

            if (nowX + 1 < N && !visited[nowX + 1][nowY] && map[nowX + 1][nowY] == c) {
                queue.add(new Point(nowX + 1, nowY));
                visited[nowX + 1][nowY] = true;
            }

            if (nowX - 1 >= 0 && !visited[nowX - 1][nowY] && map[nowX - 1][nowY] == c) {
                queue.add(new Point(nowX - 1, nowY));
                visited[nowX - 1][nowY] = true;
            }

            if (nowY + 1 < M && !visited[nowX][nowY + 1] && map[nowX][nowY + 1] == c) {
                queue.add(new Point(nowX, nowY + 1));
                visited[nowX][nowY + 1] = true;
            }

            if (nowY - 1 >= 0 && !visited[nowX][nowY - 1] && map[nowX][nowY - 1] == c) {
                queue.add(new Point(nowX, nowY - 1));
                visited[nowX][nowY - 1] = true;
            }
        }
        if (list.size() >= 4) {
            for (Point point : list) {
                map[point.x][point.y] = '.';
            }
        }
        return list.size();
    }

    static void redefine() {
        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != '.' && map[i + 1][j] == '.') {
                    int up = 0;
                    for (int k = i - 1; k >= 0; k--) {
                        if (map[k][j] == '.') {
                            up = k + 1;
                            break;
                        }
                    }

                    int down = N - 1;
                    for (int k = i + 1; k < N; k++) {
                        if (map[k][j] != '.') {
                            down = k - 1;
                            break;
                        }
                    }

                    if (up == 0) {
                        for (int k = down; k >= up + 1; k--) {
                            map[k][j] = map[k - 1][j];
                        }
                        map[up][j] = '.';
                    } else {
                        for (int k = down; k >= up; k--) {
                            map[k][j] = map[k - 1][j];
                        }
                    }
                }
            }
        }
    }
}
