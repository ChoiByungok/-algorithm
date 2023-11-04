package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 10451 순열 사이클
 */
public class Solution28 {
    static class Permutation {
        int index;
        int number;

        public Permutation(int index, int number) {
            this.index = index;
            this.number = number;
        }
    }

    static boolean[] visited;
    static Permutation[] permutations;

    public static void main(String[] args) throws IOException {
        StringBuilder answer = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bufferedReader.readLine());

        for (int i = 0; i < T; i++) {
            int count = 0;
            int N = Integer.parseInt(bufferedReader.readLine());
            permutations = new Permutation[N + 1];
            visited = new boolean[N + 1];

            String[] split = bufferedReader.readLine().split(" ");
            for (int j = 1; j <= N; j++) {
                permutations[j] = new Permutation(j, Integer.parseInt(split[j - 1]));
            }

            for (int j = 1; j <= N; j++) {
                if (!visited[j]) {
                    visited[j] = true;
                    count++;
                    dfs(permutations[j].number);
                }
            }
            answer.append(count).append("\n");
        }
        System.out.println(answer);
    }

    private static void dfs(int number) {
        if(!visited[number]) {
            visited[number] = true;
            dfs(permutations[number].number);
        }
    }
}
