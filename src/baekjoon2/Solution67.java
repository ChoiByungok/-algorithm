package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 20920 영단어 암기는 괴로워
 * 우선 해시맵을 이용하여 영단어의 빈도수를 계산한다
 * 그 이후 조건에 맞게 정렬을 한 뒤 출력해주면 된다.
 */
public class Solution67 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = bufferedReader.readLine().split(" ");
        StringBuilder answer = new StringBuilder();
        int N = Integer.parseInt(nm[0]);
        int M = Integer.parseInt(nm[1]);
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String readLine = bufferedReader.readLine();
            if (readLine.length() < M) {
                continue;
            }
            map.put(readLine, map.getOrDefault(readLine, 0) + 1);
        }

        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort((o1, o2) -> {
            if (o1.getValue().equals(o2.getValue())) {
                if (o1.getKey().length() == o2.getKey().length()) {
                    return o1.getKey().compareTo(o2.getKey());
                }
                return o2.getKey().length() - o1.getKey().length();
            }
            return o2.getValue() - o1.getValue();
        });
        for (Map.Entry<String, Integer> string : list) {
            answer.append(string.getKey()).append("\n");
        }

        System.out.println(answer);
    }
}
