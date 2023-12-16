package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 11725 트리의 부모 찾기
 */
public class Solution70 {
    static class Node {
        int parent;
        int child;

        public Node(int parent, int child) {
            this.parent = parent;
            this.child = child;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        List<ArrayList<Integer>> graph = new ArrayList<>();
        boolean[] visited = new boolean[N + 1];
        int[] parents = new int[N + 1];
        StringBuilder answer = new StringBuilder();
        Queue<Node> queue = new LinkedList<>();

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < N - 1; i++) {
            String[] split = bufferedReader.readLine().split(" ");
            int a = Integer.parseInt(split[0]);
            int b = Integer.parseInt(split[1]);
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        for (Integer integer : graph.get(1)) {
            queue.add(new Node(1, integer));
        }
        visited[1] = true;

        while (!queue.isEmpty()) {
            Node now = queue.poll();
            int parent = now.parent;
            int child = now.child;
            if (parents[child] != 0) {
                continue;
            }
            parents[child] = parent;
            for (Integer integer : graph.get(child)) {
                if (!visited[integer]) {
                    visited[integer] = true;
                    queue.add(new Node(child, integer));
                }
            }
        }
        for (int i = 2; i <= N; i++) {
            answer.append(parents[i]).append("\n");
        }
        System.out.println(answer);
    }
}
