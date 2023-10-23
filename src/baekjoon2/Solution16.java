package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 2579 계단 오르기
 */
public class Solution16 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int[] stairs = new int[n + 1];
        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            stairs[i] = Integer.parseInt(bufferedReader.readLine());
        }

        if (n >= 3) {
            dp[1] = stairs[1];
            dp[2] = stairs[1] + stairs[2];
            dp[3] = Math.max(stairs[1], stairs[2]) + stairs[3];

            for (int i = 4; i <= n; i++) {
                dp[i] = Math.max(dp[i - 3] + stairs[i - 1], dp[i - 2]) + stairs[i];
            }
            System.out.println(dp[n]);
        } else if (n == 2){
            System.out.println(stairs[n - 1] + stairs[n]);
        } else if (n == 1) {
            System.out.println(stairs[n]);
        }
    }
}
