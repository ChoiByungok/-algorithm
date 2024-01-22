package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 1976 여행가자
 * 입력값으로 인접리스트를 생성한다음
 * bfs로 탐색하여 모든 노드들을 방문한다. 단 이때
 * boolean 형 배열을 만들어서 현재 노드를 방문처리해야 한다.
 * 하지 않으면 while 문 안에서 무한루프에 빠지게 된다.
 * 그리하여 현재 노드가 목표노드에 도달하게 되면 갈 수 있는 경로이므로 true 처리
 * 가지못하면 false 처리를 하여 문제를 풀면 된다.
 */
public class Solution106 {
    static List<ArrayList<Integer>> graph = new ArrayList<>();
    static int[] plan;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());
        int M = Integer.parseInt(bufferedReader.readLine());
        boolean yes = true;
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 1; i <= N; i++) {
            String[] split = bufferedReader.readLine().split(" ");
            for (int j = 0; j < split.length; j++) {
                if (split[j].equals("1")) {
                    graph.get(i).add(j + 1);
                }
            }
        }
        String[] split = bufferedReader.readLine().split(" ");
        plan = new int[M];
        for (int i = 0; i < split.length; i++) {
            plan[i] = Integer.parseInt(split[i]);
        }

        for (int i = 1; i < plan.length; i++) {
            int now = plan[i - 1];
            int goal = plan[i];
            if (!bfs(now, goal)) {
                yes = false;
                break;
            }
        }
        System.out.println(yes ? "YES" : "NO");

    }

    static boolean bfs(int now, int goal) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(now);
        boolean[] visited = new boolean[N + 1];
        while (!queue.isEmpty()) {
            Integer nowCity = queue.poll();
            visited[nowCity] = true;
            if (nowCity == goal) {
                return true;
            }
            for (Integer city : graph.get(nowCity)) {
                if (!visited[city]) {
                    queue.add(city);
                }
            }
        }

        return false;
    }
}
