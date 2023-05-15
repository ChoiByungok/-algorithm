package Programmers2;

/**
 * 멀리 뛰기
 */
public class Solution34 {

    public long solution(int n) {
        long[] dp = new long[2001];
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i < dp.length; i++) {
            dp[i] = (dp[i - 2] + dp[i - 1]) % 1234567;
        }

        return dp[n];
    }

    public static void main(String[] args) {
        int n = 4;
        System.out.println(new Solution34().solution(n));
    }
}
