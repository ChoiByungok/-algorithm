package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 1253 좋다
 */
public class Solution36 {
    static int[] sequence;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());
        sequence = new int[N];
        int count = 0;

        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());

        for (int i = 0; i < N; i++) {
            sequence[i] = Integer.parseInt(tokenizer.nextToken());
        }
        Arrays.sort(sequence);

        for (int i = 0; i < N; i++) {
            if (comparison(i)) {
                count++;
            }
        }
        System.out.println(count);
    }

    private static boolean comparison(int i) {
        int start = 0;
        int end = N - 1;

        while (true) {
            if (start == i) {
                start++;
            }
            if (end == i) {
                end--;
            }
            if (start >= end) {
                return false;
            }

            if (sequence[start] + sequence[end] > sequence[i]) {
                end--;
            } else if (sequence[start] + sequence[end] < sequence[i]) {
                start++;
            } else {
                return true;
            }
        }
    }
}
