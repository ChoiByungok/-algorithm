package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 14675 단절점과 단절선 (Silver1)
 * 단절점(cut vertex) : 해당 정점을 제거하였을 때, 그 정점이 포함된 그래프가 2개 이상으로 나뉘는 경우, 이 정점을 단절점이라 한다.
 * 단절선(bridge) : 해당 간선을 제거하였을 때, 그 간선이 포함된 그래프가 2개 이상으로 나뉘는 경우, 이 간선을 단절선이라 한다.
 * 이 문제는 트리의 성질을 잘 알고 있으면 그래프 탐색 없이도 쉽게 풀수 있는 문제라고 한다
 * 물론 나는 잘 몰라서 다른 사람의 풀이를 참조하였다.
 * 트리의 모든 간선은 노드와 연결되어 있기 때문에 모든 간선은 단절선 이므로 질의의 2번이 입력된다면 yes를 출력해주면 된다.
 * 그럼 이제 어떤 정점이 단절점인지 구분을 해야 하는데 해당 정점이 간선을 2개이상 가지고 있다면 단절점이 되는 것이다.
 * 다른 정점들과 연결된 간선이 1개라면 그 정점을 제거 해봤자 자기 혼자만 사라지고 말기 때문이다
 * 결론은 정점과 간선의 정보를 인접리스트 형식으로 입력 받은 뒤
 * 1이 입력되었을 땐 해당 노드의 간선의 숫자가 1개 인지 아닌지만 구분해서 출력하면 되고
 * 2가 입력되었을 땐 그냥 yes 를 출력해주면 된다.
 */
public class Solution69 {
    static List<ArrayList<Integer>> tree = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        int N = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i <= N; i++) {
            tree.add(new ArrayList<>());
        }
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            tree.get(a).add(b);
            tree.get(b).add(a);
        }

        int q = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < q; i++) {
            StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
            String query = tokenizer.nextToken();
            int num = Integer.parseInt(tokenizer.nextToken());

            if (query.equals("1")) {
                ArrayList<Integer> node = tree.get(num);
                if (node.size() == 1) {
                    answer.append("no").append("\n");
                } else {
                    answer.append("yes").append("\n");
                }
            } else {
                answer.append("yes").append("\n");
            }
        }
        System.out.println(answer);
    }
}
