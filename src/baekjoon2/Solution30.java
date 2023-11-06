package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 2644 촌수계산
 */
public class Solution30 {
    static int n;
    static int x;
    static int y;
    static int answer = 0;
    static boolean[][] graph;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bufferedReader.readLine());
        graph = new boolean[n + 1][n + 1];
        visited = new boolean[n + 1];

        String[] xy = bufferedReader.readLine().split(" ");
        x = Integer.parseInt(xy[0]);
        y = Integer.parseInt(xy[1]);

        int m = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < m; i++) {
            String[] split = bufferedReader.readLine().split(" ");
            int a = Integer.parseInt(split[0]);
            int b = Integer.parseInt(split[1]);
            graph[a][b] = true;
            graph[b][a] = true;
        }
        visited[x] = true;

        for (int i = 1; i <= n; i++) {
            if (graph[x][i]) {
                answer++;
                dfs(i);
            }
        }
        if (!visited[y]) {
            System.out.println(-1);
        }
    }

    private static void dfs(int x) {
        visited[x] = true;
        if (x == y) {
            System.out.println(answer);
            return;
        }
        for (int j = 1; j <= n; j++) {
            if (graph[x][j] && !visited[j]) {
                answer++;
                dfs(j);
            }
        }
        answer--;
    }
}
