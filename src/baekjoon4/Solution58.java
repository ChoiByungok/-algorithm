package baekjoon4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 16940 BFS 스페셜 저지 (Gold3)
 * bfs 탐색을 진행하는데 입력으로 주어진 순서가 올바른 순서면 1을 출력 아니면 0을 출력해야 한다.
 * 탐색은 1번 정점부터 시작한다.
 * 1. 입력으로 주어진 순서를 배열형태로 저장해놓는다.
 * 2. 1번정점과 연결되어 있는 정점들을 방문처리하고 그 정점들을 SET 자료구조에 넣는다.
 * 3. 입력으로 주어진 방문배열의 1번인덱스부터 방금 추가된 SET 자료구조의 사이즈만큼 반복을 실시한다. 1 ~ 1 + set.size
 * 4. 순서는 중요하지 않지만 방금 추가된 SET 자료구조에 입력으로 주어진 숫자들이 모두 들어있어야 한다.
 * 5. 들어있으면 큐에 담는다 아니면 0을 반환한다.
 * 1번정점이 2 와 3 과 연결되어있고 2는 4 5  3은 6이랑 연결되어있을때
 * 1 2 3 4 5 6 으로 주어지거나 1 3 2 6 4 5 로 주어진다면 올바른 순서인데
 * 1 3 2 4 5 6 은 안된다는 것이다.
 */
public class Solution58 {
    static int N;
    static List<ArrayList<Integer>> graph = new ArrayList<>();
    static boolean[] visited;
    static int[] answer;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());
        visited = new boolean[N + 1];
        answer = new int[N];
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
            int A = Integer.parseInt(tokenizer.nextToken());
            int B = Integer.parseInt(tokenizer.nextToken());
            graph.get(A).add(B);
            graph.get(B).add(A);
        }

        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < N; i++) {
            answer[i] = Integer.parseInt(tokenizer.nextToken());
        }

        System.out.println(bfs());
    }

    static int bfs() {
        int index = 1;
        visited[1] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        while (!queue.isEmpty()) {
            Set<Integer> set = new HashSet<>();
            Integer now = queue.poll();
            for (Integer next : graph.get(now)) {
                if (!visited[next]) {
                    visited[next] = true;
                    set.add(next);
                }
            }

            for (int i = index; i < index + set.size(); i++) {
                int next = answer[i];
                if (set.contains(next)) {
                    queue.add(next);
                } else {
                    return 0;
                }
            }

            index += set.size();
        }
        return 1;
    }
}
