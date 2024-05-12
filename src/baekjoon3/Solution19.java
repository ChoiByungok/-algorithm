package baekjoon3;

import java.io.*;

/**
 * 10974 모든 순열 (Silver3)
 * 입력받은 숫자보다 하나더 큰 boolean형 배열을 선언한다.
 * 1 부터 N 까지 반복문을 돌리며 재귀를 실행한다.
 * 그때 해당되는 boolean형의 인덱스값은 true로 바꾸어 중복을 피해준다.
 * 1부터 차례대로 실행되니 완성되는순열은 결국 오름차순 정렬이 된다.
 * 그렇게 모든 순열을 StringBuilder에 넣고 출력해주면 된다.
 */
public class Solution19 {
    static int N;
    static boolean[] visited;
    static StringBuilder answer = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());
        visited = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            visited[i] = true;
            backTracking(1, new StringBuilder().append(i).append(" "));
            visited[i] = false;
        }
        System.out.println(answer);
    }

    static void backTracking(int depth, StringBuilder builder) {
        if (depth == N) {
            answer.append(builder).append("\n");
            return;
        }
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                backTracking(depth + 1, new StringBuilder(builder).append(i).append(" "));
                visited[i] = false;
            }
        }
    }
}
