package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 최소 최대2 20053
 */
public class Solution8 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int repeat = Integer.parseInt(bufferedReader.readLine());

        for (int i = 0; i < repeat; i++) {
            int length = Integer.parseInt(bufferedReader.readLine());

            int[] arr = new int[length];
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            while (stringTokenizer.hasMoreTokens()){
                for (int j = 0; j < arr.length; j++) {
                    arr[j] = Integer.parseInt(stringTokenizer.nextToken());
                }
            }
            Arrays.sort(arr);
            System.out.println(arr[0] + " " + arr[length - 1]);
        }

    }
}
