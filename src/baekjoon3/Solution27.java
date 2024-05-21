package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 9205 맥주 마시면서 걸어가기 (Gold5)
 * 처음 이문제를 접했을때 무식하게 65535 * 65535 크기의 2차원 배열을 만들어 해결하려고 함
 * 한칸이동을 50으로 잡고 이동할때마다 맥주 하나씩 줄이고 그렇게 풀으려고 했는데
 * 도저히 아닌거같아서 결국 다른사람의 풀이를 참고했음
 * 알고보니 간단했음 입력받은 편의점의 숫자만큼 리스트와 방문 배열을 만들고
 * 시작위치서부터 맨해튼거리만큼 가능한 편의점의 좌표를 큐에 넣어서 bfs로 해결하면 되는거였음
 * 맨해튼 거리라는 것을 처음 들어봐서 생소했는데 그냥 다음 노드 x 에서 현재노드 x 의 차이의 절대값
 * 다음노드 y에서 현재노드 y의 차이의 절대값을 더하면됨 그 값이 1000 이하라면 다음노드로 갈 수 있는것임
 * 그렇게 시작지점에서 편의점을 거쳐서 목적지에 갈 수 있으면 happy 아니면 sad 를 출력하면 되는것임
 */
public class Solution27 {
    static int startX;
    static int startY;
    static int goalX;
    static int goalY;
    static boolean[] visited;
    static List<int[]> list;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        int T = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < T; i++) {
            int n = Integer.parseInt(bufferedReader.readLine());
            list = new ArrayList<>();
            visited = new boolean[n];
            String[] start = bufferedReader.readLine().split(" ");
            startX = Integer.parseInt(start[0]);
            startY = Integer.parseInt(start[1]);
            for (int j = 0; j < n; j++) {
                String[] con = bufferedReader.readLine().split(" ");
                int conX = Integer.parseInt(con[0]);
                int conY = Integer.parseInt(con[1]);
                list.add(new int[] {conX, conY});
            }
            String[] goal = bufferedReader.readLine().split(" ");
            goalX = Integer.parseInt(goal[0]);
            goalY = Integer.parseInt(goal[1]);
            answer.append(bfs() ? "happy" : "sad").append("\n");
        }
        System.out.println(answer);
    }

    static boolean bfs() {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {startX, startY});
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int x = poll[0];
            int y = poll[1];

            if (Math.abs(goalX - x) + Math.abs(goalY - y) <= 1000) {
                return true;
            }

            for (int i = 0; i < list.size(); i++) {
                int[] ints = list.get(i);
                int nextX = ints[0];
                int nextY = ints[1];

                if (Math.abs(nextX - x) + Math.abs(nextY - y) <= 1000 && !visited[i]) {
                    visited[i] = true;
                    queue.add(ints);
                }
            }
        }

        return false;
    }
}
