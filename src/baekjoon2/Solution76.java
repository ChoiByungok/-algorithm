package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 4358 생태학
 * 백분율을 어떻게 구해야 할지 몰라서 해맸는데 사실 입력 받는 과정이 더 어려운 문제였음
 */
public class Solution76 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        Map<String, Double> map = new HashMap<>();
        StringBuilder answer = new StringBuilder();
        double count = 0;
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine == null || readLine.equals("")) {
                break;
            }
            map.put(readLine, map.getOrDefault(readLine, 0.0) + 1);
            count++;
        }
        List<Map.Entry<String, Double>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByKey());

        for (Map.Entry<String, Double> entry : list) {
            answer
                    .append(entry.getKey())
                    .append(" ")
                    .append(String.format("%.4f", entry.getValue() / count * 100))
                    .append("\n");
        }
        System.out.println(answer);
    }
}
