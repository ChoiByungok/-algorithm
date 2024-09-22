package baekjoon4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 18232 텔레포트 정거장 (Silver2)
 * 처음에는 텔레포트 정거장의 시작점과 도착점을 <Integer, Integer> 형태의 맵으로 풀려고 했다.
 * 근데 예를 들면 1번 노드가 3번이랑 4번으로 갈 수 있다고 했을때
 * 위의 방식으로 해결하면 입력받을 때 3번노드는 덮어지고 4번으로만 갈 수 있게 저장된다.
 * 그래서 <Integer, List> 형태의 맵으로 바꾸고 풀었는데 또 틀렸다고 나왔다.
 * 알고보니 양방향으로 이동이 가능하다고 하길래 양방향으로 입력받고 제출하니깐 통과되었다.
 */
public class Solution51 {
    static int N, S, E;
    static Map<Integer, List<Integer>> teleport = new HashMap<>();
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        visited = new boolean[N + 1];
        int M = Integer.parseInt(tokenizer.nextToken());
        tokenizer = new StringTokenizer(bufferedReader.readLine());
        S = Integer.parseInt(tokenizer.nextToken());
        E = Integer.parseInt(tokenizer.nextToken());

        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            int x = Integer.parseInt(tokenizer.nextToken());
            int y = Integer.parseInt(tokenizer.nextToken());
            if (teleport.containsKey(x)) {
                teleport.get(x).add(y);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(y);
                teleport.put(x, list);
            }

            if (teleport.containsKey(y)) {
                teleport.get(y).add(x);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(x);
                teleport.put(y, list);
            }
        }
        System.out.println(bfs());
    }

    static int bfs() {
        int answer = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {S, 0});
        visited[S] = true;
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int now = poll[0];
            int second = poll[1];

            if (now == E) {
                answer = second;
                break;
            }

            if (teleport.containsKey(now)) {
                for (Integer integer : teleport.get(now)) {
                    if (!visited[integer]) {
                        visited[integer] = true;
                        queue.add(new int[] {integer, second + 1});
                    }
                }
            }

            if (now + 1 <= N && !visited[now + 1]) {
                visited[now + 1] = true;
                queue.add(new int[] {now + 1, second + 1});
            }

            if (now - 1 >= 1 && !visited[now - 1]) {
                visited[now - 1] = true;
                queue.add(new int[] {now - 1, second + 1});
            }
        }
        return answer;
    }
}
