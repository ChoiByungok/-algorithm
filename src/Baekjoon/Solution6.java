package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 2753 윤년
 */


public class Solution6 {
    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int input = Integer.parseInt(bufferedReader.readLine());

        int answer = input % 4 == 0 && (!(input % 100 == 0) || (input % 400 == 0)) ? 1 : 0;

        System.out.println(answer);

    }
}
