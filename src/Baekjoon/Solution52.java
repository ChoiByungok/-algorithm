package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 * 24479 알고리즘 수업 - 깊이 우선 탐색 1
 */
public class Solution52 {

    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static boolean[] visited;
    static int[] result;
    static int count = 1;
    static StringBuilder answer = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] NMR = bufferedReader.readLine().split(" ");
        int N = Integer.parseInt(NMR[0]); //정점의 수
        int M = Integer.parseInt(NMR[1]); //간선의 수
        int R = Integer.parseInt(NMR[2]); //시작할 노드

        visited = new boolean[N + 1];
        result = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            String[] split = bufferedReader.readLine().split(" ");
            int x = Integer.parseInt(split[0]);
            int y = Integer.parseInt(split[1]);
            graph.get(x).add(y);
            graph.get(y).add(x);
        }
        dfs(R);
        for (int i = 1; i < result.length; i++) {
            answer.append(result[i]).append("\n");
        }
        System.out.println(answer);
    }

    static void dfs(int node) {
        visited[node] = true;
        result[count++] = node;
        Collections.sort(graph.get(node));
        for (int i = 0; i < graph.get(node).size(); i++) {
            if (!visited[graph.get(node).get(i)]) {
                dfs(graph.get(node).get(i));
            }
        }
    }
}
