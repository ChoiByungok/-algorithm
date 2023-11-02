package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 14501 퇴사
 * 점화식 dp[n] = max(dp[n+1], dp[i + t[i]] + p[i])
 * 오늘 날짜에 할 수 있는 일을 하는게 더 값어치가 크냐 아님 거르고 다음 날짜에 할 수 있는 일을 하는게 더 값어치가크냐 비교해야함
 */
public class Solution26 {
    static int[] dp;
    static int[] t;
    static int[] p;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());
        t = new int[N + 1];
        p = new int[N + 1];
        dp = new int[N + 2];

        for (int i = 1; i <= N; i++) {
            String[] split = bufferedReader.readLine().split(" ");
            t[i] = Integer.parseInt(split[0]);
            p[i] = Integer.parseInt(split[1]);
        }
        if (bufferedReader.readLine().equals("b")) {
            bottom_up();
        } else {
            Arrays.fill(dp, -1);
            top_down(1);
        }
        System.out.println(Arrays.toString(dp));
        System.out.println(dp[1] == -1 ? 0 : dp[1]);
    }
    private static void bottom_up() {
        for (int i = N; i >= 1; i--) {
            if (i + t[i] > N + 1) {
                dp[i] = dp[i + 1];
            } else {
                dp[i] = Math.max(dp[i + 1], dp[i + t[i]] + p[i]);
            }
        }
    }
    static int top_down(int n) {
        if (n > N) {
            return 0;
        }
        if (dp[n] != -1) {
            return dp[n];
        }
        if (n + t[n] <= N + 1) {
            dp[n] = top_down(n + t[n]) + p[n];
        }
        return dp[n] = Math.max(dp[n], top_down(n + 1));
    }
}
