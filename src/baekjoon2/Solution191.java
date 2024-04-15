package baekjoon2;

import java.io.*;
import java.util.*;

/**
 * 15654 N과 M (5) (Silver3)
 * 어제 배웠던 dfs로 구현된 순열 알고리즘을 적용시켜 풀어본 문제
 * 숫자가 낮은순으로 출려갛라고 했으니 정렬을 먼저 실행한 뒤
 * 순열 알고리즘을 사용하였다.
 */
public class Solution191 {
    static int N;
    static int M;
    static int[] arr;
    static StringBuilder answer = new StringBuilder();
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());

        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        arr = new int[N];
        visited = new boolean[N];

        tokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(tokenizer.nextToken());
        }

        Arrays.sort(arr);
        List<Integer> list = new ArrayList<>();
        permutation(0, list);
        System.out.println(answer);
    }

    static void permutation(int count, List<Integer> list) {
        if (count == M) {
            for (Integer integer : list) {
                answer.append(integer).append(" ");
            }
            answer.append("\n");
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                list.add(arr[i]);
                permutation(count + 1, list);
                list.remove(count);
                visited[i] = false;
            }
        }
    }
}
