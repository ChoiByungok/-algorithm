package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 10819 차이를 최대로 (Silver2)
 * 처음에는 문제를 제대로 이해 못해서 어떻게 답이 62가 나오는지 이해못했는데
 * 알고보니 01 12 23 34 이런식으로 계산을 하는거였음
 * 어떻게든 중복 연산을 줄이고 싶어서 매개변수로 계산을 하면서 넘기고 싶었지만
 * 방법이 없었고 결국 배열을 완성시킨 뒤에 연산을 하는방법을 택함
 */
public class Solution17 {
    static int N;
    static int[] arr;
    static int[] temp;
    static boolean[] visited;
    static int answer = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());
        arr = new int[N];
        temp = new int[N];
        visited = new boolean[N];
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(tokenizer.nextToken());
        }

        backTracking(0);
        System.out.println(answer);
    }


    static void backTracking(int depth) {
        if (depth == N) {
            answer = Math.max(answer, calc());
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                temp[depth] = arr[i];
                backTracking(depth + 1);
                visited[i] = false;
            }
        }
    }
    
    static int calc() {
        int sum = 0;
        for (int i = 0; i < N - 1; i++) {
            sum += Math.abs(temp[i] - temp[i + 1]);
        }
        return sum;
    }
}
