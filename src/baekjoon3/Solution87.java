package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1507 궁금한 민호 (Gold2)
 * 지금까지 경로가 주어지면 그 경로로 최소경로를 구해서 출력하는것이 문제였다면
 * 이번 문제는 최단경로가 먼저 주어지면 거기서 원래그래프를 역순으로 찾아내서 모든 경로의 합을 출력하는 문제이다.
 * 어떤 간선이 직접적으로 연결된 경로인지 아닌지 파악해야하는데 어떻게 해야하는지 모르겠다.
 * 결국 답안지를 봤다 플로이드 와샬 알고리즘은 중간에 노드를 거쳐서 가는게 직접 가는것보다 빠르면 갱신해주는 알고리즘인데
 * 그 말은 즉 [start][end] > [start][mid] + [mid][end] 가 되었을때 [start][end] = [start][mid] + [mid][end] 가 된다는 것이다.
 * 지금 문제에서 주어진 최소경로배열에 다시한번 플로이드 와샬 알고리즘을 사용하여
 * [start][end] = [start][mid] + [mid][end] 인 간선이 존재한다면 해당 [start][end]노드는 [start][mid], [mid][end] 의 두 간선으로
 * 이루어진 간접적인 간선이라는 것이다. 이때 [start][end] 이 간선을 0으로 바꿔주면 된다. 해당 작업을 마치면
 * 직접적으로 연결되어있는 간선을 제외하고 모두 연결이 제거된다.
 * 그리고 이미 주어진 최단거리배열에서 최단거리 초기화 과정이 또 발생한다는 것은 모순이 발생한다는 뜻이므로 문제에서 주어진 예외인 -1을 출력해주면 된다.
 * 해당 배열이 모순이 있는지 없는지 판단하기 위해선 입력받은 배열을 하나 복사해서 진행해야 한다. 기존의 배열로 돌리기 위한 배열로 진행한다면 당연히 안된다.
 */
public class Solution87 {
    static int[][] map;
    static int[][] temp;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        map = new int[N][N];
        temp = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
                temp[i][j] = map[i][j];
            }
        }

        System.out.println(floyd(N));
    }

    private static int floyd(int N) {
        for (int mid = 0; mid < N; mid++) {
            for (int start = 0; start < N; start++) {
                for (int end = 0; end < N; end++) {
                    if (start == end || start == mid || mid == end) {
                        continue;
                    }
                    if (temp[start][end] > temp[start][mid] + temp[mid][end]) {
                        return -1;
                    }

                    if (temp[start][end] == temp[start][mid] + temp[mid][end]) {
                        map[start][end] = 0;
                    }

                }
            }
        }
        return calc(N);
    }

    static int calc(int N) {
        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sum += map[i][j];
            }
        }
        return sum / 2;
    }
}
