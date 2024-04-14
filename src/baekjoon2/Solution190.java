package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 17406 배열 돌리기 4 (Gold4)
 * 모든 연산은 한번씩 사용하되 순서는 바꿔도 상관없다 이때 행의 최솟값이 가장 적게나오는 순서를 구하는 문제이다.
 * 그러면 사용순서를 매번 바꿔서 적용시켜야 하는데 어떻게 해야할지 고민중
 * 고민 끝에 구글링 했는데 예전에 이산수학에서 배웠던 순열알고리즘을 이용하여 푸는 모습을 봄
 * 그래서 순열알고리즘으로 순서를 정하기로 함 근데 이제 순서를 먼저 정하고 그 뒤에 연산을 할지
 * 아니면 순서를 정하면서 연산을 할지 고민됨 순서를 미리 구한 뒤 연산을 하면 뇌가 편하지만 성능이 안좋을거같고
 * 순서를 정하면서 연산을 하면 중복된 연산을 피할 수 있으니 성능은 좋을거같지만 뇌가 편하지 않음
 * dfs는 아무래도 재귀를 이용하다보니 뇌버깅을 하기가 너무 어려움 내가 원하는건 마지막 연산을 끝낸 뒤
 * 다시 첫번째 체크 지점으로 돌아왔을때 첫번째 연산만 끝낸 배열이 필요한데 어떻게 해야할지 모르겠음
 * 결국 순서를 정하면서 연산을 하는 방법은 포기함 너무 헷갈리고 문제푸는데 시간이 너무많이 들어가서
 * 그냥 순서를 다 구한뒤 연산을 하기로 했음 배열 돌리는거야 많이 해봐서 금방했는데
 * 순열 알고리즘은 처음 배워감 재귀를 사용하는 문제라 너무 헷갈렸음 나름대로 bfs는 자신있다고 생각했는데
 * 재귀를 이용한 dfs는 좀더 학습해야 할거같음
 */
public class Solution190 {
    static class Operation {
        int r;
        int c;
        int s;

        public Operation(int r, int c, int s) {
            this.r = r;
            this.c = c;
            this.s = s;
        }
    }
    static int N;
    static int M;
    static int K;
    static int[][] copyMap;
    static boolean[] visited;
    static int answer = Integer.MAX_VALUE;
    static Operation[] operations;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        copyMap = new int[N][M];
        K = Integer.parseInt(tokenizer.nextToken());
        operations = new Operation[K];
        visited = new boolean[K];

        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < M; j++) {
                copyMap[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        for (int i = 0; i < K; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            int r = Integer.parseInt(tokenizer.nextToken());
            int c = Integer.parseInt(tokenizer.nextToken());
            int s = Integer.parseInt(tokenizer.nextToken());
            operations[i] = new Operation(r, c, s);
        }
        List<Operation> order = new ArrayList<>();
        permutation(0, order);

        System.out.println(answer);
    }

    static void permutation(int count, List<Operation> list) {
        if (count == K) {
            calculate(rotate(list));
            return;
        }

        for (int i = 0; i < K; i++) {
            if (!visited[i]) {
                visited[i] = true;
                list.add(operations[i]);
                permutation(count + 1, list);
                list.remove(count);
                visited[i] = false;
            }
        }
    }

    static int[][] copy() {
        int[][] newMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            if (M >= 0) {
                System.arraycopy(copyMap[i], 0, newMap[i], 0, M);
            }
        }

        return newMap;
    }

    private static void calculate(int[][] map) {
        for (int i = 0; i < N; i++) {
            int sum = 0;
            for (int j = 0; j < M; j++) {
                sum += map[i][j];
            }
            answer = Math.min(answer, sum);
        }
    }

    static int[][] rotate(List<Operation> list) {
        int[][] copyMap = copy();

        for (Operation operation : list) {
            int[][] tempMap = new int[N][M];
            int r = operation.r;
            int c = operation.c;
            int s = operation.s;

            int x1 = r - s - 1;
            int y1 = c - s - 1;
            int x2 = r + s - 1;
            int y2 = c + s - 1;

            while (x1 < x2 && y1 < y2) {
                tempMap[x1][y1 + 1] = copyMap[x1][y1];
                tempMap[x1 +1][y2] = copyMap[x1][y2];
                tempMap[x2][y2 - 1] = copyMap[x2][y2];
                tempMap[x2 - 1][y1] = copyMap[x2][y1];

                if (y2 - (y1 + 1) >= 0) {
                    System.arraycopy(copyMap[x1], y1 + 1, tempMap[x1], y1 + 1 + 1, y2 - (y1 + 1));
                }

                for (int i = x1 + 1; i < x2; i++) {
                    tempMap[i + 1][y2] = copyMap[i][y2];
                }

                if (y2 - 1 - y1 >= 0) {
                    System.arraycopy(copyMap[x2], y1 + 1, tempMap[x2], y1, y2 - 1 - y1);
                }

                for (int i = x2 - 1; i > x1; i--) {
                    tempMap[i - 1][y1] = copyMap[i][y1];
                }

                x1++;
                y1++;
                x2--;
                y2--;
            }
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (tempMap[i][j] == 0) {
                        tempMap[i][j] = copyMap[i][j];
                    }
                }
            }
            copyMap = tempMap;
        }
        return copyMap;
    }
}
