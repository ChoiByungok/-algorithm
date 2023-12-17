package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 3273 두 수의 합
 */
public class Solution71 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int[] arr = new int[n];
        int answer = 0;
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(tokenizer.nextToken());
        }
        Arrays.sort(arr);
        int x = Integer.parseInt(bufferedReader.readLine());
        int start = 0;
        int end = n - 1;

        while (start < end) {
            int sum = arr[start] + arr[end];
            if (sum == x) {
                start++;
                end--;
                answer++;
            } else if (sum > x) {
                end--;
            } else {
                start++;
            }
        }
        System.out.println(answer);
    }
}
