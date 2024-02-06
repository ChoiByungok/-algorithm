package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 14891 톱니바퀴 (Gold5)
 * 나는 Gear 라는 객체를 만들고 내부 변수로 길이가 8인 boolean 배열을 만들어놨다.
 * 그리고 길이가 4인 Gear 배열을 만든다음
 * 각 기어의 boolean 배열 2번째 요소를 left 6번째 요소를  right 라고 칭한다.
 * 예를 들어 첫번째 기어를 시계방향으로 돌린다 했을때
 * 첫번째 기어의 오른쪽과 두번째 기어의 왼쪽을 비교하여 같으면 냅두고 다르면 두번째 기어를 반시계방향으로 돌린다.
 * 이때 두번째 기어의 오른쪽과 세번째 기어의 왼쪽을 비교하여 같으면 냅두고 다르면 세번째 기어를 시계방향으로 돌린다.
 * 그리고 마지막으로 네번째 기어의 왼쪽과 세번째 기어의 오른쪽을 비교하여 같으면 냅두고 다르면 네번째 기어를 반시계방향으로 돌린다.
 * 이때 주의 해야 하는점은 기어를 돌리기전에 모든 기어의 오른쪽과 왼쪽을 미리 저장해놔야 한다.
 * 왜냐하면 기어는 순차적으로 돌아가는게 아니라 한번에 같이 돌아가기 때문이다.
 */
public class Solution121 {
    static class Gear {
        boolean[] gears;

        public Gear(boolean[] gears) {
            this.gears = gears;
        }
        public void rotation(boolean clockWise) {

            boolean gear1 = gears[0];
            boolean gear2 = gears[1];
            boolean gear3 = gears[2];
            boolean gear4 = gears[3];
            boolean gear5 = gears[4];
            boolean gear6 = gears[5];
            boolean gear7 = gears[6];
            boolean gear8 = gears[7];
            if (clockWise) {
                gears[0] = gear8;
                gears[1] = gear1;
                gears[2] = gear2;
                gears[3] = gear3;
                gears[4] = gear4;
                gears[5] = gear5;
                gears[6] = gear6;
                gears[7] = gear7;
            } else {
                gears[0] = gear2;
                gears[1] = gear3;
                gears[2] = gear4;
                gears[3] = gear5;
                gears[4] = gear6;
                gears[5] = gear7;
                gears[6] = gear8;
                gears[7] = gear1;
            }
        }

        public boolean top() {
            return gears[0];
        }

        public boolean right() {
            return gears[2];
        }

        public boolean left() {
            return gears[6];
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        Gear[] gears = new Gear[4];
        int answer = 0;
        for (int i = 0; i < gears.length; i++) {
            String readLine = bufferedReader.readLine();
            boolean[] gear = new boolean[8];
            for (int j = 0; j < readLine.length(); j++) {
                if (readLine.charAt(j) == '1') {
                    gear[j] = true;
                }
            }
            gears[i] = new Gear(gear);
        }

        int N = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < N; i++) {
            String[] split = bufferedReader.readLine().split(" ");
            int num = Integer.parseInt(split[0]) - 1;
            boolean clockWise = !split[1].equals("-1");

            boolean oneRight = gears[0].right();
            boolean twoLeft = gears[1].left();
            boolean twoRight = gears[1].right();
            boolean threeLeft = gears[2].left();
            boolean threeRight = gears[2].right();
            boolean fourLeft = gears[3].left();
            switch (num) {
                case 0:
                    gears[num].rotation(clockWise);
                    if (oneRight != twoLeft) {
                        gears[num + 1].rotation(!clockWise);
                        if (twoRight != threeLeft) {
                            gears[num + 2].rotation(clockWise);
                            if (threeRight != fourLeft) {
                                gears[num + 3].rotation(!clockWise);
                            }
                        }
                    }
                    break;
                case 1:
                    gears[num].rotation(clockWise);
                    if (oneRight != twoLeft) {
                        gears[num - 1].rotation(!clockWise);
                    }
                    if (twoRight != threeLeft) {
                        gears[num + 1].rotation(!clockWise);
                        if (threeRight != fourLeft) {
                            gears[num + 2].rotation(clockWise);
                        }
                    }
                    break;
                case 2:
                    gears[num].rotation(clockWise);
                    if (threeRight != fourLeft) {
                        gears[num + 1].rotation(!clockWise);
                    }
                    if (threeLeft != twoRight) {
                        gears[num - 1].rotation(!clockWise);
                        if (twoLeft != oneRight) {
                            gears[num - 2].rotation(clockWise);
                        }
                    }
                    break;
                case 3:
                    gears[num].rotation(clockWise);
                    if (fourLeft != threeRight) {
                        gears[num - 1].rotation(!clockWise);
                        if (threeLeft != twoRight) {
                            gears[num - 2].rotation(clockWise);
                            if (twoLeft != oneRight) {
                                gears[num - 3].rotation(!clockWise);
                            }
                        }
                    }
                    break;

            }
        }

        for (int i = 0; i < gears.length; i++) {
            if (gears[i].top()) {
                switch (i + 1) {
                    case 1:
                        answer += 1;
                        break;
                    case 2:
                        answer += 2;
                        break;
                    case 3:
                        answer += 4;
                        break;
                    case 4:
                        answer += 8;
                        break;
                }
            }
        }

        System.out.println(answer);
    }
}
