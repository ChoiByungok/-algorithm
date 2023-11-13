package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 26091 현대모비스 소프트웨어 아카데미
 */
public class Solution37 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = bufferedReader.readLine().split(" ");
        int N = Integer.parseInt(nm[0]);
        int M = Integer.parseInt(nm[1]);
        int[] academyMembers = new int[N];
        int answer = 0;

        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < N; i++) {
            academyMembers[i] = Integer.parseInt(tokenizer.nextToken());
        }
        Arrays.sort(academyMembers);
        int start = 0;
        int end = N - 1;
        while (start < end) {
            if (academyMembers[start] + academyMembers[end] < M) {
                start++;
            } else {
                start++;
                end--;
                answer++;
            }
        }
        System.out.println(answer);
    }
}
