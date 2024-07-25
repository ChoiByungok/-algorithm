package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 17182 우주탐사선 (Gold3)
 * 모든 행성을 탐사하는데 걸리는 최소시간을 출력해야 하는데 이 문제의 어려운 점은 이미 방문 했던 행성을 다시 방문 할 수 있다는 점이다.
 * 로직을 잘못짜게 되면 계속 가중치가 적은 행성으로만 가려고 해서 무한루프에 빠질거같다. 어떻게 해야 할까
 * 정답은 플로이드 워셜 알고리즘이었다. 플로이드 워셜 알고리즘을 사용하여 행성간 최소 가중치를 미리 구해두면
 * 방문처리를 할 수 있게 된다. 2번예제의 정답은 74이고 방문 행성은 1 -> 0 -> 3 -> 0 -> 2 인데
 * 플로이드 워셜 알고리즘을 사용하면 3 -> 0 -> 2 이 부분이 3 - > 2 로 최적화가 되기 때문이다.
 * 그렇게 플로이드 워셜알고리즘을 진행 시키고 그 다음은 백트래킹을 사용하면 답을 구할수 있다.
 */
public class Solution92 {
    static int N;
    static int[][] map;
    static int answer = Integer.MAX_VALUE;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        int start = Integer.parseInt(tokenizer.nextToken());
        map = new int[N][N];
        visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }
        floyd();
        visited[start] = true;
        backTracking(start, 0, 1);
        System.out.println(answer);

    }
    static void backTracking(int now, int sum, int count) {
        if (count == N) {
            answer = Math.min(answer, sum);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                backTracking(i, sum + map[now][i], count + 1);
                visited[i] = false;
            }
        }
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
