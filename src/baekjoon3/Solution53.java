package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 13023 ABCDE (Gold5)
 * 문제 이해가 가장 어려운 문제 다음과 같은 친구 조건이 아무리 문제를 읽어보아도 무슨 소리인지 모르겠음
 * 특정 노드에서 모든 노드에 중복없이 방문하면 되는줄 알았는데
 * 그게 아니라 depth가 5를 만족하면 되는거였음 근데 왜 5인지 제출 하고 나서도 아직도 이해가 안감
 */
public class Solution53 {
    static boolean[] visited;
    static List<ArrayList<Integer>> graph = new ArrayList<>();
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());

        visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        for (int i = 0; i < N; i++) {
            visited[i] = true;
            dfs(i, 1);
            visited[i] = false;
        }
        System.out.println(0);
    }

    static void dfs(int node, int depth) {
        if (depth == 5) {
            System.out.println(1);
            System.exit(0);
        }

        for (Integer integer : graph.get(node)) {
            if (!visited[integer]) {
                visited[integer] = true;
                dfs(integer, depth + 1);
                visited[integer] = false;
            }
        }
    }
}
