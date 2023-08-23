package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 1764 듣보잡
 */
public class Solution27 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stringBuilder = new StringBuilder();
        int count = 0;
        Set<String> set = new HashSet<>();
        List<String> list = new ArrayList<>();

        String[] nm = bufferedReader.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);

        for (int i = 0; i < n; i++) {
            set.add(bufferedReader.readLine());
        }

        for (int i = 0; i < m; i++) {
            String readLine = bufferedReader.readLine();
            if (set.contains(readLine)) {
                count++;
                list.add(readLine);
            }
        }
        list.sort(String::compareTo);

        for (String s : list) {
            stringBuilder.append(s).append("\n");
        }
        System.out.println(count);
        System.out.println(stringBuilder);
    }
}
