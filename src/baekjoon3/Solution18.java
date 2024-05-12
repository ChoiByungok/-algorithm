package baekjoon3;

import java.io.*;
/**
 * 16922 로마 숫자 만들기 (Silver3)
 * 매개변수로 숫자의 합을 넘기며 마지막에 depth가 N과 같아졌을때
 * 중복 숫자를 거르기 위하여 set자료구조에 숫자의 합을 넣고
 * 마지막에 set의 크기를 출력하면 되는줄 알았다 예제 3문제도 문제없이 통과되길래
 * 제출했더니 시간초과가 발생하였다 N의 최대값 20을 사용해보니 실제로 느렸다.
 * 왜 시간초과가 발생하며 어떻게 해야 시간초과를 해결할 수 있을까
 * 백트래킹이란 기본적으로 가지치기를 하는작업을 의미한다.
 * 151 이나 511이나 115나 전부 값은7로 같다 이 경우는 중복수열이고 이때 start라는 변수를 매개변수로 넘겨
 * 반복문을 start부터 시작해야 해당 중복수열을 피할수 있는것이다. 그동안 그냥 써왔는데 이러한 기능을 한다는것을 이번에 알게되었다.
 */
public class Solution18 {
    static int[] roma = {1, 5, 10, 50};
    static int N;
    static boolean[] com = new boolean[50 * 50 + 1];
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());
        int count = 0;
        backTracking(0, 0, 0);


        for (int i = 1; i < com.length; i++) {
            if (com[i]) {
                count++;
            }
        }

        System.out.println(count);
    }

    static void backTracking(int depth, int sum, int start) {
        if (depth == N) {
            if (!com[sum]) {
                com[sum] = true;
            }
            return;
        }
        for (int i = start; i < roma.length; i++) {
            backTracking(depth + 1, sum + roma[i], i);
        }
    }
}
