package baekjoon4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 16202 MST 게임 (Gold3)
 * 이번 MST 관련문제는 크루스칼을 배워서 적용해볼려 했는데
 * 다른사람의 풀이가 모두 크루스칼 알고리즘으로 푼 문제밖에 없길래
 * 약간 반대심리가 발동하여 프림알고리즘으로 해결하였다.
 * 가장 가중치가 적은 간선 하나씩 줄여가면서 최소신장트리 가중치를 출력하는 문제인데
 * 한번 0이 나온 이상 그 뒤는 뭘 해도 0이 나오기때문에
 * 0 이후에는 프림알고리즘을 실행하지않고 그냥 0을 출력해주었다.
 * 근데 역시나 성능은 좋지 않다.
 */
public class Solution14 {
    static class Node {
        int num;
        int cost;

        public Node(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }
    }
    static int N;
    static List<ArrayList<Node>> graph;
    static int[][] edge;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());
        int K = Integer.parseInt(tokenizer.nextToken());
        edge = new int[M][2];

        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            int x = Integer.parseInt(tokenizer.nextToken());
            int y = Integer.parseInt(tokenizer.nextToken());
            edge[i][0] = x;
            edge[i][1] = y;
        }

        boolean zero = false;

        for (int i = 0; i < K; i++) {
            if (zero) {
                answer.append(0).append(" ");
            } else {
                graph = new ArrayList<>();
                for (int j = 0; j <= N; j++) {
                    graph.add(new ArrayList<>());
                }

                for (int j = i; j < M; j++) {
                    int x = edge[j][0];
                    int y = edge[j][1];
                    graph.get(x).add(new Node(y, j + 1));
                    graph.get(y).add(new Node(x, j + 1));
                }

                int prim = prim();
                answer.append(prim).append(" ");
                if (prim == 0) {
                    zero = true;
                }
            }
        }
        System.out.println(answer);
    }

    static int prim() {
        int answer = 0;
        boolean[] visited = new boolean[N + 1];
        Queue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(n -> n.cost));
        queue.add(new Node(1, 0));
        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            int num = poll.num;
            int cost = poll.cost;

            if (visited[num]) {
                continue;
            }
            answer += cost;
            visited[num] = true;

            if (check(visited)) {
                return answer;
            }
            for (Node node : graph.get(num)) {
                int next = node.num;
                if (!visited[next]) {
                    queue.add(node);
                }
            }

        }
        return 0;
    }

    static boolean check(boolean[] visited) {
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                return false;
            }
        }
        return true;
    }
}
