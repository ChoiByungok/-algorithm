package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 1967 트리의 지름 (Gold4)
 * 어떻게 접근해야 할지 모르니 처음에는 완전탐색으로 진행해보았음
 * 완전탐색 dfs를 사용하니깐 통과는 되는데 다른사람의 풀이보다 10배는 느린코드가 완성이됨
 * 어떻게 해야 효율적으로 풀 수 있을까?
 * 자식이 없는 노드 즉 리프노드만 탐색을 진행했더니 조금 효율이 좋아졌지만 아직 다른 사람의 성능을 따라가긴 멀었다.
 * 그래서 구글링을 했더니 루트노드부터 탐색을 진행하여 가장 가중치가 먼 리프노드를 찾은 뒤
 * 해당 리프노드에서 탐색을 진행하면 효율이 좋다는 사실을 알게 되었다.
 * 문제 예제에서는 루트부터 탐색을 진행하면 가장 가중치가 먼 리프노드는 28의 가중치를 가진 9번 노드이며
 * 9번노드만 탐색을 진행하면 답이 나온다는것이다. 어떻게 저런식으로 문제를 접근했는지는 모르겠지만
 * 다른사람들의 코드들도 다 저 방법으로 푼거같다.
 */
public class Solution42 {
    static int answer = Integer.MIN_VALUE;
    static List<ArrayList<int[]>> list = new ArrayList<>();
    static boolean[] visited;
    static boolean[] leaf;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }

        leaf = new boolean[N + 1];
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            int cost = Integer.parseInt(tokenizer.nextToken());
            list.get(a).add(new int[] {b, cost});
            list.get(b).add(new int[] {a, cost});
            leaf[a] = true;
        }

        for (int i = 1; i <= N; i++) {
            if (!leaf[i]) {
                visited = new boolean[N + 1];
                visited[i] = true;
                dfs(i, 0);
            }
        }
        System.out.println(answer);
    }

    private static void dfs(int start, int accumulate) {
        ArrayList<int[]> ints = list.get(start);
        for (int[] anInt : ints) {
            if (!visited[anInt[0]]) {
                visited[anInt[0]] = true;
                dfs(anInt[0], accumulate + anInt[1]);
            }
        }
        answer = Math.max(answer, accumulate);
    }
}
