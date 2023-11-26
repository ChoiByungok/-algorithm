package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 16562 친구비
 * 처음에는 friend 라는 객체를 만들어서 번호랑 cost를 할당하고 계산해보려고 했는데 생각해보니깐 굳이 그럴필요가 없었음
 * 깊이 우선 탐색을 사용하여 친구의 친구인 사람까지 죄다 탐색을 하여 가장 작은 코스트를 찾아냄
 * 그리고 여러개의 집단에서 도출된 최소의 비용을 전부 합쳐서 입력받은 돈보다 큰지 작은지 비교해서 풀어냈음
 */
public class Solution50 {
    static boolean[] visited;
    static int[] costs;
    static List<ArrayList<Integer>> graph = new ArrayList<>();
    static int min;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] nmk = bufferedReader.readLine().split(" ");
        int n = Integer.parseInt(nmk[0]); //학생의 수
        int m = Integer.parseInt(nmk[1]); //친구 관계 수
        int k = Integer.parseInt(nmk[2]); //가지고 있는 돈
        int answer = 0;
        visited = new boolean[n + 1];
        costs = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            String[] split = bufferedReader.readLine().split(" ");
            int x = Integer.parseInt(split[0]);
            int y = Integer.parseInt(split[1]);
            graph.get(x).add(y);
            graph.get(y).add(x);
        }

        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                min = Integer.MAX_VALUE;
                dfs(i);
                answer += min;
            }
        }
        System.out.println(answer > k ? "Oh no" : answer);
    }

    private static void dfs(int i) {
        visited[i] = true;
        min = Math.min(min, costs[i - 1]);
        for (Integer integer : graph.get(i)) {
            if (!visited[integer]) {
                dfs(integer);
            }
        }
    }

}
