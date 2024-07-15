package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 2211 네트워크 복구 (Gold2)
 * 다익스트라 알고리즘으로 1번 컴퓨터부터 모든 컴퓨터의 최소경로를 구한다
 * 최소값이 갱신되는 과정에서 어떤 컴퓨터가 어떤 컴퓨터랑 회선이 연결되었는지 같이 갱신한다.
 * 완성된 회선연결이 담긴 배열을 이용해서 출력을 진행하면 된다.
 * 이 문제또한 약간 소 뒷걸음질 치다가 쥐잡은 꼴이라 풀어도 뭔가 찝찝함이 남아있는 문제다.
 */
public class Solution82 {
    static class Computer {
        int end;
        int weight;

        public Computer(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }
    }
    static int N;
    static int M;
    static List<ArrayList<Computer>> network = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());

        for (int i = 0; i <= N; i++) {
            network.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            int start = Integer.parseInt(tokenizer.nextToken());
            int end = Integer.parseInt(tokenizer.nextToken());
            int weight = Integer.parseInt(tokenizer.nextToken());
            network.get(start).add(new Computer(end, weight));
            network.get(end).add(new Computer(start, weight));
        }

        dijkstra();
    }

    static void dijkstra() {
        Queue<Computer> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight));
        int[] distance = new int[N + 1];
        int[] circuit = new int[N + 1];
        Arrays.fill(distance, 987654321);
        distance[1] = 0;
        queue.add(new Computer(1, 0));

        while (!queue.isEmpty()) {
            Computer poll = queue.poll();
            int now = poll.end;

            for (Computer computer : network.get(now)) {
                int end = computer.end;
                int nextWeight = computer.weight;

                if (distance[end] > distance[now] + nextWeight) {
                    distance[end] = distance[now] + nextWeight;
                    queue.add(new Computer(end, distance[end]));
                    circuit[end] = now;
                }
            }
        }

        StringBuilder builder = new StringBuilder();

        builder.append(N - 1).append("\n");
        for (int i = 2; i <= N; i++) {
            builder.append(i).append(" ").append(circuit[i]).append("\n");
        }
        System.out.println(builder);
    }
}
