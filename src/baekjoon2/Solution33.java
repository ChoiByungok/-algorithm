package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1806 부분합
 */
public class Solution33 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] ns = bufferedReader.readLine().split(" ");
        int n = Integer.parseInt(ns[0]);
        int s = Integer.parseInt(ns[1]);
        int[] sequence = new int[n + 1];
        int answer = Integer.MAX_VALUE;

        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < n; i++) {
            sequence[i] = Integer.parseInt(tokenizer.nextToken());
        }

        int sum = 0;
        int start = 0;
        int end = 0;

        while (start <= n && end <= n) {
            if (sum < s) {
                sum += sequence[end++];
            } else {
                answer = Math.min(answer, (end - start));
                sum -= sequence[start++];
            }
        }
        System.out.println(start == 0 ? 0 : answer);
    }
}
