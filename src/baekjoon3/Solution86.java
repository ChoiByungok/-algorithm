package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 11265 끝나지 않는 파티 (Gold5)
 * 오랜만에 풀어본 플로이드 워셜 알고리즘문제
 * 이 문제를 정답제출 현황을 보면 속도가 빠른 정답과 느린 정답이 10배 가까이 차이나는것을 볼 수 있는데
 * 다익스트라로 풀면 오래걸리는듯 싶다.
 * 뇌피셜인데 어차피 배열 길이 500밖에 안되기 때문에 플로이드 워셜 알고리즘 돌려도 12500000 번밖에 반복안한다.
 * 그냥 맘편하게 플로이드 워셜 알고리즘 딱 한번만 돌리고 그다음 확인하는게 더 나은거같다.
 */
public class Solution86 {
    static int[][] map;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        StringBuilder answer = new StringBuilder();
        N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        floyd();

        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            int start = Integer.parseInt(tokenizer.nextToken()) - 1;
            int end = Integer.parseInt(tokenizer.nextToken()) - 1;
            int time = Integer.parseInt(tokenizer.nextToken());
            answer.append(map[start][end] > time ? "Stay here" : "Enjoy other party").append("\n");
        }

        System.out.println(answer);
    }

    static void floyd() {
        for (int middle = 0; middle < N; middle++) {
            for (int start = 0; start < N; start++) {
                for (int end = 0; end < N; end++) {
                    map[start][end] = Math.min(map[start][end], map[start][middle] + map[middle][end]);
                }
            }
        }
    }

    static void print() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
