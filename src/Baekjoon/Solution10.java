package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 더하기 사이클 1110
 */
public class Solution10 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int input = Integer.parseInt(bufferedReader.readLine());
        int copy = input;
        int count = 0;

        do {
            int x = input % 10; //일의 자리
            int y = input / 10; //십의 자리
            int z = x + y;
            input = (x * 10) + (z % 10);
            count++;

        } while (input != copy);

        System.out.println(count);
    }
}
