package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 14888 연산자 끼워넣기 (Silver1)
 * 순열 알고리즘 응용편 중복이 가능하지만 순서는 존재하는 순열을 만들어야한다.
 * 이전에는 방문 배열을 만들어 체크를 했지만 이번에는 중복이 존재하기 때문에 다른방법으로 접근해야 하는데
 * 마침 문제에 몇번사용이 가능한지 주어진다.
 * 그래서 재귀호출 이전 이후에 증감연산자를 사용하여 사용카운트를 조절해주었고
 * 그값이 0보다 클때만 접근가능하도록 하였다.
 * 그렇게 순서가 정해지면 하라는대로 연산을 하면된다.
 * 친절하게도 연산우선순위는 없고 왼쪽부터 연산을 하면 되기때문에 편리했다.
 * 슬슬 백트래킹 문제에 감이 잡히는거같다.
 */
public class Solution192 {
    static int N;
    static int[] arr;
    static int[] operator;
    static int MAX = Integer.MIN_VALUE;
    static int MIN = Integer.MAX_VALUE;
    static int count = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());
        arr = new int[N];
        operator = new int[4];
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(tokenizer.nextToken());
        }
        tokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < 4; i++) {
            operator[i] = Integer.parseInt(tokenizer.nextToken());
            count += operator[i];
        }
        List<Integer> order = new ArrayList<>();
        permutation(0, order);
        System.out.println(MAX + "\n" + MIN);
    }

    static void permutation(int depth, List<Integer> list) {
        if (depth == count) {
            int calc = arr[0];
            for (int i = 1; i < N; i++) {
                Integer integer = list.get(i - 1);
                switch (integer) {
                    case 0:
                        calc += arr[i];
                        break;
                    case 1:
                        calc -= arr[i];
                        break;
                    case 2:
                        calc *= arr[i];
                        break;
                    case 3:
                        if (calc < 0) {
                            calc = -1 * ((-1 * calc) / arr[i]);
                        } else {
                            calc /= arr[i];
                        }
                        break;
                }
            }
            MAX = Math.max(MAX, calc);
            MIN = Math.min(MIN, calc);
            return;
        }

        for (int i = 0; i < operator.length; i++) {
            if (operator[i] > 0) {
                operator[i]--;
                list.add(i);
                permutation(depth + 1, list);
                operator[i]++;
                list.remove(depth);
            }
        }
    }
}
