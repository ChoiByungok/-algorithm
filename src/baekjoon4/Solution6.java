package baekjoon4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 11780 플로이드 2 (Gold2)
 * 플로이드 와셜 알고리즘을 이용하여 최단거리를 구하 되
 * 각 노드당 최단거리 경로를 각각 출력해줘야 함
 * 예를 들어 1 -> 2 가는데 최단경로가 1 3 5 2 라면 이걸 출력해줘야 함
 * 경로를 어떻게 저장해야 할까 결국 다른사람의 풀이를 보고 풀었다.
 * 경로 배열을 하나 생성해서 최소값 갱신이 되었을 때
 * path[start][end] = path[mid][end]; 해당 로직을 하나 넣어주면 된다.
 * 이거 푼 사람은 이 생각을 어떻게 할 수 있었을까 어렴풋이 짐작은 했는데
 * 나보고 이렇게 해결하라고 했으면 백만년이 걸려도 못풀었을듯 싶다.
 */
public class Solution6 {
    static final long INF = 10000000001L;
    static long[][] map;
    static int N;
    static int[][] path;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());
        map = new long[N + 1][N + 1];
        path = new int[N + 1][N + 1];
        for (int i = 0; i <= N; i++) {
            Arrays.fill(map[i], INF);
            map[i][i] = 0;
        }
        int M = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < M; i++) {
            StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            int c = Integer.parseInt(tokenizer.nextToken());
            if (map[a][b] != 0) {
                map[a][b] = Math.min(map[a][b], c);
            } else {
                map[a][b] = c;
            }
            path[a][b] = a;
        }
        floyd();
        System.out.println(shortestPath().append(routeTracking()));
    }

    static void floyd() {
        for (int mid = 1; mid <= N; mid++) {
            for (int start = 1; start <= N; start++) {
                for (int end = 1; end <= N; end++) {
                    if (start == end || start == mid || mid == end) {
                        continue;
                    }
                    if (map[start][end] > map[start][mid] + map[mid][end]) {
                        map[start][end] = map[start][mid] + map[mid][end];
                        path[start][end] = path[mid][end];
                    }
                }
            }
        }
    }
    static StringBuilder shortestPath() {
        StringBuilder answer = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (map[i][j] == INF) {
                    answer.append(0).append(" ");
                } else {
                    answer.append(map[i][j]).append(" ");
                }
            }
            answer.append("\n");
        }
        return answer;
    }

    static StringBuilder routeTracking() {
        StringBuilder answer = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                Stack<Integer> stack = new Stack<>();
                if (path[i][j] == 0) {
                    answer.append(0);
                } else {
                    int temp = j;
                    stack.push(j);
                    while (temp != i) {
                        temp = path[i][temp];
                        stack.push(temp);
                    }
                    answer.append(stack.size()).append(" ");
                    while (!stack.isEmpty()) {
                        answer.append(stack.pop()).append(" ");
                    }
                }

                answer.append("\n");
            }
        }
        return answer;
    }

    static void print() {
        System.out.println();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                System.out.print(path[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
