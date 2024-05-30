package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 14938 서강그라운드 (Gold4)
 * 다른사람의 풀이를 보지 않고 스스로 풀어낸 2번째 플로이드 워셜문제
 * 무방향 그래프라고 했으니 입력받을 때 인접행렬 두 좌표에 모두 값을 넣어준다.
 * 자기 자신은 0으로 초기화하고 직접 연결되어있지 않은 정점들과의 간선은 INF 값으로 초기화한다.
 * 플로이드 워셜 알고리즘을 사용하여 모든 정점의 간선의 가중치 최소값을 계산하여 넣어준다.
 * 예은이가 습득가능한 보급품의 최댓값을 알아내야 하니
 * 1번 정점부터 n번 정점까지 모두 살펴보면서 계산을 하면된다.
 * 인접행렬의 각 좌표값이 수색범위보다 같거나 작으면 그 값을 더해서
 * 모두 더한 값을 이전값과 비교하여 크면 갱신하고 아니면 갱신하지 않는 방법으로
 * 최댓값을 찾아내었다.
 */
public class Solution36 {
    static final int INF = 987654321;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        int answer = Integer.MIN_VALUE;
        int n = Integer.parseInt(tokenizer.nextToken()); // 지역의 갯수 (정점의 수)
        int m = Integer.parseInt(tokenizer.nextToken()); // 예은이의 수색범위
        int r = Integer.parseInt(tokenizer.nextToken()); // 길의 개수 (간선의 수)
        int[] t = new int[n];
        int[][] matrix = new int[n][n];

        tokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < n; i++) {
            t[i] = Integer.parseInt(tokenizer.nextToken());
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    matrix[i][j] = INF;
                }
            }
        }

        for (int i = 0; i < r; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            int cost = Integer.parseInt(tokenizer.nextToken());

            matrix[a - 1][b - 1] = cost;
            matrix[b - 1][a - 1] = cost;
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (matrix[i][j] > matrix[i][k] + matrix[k][j]) {
                        matrix[i][j] = matrix[i][k] + matrix[k][j];
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] <= m) {
                    sum += t[j];
                }
            }
            answer = Math.max(answer, sum);
        }

        System.out.println(answer);
    }

    static void print(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
