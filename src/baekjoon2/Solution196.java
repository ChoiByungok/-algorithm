package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 14889 스타트와 링크 (Silver1)
 * 우선 boolean배열을 생성하여 팀을 나눠주는 작업을 해주었다.
 * true이면 스타트팀 false면 링크팀이다.
 * 근데 여기서 주의할 점은 예를들어 인원이 4명일때
 * true true false false 로 팀이 나눠지나
 * false false true true 로 팀이 나눠지나 결과는 같다는 것이다. 이 중복을 해결하기 위해
 * 연산을 진행하기전에 조건문을 하나 더 두었다. boolean 배열의 첫번째 요소가 true 일때만 연산을 하기로 한것이다.
 * 첫번째 요소가 true 인 배열과 false 인 배열이 정확히 대칭성을 보여줬기 때문이다.
 * 그렇게 중복 요소를 제거하고 그 뒤에는 이중 반복문을 사용하여 두 팀의 능력치의 합을 계산 하였다.
 */
public class Solution196 {
    static int N;
    static int[][] table;
    static boolean[] team;
    static int answer = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());
        table = new int[N][N];
        team = new boolean[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < N; j++) {
                table[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        permutation(0, 0);
        System.out.println(answer);
    }

    static void permutation(int count, int start) {
        if (count == N / 2 && team[0]) {
            int sTotal = 0;
            int lTotal = 0;
            for (int i = 0; i < team.length; i++) {
                for (int j = i + 1; j < team.length; j++) {
                    if (team[i] && team[j]) {
                        sTotal += table[i][j];
                        sTotal += table[j][i];
                    } else if (!team[i] && !team[j]) {
                        lTotal += table[i][j];
                        lTotal += table[j][i];
                    }
                }
            }
            answer = Math.min(answer, Math.abs(sTotal - lTotal));
        }

        for (int i = start; i < N; i++) {
            if (!team[i]) {
                team[i] = true;
                permutation(count + 1, i + 1);
                team[i] = false;
            }
        }
    }
}
