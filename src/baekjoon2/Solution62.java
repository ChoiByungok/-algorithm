package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 2910 빈도 정렬
 * HashMap 자료구조에 구현되어 있는 getOrDefault() 메서드를 잘 사용하면 되는 문제
 * 해당 메서드는 처음 들어오는 키값의 value 는 0 으로 두고 중복되는 키 값이 들어올 때 마다 1씩 증가시켜주는 메서드이다.
 * LinkedHashMap 을 사용한 이유는 들어온 순서를 따지기 때문에 사용했다.
 * 그 이후엔 빈도 값으로 정렬한 뒤 출력 해주면 된다.
 */
public class Solution62 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] nc = bufferedReader.readLine().split(" ");
        int N = Integer.parseInt(nc[0]);
        int C = Integer.parseInt(nc[1]);
        StringBuilder answer = new StringBuilder();
        Map<Integer, Integer> map = new LinkedHashMap<>();

        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        while (tokenizer.hasMoreTokens()) {
            int num = Integer.parseInt(tokenizer.nextToken());
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort((o1, o2) -> o2.getValue() - o1.getValue());

        for (Map.Entry<Integer, Integer> num : list) {
            Integer repeat = num.getValue();
            for (int i = 0; i < repeat; i++) {
                answer.append(num.getKey()).append(" ");
            }
        }

        System.out.println(answer);
    }
}
