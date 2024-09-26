package baekjoon4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 16509 장군 (Gold5)
 * 어차피 왕과 상밖에 없기때문에 상이 가는 경로에 기물이 있다면 해당 위치로는 갈 수 없다라는 조건을 무시했었다.
 * 그런데 3번예제에서 틀렸다길래 찾아보니 그 기물중에 왕이 포함될수도 있다.
 * 그러면 상이 움직이는 경로상에 왕이 존재하는지 하나하나 따져봐야 하는데
 * 내가 대가리가 비상했더라면 어떻게 간결한 반복문하나로 해결했겠지만
 * 빡대가리라 한칸한칸 노가다로 해당 위치에 왕이 있는지 확인하면서 조건문을 넣고 구현해봤는데
 * 3번예제가 똑바로 출력되지 않는다 결국 디버깅을 통해서 왜 틀린지 찾아내었고 간신히 통과하였다.
 */
public class Solution55 {
    static boolean[][] map = new boolean[10][9];
    static int startX, startY, endX, endY;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] start = bufferedReader.readLine().split(" ");
        startX = Integer.parseInt(start[0]);
        startY = Integer.parseInt(start[1]);
        String[] end = bufferedReader.readLine().split(" ");
        endX = Integer.parseInt(end[0]);
        endY = Integer.parseInt(end[1]);
        System.out.println(bfs());
    }

    static int bfs() {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {startX, startY, 0});
        map[startX][startY] = true;
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int x = poll[0];
            int y = poll[1];
            int step = poll[2];

            if (x == endX && y == endY) {
                return step;
            }

            if (x - 1 >= 0 && (x - 1 != endX || y != endY)) { //위
                if (x - 2 >= 0 && y - 1 >= 0 && (x - 2 != endX || y - 1 != endY)) {
                    if (x - 3 >= 0 && y - 2 >= 0 && !map[x - 3][y - 2]) {
                        map[x - 3][y - 2] = true;
                        queue.add(new int[] {x - 3, y - 2, step + 1});
                    }
                }

                if (x - 2 >= 0 && y + 1 <= 8 && (x - 2 != endX || y + 1 != endY)) {
                    if (x - 3 >= 0 && y + 2 <= 8 && !map[x - 3][y + 2]) {
                        map[x - 3][y + 2] = true;
                        queue.add(new int[] {x - 3, y + 2, step + 1});
                    }
                }
            }

            if (y + 1 <= 8 && (x != endX || y + 1 != endY)) { // 오른쪽
                if (x - 1 >= 0 && y + 2 <= 8 && (x - 1 != endX || y + 2 != endY)) {
                    if (x - 2 >= 0 && y + 3 <= 8 && !map[x - 2][y + 3]) {
                        map[x - 2][y + 3] = true;
                        queue.add(new int[] {x - 2, y + 3, step + 1});
                    }
                }

                if (x + 1 <= 9 && y + 2 <= 8 && (x + 1 != endX || y + 2 != endY)) {
                    if (x + 2 <= 9 && y + 3 <= 8 && !map[x + 2][y + 3]) {
                        map[x + 2][y + 3] = true;
                        queue.add(new int[] {x + 2, y + 3, step + 1});
                    }
                }
            }

            if (x + 1 <= 9 && (x + 1 != endX || y != endY)) { // 아래
                if (x + 2 <= 9 && y + 1 <= 8 && (x + 2 != endX || y + 1 != endY)) {
                    if (x + 3 <= 9 && y + 2 <= 8 && !map[x + 3][y + 2]) {
                        map[x + 3][y + 2] = true;
                        queue.add(new int[] {x + 3, y + 2, step + 1});
                    }
                }

                if (x + 2 <= 9 && y - 1 >= 0 && (x + 2 != endX || y - 1 != endY)) {
                    if (x + 3 <= 9 && y - 2 >= 0 && !map[x + 3][y - 2]) {
                        map[x + 3][y - 2] = true;
                        queue.add(new int[] {x + 3, y - 2, step + 1});
                    }
                }
            }

            if (y - 1 >= 0 && (x != endX || y - 1 != endY)) { //왼쪽
                if (x + 1 <= 9 && y - 2 >= 0 && (x + 1 != endX || y - 2 != endY)) {
                    if (x + 2 <= 9 && y - 3 >= 0 && !map[x + 2][y - 3]) {
                        map[x + 2][y - 3] = true;
                        queue.add(new int[] {x + 2, y - 3, step + 1});
                    }
                }

                if (x - 1 >= 0 && y - 2 >= 0 && (x - 1 != endX || y - 2 != endY)) {
                    if (x - 2 >= 0 && y - 3 >= 0 && !map[x - 2][y - 3]) {
                        map[x - 2][y - 3] = true;
                        queue.add(new int[] {x - 2, y - 3, step + 1});
                    }
                }
            }
        }

        return -1;
    }
}
