package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 2442 별 찍기 - 5 (Bronze3)
 * 맞게는 풀었는데 출력형식이 틀렸던 문제
 * 별 뒤에 공백을 출력하면 안된다.
 */
public class Solution185 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        int N = Integer.parseInt(bufferedReader.readLine());
        int M = N + N - 1;
        int middle = M / 2;
        int star = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= middle + star; j++) {
                if (j >= middle - star) {
                    answer.append("*");
                } else {
                    answer.append(" ");
                }
            }
            answer.append("\n");
            star++;
        }

        System.out.println(answer);
    }
}
