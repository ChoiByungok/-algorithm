package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 10813 공 바꾸기 (Bronze2)
 * 머리도 식힐겸 풀어본 쉬운문제
 * 자바같은 객체지향 언어를 사용하는 이상 객체지향적으로 풀어볼까 했으나
 * 아무리 봐도 배열로 만들어 풀어버리면 훨씬간단하기에 배열로 풀음
 */
public class Solution153 {
    public static void main(String[] args) throws IOException {
        StringBuilder answer = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());
        int[] bucket = new int[N];
        for (int i = 0; i < N; i++) {
            bucket[i] = i + 1;
        }

        while (M-- > 0) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            int i = Integer.parseInt(tokenizer.nextToken()) - 1;
            int j = Integer.parseInt(tokenizer.nextToken()) - 1;
            int temp = bucket[i];
            bucket[i] = bucket[j];
            bucket[j] = temp;
        }

        for (int i = 0; i < N; i++) {
            answer.append(bucket[i]).append(" ");
        }

        System.out.println(answer);
    }
}
