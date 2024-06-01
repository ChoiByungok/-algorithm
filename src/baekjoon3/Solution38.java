package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1613 역사 (Gold3)
 * 사건들의 전후관계를 출력해야하는 알고리즘
 * 역시 이 문제 또한 앞서 풀었던 저울(10159) 문제와 같다고 보면된다.
 * 해당 그래프는 방향그래프이며 입력을 받은 후
 * 모든 정점에 대해 연결되어있는 정점을 플로이드 워셜 알고리즘을 이용하여 연결한다.
 * 그리고 후에 입력받은 좌표들을 토대로
 * a 랑 b 가 입력되었다 했을때
 * arr[a][b] 가 true라면 a가 b보다 앞선 사건이기에 -1을 출력시켜주고
 * arr[b][a] 가 true라면 b가 a보다 앞선 사건이기에 1을 출력시켜주고
 * 둘다 true가 아니라면 두 사건의 전후관계를 알 수 없기에 0을 출력시켜주면 된다.
 */
public class Solution38 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int K = Integer.parseInt(tokenizer.nextToken());
        boolean[][] matrix = new boolean[N][N];

        for (int i = 0; i < K; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            matrix[a - 1][b - 1] = true;
        }

        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (matrix[i][k] && matrix[k][j]) {
                        matrix[i][j] = true;
                    }
                }
            }
        }

        int s = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < s; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            if (matrix[a - 1][b - 1]) {
                answer.append(-1).append("\n");
            } else if (matrix[b - 1][a - 1]) {
                answer.append(1).append("\n");
            } else {
                answer.append(0).append("\n");
            }
        }

        System.out.println(answer);
    }
}
