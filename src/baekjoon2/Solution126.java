package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 20055 컨베이어 벨트 위의 로봇 (Gold5)
 * 문제 이해하는데 한참 걸림 결국 이해 못해서 질문게시판 뒤져가면서 이해함
 * 그래서 어찌저찌 예제들은 다 맞길래 제출했더니 56퍼에서 시간초과남
 * 다음에 재도전 해야할 문제
 */
public class Solution126 {
    static class Conveyor {
        Plate[] belt;
        static int zeroCount = 0;

        public void setConveyor(StringTokenizer tokenizer) {
            int beltSize = 2 * N;
            belt = new Plate[beltSize];
            for (int i = 0; i < beltSize; i++) {
                belt[i] = new Plate(Integer.parseInt(tokenizer.nextToken()), false);
            }
        }

        public void move() {
            Plate temp = belt[belt.length - 1];
            for (int i = belt.length - 1; i > 0; i--) {
                belt[i] = belt[i - 1];
            }
            belt[0] = temp;
        }

        public void moveRobot() {
            for (int i = N - 2; i >= 0; i--) {
                Plate plate = belt[i];
                Plate nextPlate = belt[i + 1];
                if (plate.isRobot()) {
                    if (!nextPlate.isRobot() && nextPlate.durability > 0) {
                        plate.removeRobot();
                        nextPlate.setRobot();
                    }
                }
            }
        }
    }

    static class Plate {
        int durability;
        boolean robot;

        public Plate(int durability, boolean robot) {
            this.durability = durability;
            this.robot = robot;
        }

        public void setRobot() {
            robot = true;
            durability--;
            if (durability == 0) {
                Conveyor.zeroCount++;
            }
        }

        public void removeRobot() {
            robot = false;
        }

        public boolean isRobot() {
            return robot;
        }
    }
    static int N;
    static int K;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        Conveyor conveyor = new Conveyor();
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        int answer = 0;
        N = Integer.parseInt(tokenizer.nextToken());
        K = Integer.parseInt(tokenizer.nextToken());
        tokenizer = new StringTokenizer(bufferedReader.readLine());
        conveyor.setConveyor(tokenizer);


        while (Conveyor.zeroCount != K) {
            answer++;
            conveyor.move();
            if (conveyor.belt[N - 1].isRobot()) {
                conveyor.belt[N - 1].removeRobot();
            }
            conveyor.moveRobot();
            if (conveyor.belt[N - 1].isRobot()) {
                conveyor.belt[N - 1].removeRobot();
            }
            if (conveyor.belt[0].durability > 0) {
                conveyor.belt[0].setRobot();
            }
        }

        System.out.println(answer);
    }
}
