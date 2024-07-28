package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 11657 타임머신 (Gold4)
 * 해당 문제는 그래프상 최단거리를 찾는 문제인데 지금까지 써왔던 플로이드 와샬 과 다익스트라 알고리즘을 이용하여 해결할 수 없는문제이다.
 * 왜냐하면 음의가중치가 존재하기 때문이다. 사실 음의 가중치만 있다고 해선 문제될게 없지만 음의가중치가 존재하는 사이클이 있기때문에
 * 벨만포드 알고리즘이라는 알고리즘을 사용해야 한다. 음의 가중치가 존재하는 사이클이 있다면 경로는 계속 줄어들기 때문이다.
 * 기본적으로 벨만포드 알고리즘은 다익스트라 알고리즘처럼 최소 비용을 갱신해가면서 진행한다. 하지만 다익스트라 처럼
 * 우선순위 큐를 사용하는것이 아니라 각 노드를 하나씩 살펴보면서 최소비용을 갱신해가기 때문에 시간복잡도 상에서는 좋지않다.
 * 현실세계에선 음의 가중치라는것이 존재하지 않기때문에 알고리즘도 직관적으로 이해가 가지 않는다.
 * 정리하자면 (정점의 수 -1) 번 반복과정은 최단 거리를 찾는 과정이라고 보면 된다. (정점의 수 -1) 번 반복하는 이유는
 * 최단 경로의 최대 길이가 (정점의 수 -1) 이기 때문이다.
 * 최단 거리를 찾는 과정을 마친 후에는 음의 가중치를 가진 사이클이 존재하는지 아닌지 판별하는데
 * (정점의 수 -1) 번 반복했던 과정을 단 1번만 진행 시키면 된다.
 * 여기서 또 최단 거리 갱신이 발생한다는 것은 음의 가중치가 존재한다는 뜻이 되는 것이다.
 */
public class Solution95 {
    static class City {
        int end;
        int cost;

        public City(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }
    }
    static int N;
    static int M;
    static List<ArrayList<City>> graph = new ArrayList<>();
    static long[] dist;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());

        StringBuilder answer = new StringBuilder();
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            int c = Integer.parseInt(tokenizer.nextToken());
            graph.get(a).add(new City(b, c));
        }

        if (bellman_ford()) {
            System.out.println(-1);
        } else {
            for (int i = 2; i <= N; i++) {
                if (dist[i] == Integer.MAX_VALUE) {
                    answer.append(-1).append("\n");
                } else {
                    answer.append(dist[i]).append("\n");
                }
            }
        }
        System.out.println(answer);
    }

    static boolean bellman_ford() {
        dist = new long[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;
        boolean isCycle = false;

        for (int i = 1; i < N; i++) {
            isCycle = false;
            for (int j = 1; j <= N ; j++) {
                for (City city : graph.get(j)) {
                    if (dist[j] == Integer.MAX_VALUE) {
                        break;
                    }

                    if (dist[city.end] > dist[j] + city.cost) {
                        dist[city.end] = dist[j] + city.cost;
                        isCycle = true;
                    }
                }
                System.out.println("dist = " + Arrays.toString(dist));
            }
            if (!isCycle) {
                break;
            }
        }

        if (isCycle) {
            for (int i = 1; i <= N; i++) {
                for (City city : graph.get(i)) {
                    if (dist[city.end] > dist[i] + city.cost) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
