package baekjoon4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 16397 탈출 (Gold4)
 * A연산 B연산 모두 제대로 짰다고 생각했는데 제출하자마자 틀렸다고 나옴
 * 문제에 포함된 예제 및 질문게시판에 존재하는 모든 반례가 통과되는데
 * 제출하자마자 틀렸다고 뜸 그래서 챗 지피티에 질문함
 * B연산에 문제가 있다고 함 가장 큰 자릿수를 1 줄이는게 B연산인데
 * 123 -> 246이되고 여기서 가장 큰 자릿수인 2를 1로 줄여서 146을 만드는게 B연산임
 * 나는 246을 String으로 바꾼뒤 길이(3)를 구한다음 거기서 1을 뺀 2를 10의 거듭제곱(100)시킨뒤 246에서 100을 빼는 방법으로 풀었음
 * 근데 이게 틀렸다고 함 설명을 들어도 납득할 수 없었음 어쨋든 챗지피티가 푼 해답은
 * 그냥 246의 첫자리를 추출한 뒤에 값을 1줄이고 다시 문자열을 조립하는 방법이었음
 * 내가 해결한 방법과 도대체 뭔 차이가 있는지 모르겠지만 챗지피티 방법으로 제출하니깐 바로 통과됨
 */
public class Solution56 {
    static final int MAX = 100000;
    static int N, T, G;
    static boolean[] visited = new boolean[MAX];
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] split = bufferedReader.readLine().split(" ");
        N = Integer.parseInt(split[0]);
        T = Integer.parseInt(split[1]);
        G = Integer.parseInt(split[2]);
        int answer = bfs();
        System.out.println(answer == -1 ? "ANG" : answer);
    }

    static int bfs() {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {N, 0});
        visited[N] = true;
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int now = poll[0];
            int step = poll[1];

            if (now == G) {
                return step;
            }

            if (step >= T) {
                continue;
            }

            if (now + 1 < MAX && !visited[now + 1]) {
                visited[now + 1] = true;
                queue.add(new int[] {now + 1, step + 1});
            }

            if (now != 0 && now * 2 < MAX) {
                int doubled = now * 2;
                String s = String.valueOf(doubled);
                int high = Character.getNumericValue(s.charAt(0));

                if (high > 0) {
                    high--;
                }
                
                StringBuilder builder = new StringBuilder();
                builder.append(high);
                for (int i = 1; i < s.length(); i++) {
                    builder.append(s.charAt(i));
                }
                int next = Integer.parseInt(builder.toString());

                if (!visited[next]) {
                    visited[next] = true;
                    queue.add(new int[] {next, step + 1});
                }
            }
        }
        return -1;
    }
}
