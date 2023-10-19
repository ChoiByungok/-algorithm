package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 1904 01타일
 */
public class Solution12 {
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        dp = new int[N + 1];

        if (N == 1) {
            System.out.println(N);
        } else if (N == 2) {
            System.out.println(N);
        } else {
            dp[1] = 1;
            dp[2] = 2;
            for (int i = 3; i <= N; i++) {
                dp[i] = (dp[i - 2] + dp[i - 1]) % 15746;
            }
            System.out.println(dp[N]);
        }
    }
}
