package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 1197 최소 스패닝 트리 (Gold4)
 * 최소신장 트리를 구현하는데 크루스칼 알고리즘만 사용되는줄 알았는데
 * 알고보니 나의 풀이방법이 또다른 방법인 프림(prim) 알고리즘이었던 것이다.
 * 크루스칼 알고리즘보다 직관적이라고 생각함
 */
public class Solution41 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        int answer = 0;
        int V = Integer.parseInt(tokenizer.nextToken());
        int E = Integer.parseInt(tokenizer.nextToken());

        List<ArrayList<int[]>> list = new ArrayList<>();
        Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        boolean[] visited = new boolean[V + 1];
        for (int i = 0; i <= V; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            int A = Integer.parseInt(tokenizer.nextToken());
            int B = Integer.parseInt(tokenizer.nextToken());
            int C = Integer.parseInt(tokenizer.nextToken());
            list.get(A).add(new int[] {B, C});
            list.get(B).add(new int[] {A, C});
        }

        int now = 1;
        visited[now] = true;
        int count = 1;
        while (count != V) {
            queue.addAll(list.get(now));
            int next;
            int cost;

            while (true) {
                int[] poll = queue.poll();
                if (!visited[poll[0]]) {
                    next = poll[0];
                    cost = poll[1];
                    count++;
                    break;
                }
            }

            now = next;
            answer += cost;
            visited[now] = true;
        }

        System.out.println(answer);
    }
}
