package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 2839 설탕배달
 */
public class Solution47 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        int temp = N;
        int answer = 0;

        while (temp > 0) {
            if (N % 5 == 0) {
                answer = N / 5;
                break;
            }
            temp -= 3;
            if (temp % 5 == 0) {
                answer = temp / 5;
                answer += (N - temp) / 3;
                break;
            }
        }

        System.out.println(answer == 0 ? -1 : answer);
    }
}
