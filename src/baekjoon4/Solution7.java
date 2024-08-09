package baekjoon4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 1058 친구 (Silver2)
 * 두사람이 직접 친구이거나 한명 거처서 친구이면 2-친구 존재임
 * 입력이 주어졌을 때 2-친구관계가 가장 많은 수를 출력하면 됨
 * 근데 왜 4번예제는 답이 8일까 내가 이해를 잘못한건가
 * 아무리 풀어도 9가 나온다. 다른 예제들은 다 맞는데 왜 얘는 답이 8일까
 * 내가 문제를 잘못이해한듯 싶다. 한다리 걸치는거까지는 친구이지만 그 외에는 친구로 카운트를 하면 안된다.
 * 즉 입력으로 주어진 직접적인 친구의 가중치를 1로 두고
 * 플로이드 워셜알고리즘을 통해 모든 간선의 가중치를 구한다.
 * 여기서 가중치의 값이 2를 넘어가면 친구가 아니다.
 * 즉 1 혹은 2 의 가중치를 가진 친구들만 카운트를 세준다.
 * 그 중에 가장 큰 값을 출력해주면 된다.
 * 플로이드 워셜 알고리즘 응용편인데 자꾸 나는 boolean 형을 써서 해결하려는 이상한 습관이 잡혀서
 * 자꾸 해매게 되는거같다.
 */
public class Solution7 {
    static final int INF = 54321;
    static int N;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());
        map = new int[N][N];
        int answer = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            Arrays.fill(map[i], INF);
            map[i][i] = 0;
        }

        for (int i = 0; i < N; i++) {
            String readLine = bufferedReader.readLine();
            for (int j = 0; j < N; j++) {
                char c = readLine.charAt(j);
                if (c == 'Y') {
                    map[i][j] = 1;
                }
            }
        }

        floyd();
        for (int i = 0; i < N; i++) {
            int two_friend = 0;
            for (int j = 0; j < N; j++) {
                if (i == j) {
                    continue;
                }
                if (map[i][j] <= 2) {
                    two_friend++;
                }
            }
            answer = Math.max(answer, two_friend);
        }
        System.out.println(answer);
    }

    static void floyd() {
        for (int mid = 0; mid < N; mid++) {
            for (int start = 0; start < N; start++) {
                for (int end = 0; end < N; end++) {
                    if (start == end || start == mid || mid == end) {
                        continue;
                    }
                    map[start][end] = Math.min(map[start][end], map[start][mid] + map[mid][end]);
                }
            }
        }
    }
}
