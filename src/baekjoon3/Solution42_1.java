package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 1967 트리의 지름 (Gold4)
 * 가장 효율이 좋은 코드로 풀은문제 루트노드부터 시작하여 가중치가 가장 먼 노드를 찾은 뒤
 * 해당 노드만 탐색을 진행하였다.
 */
public class Solution42_1 {
    static List<ArrayList<int[]>> list = new ArrayList<>();
    static boolean[] visited;
    static int[] weight;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }
        weight = new int[N + 1];
        visited = new boolean[N + 1];
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            int cost = Integer.parseInt(tokenizer.nextToken());
            list.get(a).add(new int[] {b, cost});
            list.get(b).add(new int[] {a, cost});
        }

        visited[1] = true;
        dfs(1, 0);
        int start = 1;
        int max = weight[1];
        for (int i = 2; i <= N; i++) {
            if (max < weight[i]) {
                start = i;
                max = weight[i];
            }
        }

        visited = new boolean[N + 1];
        weight = new int[N + 1];
        visited[start] = true;
        dfs(start, 0);
        Arrays.sort(weight);
        System.out.println(weight[N]);
    }

    static void dfs(int start, int accumulate) {
        weight[start] = accumulate;

        for (int[] ints : list.get(start)) {
            if (!visited[ints[0]]) {
                visited[ints[0]] = true;
                dfs(ints[0], accumulate + ints[1]);
            }
        }
    }
}
