package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 11403 경로 찾기
 */
public class Solution59 {
    static StringBuilder answer = new StringBuilder();
    static boolean[][] visited;
    static int[][] graph;
    static int now;
    public static void main(String[] args) throws IOException {
        init();
        routeSearch();
        answer();
    }

    private static void init() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        graph = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }
    }

    static void routeSearch() {
        for (int i = 0; i < graph.length; i++) {
            now = i;
            dfs(now);
        }
    }

    static void dfs(int row) {
        for (int i = 0; i < graph[row].length; i++) {
            if (graph[row][i] == 1) {
                if (!visited[now][i]) {
                    visited[now][i] = true;
                    dfs(i);
                }
            }
        }
    }
    private static void answer() {
        for (boolean[] booleans : visited) {
            for (boolean aBoolean : booleans) {
                if (aBoolean) {
                    answer.append("1 ");
                } else {
                    answer.append("0 ");
                }
            }
            answer.append("\n");
        }
        System.out.println(answer);
    }
}
