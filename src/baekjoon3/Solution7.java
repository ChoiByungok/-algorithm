package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 19949 영재의 시험 (Silver2)
 * 이 문제의 주의할 점은 3번연속 같은 번호로 찍으면 안된다는 것이다.
 * 그래서 depth가 2이상일때 -1 번째 인덱스랑 -2번째 인덱스랑 현재 찍으려는 번호가 모두 같다면
 * 다음 번호로 넘기는 방식으로 배열을 채웠다 그렇게 배열이 다 채워지면 그 이후에 채점을 해서 5개 이상
 * 맞추었을때만 카운트를 증가시켜주었다. 같은 번호 3개이상 찍는 이러한 제한이 없었다면 그냥 현재 번호가 맞는지 아닌지
 * 그 자리에서 채점을 한 뒤 결과를 재귀하는 방법으로 풀었을거같다.
 */
public class Solution7 {
    static int[] answer = new int[10];
    static int count = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < 10; i++) {
            answer[i] = Integer.parseInt(tokenizer.nextToken());
        }
        int[] arr = new int[10];
        dfs(0, arr);
        System.out.println(count);
    }

    static void dfs(int depth, int[] arr) {
        if (depth == 10) {
            if (grading(arr) >= 5) {
                count++;
            }
            return;
        }

        for (int i = 1; i <= 5; i++) {
            if (depth > 1) {
                if (arr[depth - 1] == i && arr[depth - 2] == i) {
                    continue;
                }
            }
            arr[depth] = i;
            dfs(depth + 1, arr);
        }
    }

    static int grading(int[] arr) {
        int count = 0;
        for (int i = 0; i < answer.length; i++) {
            if (arr[i] == answer[i]) {
                count++;
            }
        }
        return count;
    }
}
