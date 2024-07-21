package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 21278 호석이 두 마리 치킨 (Gold5)
 * 모든 건물들이 길로 이루어진 마을에서 치킨집 2곳을 차렸을때
 * 어디다 2곳을 차려야 모든 건물을 왕복으로 다녀왔을 때 최소값이 되는가 구하는 문제
 * 즉 모든 정점에서 최단거리를 구하고 거기서 완전탐색을 사용하여 임의의 치킨집 2곳을 선정한 후
 * 최단거리의 합을 구해서 그 최단거리의 합이 가장 적은 두곳을 찾아야 하는데
 * 처음에는 완전탐색을 먼저해서 임의의 치킨집 두곳을 선정하고 거기서 플로이드 와샬 알고리즘을 실행했더니 2퍼센트에서 시간초과가 발생하였다.
 * 당연히 비효율적이다 최적의 방법은 플로이드 와샬 알고리즘을 1번 실행하고 거기서 두 치킨집을 찾는것인데
 * 어떻게 해야할지 몰라서 고민하다가 결국 다른사람의 답지를 보았다.
 * 치킨집 2곳을 선정한 후 배열을 탐색하는데 sum += Math.min(map[c1][i], map[c2][i]); 이런식으로 어떤 빌딩을 갈 때
 * 두 치킨집중 더 가까운곳의 최단거리의 합을 누적시켜주면 되는거였다.
 * 답을 보면 이렇게 간단하게 해결할 수 있는데 이걸 생각해내지못해 답을 봤다는것이 조금 답답한 문제였다.
 */
public class Solution88 {
    static final int INF = 987654321;
    static int[][] map;
    static int N;
    static int MIN = Integer.MAX_VALUE;
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
            int A = Integer.parseInt(tokenizer.nextToken()) - 1;
            int B = Integer.parseInt(tokenizer.nextToken()) - 1;

            map[A][B] = 2;
            map[B][A] = 2;
        }

        floyd();

        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                int calc = calc(i, j);
                if (MIN > calc) {
                    MIN = calc;
                    answer = new StringBuilder();
                    answer.append(i + 1).append(" ").append(j + 1).append(" ").append(calc);
                }
            }
        }
        System.out.println(answer);
    }
    static void floyd() {
        for (int mid = 0; mid < N; mid++) {
            for (int start = 0; start < N; start++) {
                for (int end = 0; end < N; end++) {
                    map[start][end] = Math.min(map[start][end], map[start][mid] + map[mid][end]);
                }
            }
        }
    }

    static int calc(int c1, int c2) {
        int sum = 0;
        for (int i = 0; i < N; i++) {
            sum += Math.min(map[c1][i], map[c2][i]);
        }
        return sum;
    }
}
