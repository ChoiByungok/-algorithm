package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 11779 최소비용 구하기 2 (Gold3)
 * 최소비용 구하는 거야 하던대로 다익스트라 알고리즘을 사용하면 되는데
 * 문제는 목적지에 도착했을 떄 그 경로를 구해야 한다는 것이다. 어떻게 해야 경로를 저장할 수 있을까
 * 경로 역추적은 배열 하나 선언해서 거리 갱신이 발생했을때 배열의 부모 자식 설정을 해주면
 * 그 배열 가지고 역추적 해서 경로를 나타낼 수 있다. 그렇게 해서 제출했는데
 * 23퍼센트에서 시간초과 발생 지금까진 다익스트라 풀면서 시간초과가 발생한 적이 없는데
 * 왜 발생했을까 지금까지 다익스트라 알고리즘을 사용하면서 신경조차도 쓰지 않았던 부분이었는데
 * 코드를 더 최적화 시키는 부분이 있었다.
 *  if (dist[now] >= cost) 이 부분인데
 *  이게 무슨코드냐면 쉽게 말해 현재 노드까지의 비용이 이전에 계산된 비용보다 크지 않은 경우에만 다음 경로를 탐색하도록 조건을 건 것이다.
 *  예를 들어 5개의 정점이 있다고 친다.
 *  시작은 1 목표는 5
 *  1 -> 2 가중치 1
 *  2 -> 3 가중치 1
 *  3 -> 4 가중치 1
 *  4 -> 5 가중치 1
 *  1 -> 5 가중치 10
 * 이라고 했을때 처음 1과 연결되어있는 2 그리고 5번 정점이 우선순위큐에 들어가게 된다.
 * 1 -> 5 10 간선은 가중치가 크기떄문에 한참 뒤에야 나오게 될텐데
 * 그땐 이미 1 -> 5 가는 가중치가 4로 최적화 되어있을 것이다.
 * 10은 4보다 크니 이후 코드는 볼 필요도 없는것이다.
 * 이 로직을 넣으니 시간초과가 발생하지 않았고 다음부터는 무조건 넣어야 겠다고 생각했다.
 */
public class Solution96 {
    static final int INF = 99999999;
    static class City {
        int end;
        int cost;

        public City(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }
    }
    static int N;
    static List<ArrayList<City>> cities = new ArrayList<>();
    static int[] path;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());
        int M = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i <= N; i++) {
            cities.add(new ArrayList<>());
        }
        StringTokenizer tokenizer;
        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            int c = Integer.parseInt(tokenizer.nextToken());
            cities.get(a).add(new City(b, c));
        }
        tokenizer = new StringTokenizer(bufferedReader.readLine());
        int start = Integer.parseInt(tokenizer.nextToken());
        int end = Integer.parseInt(tokenizer.nextToken());
        System.out.println(answer(start, end));

    }
    static StringBuilder answer(int start, int end) {
        StringBuilder answer = new StringBuilder();
        answer.append(dijkstra(start, end)).append("\n");
        Stack<Integer> stack = new Stack<>();

        int trace = end;
        while (trace != 0) {
            stack.push(trace);
            trace = path[trace];
        }
        answer.append(stack.size()).append("\n");
        while (!stack.isEmpty()) {
            answer.append(stack.pop()).append(" ");
        }
        return answer;
    }

    static int dijkstra(int start, int end) {
        Queue<City> queue = new PriorityQueue<>(Comparator.comparingInt(c -> c.cost));
        int[] dist = new int[N + 1];
        path = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;
        queue.add(new City(start, 0));
        while (!queue.isEmpty()) {
            City poll = queue.poll();
            int now = poll.end;
            int cost = poll.cost;
            if (dist[now] >= cost) {
                for (City city : cities.get(now)) {
                    if (dist[city.end] > cost + city.cost) {
                        dist[city.end] = cost + city.cost;
                        path[city.end] = now;
                        queue.add(new City(city.end, dist[city.end]));
                    }
                }
            }
        }
        return dist[end];
    }
}
