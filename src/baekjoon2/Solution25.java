package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 2193 이친수
 */
public class Solution25 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());

        long[] dp = new long[N + 1];
        dp[1] = 1;
        dp[2] = 1;

        for (int i = 3; i <= N; i++) {
            dp[i] = dp[i - 2] + dp[i - 1];
        }

        System.out.println(dp[N]);
    }
}
