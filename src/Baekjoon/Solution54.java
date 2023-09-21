package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 1260 DFS 와 BFS
 */
public class Solution54 {
    static StringBuilder dfsAnswer = new StringBuilder();
    static StringBuilder bfsAnswer = new StringBuilder();
    static boolean[] dfsVisited;
    static boolean[] bfsVisited;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static Queue<Integer> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] VER = bufferedReader.readLine().split(" ");
        int vertex = Integer.parseInt(VER[0]);
        int edge = Integer.parseInt(VER[1]);
        int start = Integer.parseInt(VER[2]);

        dfsVisited = new boolean[vertex + 1];
        bfsVisited = new boolean[vertex + 1];

        for (int i = 0; i <= vertex; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < edge; i++) {
            String[] split = bufferedReader.readLine().split(" ");
            int x = Integer.parseInt(split[0]);
            int y = Integer.parseInt(split[1]);

            graph.get(x).add(y);
            graph.get(y).add(x);
        }
        for (ArrayList<Integer> integers : graph) {
            Collections.sort(integers);
        }

        dfs(start);
        bfs(start);
        System.out.println(dfsAnswer);
        System.out.println(bfsAnswer);
    }

    static void dfs(int node) {
        dfsVisited[node] = true;
        dfsAnswer.append(node).append(" ");
        for (Integer adjacent : graph.get(node)) {
            if (!dfsVisited[adjacent]) {
                dfs(adjacent);
            }
        }
    }

    private static void bfs(int start) {
        queue.add(start);
        while (!queue.isEmpty()) {
            Integer node = queue.poll();
            bfsVisited[node] = true;
            bfsAnswer.append(node).append(" ");
            for (Integer adjacent : graph.get(node)) {
                if (!queue.contains(adjacent) && !bfsVisited[adjacent]) {
                    queue.add(adjacent);
                }
            }
        }
    }
}
