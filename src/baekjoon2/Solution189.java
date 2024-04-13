package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 2108 통계학 (Silver3)
 * 소수점 반올림에서 살짝 헷갈렸지만 Math.round()라는 메서드를 사용해서 해결하였고
 * 최빈값구하는건 어떻게 해야할까 고민하다가 Map자료구조를 사용하여 해결하였다.
 * 방법은 조금 억지스럽긴한데 key값에 해당 정수를 넣고 value값에 그 정수가 나온 횟수를 카운트 한 뒤
 * 카운트 순으로 내림차순 정렬한다 이때 카운트가 같으면 key 값으로 오름차순 정렬한다.
 * 그 뒤 N이 1이거나 정렬된 엔트리의 첫번째요소의 value값이 두번째 요소의 value 값보다 크면 첫번째 요소의 키값이 최빈값이고
 * 아닐 시에는 최빈값이 여러개라 2번째요소를 출력하도록 하였다.
 */
public class Solution189 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        StringBuilder answer = new StringBuilder();
        List<Integer> list = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        int total = 0;
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(bufferedReader.readLine());
            list.add(num);
            map.put(num, map.getOrDefault(num, 0) + 1);
            total += num;
        }

        answer.append(Math.round((double) total / N)).append("\n");

        list.sort(Comparator.comparingInt(o -> o));
        answer.append(list.get(N / 2)).append("\n");

        List<Map.Entry<Integer, Integer>> entryList = new ArrayList<>(map.entrySet());
        entryList.sort((o1, o2) -> {
            if (o1.getValue().equals(o2.getValue())) {
                return o1.getKey() - o2.getKey();
            }
            return o2.getValue() - o1.getValue();
        });

        if (N == 1 || entryList.get(0).getValue() > entryList.get(1).getValue()) {
            answer.append(entryList.get(0).getKey()).append("\n");
        } else {
            answer.append(entryList.get(1).getKey()).append("\n");
        }

        answer.append(list.get(N - 1) - list.get(0));
        System.out.println(answer);
    }
}
