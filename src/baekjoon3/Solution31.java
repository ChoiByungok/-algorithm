package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1520 내리막 길 (Gold3)
 * 0,0 부터 시작해서 N - 1, M - 1 까지 가는데 현재 위치한 좌표의 숫자보다 낮은 숫자의 칸으로만 이동할 수 있음
 * 그렇게 총 몇개의 경로가 있는지 출력하는 문제임 문제이해는 간단해서 그냥 직관적으로 dfs를 이용하여 풀어봄
 * 문제에서 주어진 예제는 맞췄는데 16퍼센트에서 메모리 초과가남 역시 골드3짜리 문제가 이렇게 간단하게 풀릴리가 없지
 * 아마 dfs를 호출할때마다 방문배열을 복사해서 그런거같음 그렇다면 메모리 초과가 나지 않으려면 어떻게 해야할까?
 * 방문배열을 복사하지 않고 dfs메서드를 가기전에 해당 좌표를 true로 바꾸고 반환되고 나서 false로 다시 바꿔주는
 * 로직으로 변경하면서 더이상 배열을 복사하지 않아도 되게끔 바꿨더니 이번에는 16퍼센트에서 시간초과가 발생하였다.
 * 내 로직은 목적지에 도착했을때 방문배열을 초기화 시켜 모든 경로를 처음부터 탐색하게 되기에 시간초과가 발생하는것같다.
 * 결국 이 문제는 중간중간 연산값들을 저장해나가면서 풀어야 하는 dp문제였던 것이다.
 * 예를들어 5X5 배열이 있다고 쳤을때 2,2에서 4,4까지 가는 경우의수가 2개라는것을 알아냈을 때
 * 2,2에 오게되면 그 이후 경로는 탐색하지 않고 그냥 2를 더해주면 되는것이다.
 * 근데 dfs에 dp를 어떻게 접목시킬것인가 참 어려운 문제다.
 * 결국 다른사람의 풀이를 보고 풀었다. 해당 좌표가 방문이 된 좌표인지 아닌지 구분하기 위하여 모든 좌표의 값을 -1로 초기화 시켜준다.
 * 그리고 탐색을 진행하는데 그렇게 목적지에 도착하게 되면 목적지까지 왔던 경로의 좌표들이 1로 초기화 될것이다.
 * 그러면 분기점에서 다시 목적지까지 탐색을 하게 되는데 다음에 가야할 좌표에 1이 초기화 되어있으면 그냥 그 값을 더해주고 재귀를 나가주면된다.
 * 그렇게 dfs 탐색을 마치고 나면 시작좌표에는 모든 경로의 수가 저장되어 있을것이다.
 */
public class Solution31 {
    static int N;
    static int M;
    static int[][] map;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        map = new int[N][M];
        dp = new int[N][M];
        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
                dp[i][j] = -1;
            }
        }
        System.out.println(dfs(0, 0));
    }

    static int dfs(int x, int y) {
        if (x == N - 1 && y == M - 1) {
            return 1;
        }

        if (dp[x][y] == -1) {
            dp[x][y] = 0;

            if (x + 1 < N && map[x][y] > map[x + 1][y]) {
                dp[x][y] += dfs(x + 1, y);
            }

            if (x - 1 >= 0 && map[x][y] > map[x - 1][y]) {
                dp[x][y] += dfs(x - 1, y);
            }

            if (y + 1 < M && map[x][y] > map[x][y + 1]) {
                dp[x][y] += dfs(x, y + 1);
            }

            if (y - 1 >= 0 && map[x][y] > map[x][y - 1]) {
                dp[x][y] += dfs(x, y - 1);
            }

        } else {
            return dp[x][y];
        }

        return dp[x][y];
    }

}
