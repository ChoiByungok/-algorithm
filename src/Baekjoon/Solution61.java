package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 1012 유기농 배추
 */
public class Solution61 {
    static class Cabbage {
        int row;
        int column;

        public Cabbage(int x, int y) {
            this.row = x;
            this.column = y;
        }
    }
    static boolean[][] map;
    static StringBuilder answer = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < T; i++) {
            String[] split = bufferedReader.readLine().split(" ");
            int M = Integer.parseInt(split[0]); //가로길이
            int N = Integer.parseInt(split[1]); //세로길이
            int cabbage = Integer.parseInt(split[2]); //배추 갯수
            map = new boolean[M][N];
            for (int j = 0; j < cabbage; j++) {
                String[] coordinate = bufferedReader.readLine().split(" ");
                int x = Integer.parseInt(coordinate[0]);
                int y = Integer.parseInt(coordinate[1]);
                map[x][y] = true;
            }
            search();
        }
        System.out.println(answer);
    }
    static void search() {
        Queue<Cabbage> queue = new LinkedList<>();
        int bug = 0;
        int m = map.length;
        int n = map[0].length;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j]) {
                    bug++;
                    queue.add(new Cabbage(i, j));
                    while (!queue.isEmpty()) {
                        Cabbage now = queue.poll();
                        int row = now.row;
                        int column = now.column;

                        if (!map[row][column]) {
                            continue;
                        } else {
                            map[row][column] = false;
                        }

                        if (row + 1 < m) {
                            queue.add(new Cabbage(row + 1, column));
                        }
                        if (row - 1 >= 0) {
                            queue.add(new Cabbage(row - 1, column));
                        }
                        if (column + 1 < n) {
                            queue.add(new Cabbage(row, column + 1));
                        }
                        if (column - 1 >= 0) {
                            queue.add(new Cabbage(row, column - 1));
                        }
                    }
                }
            }
        }
        answer.append(bug).append("\n");
    }
}
