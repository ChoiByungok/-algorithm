package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 2529 부등호 (Silver1)
 * 0 ~ 9 까지 숫자를 이용하여 입력으로 주어진 부등호 순서열에 알 맞는 숫자를 배열하는 문제
 * 주의 할 점은 순서열이 최대 9자인데 9자를 받았을 시 int형 범위를 벗어나기 때문에
 * 처음부터 Long형으로 지정해야 한다. 그리고 숫자를 배열했을때 0으로 시작하는 숫자가 나올 수 있는데
 * 해당 숫자를 파싱하면 앞에 0이 날아가게 되므로 마지막에 입력으로 주어진 순서열의 길이와
 * 숫자배열의 길이가 같다면 0이 날아간거 이므로 0을 붙여주어 출력시켜주면 된다.
 */
public class Solution10 {
    static int N;
    static String[] sequence;
    static boolean[] visited;
    static long MAX = Long.MIN_VALUE;
    static long MIN = Long.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());
        visited = new boolean[10];
        sequence = bufferedReader.readLine().split(" ");
        dfs(0, new StringBuilder());
        String min = MIN + "";
        if (min.length() == N) {
            min = "0" + min;
        }

        System.out.println(MAX + "\n" + min);
    }

    static void dfs(int depth, StringBuilder builder) {
        if (depth > 1) {
            if (!compare(builder)) {
                return;
            }
            if (depth == N + 1) {
                long answer = Long.parseLong(builder.toString());
                MAX = Math.max(MAX, answer);
                MIN = Math.min(MIN, answer);
                return;
            }
        }

        for (int i = 0; i <= 9; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(depth + 1, new StringBuilder(builder).append(i));
                visited[i] = false;
            }
        }
    }

    static boolean compare(StringBuilder builder) {
        for (int i = 0; i < builder.length() - 1; i++) {
            int i1 = builder.charAt(i) - '0';
            int i2 = builder.charAt(i + 1) - '0';

            if (sequence[i].equals(">") && i1 < i2) {
                return false;
            } else if (sequence[i].equals("<") && i1 > i2) {
                return false;
            }
        }
        return true;
    }
}
