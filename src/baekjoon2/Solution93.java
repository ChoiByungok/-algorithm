package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 3077 임진왜란
 * 둘째줄 입력값을 key value 형태의 hashMap 에 저장한다.
 * naboo geonosis yavin hoth endor 의 문자열이 들어온다면
 * naboo=1 geonosis=2 yavin=3 hoth=4 endor=5 형태로 저장된다.
 * 셋째줄 입력값도 hashMap 에 저장한다.
 * geonosis yavin hoth endor naboo 의 문자열이 들어온다면
 * geonosis=1 yavin=2 hoth=3 endor=4 naboo=5 로 저장된다.
 * 이제 첫번째 해시맵을 리스트로 변환하여 완전탐색을 실시한다.
 * 그러면 두개의 키가 나올텐데 무조건 첫번째 value 보다 두번째 value가 더 커야 정답으로 인정된다.
 * 그 때마다 count 를 증가시켜주면 된다.
 */
public class Solution93 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        Map<String, Integer> map1 = new LinkedHashMap<>();
        Map<String, Integer> map2 = new LinkedHashMap<>();
        StringBuilder answer = new StringBuilder();
        int count = 0;
        int N = Integer.parseInt(bufferedReader.readLine());
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 1; i <= N; i++) {
            map1.put(tokenizer.nextToken(), i);
        }
        tokenizer = new StringTokenizer(bufferedReader.readLine());

        for (int i = 1; i <= N; i++) {
            map2.put(tokenizer.nextToken(), i);
        }

        List<Map.Entry<String, Integer>> list = new ArrayList<>(map1.entrySet());

        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (map2.get(list.get(i).getKey()) < map2.get(list.get(j).getKey())) {
                    count++;
                }
            }
        }
        answer.append(count).append("/").append(N * (N - 1) / 2);
        System.out.println(answer);
    }
}
