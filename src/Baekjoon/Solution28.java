package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/**
 * 11478 서로 다른 부분 문자열의 개수
 */
public class Solution28 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        Set<String> set = new HashSet<>();

        String readLine = bufferedReader.readLine();
        int length = readLine.length();

        for (int i = 1; i <= readLine.length(); i++) {
            for (int j = 0; j < readLine.length(); j++) {
                int interval = i + j;
                if (interval > length) {
                    break;
                }
                String substring = readLine.substring(j, interval);
                set.add(substring);
            }
        }
        System.out.println(set.size());
    }
}
