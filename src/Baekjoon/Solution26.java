package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/**
 * 14425 문자열 집합
 */
public class Solution26 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        Set<String> set = new HashSet<>();
        int answer = 0;

        String[] nm = bufferedReader.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);

        for (int i = 0; i < n; i++) {
            set.add(bufferedReader.readLine());
        }

        for (int i = 0; i < m; i++) {
            String readLine = bufferedReader.readLine();
            if (set.contains(readLine)) {
                answer++;
            }
        }
        System.out.println(answer);
    }
}
