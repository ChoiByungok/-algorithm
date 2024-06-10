package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 1446 지름길 (Silver1)
 * 난이도는 실버1인데 체감상 이전에 풀었던 골드4 짜리 문제보다 훨씬어려웠던 문제
 * 어떻게 접근해야할지 몰라서 한참 고민하다가 결국 다른사람의 코드를 보고 간신히 이해를 하여풀었다.
 * 사실은 다익스트라 카테고리에 묶여있길래 다익스트라로 풀려고 했는데 어떻게 풀어야 할지 몰라서
 * 다른사람의 풀이를 참고했는데 dfs로 해결하였길래 dfs로 풀었다. 문제를 제출한 시점에서
 * 느낌상 이해했을 뿐 완전히 이해하진 못했기 때문에 풀이과정을 상세히 적지는 못할거같다.
 */
public class Solution47 {
    static class Node {
        int start;
        int end;
        int distance;

        public Node(int start, int end, int distance) {
            this.start = start;
            this.end = end;
            this.distance = distance;
        }
    }
    static List<Node> shortCut = new ArrayList<>();
    static boolean[] visited;
    static int D;
    static int result = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        D = Integer.parseInt(tokenizer.nextToken());
        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            int start = Integer.parseInt(tokenizer.nextToken());
            int end = Integer.parseInt(tokenizer.nextToken());
            int distance = Integer.parseInt(tokenizer.nextToken());
            if (end > D) {
                continue;
            }
            if (end - start < distance) {
                continue;
            }
            shortCut.add(new Node(start, end, distance));
        }
        shortCut.sort((o1, o2) -> {
            if (o1.start == o2.start) {
                return o1.end - o2.end;
            }
            return o1.start - o2.start;
        });
        visited = new boolean[shortCut.size()];
        dfs(0, 0);
        System.out.println(result);
    }

    private static void dfs(int now, int distance){
        if(now > D){
            return;
        }
        else if(now == D){
            result = Math.min(result, distance);
            return;
        }
        else{
            result = Math.min(result, distance+(D - now));
        }

        for(int i = 0; i < shortCut.size(); i++){
            if(!visited[i]) {
                Node node = shortCut.get(i);
                if(node.start == now) {
                    visited[i]=true;
                    dfs(node.end, distance + node.distance);
                    visited[i]=false;
                }
            }
        }
        dfs(now + 1, distance + 1);
    }
}
