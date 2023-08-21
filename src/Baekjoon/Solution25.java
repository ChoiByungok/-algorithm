package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 10815 숫자카드
 */
public class Solution25 {
    public static void main(String[] args) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        Map<String , Integer> map = new LinkedHashMap<>();

        int N = Integer.parseInt(bufferedReader.readLine());
        String[] cards = bufferedReader.readLine().split(" ");
        int M = Integer.parseInt(bufferedReader.readLine());
        String[] numbers = bufferedReader.readLine().split(" ");

        for (String number : numbers) {
            map.put(number, 0);
        }

        for (String card : cards) {
            if (!map.containsKey(card)) {
                continue;
            }
            map.put(card, map.getOrDefault(card, 0) + 1);
        }

        map.values().forEach(value -> stringBuilder.append(value).append(" "));
        System.out.println(stringBuilder);
    }
}
