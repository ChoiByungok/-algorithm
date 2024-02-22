package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 2174 로봇 시뮬레이션 (Gold5)
 * 2차원 배열을 만들어 풀어보려고 했는데 문제 자체가
 * 우리가 생각하는 2차원 배열과 방향이 다르고 써봤자 괜히 햇갈릴거 같아서 사용하지 않았다.
 * 대신 로봇클래스에 좌표를 주어서 해당 좌표가 처음에 입력받은 숫자를 넘느냐 안넘느냐만 구분하였다.
 * 그러한 로봇클래스는 여러대가 있으니 로봇클래스배열을 생성하였다 로봇의 번호는 배열의 위치로 구분한다.
 * 명령어는 따로 명령어 배열을 받아서 저장하였다. 왜냐하면 입력받는 도중에 종료해버리면 통과가 안될거같다고 생각했기때문이다.
 * 초기화 작업이 완료되었으면 입력받은 명령어 배열을 순서대로 반복시킨다.
 * 그렇게 명령어를 진행하면서 해당 로봇이 범위를 벗어나거나
 * 다른 로봇과 부딪쳤을때 명령어 반복문을 탈출하고
 * 아니면 OK를 출력하면 된다.
 */
public class Solution137 {
    static class Robot {
        int a;
        int b;
        String dir;

        public Robot(int a, int b, String dir) {
            this.a = a;
            this.b = b;
            this.dir = dir;
        }

        public void rotate(String lr) {
            if (lr.equals("L")) {
                switch (dir) {
                    case "N":
                        this.dir = "W";
                        break;
                    case "W":
                        this.dir = "S";
                        break;
                    case "S":
                        this.dir = "E";
                        break;
                    case "E":
                        this.dir = "N";
                        break;
                }
            } else {
                switch (dir) {
                    case "N":
                        this.dir = "E";
                        break;
                    case "W":
                        this.dir = "N";
                        break;
                    case "S":
                        this.dir = "W";
                        break;
                    case "E":
                        this.dir = "S";
                        break;
                }
            }
        }

        public void forward() {
            switch (dir) {
                case "N":
                    b++;
                    break;
                case "W":
                    a--;
                    break;
                case "S":
                    b--;
                    break;
                case "E":
                    a++;
                    break;
            }
        }
    }
    static int A;
    static int B;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] ab = bufferedReader.readLine().split(" ");
        StringBuilder answer = new StringBuilder();
        A = Integer.parseInt(ab[0]);
        B = Integer.parseInt(ab[1]);
        String[] nm = bufferedReader.readLine().split(" ");
        int N = Integer.parseInt(nm[0]);
        int M = Integer.parseInt(nm[1]);
        Robot[] robots = new Robot[N];
        String[] commands = new String[M];
        for (int i = 0; i < N; i++) {
            String[] split = bufferedReader.readLine().split(" ");
            robots[i] = new Robot(Integer.parseInt(split[0]), Integer.parseInt(split[1]), split[2]);
        }
        for (int i = 0; i < M; i++) {
            commands[i] = bufferedReader.readLine();
        }

        boolean error = false;
        label:
        for (String command : commands) {
            String[] split = command.split(" ");
            int num = Integer.parseInt(split[0]) - 1;
            String com = split[1];
            int repeat = Integer.parseInt(split[2]);
            for (int i = 0; i < repeat; i++) {
                Robot robot = robots[num];
                if (com.equals("F")) {
                    robot.forward();
                } else {
                    robot.rotate(com);
                }
                int a = robot.a;
                int b = robot.b;
                if (a > A || a < 1 || b > B || b < 1) {
                    answer.append("Robot ").append(num + 1).append(" crashes into the wall");
                    error = true;
                    break label;
                }

                for (int j = 0; j < robots.length; j++) {
                    if (j == num) {
                        continue;
                    }
                    int a1 = robots[j].a;
                    int b1 = robots[j].b;
                    if (a == a1 && b == b1) {
                        answer.append("Robot ").append(num + 1).append(" crashes into robot ").append(j + 1);
                        error = true;
                        break label;
                    }
                }
            }
        }
        System.out.println(error ? answer : "OK");
    }
}
