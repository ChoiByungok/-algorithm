package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 13424 비밀모임 (Gold4)
 * N개의 방이 있을때 각 방들은 M개의 간선으로 연결되어있음
 * 각 간선은 가중치가 주어졌으며 각 방은 간선으로 양방향 연결되어있음
 * K명의 친구가 있을때 어디 방에서 모였을때 친구들의 이동거리의 합이 가장 적은가를 구하는 문제
 * 우선 방의 갯수가 100개가 맥시멈이므로 플로이드 워셜 알고리즘을 사용해도 통과되는데
 * 나는 다익스트라 알고리즘을 사용하였다. K명의 친구만큼 다익스트라 알고리즘을 돌리고
 * 거기서 나온 최단거리 배열의 합으로 방을 구하였는데
 * 지문을 똑바로 안읽어서 조금 해멨다
 * 1번친구가 1번방에 있을때 1번방이 만약 최단거리 방이라면 제외시켰는데
 * 그런거 신경쓰지 말고 그냥 1번방이 최단거리 방이라면 1번방을 출력하면 되는것이다.
 */
public class Solution84 {
    static class Room {
        int num;
        int value;

        public Room(int num, int value) {
            this.num = num;
            this.value = value;
        }
    }
    static List<ArrayList<Room>> map;
    static int[][] distance;
    static final int INF = 10000000;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        int T = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < T; i++) {
            map = new ArrayList<>();
            StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
            int N = Integer.parseInt(tokenizer.nextToken());
            int M = Integer.parseInt(tokenizer.nextToken());
            for (int j = 0; j <= N; j++) {
                map.add(new ArrayList<>());
            }

            for (int j = 0; j < M; j++) {
                tokenizer = new StringTokenizer(bufferedReader.readLine());
                int a = Integer.parseInt(tokenizer.nextToken());
                int b = Integer.parseInt(tokenizer.nextToken());
                int c = Integer.parseInt(tokenizer.nextToken());
                map.get(a).add(new Room(b, c));
                map.get(b).add(new Room(a, c));
            }

            int K = Integer.parseInt(bufferedReader.readLine());
            distance = new int[K][N + 1];

            tokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < K; j++) {
                int start = Integer.parseInt(tokenizer.nextToken());
                dijkstra(start, j);
            }

            int index = 0;
            int min = Integer.MAX_VALUE;
            for (int j = 1; j <= N; j++) {
                int sum = 0;
                for (int k = 0; k < K; k++) {
                    sum += distance[k][j];
                }
                if (sum < min) {
                    index = j;
                    min = sum;
                }
            }
            answer.append(index).append("\n");
        }
        System.out.println(answer);
    }
    static void dijkstra(int start, int index) {
        Arrays.fill(distance[index], INF);
        distance[index][start] = 0;
        Queue<Room> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.value));
        queue.add(new Room(start, 0));
        while (!queue.isEmpty()) {
            Room room = queue.poll();
            int now = room.num;
            int value = room.value;

            for (Room next : map.get(now)) {
                if (distance[index][next.num] > value + next.value) {
                    distance[index][next.num] = value + next.value;
                    queue.add(new Room(next.num, distance[index][next.num]));
                }
            }
        }
    }
}
