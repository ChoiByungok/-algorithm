package baekjoon4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 21937 작업 (Silver1)
 * 해당 작업 X를 하기위해 앞서 몇개의 작업을 종료해야 하나 구하는 문제인데
 * 예제 1번대로 풀려면 그냥 dfs 탐색하면서 depth 를 하나씩 증가시키면 풀리지만
 * 그 로직대로 3번을 풀려면 3이라는 답을 도출해낼수가 없다. 왜냐하면 1번노드에서 2가지의 일을 해야 하므로 한번에 depth 를 2증가시켜야 하지만
 * 그러면 1번예제가 풀리지 않는다. 이걸 어떻게 해결해야 할까 문제는 이해가 가는데 어떻게 해결해야할지 모르겠다.
 * 일단 주어진 작업 X 부터 역순으로 탐색을 진행해야 할거같은 느낌이 든다. 그러면 입력받을 때부터 역방향으로 입력을 받아야 할거같다.
 * 결국 답지를 보고 해결했다 이 문제는 그냥 주어진 작업 X 부터 역으로 탐색하여
 * 마주치는 모든 노드를 체크해서 그 수를 더해주면 되는 문제였다.
 * 내가 너무 어렵게 생각하여 더이상 탐색할 수 없는 마지막 노드에 도착했을 때 여기까지 오는데 마주친 노드의 수 중 가장 큰 수를 출력하는줄 알고
 * 그렇게 풀었지만 다시 보니깐 문제가 그냥 작업 X를 하기 위해 해야하는 선행작업을 모두 구하는 문제였던것이다.
 * 그래서 그냥 방문배열 하나 선언하고 역으로 탐색하여 더이상 탐색할 수 없는 지점까지 모두 방문을 하여 그 노드의 수만 구하면 되는문제였다.
 * 그러니 중간에 경로가 갈라지더라도 상관이 없었던 것이다.
 */
public class Solution32 {
    static int N;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static boolean[] visited;
    static int answer = 0;
    static int X;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());
        visited = new boolean[N + 1];
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            int A = Integer.parseInt(tokenizer.nextToken());
            int B = Integer.parseInt(tokenizer.nextToken());
            graph.get(B).add(A);
        }
        System.out.println(graph);
        X = Integer.parseInt(bufferedReader.readLine());
        visited[X] = true;
        dfs(X);
        System.out.println(answer);
    }

    static void dfs(int now) {
        for (Integer next : graph.get(now)) {
            if (!visited[next]) {
                answer++;
                visited[next] = true;
                dfs(next);
            }
        }
    }
}
