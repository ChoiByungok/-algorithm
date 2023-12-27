package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 9322 철벽 보안 알고리즘
 * 처음엔 문제 이해를 아예 못함
 * 암호키1번 A B C D 가 A=1 B=2 C=3 D=4 라고 치면
 * 암호키2번은 D A B C 로 D=4 A=1 B=2 C=3 즉 4123 순서로 존재함
 * 그다음 암호화 되어있는 C B A P 는 4123순서로 되어있는거임 해당 암호를 해독 하려면 1234순서로 바꾸면 됨
 * 그러므로 해독된 답은 B A P C임
 */
public class Solution80 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bufferedReader.readLine());
        StringBuilder answer = new StringBuilder();

        for (int i = 0; i < T; i++) {
            Map<String, Integer> map1 = new HashMap<>();
            int N = Integer.parseInt(bufferedReader.readLine());
            int[] order = new int[N];
            String[] publicKey1 = bufferedReader.readLine().split(" ");
            for (int j = 0; j < publicKey1.length; j++) {
                map1.put(publicKey1[j], j);
            }

            String[] publicKey2 = bufferedReader.readLine().split(" ");
            for (int j = 0; j < publicKey2.length; j++) {
                order[j] = map1.get(publicKey2[j]);
            }

            String[] cryptogram = bufferedReader.readLine().split(" ");
            String[] decoding = new String[N];

            for (int j = 0; j < cryptogram.length; j++) {
                decoding[order[j]] = cryptogram[j];
            }
            for (String s : decoding) {
                answer.append(s).append(" ");
            }
            answer.append("\n");
        }
        System.out.println(answer);
    }
}
