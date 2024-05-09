package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 16938 캠프준비 (Gold5)
 * 이 문제는 정렬을 미리 해놓고 시작하면 편할거같아서 오름차순 정렬먼저 한 뒤 시작했다.
 * 이전문제와 똑같이 같은 길이의 boolean형 배열을 선언하고 방문 인덱스는 건너뛰는 방식으로
 * 모든 경우의 수를 찾아봤다. 그대신 이번에는 2가지 이상부터 조합이 가능하다.
 * 가장 어려운 문제와 가장 쉬운문제의 점수차이를 계속 매개변수로 넘겨주고 싶어서
 * 처음 반복문을 돌릴때 첫 요소의 인덱스값을 변동없이 계속 넘겨주면서
 * 그 인덱스 값을 사용하여 난이도의 차이를 구하면서 매개변수로 넘길 수 있었다.
 * 새로 확인해야할 문제와 기준점이 된 문제의 차이가 가장어려운 문제와 가장쉬운문제인 이유는
 * 처음에 정렬을 해놨기 때문이다. 그렇게 넘어온 매개변수들이 문제에서 주어진 조건에 해당된다면
 * 카운트를 1씩 증가시켜 최종적으로 증가된 카운트를 출력해주었다.
 */
public class Solution15 {
    static int N;
    static int L;
    static int R;
    static int X;
    static int[] difficulty;
    static boolean[] visited;
    static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        L = Integer.parseInt(tokenizer.nextToken());
        R = Integer.parseInt(tokenizer.nextToken());
        X = Integer.parseInt(tokenizer.nextToken());
        difficulty = new int[N];
        visited = new boolean[N];
        tokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < N; i++) {
            difficulty[i] = Integer.parseInt(tokenizer.nextToken());
        }
        Arrays.sort(difficulty);
        for (int i = 0; i < difficulty.length; i++) {
            visited[i] = true;
            backTracking(1, difficulty[i], i + 1, i, difficulty[i]);
            visited[i] = false;
        }
        System.out.println(answer);
    }

    static void backTracking(int count, int sum, int start, int standard, int diff) {
        if (count > 1 && sum >= L && sum <= R && diff >= X) {
            answer++;
        }

        for (int i = start; i < difficulty.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                backTracking(count + 1,
                        sum + difficulty[i],
                        i + 1, standard,
                        difficulty[i] - difficulty[standard]);
                visited[i] = false;
            }
        }
    }

}
