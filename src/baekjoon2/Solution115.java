package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * 2776 암기왕 (Silver4)
 * 해시셋을 이용하면 간단하게 풀 수 있는 문제
 */
public class Solution115 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bufferedReader.readLine());
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(bufferedReader.readLine());
            Set<String> memo = new HashSet<>();
            StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < N; j++) {
                memo.add(tokenizer.nextToken());
            }
            int M = Integer.parseInt(bufferedReader.readLine());
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < M; j++) {
                String token = tokenizer.nextToken();
                if (memo.contains(token)) {
                    answer.append(1).append("\n");
                } else {
                    answer.append(0).append("\n");
                }
            }
        }
        System.out.println(answer);
    }
}
