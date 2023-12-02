package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 20291 파일정리
 * 해시맵과 맵 정렬하는 방법만 안다면 쉽게 풀 수 있는 문제
 */
public class Solution56 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        Map<String, Integer> map = new HashMap<>();
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < N; i++) {
            String[] split = bufferedReader.readLine().split("\\.");
            map.put(split[1], map.getOrDefault(split[1], 0) + 1);
        }
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByKey());

        for (Map.Entry<String, Integer> entry : list) {
            answer.append(entry.getKey()).append(" ").append(entry.getValue()).append("\n");
        }

        System.out.println(answer);
    }
}
