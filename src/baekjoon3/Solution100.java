package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 11562 백양로 브레이크 (Gold3)
 * 양방항으로 연결된 간선은 1 단방향으로 연결된 간선에서는 정방향은 1 역방향은 -1 로 설정하여
 * 플로이드 와셜 알고리즘을 적용한 뒤 백트래킹 알고리즘을 사용하여 -1인 부분을 카운트 시켜주면서 도착지 까지 가중치를 계산하는 방법으로 풀었더니
 * 17퍼센트에서 시간초과가 발생하였다.
 * 이 문제는 어떻게 접근해야할까
 * 양방향으로 되어있는 길의 가중치는 0으로 설정하고
 * 단방향인데 정방향인 부분은 가중치를 0으로 설정하고 역방향인부분은 가중치를 1로 설정하면 된다.
 * 그렇게 설정하고 플로이드 워셜 알고리즘을 실행하면 우리가 원하는 값이 나오게 된다.
 * 내가 너무 어렵게 문제를 접근하고 있었던 것이다.
 */
public class Solution100 {
    static final int INF = 9999999;
    static int N;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        StringBuilder answer = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(map[i], INF);
            map[i][i] = 0;
        }
        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            int u = Integer.parseInt(tokenizer.nextToken()) - 1;
            int v = Integer.parseInt(tokenizer.nextToken()) - 1;
            String b = tokenizer.nextToken();
            if (b.equals("1")) {
                map[u][v] = 0;
                map[v][u] = 0;
            } else {
                map[u][v] = 0;
                map[v][u] = 1;
            }
        }
        floyd();
        int k = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < k; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            int s = Integer.parseInt(tokenizer.nextToken()) - 1;
            int e = Integer.parseInt(tokenizer.nextToken()) - 1;
            answer.append(map[s][e]).append("\n");
        }
        System.out.println(answer);
    }
    static void floyd() {
        for (int mid = 0; mid < N; mid++) {
            for (int start = 0; start < N; start++) {
                for (int end = 0; end < N; end++) {
                    if (start == end || start == mid || mid == end) {
                        continue;
                    }
                    map[start][end] = Math.min(map[start][end], map[start][mid] + map[mid][end]);
                }
            }
        }
    }

}
