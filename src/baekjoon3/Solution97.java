package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 1865 웜홀 (Gold3)
 * 이전에 배웠던 벨만 포드 알고리즘을 이용하여 풀어보았다.
 * 솔직히 말해서 문제를 이해 못했다.
 * 임의의 점에서 시작하여 다시 시작 위치로 돌아왔을 때 출발시간보다 시간이 되돌아가 있다면 YES 아니면 NO 를 출력하라 했는데
 * 어떤 정점을 임의로 따져야 하는지 아니면 방향도 따져야 하는지 모든 정점을 start 로 두고 다 확인해야 하는지 몰라서
 * 그냥 1번정점을 기준으로 최단거리를 만들었다 그리고 음의 가중치가 존재하는 사이클이 있는지 확인한 후
 * 존재하면 YES 아니면 NO를 출력하도록 했는데 통과가 되긴 하였다.
 * 소 뒷걸음질 치다가 쥐잡은 격으로 푼 문제라 찝찝함이 남는 문제다
 * 다른사람의 풀이를 보았다. 나는 1번정점을 임의의 정점으로 기준하여 음의 사이클이 있는지 없는지 판단하였는데
 * 이게 통과된 이유가 해당 정점이 연결이 되어있는지 아닌지 판별을 하지 않았기 때문에 통과된거였다.
 * 만약 1번정점을 임의의 정점으로 정하고 알고리즘을 실행했는데 1번정점이 다른정점들과 연결이 되어있지 않은 상태에서
 * 연결이 되어있는지 아닌지 판별하는 코드를 넣었다면 아마 통과가 되지 않았을 것이다.
 * (1번정점은 다른 정점과 연결되어 있지 않은데 연결 유무 판별 후에 다음 코드를 실행하지 않을 것이므로)
 * 즉 모든 정점을 다 확인 하는 코드였다면 정점이 연결되어있는지 아닌지 판별하는 코드를 넣어야 통과가 되는것이고
 * 나처럼 임의의 정점을 그냥 하나 정해서 음의 사이클을 찾는 코드였다면 정점이 연결되어있는지 아닌지 판별하는 코드를 넣지 말아야 하는것이다.
 */
public class Solution97 {
    static final int INF = 99999999;
    static class Point {
        int end;
        int cost;

        public Point(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }
    }
    static int N;
    static List<ArrayList<Point>> points;
    public static void main(String[] args) throws IOException {
        StringBuilder answer = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < TC; i++) {
            points = new ArrayList<>();
            StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
            N = Integer.parseInt(tokenizer.nextToken());
            int M = Integer.parseInt(tokenizer.nextToken());
            int W = Integer.parseInt(tokenizer.nextToken());
            for (int j = 0; j <= N; j++) {
                points.add(new ArrayList<>());
            }
            for (int j = 0; j < M; j++) {
                tokenizer = new StringTokenizer(bufferedReader.readLine());
                int S = Integer.parseInt(tokenizer.nextToken());
                int E = Integer.parseInt(tokenizer.nextToken());
                int T = Integer.parseInt(tokenizer.nextToken());
                points.get(S).add(new Point(E, T));
                points.get(E).add(new Point(S, T));
            }

            for (int j = 0; j < W; j++) {
                tokenizer = new StringTokenizer(bufferedReader.readLine());
                int S = Integer.parseInt(tokenizer.nextToken());
                int E = Integer.parseInt(tokenizer.nextToken());
                int T = Integer.parseInt(tokenizer.nextToken()) * -1;
                points.get(S).add(new Point(E, T));
            }

            answer.append(bellman_ford() ? "YES" : "NO").append("\n");
        }
        System.out.println(answer);
    }

    static boolean bellman_ford() {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[1] = 0;
        for (int i = 0; i < N - 1; i++) {
            for (int j = 1; j <= N; j++) {
                for (Point point : points.get(j)) {
                    if (dist[point.end] > dist[j] + point.cost) {
                        dist[point.end] = dist[j] + point.cost;
                    }
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            for (Point point : points.get(i)) {
                if (dist[point.end] > dist[i] + point.cost) {
                    return true;
                }
            }
        }

        return false;
    }
}
