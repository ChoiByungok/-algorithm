package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 1707 이분 그래프 (Gold4)
 * 처음에 이분 그래프가 무슨말인지 몰라서 한참 해멤
 * 정점을 둘로 나눴을때 공유하는 간선이 있는지 없는지 판단하는것인데
 * 그냥 간단하게 1번 정점이랑 2번 3번 정점이 연결되어 있다고 치면
 * 1번 정점을 1이라고 했을때 2번 3번정점은 2가 되어야함
 * 그리고 2번 정점과 4번 정점이 연결되어있다고 치면 4번정점은 다시 1이되야함
 * 만약 4번정점과 1번정점이 연결되어 있다면 두 정점은 숫자가 달라야하는데 앞에서 봤듯이 두 정점은 둘다 1임
 * 이러면 이분그래프가 아닌것임 이제 이분그래프가 뭔지 알았으니 그래프 탐색을 이용하여 입력받은 그래프가
 * 이분그래프인지 아닌지 분별하면 됨
 * 이때 주의해야 할 점은 간선이 없는 입력값이 주어진다는 것이랑
 * 모든 그래프가 연결되어 있지 않다는것만 조심하면 된다.
 * 그렇기 때문에 모든 정점을 다 확인해봐야함 방문배열을 하나 만들어 놓으면
 * 모든 정점을 다 확인해 볼수는 있지만 다 탐색하지는 않기때문에 시간초과가 발생하지 않음
 */
public class Solution30 {
    static List<ArrayList<Integer>> graph;
    static int[] division;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        StringBuilder answer = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < T; i++) {
            graph = new ArrayList<>();
            String[] VE = bufferedReader.readLine().split(" ");
            int V = Integer.parseInt(VE[0]);
            int E = Integer.parseInt(VE[1]);
            for (int j = 0; j <= V; j++) {
                graph.add(new ArrayList<>());
            }
            division = new int[V + 1];
            visited = new boolean[V + 1];
            for (int j = 0; j < E; j++) {
                String[] split = bufferedReader.readLine().split(" ");
                int V1 = Integer.parseInt(split[0]);
                int V2 = Integer.parseInt(split[1]);
                graph.get(V1).add(V2);
                graph.get(V2).add(V1);
            }
            boolean YES = true;
            for (int j = 1; j <= V; j++) {
                if (!visited[j]) {
                    if (!bfs(j)) {
                        YES = false;
                        break;
                    }
                }
            }
            answer.append(YES ? "YES" : "NO").append("\n");

        }
        System.out.println(answer);
    }

    static boolean bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;
        division[start] = 1;

        while (!queue.isEmpty()) {
            int poll = queue.poll();
            int div = division[poll];

            for (Integer integer : graph.get(poll)) {
                if (poll == integer) {
                    continue;
                }
                int div2 = division[integer];
                if (div == 1) {
                    if (div2 == 0) {
                        division[integer] = 2;
                    } else {
                        if (div == div2) {
                            return false;
                        }
                    }
                } else {
                    if (div2 == 0) {
                        division[integer] = 1;
                    } else {
                        if (div == div2) {
                            return false;
                        }
                    }
                }
                if (!visited[integer]) {
                    visited[integer] = true;
                    queue.add(integer);
                }
            }
        }
        return true;
    }
}
