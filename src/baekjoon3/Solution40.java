package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 1922 네트워크 연결 (Gold4)
 * 뭔가 느낌적으로 우선순위 큐를 사용해야할거같은 느낌이 들어서 사용했더니 통과함
 * 노드의 갯수만한 방문배열을 하나 선언하고
 * 각 노드에서 이동가능한 노드와 그 가중치를 인접리스트에 저장함
 * 무방향 그래프이기때문에 양쪽 노드 전부 입력해주면 됨
 * 시작은 1번 노드부터 시작해서 1번노드에서 갈 수 있는 노드와 간선의 가중치가 저장된 배열을 우선순위 큐에 집어넣음
 * 우선순위 큐이기때문에 가중치가 적은 노드가 앞으로 갈거임 그 노드를 큐에서 꺼내 이동시키면 됨
 * 단 이때 가중치가 적어도 이미 방문한적이 있는 노드라면 이동 불가능
 * 즉 방문한적 없는 노드중 가중치가 가장 적은 노드로 이동시키면 됨
 * 1번 노드는 가중치가 4인 3번 노드와 5인 2번 노드로 갈 수 있다면 가중치가 적은 3번 노드로 갈거임
 * 그러면 우선순위 큐에는 2-5가 남게되고 현재위치는 3번 노드가됨
 * 3번 노드가 갈 수 있는 위치는 1-4 2-3 4-6 이기때문에 일단 전부 큐에 넣음
 * 그러면 우선순위 큐 이기때문에 2-3 1-4 2-5 4-6 으로 정렬될거임
 * 그러면 다음 노드는 2-3에 의해 2번노드로 가게됨
 * 이런식으로 계속 반복하다보면 최소가중치를 구할 수 있다.
 * 다른사람의 풀이를 보니 크루스칼 알고리즘이라는것을 사용하여 최소신장트리를 만드는것을 볼수 있었다.
 * 다음에는 해당 알고리즘을 사용하여 풀어봐야겠다.
 */
public class Solution40 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int answer = 0;
        int N = Integer.parseInt(bufferedReader.readLine());
        int M = Integer.parseInt(bufferedReader.readLine());
        List<ArrayList<int[]>> list = new ArrayList<>();
        Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        boolean[] visited = new boolean[N + 1];
        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            int c = Integer.parseInt(tokenizer.nextToken());

            list.get(a).add(new int[] {b, c});
            list.get(b).add(new int[] {a, c});
        }

        int now = 1;
        visited[now] = true;
        while (check(visited)) {
            queue.addAll(list.get(now));

            int next;
            int cost;
            while (true) {
                int[] poll = queue.poll();

                if (!visited[poll[0]]) {
                    next = poll[0];
                    cost = poll[1];
                    break;
                }
            }
            now = next;
            answer += cost;
            visited[now] = true;
        }
        System.out.println(answer);
    }

    static boolean check(boolean[] visited) {
        for (int i = 1; i < visited.length; i++) {
            if (!visited[i]) {
                return true;
            }
        }
        return false;
    }
}
