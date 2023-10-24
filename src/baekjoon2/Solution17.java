package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 11053 가장 긴 증가하는 부분 수열
 */
public class Solution17 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        int[] arr = new int[N];
        int[] dp = new int[N];
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(tokenizer.nextToken());
        }

        dp[0] = 1;

        for (int i = 1; i < arr.length; i++) {
            int count = 1;
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j] && dp[j] >= count) {
                    count++;
                }
            }
            dp[i] = count;
        }

        Arrays.sort(dp);
        System.out.println(dp[dp.length - 1]);
    }
}
