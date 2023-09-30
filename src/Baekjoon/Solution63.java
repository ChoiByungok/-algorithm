package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 10026 적록색약
 */
public class Solution63 {
    static class Color {
        char color;
        int x;
        int y;

        public Color(char color, int x, int y) {
            this.color = color;
            this.x = x;
            this.y = y;
        }
    }
    static int N;
    static char[][] rgb;
    static char[][] rgBlind;
    static boolean[][] visited;
    static Queue<Color> queue = new LinkedList<>();
    static StringBuilder answer = new StringBuilder();
    static int count;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());

        rgb = new char[N][N];
        rgBlind = new char[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            String readLine = bufferedReader.readLine();
            for (int j = 0; j < readLine.length(); j++) {
                rgb[i][j] = readLine.charAt(j);
                if (readLine.charAt(j) == 'R') {
                    rgBlind[i][j] = 'G';
                } else {
                    rgBlind[i][j] = readLine.charAt(j);
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    bfs(i, j, rgb[i][j], false);
                }
            }
        }

        answer.append(count).append(" ");
        count = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j]) {
                    bfs(i, j, rgBlind[i][j], true);
                }
            }
        }

        answer.append(count);
        System.out.println(answer);
    }
    static void bfs(int x, int y, char color, boolean blind) {
        count++;
        queue.add(new Color(color, x, y));
        if (blind) {
            while (!queue.isEmpty()) {
                Color now = queue.poll();
                char nowColor = now.color;
                int nowX = now.x;
                int nowY = now.y;

                if (!visited[nowX][nowY]) {
                    continue;
                } else {
                    visited[nowX][nowY] = false;
                }

                if (nowX + 1 < N) {
                    if (rgBlind[nowX + 1][nowY] == nowColor) {
                        queue.add(new Color(nowColor, nowX + 1, nowY));
                    }
                }

                if (nowX - 1 >= 0) {
                    if (rgBlind[nowX - 1][nowY] == nowColor) {
                        queue.add(new Color(nowColor, nowX - 1, nowY));
                    }
                }

                if (nowY + 1 < N) {
                    if (rgBlind[nowX][nowY + 1] == nowColor) {
                        queue.add(new Color(nowColor, nowX, nowY + 1));
                    }
                }

                if (nowY - 1 >= 0) {
                    if (rgBlind[nowX][nowY - 1] == nowColor) {
                        queue.add(new Color(nowColor, nowX, nowY - 1));
                    }
                }
            }
        } else {
            while (!queue.isEmpty()) {
                Color now = queue.poll();
                char nowColor = now.color;
                int nowX = now.x;
                int nowY = now.y;

                if (visited[nowX][nowY]) {
                    continue;
                } else {
                    visited[nowX][nowY] = true;
                }

                if (nowX + 1 < N) {
                    if (rgb[nowX + 1][nowY] == nowColor) {
                        queue.add(new Color(nowColor, nowX + 1, nowY));
                    }
                }

                if (nowX - 1 >= 0) {
                    if (rgb[nowX - 1][nowY] == nowColor) {
                        queue.add(new Color(nowColor, nowX - 1, nowY));
                    }
                }

                if (nowY + 1 < N) {
                    if (rgb[nowX][nowY + 1] == nowColor) {
                        queue.add(new Color(nowColor, nowX, nowY + 1));
                    }
                }

                if (nowY - 1 >= 0) {
                    if (rgb[nowX][nowY - 1] == nowColor) {
                        queue.add(new Color(nowColor, nowX, nowY - 1));
                    }
                }
            }
        }
    }
}

