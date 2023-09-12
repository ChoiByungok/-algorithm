package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 13241 최소공배수
 */
public class Solution45 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] AB = bufferedReader.readLine().split(" ");
        long A = Long.parseLong(AB[0]);
        long B = Long.parseLong(AB[1]);
        long mul = A * B;
        long div = B % A;
        while (div != 0) {
            B = A;
            A = div;
            div = B % A;
        }

        long LCM = mul / A;
        System.out.println(LCM);
    }
}
