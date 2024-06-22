package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 1240 노드사이의 거리 (Gold5)
 * 처음에는 노드사이의 거리를 출력하라길래 플로이드 워셜이나 다익스트라를 사용해야 하나 고민함
 * 근데 노드의 갯수가 1000개가 넘어가므로 플로이드 워셜은 못하고
 * 간선이 여러개가 아닌거같아서 다익스트라도 쓸 필요가 없다고 판단함
 * 그래서 그냥 dfs를 사용함 그래프 탐색을 진행하면서 매개변수로 주어진
 * start와 end의 값이 같아진다면 결국 도착했다는 뜻이고 그때 누적되었던 값을
 * 출력해주면 된다. 이때 주의해야 할점은 방문배열을 만들어야한다는것이다.
 * 그렇지 않으면 무한루프를 돌것이기 때문이다. 생각보다 너무 간단했던(?) 문제
 */
public class Solution59 {
    static class Node {
        int end;
        int cost;

        public Node(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }
    }
    static List<ArrayList<Node>> tree = new ArrayList<>();
    static int distance;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());
        for (int i = 0; i <= N; i++) {
            tree.add(new ArrayList<>());
        }
        for (int i = 0; i < N - 1; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            int c = Integer.parseInt(tokenizer.nextToken());
            tree.get(a).add(new Node(b, c));
            tree.get(b).add(new Node(a, c));
        }

        for (int i = 0; i < M; i++) {
            visited = new boolean[N + 1];
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            int start = Integer.parseInt(tokenizer.nextToken());
            int end = Integer.parseInt(tokenizer.nextToken());
            search(start, end, 0);
            answer.append(distance).append("\n");
        }
        System.out.println(answer);
    }

    static void search(int start, int end, int total) {
        if (start == end) {
            distance = total;
            return;
        }

        visited[start] = true;
        for (Node node : tree.get(start)) {
            int next = node.end;
            int cost = node.cost;
            if (!visited[next]) {
                search(next, end, total + cost);
            }
        }
    }
}
