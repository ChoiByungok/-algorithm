package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 13567 로봇 (Silver4)
 * 로봇이라는 객체에 boolean 형으로 error 이라는 변수가 있는데
 * 한번이라도 판 밖으로 나가면 error 을 true 로 주어
 * -1을 출력하게끔 하였다. 마음같아선 입력을 받으면서 break를 걸고 싶지만
 * 그러면 오류가 뜰거같아서 하지 않았다.
 */
public class Solution155 {
    static class Robot {
        int x;
        int y;
        int dir;
        boolean error;

        public Robot(int x, int y, int dir, boolean error) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.error = error;
        }

        public void command(String command, int value) {
            if (command.equals("MOVE")) {
                switch (dir) {
                    case 1:
                        y = y + value;
                        if (y > M) {
                            error = true;
                        }
                        break;
                    case 2:
                        x = x + value;
                        if (x > M) {
                            error = true;
                        }
                        break;
                    case 3:
                        y = y - value;
                        if (y < 0) {
                            error = true;
                        }
                        break;
                    case 4:
                        x = x - value;
                        if (x < 0) {
                            error = true;
                        }
                        break;
                }
            } else {
                if (value == 0) {
                    if (dir == 1) {
                        dir = 4;
                    } else {
                        dir--;
                    }
                } else {
                    if (dir == 4) {
                        dir = 1;
                    } else {
                        dir++;
                    }
                }
            }
        }

        @Override
        public String toString() {
            return error ? "-1" : x + " " + y;
        }
    }
    static int M;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] MN = bufferedReader.readLine().split(" ");
        M = Integer.parseInt(MN[0]);
        int N = Integer.parseInt(MN[1]);
        Robot robot = new Robot(0, 0, 2, false);

        for (int i = 0; i < N; i++) {
            String[] split = bufferedReader.readLine().split(" ");
            String command = split[0];
            int value = Integer.parseInt(split[1]);
            robot.command(command, value);
        }

        System.out.println(robot);
    }
}
