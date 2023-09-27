package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 2667 단지번호붙이기
 */
public class Solution60 {
    static class Start {
        int i;
        int j;

        public Start(int i, int j) {
            this.i = i;
            this.j = j;
        }

    }
    static Queue<Start> queue = new LinkedList<>();
    static List<Integer> list = new ArrayList<>();
    static StringBuilder answer = new StringBuilder();
    static boolean[][] visited;
    static int N;

    public static void main(String[] args) throws IOException {
        init();

        for (int i = 0; i < visited.length; i++) {
            for (int j = 0; j < visited[i].length; j++) {
                if (visited[i][j]) {
                    search(i, j);
                }
            }
        }

        answer();
    }

    static void search(int i, int j) {
        int area = 0;
        queue.add(new Start(i, j));
        while (!queue.isEmpty()) {
            Start now = queue.poll();
            int nowI = now.i;
            int nowJ = now.j;

            if (!visited[nowI][nowJ]) {
                continue;
            } else {
                visited[nowI][nowJ] = false;
            }

            if (nowI + 1 < N && visited[nowI + 1][nowJ]) {
                queue.add(new Start(nowI + 1, nowJ));
            }
            if (nowI > 0 && visited[nowI - 1][nowJ]) {
                queue.add(new Start(nowI - 1, nowJ));
            }
            if (nowJ + 1 < N && visited[nowI][nowJ + 1]) {
                queue.add(new Start(nowI, nowJ + 1));
            }
            if (nowJ > 0 && visited[nowI][nowJ - 1]) {
                queue.add(new Start(nowI, nowJ - 1));
            }
            area++;
        }
        list.add(area);
    }

    private static void init() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            String[] split = bufferedReader.readLine().split("");
            for (int j = 0; j < N; j++) {
                if (split[j].equals("1")) {
                    visited[i][j] = true;
                }
            }

        }
    }

    private static void answer() {
        answer.append(list.size()).append("\n");
        Collections.sort(list);
        for (Integer integer : list) {
            answer.append(integer).append("\n");
        }
        System.out.println(answer);
    }
}
