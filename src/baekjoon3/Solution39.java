package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1719 택배 (Gold3)
 * 기존의 플로이드 워셜 문제와는 조금 다른 가중치가 가장 적은 값을 출력하는것이아닌
 * 그 가중치가 가장 적은 경로를 찾아내야 하는 문제이다.
 * 그러면 중간중간 경로를 저장해놔야 하는데 어떻게 해야할지 아예모르겠다.
 * 다른사람의 풀이를 보니 다익스트라 알고리즘을 사용하여 해결한 모습을 볼 수 있었는데
 * 아직 다익스트라는 배우지않았고 플로이드 워셜로 해결할 수 있을거같아서 플로이드 워셜알고리즘을 이용하여 해결해보았다.
 * 2개의 2차원배열을 선언한다 하나는 가중치의 최솟값을 갱신해나가는 배열과 하나는 경로를 저장하는 배열이다.
 * 가중치 배열은 입력받을때 가중치를 넣어주고 경로배열에는 이어진 정점의 숫자를 넣어준다.
 * 플로이드워셜 알고리즘을 진행하면서 가중치의 값이 갱신되면 그때 중간노드의 값을 경로배열에 넣어준다.
 * 그렇게 가중치에따라 중간경로가 계속 갱신될것이고 마지막에 완성된 경로배열을 출력시켜주면된다.
 * 사실 사고력을 바탕으로 푼 문제가 아니라 이것저것 값 집어넣다가 소 뒷걸음질에 쥐가 잡히듯이 풀린문제라 많이 찝찝하다.
 */
public class Solution39 {
    static final int INF = 987654321;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(tokenizer.nextToken());
        int m = Integer.parseInt(tokenizer.nextToken());
        int[][] matrix = new int[n][n];
        int[][] answer = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    matrix[i][j] = INF;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            int cost = Integer.parseInt(tokenizer.nextToken());
            matrix[a - 1][b - 1] = cost;
            matrix[b - 1][a - 1] = cost;
            answer[a - 1][b - 1] = b;
            answer[b - 1][a - 1] = a;
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (matrix[i][j] > matrix[i][k] + matrix[k][j]) {
                        matrix[i][j] = matrix[i][k] + matrix[k][j];
                        answer[i][j] = answer[i][k];
                    }
                }
            }
        }

        print(answer);
    }

    static void print(int[][] matrix) {
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] == 0) {
                    answer.append("- ");
                } else {
                    answer.append(matrix[i][j]).append(" ");
                }
            }
            answer.append("\n");
        }
        System.out.println(answer);
    }
}
