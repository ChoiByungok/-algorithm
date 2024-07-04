package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 2668 숫자 고르기 (Gold5)
 * 문제 이해하는게 조금 어려웠던 문제 사이클을 발생시키는 숫자들을 고르면 된다.
 * 배열의 첫번째 요소부터 마지막까지 탐색을 하면서 찾아 내야 하는데
 * 1부터 탐색을 시작했다면 다시 1로 돌아와야 한다는 소리이다.
 * 1 3 1, 3 1 3, 5 5 이렇게 사이클이 돌으므로 리스트에는 3, 1, 5가 들어갈 것이다.
 * 근데 오름차순 으로 출력하랬으니 정렬을 하고 출력해주면 된다.
 */
public class Solution71 {
    static int[] graph;
    static boolean[] visited;
    static List<Integer> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        int N = Integer.parseInt(bufferedReader.readLine());
        graph = new int[N + 1];
        visited = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = Integer.parseInt(bufferedReader.readLine());
        }

        for (int i = 1; i <= N; i++) {
            visited[i] = true;
            dfs(i, i);
            visited[i] = false;
        }
        answer.append(list.size()).append("\n");
        Collections.sort(list);
        for (Integer integer : list) {
            answer.append(integer).append("\n");
        }
        System.out.println(answer);
    }

    static void dfs(int start, int end) {
        if (!visited[graph[start]]) {
            visited[graph[start]] = true;
            dfs(graph[start], end);
            visited[graph[start]] = false;
        }

        if (graph[start] == end) {
            list.add(start);
        }
    }
}
