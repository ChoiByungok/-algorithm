package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 1325 효율적인 해킹 (Silver1)
 * 제목 말 그대로 효율적으로 풀어야 시간초과가 발생하지 않는 문제
 * 단방향 그래프이고 a가 b를 신뢰하면 b를 해킹했을때 a도 해킹된다길래
 * 인접 리스트 b에 a를 담는식으로 초기화를 했는데
 * 이렇게 하면 시간초과가 발생한다. 내가 어떤 컴퓨터를 해킹 할 수 있는지 찾지말고
 * 내가 어떤 컴퓨터에 의해 해킹 되는지 확인해야 빠르다고 한다. 솔직히 왜 그런지 잘모르겠다
 * 어쨋든 무수한 시간초과 코드를 작성하고 다른사람의 풀이를 보고 간신히 해결하였다.
 * 실버1 문제였지만 괜히 정답률이 낮은게 아니였다.
 */
public class Solution76 {
    static List<ArrayList<Integer>> network = new ArrayList<>();
    static int[] counts;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        StringBuilder answer = new StringBuilder();
        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());

        counts = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            network.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            int A = Integer.parseInt(tokenizer.nextToken());
            int B = Integer.parseInt(tokenizer.nextToken());
            network.get(A).add(B);
        }


        for (int i = 1; i <= N; i++) {
            visited = new boolean[N + 1];
            bfs(i);
        }
        int max = 0;
        for (int i = 1; i <= N; i++) {
            if (counts[i] > max) {
                max = counts[i];
            }
        }

        for (int i = 1; i <= N; i++) {
            if (max == counts[i]) {
                answer.append(i).append(" ");
            }
        }
        System.out.println(answer);
    }

    static void bfs(int start) {
        Queue <Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;

        while(!queue.isEmpty()) {
            int now = queue.poll();
            for (Integer integer : network.get(now)) {
                if (!visited[integer]) {
                    visited[integer] = true;
                    counts[integer]++;
                    queue.add(integer);
                }
            }
        }
    }
}
