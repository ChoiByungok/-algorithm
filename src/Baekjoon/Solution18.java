package Baekjoon;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * 1427 소트인사이드
 */
public class Solution18 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stringBuilder = new StringBuilder();
        String N = bufferedReader.readLine();
        int[] array = Stream.of(N.split("")).mapToInt(i -> Integer.parseInt(i)).toArray();
        Arrays.sort(array);
        for (int i = array.length - 1 ; i >= 0; i--) {
            stringBuilder.append(array[i]);
        }
        System.out.println(stringBuilder);
    }
}
