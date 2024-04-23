package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 6603 로또 (Silver2)
 * 입력받은 배열중에서 6개의 숫자를 오름차순으로 골라 출력하는 문제
 * 오름차순으로 골라야 하기때문에 우선 오름차순으로 정렬부터 해주여야한다.
 * 그 이후엔 평소 백트래킹 문제 풀듯이 6개가 선택되었을 때
 * 선택된 숫자들을 차례대로 출력시켜주면 된다.
 */
public class Solution199 {
    static final int K = 6;
    static StringBuilder answer = new StringBuilder();
    static int[] arr;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
            int token = Integer.parseInt(tokenizer.nextToken());
            if (token == 0) {
                break;
            }
            arr = new int[token];
            visited = new boolean[token];
            for (int i = 0; i < token; i++) {
                arr[i] = Integer.parseInt(tokenizer.nextToken());
            }
            Arrays.sort(arr);
            dfs(0, 0);
            answer.append("\n");
        }
        System.out.println(answer);
    }
    static void dfs(int depth, int start) {
        if (depth == K) {
            for (int i = 0; i < visited.length; i++) {
                if (visited[i]) {
                    answer.append(arr[i]).append(" ");
                }
            }
            answer.append("\n");
        }

        for (int i = start; i < arr.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(depth + 1, i + 1);
                visited[i] = false;
            }
        }
    }
}
