package baekjoon4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 16437 양 구출작전 (Gold3)
 * 깊이 우선 탐색을 실시하여 리프노드에 달성했을 때
 * 거기서부터 양과 늑대의 수를 계산하여 부모노드배열에 저장시켜준다.
 * 이때 늑대의 수가 더 많아진다면 부모노드에 저장시키지 않는다.
 * 어찌저찌 이해는 했지만 처음부터 끝까지 다른사람의 코드를 보고 이해함
 * 내 손으로 해결한 코드가 단 한가지도 없음
 * 그마저도 디버깅 돌려보면서 간신히 이해함
 */
public class Solution25 {
    static List<ArrayList<Integer>> island = new ArrayList<>();
    static long[] sheep;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        sheep = new long[N + 1];
        for (int i = 0; i <= N; i++) {
            island.add(new ArrayList<>());
        }

        for (int i = 2; i <= N; i++) {
            StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
            String t = tokenizer.nextToken();
            int a = Integer.parseInt(tokenizer.nextToken());
            int p = Integer.parseInt(tokenizer.nextToken());

            if (t.equals("W")) {
                sheep[i] = -a;
            } else {
                sheep[i] = a;
            }

            island.get(p).add(i);
        }
        dfs(1, - 1);
        System.out.println(sheep[1]);
    }
    static void dfs(int now, int prev) {
        for (Integer next : island.get(now)) {
            dfs(next, now);
        }

        if(prev != -1) {
            if(sheep[now] > 0) {
                sheep[prev] += sheep[now];
                sheep[now] = 0;
            }
        }
    }
}
