package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 18429 근손실 (Silver3)
 * 매일 K 만큼 중량이 감소하는데 키트를 어떻게 써야 중량이 500밑으로 안내려가는지 그 경우의 수를 출력하는 문제
 * 여타 백트래킹 문제와 똑같이 풀었지만 메서드 앞에 매개변수로 넘어오는 중량이 500 미만이라면 반환하는 식으로 해결하였다.
 */
public class Solution8 {
    static int N;
    static int K;
    static int[] kits;
    static boolean[] visited;
    static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        K = Integer.parseInt(tokenizer.nextToken());
        kits = new int[N];
        visited = new boolean[N];
        tokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < N; i++) {
            kits[i] = Integer.parseInt(tokenizer.nextToken());
        }

        dfs(0, 500);
        System.out.println(answer);
    }

    static void dfs(int day, int weight) {
        if (weight < 500) {
            return;
        }

        if (day == N) {
            answer++;
            return;
        }

        for (int i = 0; i < kits.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(day + 1, weight + kits[i] - K);
                visited[i] = false;
            }
        }
    }
}
