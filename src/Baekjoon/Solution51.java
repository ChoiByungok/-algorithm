package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 1654 랜선자르기
 */
public class Solution51 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] KN = bufferedReader.readLine().split(" ");
        int K = Integer.parseInt(KN[0]);
        int N = Integer.parseInt(KN[1]);

        long max = Long.MIN_VALUE;
        int[] lan = new int[K];
        for (int i = 0; i < lan.length; i++) {
            lan[i] = Integer.parseInt(bufferedReader.readLine());
            if (lan[i] > max) {
                max = lan[i];
            }
        }
        long start = 0;
        long end = max + 1;
        while (start + 1 < end) {
            long mid = (start + end) / 2;
            long sum = 0;
            for (int j : lan) {
                sum += j / mid;
            }
            if (sum >= N) {
                start = mid;
            } else {
                end = mid;
            }
        }
        System.out.println(start);
    }
}
