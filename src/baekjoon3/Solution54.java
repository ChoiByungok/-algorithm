package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1937 욕심쟁이 판다 (Gold3)
 * 해당 알고리즘의 분류는 다이나믹 프로그래밍이다. 어떻게 DP로 이 문제를 접근할 수 있을까
 * 어떻게 접근하는지만 대충 본 뒤 혼자서 해결해보려 했는데
 * dp는 도저히 내머리로 혼자 풀기가 불가능해서 결국 다른사람의 코드를 보고 제출했음
 * dp관련 알고리즘 풀때마다 너무 어려워서 자신감이 떨어짐
 * 디버깅 해보면서 어떻게 로직이 돌아가는지 알고싶은데 이 문제는 dfs랑 결합되어있어 디버깅으로 파악하기도 어려움
 */
public class Solution54 {
    static int[][] map;
    static int N;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());
        map = new int[N][N];
        dp = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                answer = Math.max(answer, dfs(i, j));
            }
        }
        System.out.println(answer + 1);
    }

    private static int dfs(int x, int y) {
        int now = map[x][y];
        if (dp[x][y] != 0) {
            return dp[x][y];
        } else {
            if (x + 1 < N && now < map[x + 1][y]) {
                dp[x][y] = Math.max(dp[x][y], dfs(x + 1, y) + 1);
                dfs(x + 1, y);
            }

            if (x - 1 >= 0 && now < map[x - 1][y]) {
                dp[x][y] = Math.max(dp[x][y], dfs(x - 1, y) + 1);
                dfs(x - 1, y);
            }

            if (y + 1 < N && now < map[x][y + 1]) {
                dp[x][y] = Math.max(dp[x][y], dfs(x, y + 1) + 1);
                dfs(x, y + 1);
            }

            if (y - 1 >= 0 && now < map[x][y - 1]) {
                dp[x][y] = Math.max(dp[x][y], dfs(x, y - 1) + 1);
                dfs(x, y - 1);
            }
        }
        return dp[x][y];
    }

    static void print(int[][] dp) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
