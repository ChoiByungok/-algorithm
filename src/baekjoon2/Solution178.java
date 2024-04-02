package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 1748 수 이어 쓰기 1 (Silver4)
 * 처음에 느낌대로 그냥 StringBuilder 써서 풀었더니 메모리 초과남
 * 이런건 수학적으로 규칙을 찾아서 풀어야 하는데 재귀를 써서 풀어볼까 생각했다가 안될거같아서
 * 결국 구글 검색했는데 너무 간단해서 어이가 없었던 문제
 * 너무 어렵게 생각해서 오히려 어려웠던 문제
 */
public class Solution178 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String readLine = bufferedReader.readLine();
        int N = Integer.parseInt(readLine);
        int answer = 0;
        int digit = 1;
        int power = 10;
        for (int i = 1; i <= N; i++) {
            if (i == power) {
                digit++;
                power *= 10;
            }
            answer += digit;
        }
        System.out.println(answer);
    }
}
