package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 11724 연결 요소의 개수
 */
public class Solution65 {
    static int N;
    static boolean[][] visited = new boolean[1001][1001];
    static boolean[] vertex = new boolean[1001];

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(logic());
    }

    private static void init() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());

        N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());
        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            int u = Integer.parseInt(tokenizer.nextToken());
            int v = Integer.parseInt(tokenizer.nextToken());
            visited[u][v] = true;
            visited[v][u] = true;
        }
        for (int i = 1; i <= N; i++) {
            visited[i][i] = true;
        }
    }

    private static int logic() {
        int answer = 0;
        for (int i = 1; i <= N; i++) {
            if (!vertex[i]) {
                answer++;
                dfs(i);
            }
        }
        return answer;
    }

    private static void dfs(int start) {
        vertex[start] = true;
        for (int i = 1; i <= N; i++) {
            if (visited[start][i] && !vertex[i]) {
                visited[start][i] = false;
                dfs(i);
            }
        }
    }
}
