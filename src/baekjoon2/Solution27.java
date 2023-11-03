package baekjoon2;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 10971 외판원 순회 2
 */
public class Solution27 {
    static int[][] city;
    static int answer = Integer.MAX_VALUE;
    static int N;
    static int startCity;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());
        city = new int[N][N];

        for (int i = 0; i < N; i++) {
            String[] split = bufferedReader.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                city[i][j] = Integer.parseInt(split[j]);
            }
        }

        for (int i = 0; i < N; i++) {
            visited = new boolean[N];
            startCity = i;
            visited[i] = true;
            dfs(i, 0, true);
        }
        System.out.println(answer);
    }

    private static void dfs(int nowCity, int value, boolean start) {
        if (startCity == nowCity && !start) {
            answer = Math.min(answer, value);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (city[nowCity][i] == 0 || startCity == i || visited[i]) {
                continue;
            }
            visited[i] = true;
            dfs(i, value + city[nowCity][i], false);
            visited[i] = false;
        }
        if (allCity()) {
            if (city[nowCity][startCity] != 0) {
                dfs(startCity, value + city[nowCity][startCity], false);
            }
        }
    }

    private static boolean allCity() {
        for (boolean b : visited) {
            if (!b) {
                return false;
            }
        }
        return true;
    }

}
