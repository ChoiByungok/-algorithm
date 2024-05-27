package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 2458 키 순서 (Gold4)
 * 주어진 정점과 간선을 가지고 자기 키가 정확히 몇번째인지 아는사람의 숫자를 구하는 문제
 * 이 문제는 플로이드 워셜 알고리즘을 사용해서 푸는문제로 알고있는데 도저히 어떻게 응용을해서 풀어야할지 감이 잡히지 않는다.
 * 플로이드 워셜 알고리즘은 모든 정점에서 최소거리를 구할 수 있는 알고리즘이지만 이 문제에선 두 정점이 어떠한 정점에 의해서
 * 연결되어 있는가를 판별할때 사용하면된다. 나였으면 백만년을 줘도 이렇게 접근할수 없었을텐데 다른사람의 풀이를보니
 * 어떠한 정점이 역방향을 포함하여 하나라도 연결되어 있으면 키 순서를 알 수 있다고 설명하고 있었다.
 * 주어진 첫번째 예제를 보면 4번 정점이 역방향을 포함하여 모든 정점들과 연결되어 있는것을 알 수 있다.
 * 우선 4번정점은 3 5번과 역방향으로 2번 6번과 정방향으로 직접 연결되어 있고 1번정점과는 5번 정점에 의해 간접적으로 연결되어있는것을 볼 수 있다.
 * 마찬가지로 2번째 예제도 1번 정점과 4번 정점이 모든 노드와 연결되어있는것을 볼 수 있다.
 * 그리하여 정리하면 플로이드 워셜 알고리즘을 이용하여 연결되어있는 정점을 모두 표시하고
 * 그 이후에 완성된 맵을 이용하여 어떠한 정점이 정방향 역방향중 하나라도 연결되어있는가를 판별하여
 * 그 갯수가 정점의 수 -1 과 같으면 그 정점은 자신의 키를 알 수 있는정점인 것이다.
 */
public class Solution33 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        int answer = 0;
        int V = Integer.parseInt(tokenizer.nextToken());
        int E = Integer.parseInt(tokenizer.nextToken());
        boolean[][] link = new boolean[V][V];
        for (int i = 0; i < E; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            link[a - 1][b - 1] = true;
        }

        for (int middle = 0; middle < V; middle++) {
            for (int start = 0; start < V; start++) {
                for (int end = 0; end < V; end++) {
                    if (link[start][middle] && link[middle][end]) {
                        link[start][end] = true;
                    }
                }
            }
        }

        for (int i = 0; i < V; i++) {
            int check = 0;
            for (int j = 0; j < V; j++) {
                if (link[i][j] || link[j][i]) {
                    check++;
                }
            }
            if (V == check + 1) {
                answer++;
            }
        }

        System.out.println(answer);
    }
}
