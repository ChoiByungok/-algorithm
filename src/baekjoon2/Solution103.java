package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * 9575 행운의 수
 * 배열 a b c 가 주어지고
 * 모든 배열에서 한개의 숫자를 꺼내서 더한 값이 5와 8로만 이루어져 있으면 행운의 수라고 불린다.
 * 배열의 크기는 50을 넘지 않는다고 했으므로 최악의 경우에도 125000회 밖에 반복하지 않는다
 * 그러므로 3중 반복문을 이용하여 문제를 풀었다.
 * 문제 풀이는 간단한데 반복문을 돌면서 합을 구하고 그 합을 문자열로 변환한뒤
 * 문자열을 순회하여 각 문자의 요소가 5와 8이 아니면 탈출시키는 식으로 해결하였다.
 * 탈출 하지 않으면 행운의 수라고 판단하여 HashSet자료구조에 넣어주는데
 * 중복을 허용하지 않는 문제이기 때문에 HashSet을 사용하였다.
 */
public class Solution103 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        int T = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < T; i++) {
            Set<Integer> set = new HashSet<>();
            int A = Integer.parseInt(bufferedReader.readLine());
            int[] arrA = new int[A];
            StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < arrA.length; j++) {
                arrA[j] = Integer.parseInt(tokenizer.nextToken());
            }
            int B = Integer.parseInt(bufferedReader.readLine());
            int[] arrB = new int[B];
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < arrB.length; j++) {
                arrB[j] = Integer.parseInt(tokenizer.nextToken());
            }
            int C = Integer.parseInt(bufferedReader.readLine());
            int[] arrC = new int[C];
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < arrC.length; j++) {
                arrC[j] = Integer.parseInt(tokenizer.nextToken());
            }
            for (int value : arrA) {
                for (int j : arrB) {
                    int middleTotal = value + j;
                    for (int k : arrC) {
                        boolean lucky = true;
                        int total = middleTotal + k;
                        String s = String.valueOf(total);
                        for (int m = 0; m < s.length(); m++) {
                            char c = s.charAt(m);
                            if (c != '5' && c != '8') {
                                lucky = false;
                                break;
                            }
                        }

                        if (lucky) {
                            set.add(total);
                        }
                    }
                }
            }
            answer.append(set.size()).append("\n");
        }

        System.out.println(answer);
    }
}
