package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 1934 최소공배수
 */
public class Solution17 {
    public static void main(String[] args) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int repeat = Integer.parseInt(bufferedReader.readLine());

        for (int i = 0; i < repeat; i++) {
            String[] split = bufferedReader.readLine().split(" ");
            int a = Integer.parseInt(split[0]);
            int b = Integer.parseInt(split[1]);
            int lcm = (a * b) / gcd(b, a);
            stringBuilder.append(lcm).append("\n");
        }

        System.out.println(stringBuilder);
    }

    private static int gcd(int a, int b) {
        int r;
        while ((a % b) != 0) {
            r = a % b;
            a = b;
            b = r;
        }
        return b;
    }
}
