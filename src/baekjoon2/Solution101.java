package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 4803 트리
 * 이 문제는 입력받은 정점과 간선을 보고 해당 그래프가 트리인지 그래프인지 구분을 해야한다.
 * 정점간의 사이클이 존재하면 그래프이고 존재하지 않으면 트리이다.
 * 트리의 조건은 정점을 v 간선은 e라고 했을때 v = e + 1 의 공식이 성립되면 트리이다.
 * 입력을 받아 인접리스트를 만들어준다
 * 방문노드 배열을 생성한다.
 * 그 이후 bfs 알고리즘을 사용하여 정점과 간선의 갯수를 세어준다.
 * 작업이 끝난후에 저 공식을 대입하여 해당 그래프가 트리인지 아닌지 구분해주면 된다.
 */
public class Solution101 {
    static List<ArrayList<Integer>> graph;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        int caseCount = 1;
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine.equals("0 0")) {
                break;
            }
            int count = 0;
            String[] nm = readLine.split(" ");
            int N = Integer.parseInt(nm[0]);
            int M = Integer.parseInt(nm[1]);
            graph = new ArrayList<>();
            visited = new boolean[N + 1];
            for (int i = 0; i <= N; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < M; i++) {
                String[] ab = bufferedReader.readLine().split(" ");
                int a = Integer.parseInt(ab[0]);
                int b = Integer.parseInt(ab[1]);

                graph.get(a).add(b);
                graph.get(b).add(a);
            }


            for (int i = 1; i <= N; i++) {
                if (!visited[i]) {
                    if (bfs(i)) {
                        count++;
                    }
                }
            }

            switch (count) {
                case 0 :
                    answer.append("Case ").append(caseCount).append(": No trees.").append("\n");
                    break;
                case 1:
                    answer.append("Case ").append(caseCount).append(": There is one tree.").append("\n");
                    break;
                default:
                    answer.append("Case ").append(caseCount).append(": A forest of ").append(count).append(" trees.").append("\n");
                    break;
            }
            caseCount++;
        }

        System.out.println(answer);
    }

    private static boolean bfs(int i) {
        Queue<Integer> queue = new LinkedList<>();
        int vertex = 0;
        int edge = 0;
        queue.add(i);
        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            if (visited[poll]) {
                continue;
            }
            visited[poll] = true;
            vertex++;

            for (Integer integer : graph.get(poll)) {
                if (!visited[integer]) {
                    queue.add(integer);
                    edge++;
                }
            }
        }
        return vertex == edge + 1;
    }
}
