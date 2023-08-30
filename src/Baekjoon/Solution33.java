package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 1181 단어정렬
 */
public class Solution33 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        Set<String> set = new HashSet<>();
        StringBuilder stringBuilder = new StringBuilder();

        int repeat = Integer.parseInt(bufferedReader.readLine());

        for (int i = 0; i < repeat; i++) {
            set.add(bufferedReader.readLine());
        }

        List<String> sortedString = set.stream().sorted(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() == o2.length()) {
                    return o1.compareTo(o2);
                }
                return o1.length() - o2.length();
            }
        }).collect(Collectors.toList());

        for (String s : sortedString) {
            stringBuilder.append(s).append("\n");
        }

        System.out.println(stringBuilder);
    }
}
