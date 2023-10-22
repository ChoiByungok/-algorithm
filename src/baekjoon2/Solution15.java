package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1912 연속합
 */
public class Solution15 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int[] sequence = new int[n];
        int[] dp = new int[n];
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());

        for (int i = 0; i < sequence.length; i++) {
            sequence[i] = Integer.parseInt(tokenizer.nextToken());
        }

        int answer = dp[0] = sequence[0];
        for (int i = 1; i < sequence.length; i++) {
            dp[i] = Math.max(dp[i-1] + sequence[i], sequence[i]);
            answer = Math.max(answer, dp[i]);
        }

        System.out.println(answer);
    }
}
