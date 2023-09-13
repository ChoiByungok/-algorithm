package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 14215 세 막대
 * 세 변의 길이를 줬을 때 길이가 가장 긴 변의 길이는 다른 두 변 길이의 합보다 작아야 삼각형을 그릴 수 있다.
 */
public class Solution46 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int[] polls = Arrays
                .stream(Arrays.stream(bufferedReader.readLine().split(" "))
                        .mapToInt(Integer::parseInt).toArray())
                .sorted()
                .toArray();

        System.out.println(polls[0] + polls[1] > polls[2] ? polls[0] + polls[1] + polls[2] : (polls[0] + polls[1]) * 2 - 1);
    }
}
