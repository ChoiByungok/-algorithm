package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 14499 주사위 굴리기 (Gold4)
 * 맵에는 숫자가 적혀있고 주사위가 맵 특정좌표 위에 존재하며
 * 1을 입력받으면 동쪽 2는서쪽 3은북쪽 4는남쪽으로 구른다.
 * 이때 맵 칸에 쓰여 있는 숫자가 0이면 주사위 바닥에 복사가 되고
 * 그 외 숫자가 적혀있으면 그 숫자가 주사위 바닥에 복사되며 그 칸은 0이된다.
 * 주사위가 갈 수 없는 위치로 구르라고 하면 무시한다.
 * 이때 매번 굴릴때마다 나오는 윗면의 숫자를 차례대로 출력하면 된다.
 * 나는 주사위라는 객체를 하나 만들어서 위, 앞, 바닥, 뒤, 오른쪽, 왼쪽 그리고 좌표 변수를 내부에 두었고
 * 그 이후에 방향이 입력되면 변수를 서로 바꿔가면서 문제를 해결하였다.
 * 예를 들어 남쪽으로 구르라고 입력받으면
 * 앞은 바닥이 되고 위는 앞이 되며 뒤는 위가 되고 바닥은 뒤가 된다. 그리고 x좌표를 1증가 시키면 된다.
 * 이렇게 내부변수들을 바꾸고 난뒤 주사위의 위를 출력시키면 되는 문제이다.
 */
public class Solution119 {
    static class Dice {
        int top;
        int front;
        int right;
        int left;
        int bottom;
        int back;
        int x;
        int y;

        public void roll(int dir) {
            int copyT;
            int copyF;
            int copyBo;
            int copyBa;
            int copyR;
            int copyL;
            switch (dir) {
                case 1:
                    copyR = right;
                    copyBo = bottom;
                    copyL = left;
                    copyT = top;
                    bottom = copyR;
                    left = copyBo;
                    top = copyL;
                    right = copyT;
                    y++;
                    break;
                case 2:
                    copyR = right;
                    copyBo = bottom;
                    copyL = left;
                    copyT = top;
                    bottom = copyL;
                    right = copyBo;
                    top = copyR;
                    left = copyT;
                    y--;
                    break;
                case 3:
                    copyBo = bottom;
                    copyF = front;
                    copyT = top;
                    copyBa = back;
                    front = copyBo;
                    top = copyF;
                    back = copyT;
                    bottom = copyBa;
                    x--;
                    break;
                case 4:
                    copyBo = bottom;
                    copyF = front;
                    copyT = top;
                    copyBa = back;
                    bottom = copyF;
                    front = copyT;
                    top = copyBa;
                    back = copyBo;
                    x++;
                    break;
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;
        StringBuilder answer = new StringBuilder();
        Dice dice = new Dice();
        String[] init = bufferedReader.readLine().split(" ");
        int N = Integer.parseInt(init[0]);
        int M = Integer.parseInt(init[1]);
        dice.x = Integer.parseInt(init[2]);
        dice.y = Integer.parseInt(init[3]);
        int k = Integer.parseInt(init[4]);

        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }
        tokenizer = new StringTokenizer(bufferedReader.readLine());
        while (tokenizer.hasMoreTokens()) {
            int dir = Integer.parseInt(tokenizer.nextToken());
            int bx = dice.x;
            int by = dice.y;
            if (dir == 1 && by == M - 1
             || dir == 2 && by == 0
             || dir == 3 && bx == 0
             || dir == 4 && bx == N - 1) {
                continue;
            }
            dice.roll(dir);
            int ax = dice.x;
            int ay = dice.y;
            if (map[ax][ay] == 0) {
                map[ax][ay] = dice.bottom;
            } else {
                dice.bottom = map[ax][ay];
                map[ax][ay] = 0;
            }
            answer.append(dice.top).append("\n");
        }

        System.out.println(answer);
    }
}
