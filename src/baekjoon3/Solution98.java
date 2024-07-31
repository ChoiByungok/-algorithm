package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 1738 골목길 (Gold1)
 * 벨만포드 알고리즘을 사용하여 풀어야 한다 근데 그동안은 최소경로를 구하고 음의 가중치 사이클이 있는지 판단했지만
 * 이번 문제는 최대경로를 구하고 양의 가중치 사이클이 있는지 판단해야 한다.
 * 부등호 방향만 반대로 하면 되는데 문제는 사이클이 있는지 아닌지 판단하는게 아니라 최대경로를 구해야 한다는 것이다.
 * 가중치가 갱신될때마다 경로를 따로 저장해둬야 한다.
 * 단순히 양의 사이클의 유무만 따졌더니 76퍼센트에서 틀렸다고 나왔다.
 * 왜냐하면 양의 사이클이 도착지점과 상관없는 곳에서 발생한다면 -1 이 아닌 정상적인 경로를 출력해줘야 하지만
 * 나는 양의 사이클이 존재하기만 하면 -1을 출력하도록 했기때문에 틀린것이다.
 * 즉 양의 사이클이 도착노드와 연결되어있는지 아닌지 판별작업을 추가적으로 해야 한다.
 */
public class Solution98 {
    static final int INF = -25000000;
    static class Node {
        int end;
        int cost;

        public Node(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }
    }
    static int N;
    static List<ArrayList<Node>> paths = new ArrayList<>();
    static int[] trace;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        StringBuilder answer = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());
        for (int i = 0; i <= N; i++) {
            paths.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            int u = Integer.parseInt(tokenizer.nextToken());
            int v = Integer.parseInt(tokenizer.nextToken());
            int w = Integer.parseInt(tokenizer.nextToken());
            paths.get(u).add(new Node(v, w));
        }

        if (bellman_ford()) {
            System.out.println(-1);
        } else {
            Stack<Integer> stack = new Stack<>();
            int temp = N;
            while (temp != 0) {
                stack.push(temp);
                temp = trace[temp];
            }

            while (!stack.isEmpty()) {
                answer.append(stack.pop()).append(" ");
            }
        }
        System.out.println(answer);
    }

    static boolean bellman_ford() {
        trace = new int[N + 1];
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[1] = 0;

        for (int i = 0; i < N - 1; i++) {
            for (int j = 1; j <= N; j++) {
                if (dist[j] != INF) {
                    for (Node node : paths.get(j)) {
                        int next = node.end;
                        int cost = node.cost;
                        if (dist[next] < dist[j] + cost) {
                            dist[next] = dist[j] + cost;
                            trace[next] = j;
                        }
                    }
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            if (dist[i] != INF) {
                for (Node node : paths.get(i)) {
                    int next = node.end;
                    int cost = node.cost;
                    if (dist[next] < dist[i] + cost) {
                        visited = new boolean[N + 1];
                        visited[i] = true;
                        if (isConnected(i)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private static boolean isConnected(int now) {
        if (now == N) {
            return true;
        }

        int count = 0;
        for (Node node : paths.get(now)) {
            if (!visited[node.end]) {
                visited[node.end] = true;
                if (isConnected(node.end)) {
                    count++;
                }
            }
        }
        return count > 0;
    }

}
