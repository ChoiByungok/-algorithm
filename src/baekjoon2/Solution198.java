package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1182 부분수열의 합 (Silver2)
 * 마찬가지로 dfs를 이용하여 수열을 만들어줌
 * 지금까지 풀어왔던 수열문제와는 달리 이 문제는 몇개 중에 몇개를 이용하여 수열을 만들건지 정해져 있지 않음
 * 그 대신 부분 수열의 합이 S가 될 경우가 몇번인지 구하는 문제임
 * 그래서 나는 재귀문에서 부분의 합이 S 가 될경우 변수를 증가시켜줌으로써 풀었음
 * 그 대신 주의할 점이 있는데 부분합을 처음에 0으로 지정할 경우
 * S가 0일시 변수를 증가시키게 됨
 * 그래서 나는 메서드를 하나 새로 만들어 boolean형 배열이 모두 false이면 false를 반환하는 메서드를 하나 만들어서 체크해주었음
 */
public class Solution198 {
    static int N;
    static int S;
    static int[] arr;
    static boolean[] visited;
    static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        S = Integer.parseInt(tokenizer.nextToken());
        arr = new int[N];
        visited = new boolean[N];
        tokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(tokenizer.nextToken());
        }
        dfs(0, 0);
        System.out.println(answer);
    }
    static void dfs(int sum, int start) {
        if (sum == S && check()) {
            answer++;
        }

        for (int i = start; i < arr.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(sum + arr[i], i + 1);
                visited[i] = false;
            }
        }
    }

    static boolean check() {
        for (int i = 0; i < visited.length; i++) {
            if (visited[i]) {
                return true;
            }
        }
        return false;
    }
}
