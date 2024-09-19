package baekjoon4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 15591 MooTube (Silver) (Gold5)
 * 문제이해가 정말 어려웠던 문제, 본문에 힌트랍시고 주어진 문장이 괜히 더 사람 햇갈리게 기술해놓음
 * 그냥 간단하게 시작노드부터 탐색을 시작하여
 * 그 시작 노드부터 연결된 노드들을 탐색하는데 연결된 간선의 가중치가 주어진 K보다 크면서
 * 방문하지 않은 노드들만 방문하여 더이상 탐색을 진행하지 못할때까지 반복하면서 카운트를 세면 되는 문제이다.
 * 의아한점은 나는 bfs 를사용하여 풀었는데 dfs 를 사용하여 해결한 사람이 시간, 공간 복잡도 측면에서 훨씬 효율적으로 풀었다는 것이다.
 */
public class Solution49 {
    static class Node {
        int end;
        int cost;

        public Node(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }
    }
    static int N;
    static boolean[] visited;
    static List<ArrayList<Node>> mooTube = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        StringBuilder answer = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        int Q = Integer.parseInt(tokenizer.nextToken());
        for (int i = 0; i <= N; i++) {
            mooTube.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            int p = Integer.parseInt(tokenizer.nextToken());
            int q = Integer.parseInt(tokenizer.nextToken());
            int r = Integer.parseInt(tokenizer.nextToken());

            mooTube.get(p).add(new Node(q, r));
            mooTube.get(q).add(new Node(p, r));
        }

        for (int i = 0; i < Q; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            int k = Integer.parseInt(tokenizer.nextToken());
            int start = Integer.parseInt(tokenizer.nextToken());
            answer.append(bfs(k, start)).append("\n");
        }

        System.out.println(answer);
    }

    static int bfs(int k, int start) {
        int answer = 0;
        visited = new boolean[N + 1];
        visited[start] = true;
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(start, 0));
        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            int now = poll.end;

            for (Node node : mooTube.get(now)) {
                int next = node.end;
                int cost = node.cost;
                if (cost >= k && !visited[next]) {
                    answer++;
                    visited[next] = true;
                    queue.add(new Node(next, cost));
                }
            }
        }
        return answer;
    }
}
