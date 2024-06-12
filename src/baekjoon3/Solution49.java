package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 10282 해킹 (Gold4)
 * 문제를 읽어보면 해당 그래프는 단방향 그래프라는 것을 알 수 있다.
 * 근데 문제를 조금 햇갈리게 써놨는데 1 2 5 라고 입력 받으면
 * 2번 컴퓨터가 1번컴퓨터를 의존한다고 해석을 해야 하므로
 * 2 -> 1 로 연결되어있는 그래프를 만들어야 한다.
 * 이것만 주의하면 생각보다 간단(?) 하게 해결할 수 있는 문제
 * 감염이 시작된 컴퓨터를 start로 두고 해당 컴퓨터가 의존하고 있는 컴퓨터들을
 * 큐에 담으면서 다익스트라 알고리즘을 사용하면 된다. 그러면 시작컴퓨터와 의존관계를 맺고 있는
 * 컴퓨터들이 몇초뒤에 감염되는지 최소값이 배열에 저장될 것이다.
 * 이때 방문배열이 그대로 false이면 의존하지 않는다는 말이고 방문배열을 순회하여 true인 요소만
 * 카운트하면 그것이 감염된 컴퓨터의 수이고 다익스트라 알고리즘에 의해 갱신된 최솟값 배열중 가장 큰 수가
 * 전체 컴퓨터가 감염되는데 걸린 전체시간 이라고 생각하면 된다.
 */
public class Solution49 {
    static class Computer {
        int dependence;
        int second;

        public Computer(int dependence, int second) {
            this.dependence = dependence;
            this.second = second;
        }

        @Override
        public String toString() {
            return "Computer{" +
                    "end=" + dependence +
                    ", second=" + second +
                    '}';
        }
    }
    static boolean[] visited;
    static int[] infection;
    static List<ArrayList<Computer>> network;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        int T = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < T; i++) {
            StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
            int n = Integer.parseInt(tokenizer.nextToken());
            int d = Integer.parseInt(tokenizer.nextToken());
            int c = Integer.parseInt(tokenizer.nextToken());
            infection = new int[n + 1];
            Arrays.fill(infection, Integer.MAX_VALUE);
            visited = new boolean[n + 1];
            network = new ArrayList<>();
            for (int j = 0; j <= n; j++) {
                network.add(new ArrayList<>());
            }

            for (int j = 0; j < d; j++) {
                tokenizer = new StringTokenizer(bufferedReader.readLine());
                int a = Integer.parseInt(tokenizer.nextToken());
                int b = Integer.parseInt(tokenizer.nextToken());
                int s = Integer.parseInt(tokenizer.nextToken());
                network.get(b).add(new Computer(a, s));
            }
            dijkstra(c);
            int max = 0;
            for (int j = 1; j <= n; j++) {
                if (infection[j] < Integer.MAX_VALUE) {
                    max = Math.max(max, infection[j]);
                }
            }
            answer.append(count(n)).append(" ").append(max).append("\n");
        }
        System.out.println(answer);
    }

    static void dijkstra(int start) {
        Queue<Computer> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.second));
        queue.add(new Computer(start, 0));
        infection[start] = 0;
        while (!queue.isEmpty()) {
            Computer poll = queue.poll();
            int now = poll.dependence;
            if (!visited[now]) {
                visited[now] = true;

                for (Computer dependence : network.get(now)) {
                    int computer = dependence.dependence;
                    int second = dependence.second;
                    if (infection[computer] > infection[now] + second) {
                        infection[computer] = infection[now] + second;
                        queue.add(new Computer(computer, infection[computer]));
                    }
                }
            }
        }
    }

    static int count(int n) {
        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (visited[i]) {
                count++;
            }
        }
        return count;
    }
}
