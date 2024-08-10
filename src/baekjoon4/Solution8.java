package baekjoon4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 18243 Small World Network (Silver1)
 * 플로이드 워셜 알고리즘 응용문제 직접 연결된 친구들의 가중치는 1로두고
 * 플로이드 워셜 알고리즘을 실행한 뒤 배열을 탐색하여 가중치가 6을 초과하는 정점이 존재하면  bigWorld 출력해주면 된다.
 */
public class Solution8 {
    static int INF = 5000;
    static int N;
    static int[][] network;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());

        network = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(network[i], INF);
            network[i][i] = 0;
        }

        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            int A = Integer.parseInt(tokenizer.nextToken()) - 1;
            int B = Integer.parseInt(tokenizer.nextToken()) - 1;
            network[A][B] = 1;
            network[B][A] = 1;
        }
        floyd();
        System.out.println(answer() ? "Small World!" : "Big World!");
    }

    static void floyd() {
        for (int mid = 0; mid < N; mid++) {
            for (int start = 0; start < N; start++) {
                for (int end = 0; end < N; end++) {
                    if (start == end || start == mid || mid == end) {
                        continue;
                    }
                    network[start][end] = Math.min(network[start][end], network[start][mid] + network[mid][end]);
                }
            }
        }
    }

    static boolean answer() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (network[i][j] > 6) {
                    return false;
                }
            }
        }

        return true;
    }
}
