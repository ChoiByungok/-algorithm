package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 2156 포도주 시식
 * 이전에 풀었던 계단오르기 문제처럼 풀었다가 틀림
 * 이유) 계단과 다르게 꼭 올라야 하는게 아니라 안마신다는 선택지가 있는데 생각하지 못함
 */
public class Solution19 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        int[] arr = new int[N + 1];
        int[] dp = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(bufferedReader.readLine());
        }

        if (N == 1) {
            System.out.println(arr[1]);
        } else if (N == 2) {
            System.out.println(arr[1] + arr[2]);
        } else {
            dp[1] = arr[1];
            dp[2] = arr[1] + arr[2];

            for (int i = 3; i <= N; i++) {
                dp[i] = Math.max(dp[i - 1], Math.max(dp[i - 3] + arr[i - 1] + arr[i], dp[i - 2] + arr[i]));
            }
            System.out.println(dp[N]);
        }
    }
}
