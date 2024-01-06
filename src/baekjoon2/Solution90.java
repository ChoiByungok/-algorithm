package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 1270 전쟁 - 땅따먹기
 * 해시맵을 이용하여 해당 번호의 군인이 몇명인지 계산하고
 * 전체 군인수의 과반 이상 나오면 점령한 땅으로 해당 군인의 번호를 출력하고
 * 아니면 SYJKGW를 출력하는 문제인데 문제 자체는 별로 어렵지 않은데
 * 병사 번호  <= 2^31 라서 Long 형으로 parsing 하지 않으면 numberFormatException 이 발생하게 된다.
 * 나는 해시맵으로 계산한 뒤 리스트로 변환하여 내림차순 정렬을 통해 가장 많은 수의 군인을 뽑아 냈지만
 * 다른 사람풀이를 보면 TreeMap 으로 바로 가장 많은 수의 군인을 찾아 내는 걸 보고
 * 다양한 자료구조를 공부해야겠다고 느끼는 문제였다.
 */
public class Solution90 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        StringBuilder answer = new StringBuilder();

        for (int i = 0; i < N; i++) {
            Map<Long, Integer> map = new HashMap<>();
            StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
            int soldiers = Integer.parseInt(tokenizer.nextToken());

            while (tokenizer.hasMoreTokens()) {
                long soldier = Long.parseLong(tokenizer.nextToken());
                map.put(soldier, map.getOrDefault(soldier, 0) + 1);
            }
            List<Map.Entry<Long, Integer>> list = new ArrayList<>(map.entrySet());
            list.sort((o1, o2) -> o2.getValue() - o1.getValue());

            if ((soldiers / 2) < list.get(0).getValue()) {
                answer.append(list.get(0).getKey()).append("\n");
            } else {
                answer.append("SYJKGW").append("\n");
            }
        }
        System.out.println(answer);
    }
}
