package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 10994 별 찍기 - 19 (Silver4)
 * 동심 사각형을 찍는 문제인데
 * 처음에는 이게 출력만 봐서는 직사각형인줄 생각하고 접근했다가 알고보니 정사각형이었던 문제
 * 우선 숫자 N을 입력받으면
 * ((N - 1) * 4) + 1 크기의 2차원 배열을 만든다 나는 boolean 타입으로 만들었다.
 * 그리고 규칙을 보면
 * 맨 바깥쪽 테두리에 별을 전부찍고
 * 한번 건너뛰고 그다음에 테두리에 별을 찍고 이런식으로 반복된다.
 * 나같은 경우는 반복문을 (배열 크기 /2 + 1) 만큼만 반복을 하였다.
 * 그리고 반복문이 짝수번째 반복문일때 해당 좌표의 테두리를 전부 true로 바꾸어 주었다.
 * 그리고 완성된 2차원 배열을 이용하여 true 일때 별을 찍어주었다.
 */
public class Solution176 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        int N = Integer.parseInt(bufferedReader.readLine());
        int size = ((N - 1) * 4) + 1;
        boolean[][] map = new boolean[size][size];

        for (int i = 0; i < size / 2 + 1; i++) {
            if (i % 2 == 0) {
                int endX = size - 1 - i;
                int endY = size - 1 - i;
                for (int j = i; j <= endY; j++) {
                    map[i][j] = true;
                }
                for (int j = i; j <= endX; j++) {
                    map[j][i] = true;
                }
                for (int j = i; j <= endX; j++) {
                    map[endX][j] = true;
                }
                for (int j = i; j < endY; j++) {
                    map[j][endY] = true;
                }
            }
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (map[i][j]) {
                    answer.append("*");
                } else {
                    answer.append(" ");
                }
            }
            answer.append("\n");
        }

        System.out.println(answer);
    }
}
