package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 10844 쉬운 계단 수
 */
public class Solution23 {
    static long[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        long answer = 0;
        dp = new long[N][10];
        dp[0] = new long[]{0, 1, 1, 1, 1, 1, 1, 1, 1, 1};

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < 10; j++) {
                if (j == 0) {
                    dp[i][j] = dp[i - 1][j + 1] % 1000000000;
                } else if (j == 9) {
                    dp[i][j] = dp[i - 1][j - 1] % 1000000000;
                } else {
                    dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % 1000000000;
                }
            }
        }

        for (int i = 0; i < 10; i++) {
            answer += dp[N - 1][i];
        }
        System.out.println(answer % 1000000000);
    }
}
