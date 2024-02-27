package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 21609 상어 중학교 (Gold2)
 * 예제도 맞고 질문게시판에 반례들도 다 맞는데
 * 1퍼센트에서 틀림 나중에 재도전
 */
public class Solution142 {
    static class Group {
        List<Point> list;
        int row;
        int col;
        int rainbow;

        public Group(List<Point> list, int row, int col, int rainbow) {
            this.list = list;
            this.row = row;
            this.col = col;
            this.rainbow = rainbow;
        }
    }
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
    static boolean[][] visited;
    static List<Group> groups;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        int answer = 0;
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        while (true) {
            groups = new ArrayList<>();
            visited = new boolean[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] > 0 && map[i][j] <= M) {
                        visited[i][j] = true;
                        bfs(i, j, map[i][j]);
                    }
                }
            }
            if (groups.isEmpty()) {
                break;
            }
            groups.sort((o1, o2) -> {
                if (o1.list.size() == o2.list.size()) {
                    if (o1.rainbow == o2.rainbow) {
                        if (o1.row == o2.row) {
                            return o2.col - o1.col;
                        }
                        return o2.row - o1.row;
                    }
                    return o2.rainbow - o1.rainbow;
                }
                return o2.list.size() - o1.list.size();
            });

            Group group = groups.get(0);
            List<Point> list = group.list;
            for (Point point : list) {
                map[point.x][point.y] = M + 1;
            }
            answer += list.size() * list.size();
            gravity();
            map = rotate();

            gravity();
        }

        System.out.println(answer);
    }

    static void bfs(int i, int j, int color) {
        List<Point> list = new ArrayList<>();
        List<Point> rainbowList = new ArrayList<>();
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(i, j));
        int rainbow  = 0;

        while (!queue.isEmpty()) {
            Point now = queue.poll();
            int x = now.x;
            int y = now.y;
            list.add(now);

            if (map[x][y] == 0) {
                rainbowList.add(new Point(x, y));
                rainbow++;
            }

            if (x + 1 < N && !visited[x + 1][y] && (map[x + 1][y] == 0 || map[x + 1][y] == color)) {
                visited[x + 1][y] = true;
                queue.add(new Point(x + 1, y));
            }

            if (x - 1 >= 0 && !visited[x - 1][y] && (map[x - 1][y] == 0 || map[x - 1][y] == color)) {
                visited[x - 1][y] = true;
                queue.add(new Point(x - 1, y));
            }

            if (y + 1 < N && !visited[x][y + 1] && (map[x][y + 1] == 0 || map[x][y + 1] == color)) {
                visited[x][y + 1] = true;
                queue.add(new Point(x, y + 1));
            }

            if (y - 1 >= 0 && !visited[x][y - 1] && (map[x][y - 1] == 0 || map[x][y - 1] == color)) {
                visited[x][y - 1] = true;
                queue.add(new Point(x, y - 1));
            }
        }

        for (Point point : rainbowList) {
            visited[point.x][point.y] = false;
        }

        if (list.size() >= 2) {
            groups.add(new Group(list, i, j, rainbow));
        }
    }

/*
    static void print() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(map[i][j] <= M ? map[i][j] == -1 ? "■|" : map[i][j] + "|" : " |");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void print2() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(map[i][j] == -1 ? "■|" : map[i][j] + "|");
            }
            System.out.println();
        }
        System.out.println();
    }
*/

    private static void gravity() {
        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] >= 0 && map[i][j] <= M && map[i + 1][j] > M) {
                    int up = 0;
                    int down = N - 1;
                    for (int k = i + 1; k <= N - 1; k++) {
                        if ((map[k][j] > 0 && map[k][j] <= M) || map[k][j] == -1) {
                            down = k - 1;
                            break;
                        }
                    }

                    for (int k = i - 1; k >= 0; k--) {
                        if (map[k][j] > M || map[k][j] == -1) {
                            up = k + 1;
                            break;
                        }
                    }
                    if (up == i) {
                        int temp = map[i][j];
                        map[i][j] = map[down][j];
                        map[down][j] = temp;
                    } else {
                        for (int k = i; k >= up; k--) {
                            int temp = map[k][j];
                            map[k][j] = map[down][j];
                            map[down][j] = temp;
                            down--;
                        }
                    }
                }
            }
        }
    }

    static int[][] rotate() {
        int[][] newMap = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                newMap[i][j] = map[j][N - 1 - i];
            }
        }
        return newMap;
    }
}
