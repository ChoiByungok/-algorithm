package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 11437 LCA (Gold3)
 * 처음에는 한번 내 방식대로 풀어봄 트리를 입력받은 뒤에
 * 공통조상을 알고싶은 노드 쌍 2개를 입력받고 루트노드부터 해당 노드들 까지 탐색을 실시한다.
 * 그 과정에서 경로를 저장하고 저장된 경로 2개를 뒤에서부터 비교하여 같은 값이 나오면
 * 같은 가장 가까운 공통조상이므로 이것을 출력하는것으로 제출 해봄
 * 1퍼센트에서 시간초과남 그래서 이번에는 부모배열을 선언한 뒤 각 노드의 인덱스값에 부모노드의 값을 집어넣는 식으로
 * 배열을 채우고 입력받은 쌍에서부터 루트노드까지 찾아가는 방법으로 풀어봄 그렇게 완성된 2개의 경로 배열을
 * 마찬가지로 비교하여 먼저나온 같은값을 출력시키는 방법으로 제출함 이번에는 잘 가다 싶더니 94퍼센트에서 시간초과 발생
 * 어쩔 수 없이 다른사람의 풀이를 참조해야 할거같음 공통조상 찾는 알고리즘이 존재해서 이걸 이해한 뒤 적용시켜 제출해보겠음
 * 공통조상 알고리즘이라는 것인데 부모배열 채우면서 같이 해당 노드의 레벨 배열도 같이 채움
 * 레벨은 루트가 1이고 거기서 간선 하나 이동할때마다 1씩 증가시키면 됨
 * 그렇게 부모배열과 레벨배열이 완성되었으면
 * 공통조상을 빨리 찾을 수 있음 우선 두 노드쌍의 레벨을 비교함
 * 같으면 그대로 진행하고 아니면 레벨을 맞춰줘야함
 * 레벨을 맞춰줬으면 거기서부터 반복문을 돌면서 한단계씩 부모를 탐색하면됨 둘이 레벨을 동기화 시켜놨으니 가능한것임
 * 그렇게 두 노드의 값이 같아지면 가까운 공통조상이고 그걸 출력하면 된다.
 */
public class Solution63 {
    static StringBuilder answer = new StringBuilder();
    static List<ArrayList<Integer>> tree = new ArrayList<>();
    static int[] parents;
    static int[] depth;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        parents = new int[N + 1];
        depth = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            tree.add(new ArrayList<>());
        }
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
            int parent = Integer.parseInt(tokenizer.nextToken());
            int child = Integer.parseInt(tokenizer.nextToken());

            tree.get(parent).add(child);
            tree.get(child).add(parent);
        }

        int M = Integer.parseInt(bufferedReader.readLine());
        setTree(1, 1, 0);

        for (int i = 0; i < M; i++) {
            StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
            int node1 = Integer.parseInt(tokenizer.nextToken());
            int node2 = Integer.parseInt(tokenizer.nextToken());
            answer.append(findCommonAncestor(node1, node2)).append("\n");
        }
        System.out.println(answer);
    }

    static void setTree(int now, int level, int parent) {
        depth[now] = level;
        parents[now] = parent;

        for (Integer integer : tree.get(now)) {
            if (integer != parent) {
                setTree(integer, level + 1, now);
            }
        }
    }

    static int findCommonAncestor(int node1, int node2) {
        int level1 = depth[node1];
        int level2 = depth[node2];

        while (level1 > level2) {
            node1 = parents[node1];
            level1--;
        }

        while (level1 < level2) {
            node2 = parents[node2];
            level2--;
        }

        while (node1 != node2) {
            node1 = parents[node1];
            node2 = parents[node2];
        }

        return node1;
    }
}
