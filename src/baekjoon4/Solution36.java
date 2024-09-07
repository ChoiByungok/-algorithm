package baekjoon4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 1326 폴짝폴짝 (Silver2)
 * 해당 칸에 있는 개구리는 그 칸에 쓰여있는 숫자의 배수만큼만 점프를 할 수 있다.
 * 쉽게 생각하고 제출했는데 틀렸다고 나왔다. 개구리가 양방향으로 움직일 수도있는데 그걸 간과한것이다.
 * 나름대로 배열을 벗어났을때 예외를 방지하기 위해 장치를 넣어놨는데 굉장히 수준이 낮은 코드가 완성되었다.
 * 이런건 논리적으로 한줄로 끝내야 하는데 난 아직 멀은거같다.
 */
public class Solution36 {
    static int N;
    static int[] stoneBridge;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());
        stoneBridge = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        String[] split = bufferedReader.readLine().split(" ");
        int start = Integer.parseInt(split[0]) - 1;
        int end = Integer.parseInt(split[1]) - 1;

        System.out.println(bfs(start, end));
    }

    static int bfs(int start, int end) {
        int answer = -1;
        boolean[] visited = new boolean[N];
        visited[start] = true;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {start, 0});
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int now = poll[0];
            int step = poll[1];
            int num = stoneBridge[now];
            if (now == end) {
                answer = step;
                break;
            }

            int next = now + num;
            int prev = now - num;
            while (true) {
                int count = 0;
                if (prev < 0) {
                    count++;
                }

                if (next >= N) {
                    count++;
                }

                if (count == 2) {
                    break;
                }

                if (prev >= 0 && !visited[prev]) {
                    visited[prev] = true;
                    queue.add(new int[] {prev, step + 1});
                }

                if (next < N && !visited[next]) {
                    visited[next] = true;
                    queue.add(new int[] {next, step + 1});
                }

                next += num;
                prev -= num;
            }

        }
        return answer;
    }
}
