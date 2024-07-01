package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 15900 나무탈출 (Silver1)
 * 확실한건 아니지만 뭔가 리프노드의 숫자가 홀수면 YES를 출력하고 아니면 NO를 출력하면 될거같다.
 * 그러면 이제 어떤 노드가 리프노드이며 그 갯수를 세면 될거같다.
 * 완전 헛다리를 짚고 잘못풀고 있었다 리프노드의 갯수를 세는것이아니라
 * 리프노드부터 루트노드까지의 거리의 총 합이 짝수면 지는거고 홀수면 이기는 문제였던 것이다.
 * 다른사람의 풀이를 보니 dfs가 아닌 bfs로 푼 사람들의 성능이 더 잘 나온것을 볼 수 있다.
 */
public class Solution68 {
    static List<ArrayList<Integer>> tree = new ArrayList<>();
    static int count = 0;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        visited = new boolean[N + 1];
        for (int i = 0; i <= N; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            tree.get(a).add(b);
            tree.get(b).add(a);
        }

        visited[1] = true;
        findLeaf(1, 0);
        System.out.println(count % 2 == 0 ? "No" : "Yes");
    }

    static void findLeaf(int root, int depth) {
        visited[root] = true;
        boolean isLeaf = true;

        for (Integer integer : tree.get(root)) {
            if (!visited[integer]) {
                findLeaf(integer, depth + 1);
                isLeaf = false;
            }
        }

        if (isLeaf) {
            count += depth;
        }
    }
}
