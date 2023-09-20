package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * 2606 바이러스
 */
public class Solution53 {
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int vertex = Integer.parseInt(bufferedReader.readLine());
        int edge = Integer.parseInt(bufferedReader.readLine());
        int count = 0;
        visited = new boolean[vertex + 1];
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
        dfs(4);
        for (int i = 2; i < visited.length; i++) {
            if (visited[i]) {
                count++;
            }
        }
        System.out.println(count);
    }

    static void dfs(int node) {
        visited[node] = true;
        for (Integer integer : graph.get(node)) {
            if (!visited[integer]) {
                dfs(integer);
            }
        }
    }
}
