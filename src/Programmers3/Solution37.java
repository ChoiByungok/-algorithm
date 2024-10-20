package Programmers3;

import java.util.*;

/**
 * 네트워크 Lv.3
 * 간접적으로 연결되있으면 하나의 네트워크로 친다.
 * 전체 노드를 탐색하여 총 몇개의 네트워크가 있는지 세면된다.
 * 1번 노드부터 방문배열 체크해가며 탐색하면 몇개의 네트워크가 있는지 셀 수 있다.
 */
public class Solution37 {
    static List<List<Integer>> graph = new ArrayList<>();
    static boolean[] visited;
    public int solution(int n, int[][] computers) {
        int answer = 0;
        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                if (computers[i][j] == 1) {
                    graph.get(i).add(j);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                answer++;
                bfs(i);
            }
        }
        return answer;
    }
    static void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            Integer now = queue.poll();

            for (Integer next : graph.get(now)) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.add(next);
                }
            }
        }
    }

    public static void main(String[] args) {
        Solution37 solution37 = new Solution37();
        System.out.println(solution37.solution(3, new int[][] {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}}));
        System.out.println(solution37.solution(3, new int[][] {{1, 1, 0}, {1, 1, 1}, {0, 1, 1}}));
    }
}
