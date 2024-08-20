package baekjoon4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 14226 이모티콘 (Gold4)
 * 그래프이론, bfs 로 해결가능한 문제라고는 쓰여있었지만 이 문제를 어떻게 bfs 로 어떻게 풀어야 할지 몰라서 결국은
 * 다른사람의 풀이를 보고 풀었다.
 * 우선 클립보드에 복사된 이모티콘 길이랑 화면에 보이는 이모티콘 길이를 각각 방문처리를 하여 무한 루프가 도는것을 방지해야 한다.
 * 클립보드가 비어있을때 화면에 붙여넣는 행동이라던가
 * 클립보드에 있는 문자와 화면에 있는 문자의 길이의 합이 주어진 S를 벗어난다던가
 * 화면의 문자길이가 1이 안되는데 하나 삭제하는 행동이라던가
 * 각각의 예외처리를 잘 해주면 무난하게 통과가 되는 문제였다.
 */
public class Solution18 {
    static class Emoticon {
        int clipboard;
        int length;
        int second;

        public Emoticon(int clipboard, int length, int second) {
            this.clipboard = clipboard;
            this.length = length;
            this.second = second;
        }
    }
    static int S;
    static boolean[][] visited = new boolean[1001][1001];
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        S = Integer.parseInt(bufferedReader.readLine());
        System.out.println(bfs());
    }
    static int bfs() {
        int answer = 0;
        Queue<Emoticon> queue = new LinkedList<>();
        queue.add(new Emoticon(0, 1, 0));
        visited[0][1] = true;
        while (!queue.isEmpty()) {
            Emoticon poll = queue.poll();
            int clipboard = poll.clipboard;
            int length = poll.length;
            int second = poll.second;
            if (length == S) {
                answer = second;
                break;
            }

            queue.add(new Emoticon(length, length, second + 1));

            if (clipboard > 0 && clipboard + length <= S && !visited[clipboard][clipboard + length]) {
                visited[clipboard][clipboard + length] = true;
                queue.add(new Emoticon(clipboard, clipboard + length, second + 1));
            }

            if (length >= 1 && !visited[clipboard][length - 1]) {
                visited[clipboard][length - 1] = true;
                queue.add(new Emoticon(clipboard, length - 1, second + 1));
            }
        }

        return answer;
    }
}
