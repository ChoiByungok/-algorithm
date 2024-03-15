package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1531 투명 (Silver5)
 * 100X100 인트형 이차원 배열 만들고
 * x1 y1 부터 x2 y2 까지 반복문 돌리면서 값을 1씩 증가시켜 줌
 * 그리고 마지막에 다시 2차원 배열 순회하면서
 * 값이 M 초과인 부분만 카운트 세서 출력하면 됨
 */
public class Solution160 {
    public static void main(String[] args) throws IOException {
        int[][] map = new int[101][101];
        int answer = 0;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());

        while (N-- > 0) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            int x1 = Integer.parseInt(tokenizer.nextToken());
            int y1 = Integer.parseInt(tokenizer.nextToken());
            int x2 = Integer.parseInt(tokenizer.nextToken());
            int y2 = Integer.parseInt(tokenizer.nextToken());
            for (int i = x1; i <= x2; i++) {
                for (int j = y1; j <= y2; j++) {
                    map[i][j]++;
                }
            }
        }
        for (int i = 1; i <= 100; i++) {
            for (int j = 1; j <= 100; j++) {
                if (map[i][j] > M) {
                    answer++;
                }
            }
        }

        System.out.println(answer);
    }
}
