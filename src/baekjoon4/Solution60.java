package baekjoon4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 18126 너구리 구구 (Silver2)
 * 간단한 bfs문제였고 실제로도 쉬워서 금방풀고 제출함 근데 66퍼에서 틀렸다고 나옴
 * 그래서 왜그런가 봤더니 간선 cost가 최대 10억임 이 값들이 누적되었을 시 인트형 범위를 벗어남
 * 그래서 타입을 long 으로 바꿨더니 통과됨
 */
public class Solution60 {
    static class Room {
        int num;
        long cost;

        public Room(int num, long cost) {
            this.num = num;
            this.cost = cost;
        }
    }
    static int N;
    static boolean[] visited;
    static List<ArrayList<Room>> rooms = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());
        visited = new boolean[N + 1];
        for (int i = 0; i <= N; i++) {
            rooms.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
            int A = Integer.parseInt(tokenizer.nextToken());
            int B = Integer.parseInt(tokenizer.nextToken());
            int C = Integer.parseInt(tokenizer.nextToken());
            rooms.get(A).add(new Room(B, C));
            rooms.get(B).add(new Room(A, C));
        }

        System.out.println(bfs());
    }

    static long bfs() {
        long answer = 0;
        visited[1] = true;
        Queue<Room> queue = new LinkedList<>();
        queue.add(new Room(1, 0));
        while (!queue.isEmpty()) {
            Room poll = queue.poll();
            int now = poll.num;
            long cost = poll.cost;

            answer = Math.max(answer, cost);

            for (Room room : rooms.get(now)) {
                int next = room.num;
                long nc = room.cost;
                if (!visited[next]) {
                    visited[next] = true;
                    queue.add(new Room(next, cost + nc));
                }
            }
        }
        return answer;
    }
}
