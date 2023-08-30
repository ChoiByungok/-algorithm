package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 7785 회사에 있는 사람
 */
public class Solution32 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        Map<String, String> map = new HashMap<>();
        StringBuilder stringBuilder = new StringBuilder();

        int repeat = Integer.parseInt(bufferedReader.readLine());

        for (int i = 0; i < repeat; i++) {
            String[] split = bufferedReader.readLine().split(" ");
            if (map.containsKey(split[0])) {
                map.remove(split[0]);
            } else {
                map.put(split[0], split[1]);
            }
        }
        List<String> list = new ArrayList<>(map.keySet());

        list.sort(Collections.reverseOrder());
        for (String s : list) {
            stringBuilder.append(s).append("\n");
        }

        System.out.println(stringBuilder);
    }
}
