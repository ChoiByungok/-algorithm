package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 14503 로봇 청소기 (Gold5)
 * 1.현재 칸이 아직 청소되지 않은 경우, 현재 칸을 청소한다.
 * 2.현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우,
 *      2_1.바라보는 방향을 유지한 채로 한 칸 후진할 수 있다면 한 칸 후진하고 1번으로 돌아간다.
 *      2_2.바라보는 방향의 뒤쪽 칸이 벽이라 후진할 수 없다면 작동을 멈춘다.
 * 3.현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우,
 *      3_1.반시계 방향으로 90도 회전한다.
 *      3_2.바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈 칸인 경우 한 칸 전진한다.
 *      3_3.1번으로 돌아간다.
 * 방향 d가 입력된다.
 * d가 0인 경우 북쪽, 1인 경우 동쪽, 2인 경우 남쪽, 3인 경우 서쪽을 바라보고 있는 것이다.
 * 처음에 문제를 이해 못해서 많이 해멨던 문제
 * 로봇이 바라보는 방향이 벽이아니고 청소를 하지 않았으면 계속 직진하면서 청소하다가 벽을 만나거나 청소를 했던 곳이라면
 * 그제서야 방향을 돌리는줄 알고 풀었음 근데 알고보니
 * 일단 방향을 먼저 돌리고 그 후에 청소를 안한 구역인지 벽인지 판단한 후 진행하는 거였음
 * 그래서 네 방향 다 돌렸는데도 갈 곳이 없으면 후진을 하는데 이때는 이미 청소한 구역이여도 갈 수 있음
 * 그러다 다시 청소 할 수 있는 구역이 나타나면 방향을 돌리고 아니면 계속 후진함
 * 계속 후진하다가 벽을 만나면 종료
 */
public class Solution117 {
    static class Robot {
        int x;
        int y;
        int dir;

        public Robot(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }
    static int N;
    static int M;
    static int[][] room;
    static Robot robot;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int answer = 0;
        String[] nm = bufferedReader.readLine().split(" ");
        N = Integer.parseInt(nm[0]);
        M = Integer.parseInt(nm[1]);
        room = new int[N][M];
        String[] rcd = bufferedReader.readLine().split(" ");
        int r = Integer.parseInt(rcd[0]);
        int c = Integer.parseInt(rcd[1]);
        int d = Integer.parseInt(rcd[2]);
        robot = new Robot(r, c, d);
        Map<Integer, List<Integer>> rotation = new HashMap<>();
        rotation.put(0, Arrays.asList(3, 2, 1, 0));
        rotation.put(1, Arrays.asList(0, 3, 2, 1));
        rotation.put(2, Arrays.asList(1, 0, 3, 2));
        rotation.put(3, Arrays.asList(2, 1, 0, 3));

        for (int i = 0; i < N; i++) {
            StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < M; j++) {
                room[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }
        boolean exit = false;
        while (!exit) {
            int x = robot.x;
            int y = robot.y;
            int dir = robot.dir;

            if (room[x][y] == 0) {
                room[x][y] = 2;
                answer++;
            }
            List<Integer> list = rotation.get(dir);
            switch (dir) {
                case 0: //북쪽을 바라보고 있을 떄
                    int count = getCount(x, y, list);
                    if (count == 4) { //네 방향 모두 갈 수 없으면
                        if (x + 1 < N && room[x+1][y] == 2) { //후진할 수 있는지 확인한다.
                            robot.x = x + 1;
                        } else { //후진했는데도 불구하고 벽이면 종료
                            exit = true;
                        }
                    }
                    break;
                case 1: // 동쪽을 바라보고 있을 때
                    count = getCount(x, y, list);
                    if (count == 4) {
                        if (y - 1 >= 0 && room[x][y-1] == 2) {
                            robot.y = y - 1;
                        } else {
                            exit = true;
                        }
                    }
                    break;
                case 2: // 남쪽을 바라보고 있을 떄
                    count = getCount(x, y, list);
                    if (count == 4) {
                        if (x - 1 >= 0 && room[x-1][y] == 2) {
                            robot.x = x - 1;
                        } else {
                            exit = true;
                        }
                    }
                    break;
                case 3: // 서쪽을 바라보고 있을 때
                    count = getCount(x, y, list);
                    if (count == 4) {
                        if (y + 1 < M && room[x][y+1] == 2) {
                            robot.y = y + 1;
                        } else {
                            exit = true;
                        }
                    }
                    break;
            }
        }
        System.out.println(answer);
    }

    private static int getCount(int x, int y, List<Integer> list) {
        int count = 0;
        label:
        for (Integer integer : list) {
            switch (integer) {
                case 0:
                    if (x - 1 >= 0 && room[x - 1][y] == 0) {
                        robot.x = x - 1;
                        robot.dir = integer;
                        break label;
                    }
                    break;
                case 1:
                    if (y + 1 < M && room[x][y + 1] == 0) {
                        robot.y = y + 1;
                        robot.dir = integer;
                        break label;
                    }
                    break;
                case 2:
                    if (x + 1 < N && room[x + 1][y] == 0) {
                        robot.x = x + 1;
                        robot.dir = integer;
                        break label;
                    }
                    break;
                case 3:
                    if (y - 1 >= 0 && room[x][y - 1] == 0) {
                        robot.y = y - 1;
                        robot.dir = integer;
                        break label;
                    }
                    break;
            }
            count++;
        }
        return count;
    }
}
