package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 9461 파도반 수열
 */
public class Solution14 {
    static long[] dp = new long[101];

    public static void main(String[] args) throws IOException {
        StringBuilder answer = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bufferedReader.readLine());
        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 1;
        dp[4] = 2;
        dp[5] = 2;
        for (int i = 6; i <= 100; i++) {
            dp[i] = dp[i-1] + dp[i-5];
        }
        for (int i = 0; i < T; i++) {
            int p = Integer.parseInt(bufferedReader.readLine());
            answer.append(dp[p]).append("\n");
        }
        System.out.println(answer);
    }
}
