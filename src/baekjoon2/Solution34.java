package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 2230 수 고르기
 */
public class Solution34 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = bufferedReader.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);
        int[] sequence = new int[n];
        int start = 0;
        int end = 0;

        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(bufferedReader.readLine());
            sequence[i] = num;
        }

        Arrays.sort(sequence);

        int answer = Integer.MAX_VALUE;
        while (end < n) {
            if (sequence[end] - sequence[start] < m) {
                end++;
                continue;
            }
            if (sequence[end] - sequence[start] == m) {
                answer = m;
                break;
            }
            answer = Math.min(answer, sequence[end] - sequence[start]);
            start++;
        }
        System.out.println(answer);
    }
}
