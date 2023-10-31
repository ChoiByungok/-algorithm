package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 12865 평범한 베낭
 * 답지보고 풀음 근데 아직도 이해 못함
 */
public class Solution24 {
    static int N;
    static int K;
    static int[][] wv;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] nk = bufferedReader.readLine().split(" ");

        N = Integer.parseInt(nk[0]);
        K = Integer.parseInt(nk[1]);
        dp = new int[N + 1][K + 1];
        wv = new int[N + 1][2];

        for (int i = 1; i <= N; i++) {
            String[] split = bufferedReader.readLine().split(" ");
            int n = Integer.parseInt(split[0]);
            int k = Integer.parseInt(split[1]);
            wv[i][0] = n;
            wv[i][1] = k;
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= K; j++) {
                if (wv[i][0] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - wv[i][0]] + wv[i][1]);
                }
            }
        }
        System.out.println(dp[N][K]);
    }
}
