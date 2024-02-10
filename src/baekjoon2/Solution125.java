package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 15662 톱니바퀴(2) (Gold5)
 * 저번에 풀었던 톱니바퀴문제 응용문제이다.
 * 저번에는 톱니바퀴가 4개로 고정되어있던 반면
 * 이번에는 최대 1000개까지 존재한다.
 * 이 문제또한 주의해야 할 점은 톱니바퀴가 순차적으로 돌아가는게 아니라 동시에 회전한다는 것이다.
 * 예를들어 10개의 톱니바퀴가 존재한다고 햇을때
 * 4번 톱니바퀴를 시계방향으로 돌리라고 명령받았으면
 * 4번부터 1번까지 그리고 4번부터 10번 톱니바퀴까지 확인하여
 * 해당 톱니바퀴부터 어디까지 돌려야 하는지 미리 파악을 해 두어야 한다.
 * 4번의 왼쪽 이가 3번의 오른쪽 이랑 같지 않으면 3번은 4번의 반대방향으로 회전한다.
 * 3번의 왼쪽이가 2번의 오른쪽 이랑 같으면 2번은 회전하지 않으며 1번톱니바퀴는 볼 필요도 없다
 * 마찬가지로 4번의 오른쪽 이랑 5번의 왼쪽이랑 다르면 5번은 4번의 반대방향으로 회전한다.
 * 5번의 오른쪽이랑 6번의 왼쪽이랑 다르면 6번은 5번의 반대방향으로 회전한다.
 * 6번의 오른쪽이랑 7번의 왼쪽이랑 같으면 7번은 회전하지 않으며 나머지 8 9 10번 톱니바퀴는 볼 필요가 없다.
 * 그러면 4번기준으로 왼쪽으로는 3번까지만 오른쪽으로는 6번까지만 돌리면 되는것이다.
 * 이런식으로 계속 진행 한 뒤 마지막에 톱니바퀴를 전체 순회하여 12시가 S극인 톱니의 갯수를 출력시키면 된다.
 */
public class Solution125 {
    static class Gear {
        boolean[] gears;

        public Gear(boolean[] gears) {
            this.gears = gears;
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

        public void rotate(boolean dir) {
            if (dir) {
                boolean temp = gears[gears.length - 1];
                for (int i = gears.length - 1; i > 0; i--) {
                    gears[i] = gears[i - 1];
                }
                gears[0] = temp;
            } else {
                boolean temp = gears[0];
                for (int i = 0; i < gears.length - 1; i++) {
                    gears[i] = gears[i + 1];
                }
                gears[gears.length - 1] = temp;
            }
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bufferedReader.readLine());
        Gear[] gears = new Gear[T];
        int answer = 0;

        for (int i = 0; i < T; i++) {
            String readLine = bufferedReader.readLine();
            boolean[] gear = new boolean[8];
            for (int j = 0; j < readLine.length(); j++) {
                char c = readLine.charAt(j);
                if (c == '1') {
                    gear[j] = true;
                }
            }
            gears[i] = new Gear(gear);
        }
        int K = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < K; i++) {
            StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
            int num = Integer.parseInt(tokenizer.nextToken()) - 1;
            boolean dir = tokenizer.nextToken().equals("1");
            int leftGear = 0;
            int rightGear = gears.length - 1;
            for (int j = num; j > 0; j--) {
                if (gears[j].left() == gears[j - 1].right()) {
                    leftGear = j;
                    break;
                }
            }

            for (int j = num; j < gears.length - 1; j++) {
                if (gears[j].right() == gears[j + 1].left()) {
                    rightGear = j;
                    break;
                }
            }
            gears[num].rotate(dir);
            boolean dir1 = !dir;
            for (int j = num - 1; j >= leftGear; j--) {
                gears[j].rotate(dir1);
                dir1 = !dir1;
            }

            boolean dir2 = !dir;
            for (int j = num + 1; j <= rightGear; j++) {
                gears[j].rotate(dir2);
                dir2 = !dir2;
            }
        }

        for (Gear gear : gears) {
            if (gear.top()) {
                answer++;
            }
        }

        System.out.println(answer);
    }
}
