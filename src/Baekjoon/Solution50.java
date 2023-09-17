package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 2805 나무자르기
 */
public class Solution50 {
    static int[] list;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] NM = bufferedReader.readLine().split(" ");
        int N = Integer.parseInt(NM[0]);
        int M = Integer.parseInt(NM[1]);
        list = new int[N];
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < list.length; i++) {
            list[i] = Integer.parseInt(stringTokenizer.nextToken());
        }
        Arrays.sort(list);

        long start = 0;
        long end = list[list.length - 1] + 1;

        while (start + 1 < end) {
            long mid = (start + end) / 2;
            long sum = 0;
            for (int i = 0; i < list.length; i++) {
                if (list[i] > mid) {
                    sum += list[i] - mid;
                }
            }
            if (sum >= M) {
                start = mid;
            } else {
                end = mid;
            }
        }
        System.out.println(start);
    }
}
