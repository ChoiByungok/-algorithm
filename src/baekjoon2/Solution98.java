package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 5107 마니또
 * 분명히 맞게 풀었다고 생각했는데 자꾸 25% 에서 ArrayIndexOutOfBoundsException 이 발생함
 * 그래서 인덱스를 1번부터 하지 않고 0부터 시작할 수 있도록 풀어보니깐
 * 이번에는 25%에서 틀렸다고 나옴 도대체 25%에는 어떤 예제가 있길래 자꾸 거기서 막히는지 모르겠음
 * 알고보니 while 문안에서 매 반복마다 자료구조들을 초기화 시켜줘야 함
 * 여기서 계속 틀린거였음
 * 문제 자체는 인접리스트로 구현하여 dfs 를 이용하여 풀면 간단하게 풀리는 문제임
 */
public class Solution98 {
    static List<ArrayList<Integer>> list;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int T = 1;
        StringBuilder answer = new StringBuilder();
        while (true) {
            Map<String, Integer> map = new LinkedHashMap<>();
            list = new ArrayList<>();
            int count = 0;
            int index = 0;
            int N = Integer.parseInt(bufferedReader.readLine());
            if (N == 0) {
                break;
            }

            visited = new boolean[N];
            for (int i = 0; i < N; i++) {
                list.add(new ArrayList<>());
            }
            for (int i = 0; i < N; i++) {
                String[] split = bufferedReader.readLine().split(" ");
                for (String name : split) {
                    if (!map.containsKey(name)) {
                        map.put(name, index++);
                    }
                }
                list.get(map.get(split[0])).add(map.get(split[1]));
            }
            for (int i = 0; i < N; i++) {
                for (Integer manito : list.get(i)) {
                    if (!visited[manito]) {
                        visited[manito] = true;
                        count++;
                        dfs(manito);
                    }
                }
            }
            answer.append(T).append(" ").append(count).append("\n");
            T++;
        }
        System.out.println(answer);
    }

    private static void dfs(int person) {
        visited[person] = true;
        for (Integer manito : list.get(person)) {
            if (!visited[manito]) {
                dfs(manito);
            }
        }
    }
}
