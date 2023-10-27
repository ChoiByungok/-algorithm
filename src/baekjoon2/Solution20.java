package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 1463 1로 만들기
 */
public class Solution20 {
    static Integer[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int X = Integer.parseInt(bufferedReader.readLine());
        dp = new Integer[X + 1];
        dp[0] = dp[1] = 0;
        bottom_up(X);
        top_down(X);
        System.out.println(dp[X]);
    }

    private static void bottom_up(int X) {
        for (int i = 2; i <= X; i++) {
            dp[i] = dp[i - 1] + 1;

            if (i % 2 == 0) {
                dp[i] = Math.min(dp[i], dp[i / 2] + 1);
            }

            if (i % 3 == 0) {
                dp[i] = Math.min(dp[i], dp[i / 3] + 1);
            }
        }
    }

    static int top_down(int n) {
        if (dp[n] == null) {
            if (n % 6 == 0) {
                dp[n] = Math.min(top_down(n / 3), Math.min(top_down(n / 2), top_down(n - 1))) + 1;
            } else if (n % 3 == 0){
                dp[n] = Math.min(top_down(n / 3), top_down(n - 1)) + 1;
            } else if (n % 2 == 0) {
                dp[n] = Math.min(top_down(n / 2), top_down(n - 1)) + 1;
            } else {
                dp[n] = top_down(n - 1) + 1;
            }
        }
        return dp[n];
    }
}
