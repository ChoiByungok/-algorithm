package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 16943 숫자 재배치 (Silver1)
 * 우선 입력받은 숫자 A를 문자열로 입력받으면 길이를 알 수있는데
 * 이 길이를 이용하여 배열의 길이를 선언할 수 있다.
 * 그 후 A를 10씩 나눠서 나온 나머지를 배열에 넣으면 숫자를 재배치 할 수있다.
 * 그런다음 오름차순으로 정렬하는데 딱히 이유는 없지만 첫번째 자리에 0이 들어갈 수 없다고 해서 했다
 * 그 이후에는 백트래킹을 실시하는데 재귀를 호출 할때마다 매개변수로 넘어온 숫자에 10을 곱하고
 * 아직 선택되지 않은 숫자배열에서 한가지 숫자를 더한뒤 다시 재귀를 호출해준다.
 * 그 때 매개변수로 넘어온 숫자가 B보다 크면 그 뒤에는 볼 필요도 없으므로 return해준다.
 * 그리고 모든배열의 숫자들을 다 사용했을때 그 숫자가 B보다 작으면 기존에 등록된 answer와 비교하여
 * 더 크면 갱신해준다.
 */
public class Solution16 {
    static int[] A;
    static int B;
    static boolean[] visited;
    static int answer = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        String a = tokenizer.nextToken();
        A = new int[a.length()];
        int parseInt = Integer.parseInt(a);
        int index = 0;
        while (parseInt != 0) {
            int mod = parseInt % 10;
            parseInt = parseInt / 10;
            A[index++] = mod;
        }
        visited = new boolean[A.length];
        B = Integer.parseInt(tokenizer.nextToken());
        Arrays.sort(A);
        for (int i = 0; i < A.length; i++) {
            if (A[i] > 0) {
                visited[i] = true;
                backTracking(1, A[i]);
                visited[i] = false;
            }
        }
        System.out.println(answer == Integer.MIN_VALUE ? -1 : answer);
    }

    static void backTracking(int depth, int com) {
        if (depth == A.length && com < B) {
            answer = Math.max(answer, com);
            return;
        }
        if (com > B) {
            return;
        }
        com = com * 10;
        for (int i = 0; i < A.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                backTracking(depth + 1, com + A[i]);
                visited[i] = false;
            }
        }
    }
}
