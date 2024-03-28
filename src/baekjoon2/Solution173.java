package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 1913 달팽이 (Silver3)
 * 홀수인 수 N이 주어지고 N*N의 이차원배열을 만들기때문에
 * 정 가운데 좌표부터 시작할 수 있다.
 * 그렇기 때문에 반복문은 N*N 만큼만 실행하면된다.
 * 정 가운데 좌표부터 시작하여 나선형 모양으로 이차원 배열의 값을 채워야 하는데
 * 자세히보면 규칙이 존재한다 처음에는 1칸 정해진 방향으로 움직이고 시계방향으로 회전한다.
 * 회전을 2번 진행하면 회전 없이 한번에 진행할 수 있는 칸이 1증가한다.
 * 즉 회전을 두번 진행하면 한번에 움직일 수 있는 거리가 1칸 늘어나게 되는것이다.
 * 규칙을 세우고 그대로 코드에 적용하니 풀리는 문제였다.
 * 주어진 숫자 N2가 어디 좌표인지 구하는것은 간단하다.
 * 반복문을 1부터 N*N 까지 진행하는데 그때 반복하는 숫자가 N2가 되었을때
 * 현재 좌표를 저장해놓으면 된다.
 */
public class Solution173 {
    static StringBuilder answer = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        int N2 = Integer.parseInt(bufferedReader.readLine());
        int[][] map = new int[N][N];
        int repeat = N * N;
        int startX = N / 2;
        int startY = N / 2;
        int dir = 1;
        int distanceCount = 0;
        int spinCount = 0;
        int distance = 1;
        int row = 0;
        int col = 0;
        for (int i = 1; i <= repeat; i++) {
            map[startX][startY] = i;
            if (i == N2) {
                row = startX + 1;
                col = startY + 1;
            }
            switch (dir) {
                case 1:
                    startX--;
                    break;
                case 2:
                    startY++;
                    break;
                case 3:
                    startX++;
                    break;
                case 4:
                    startY--;
                    break;
            }
            distanceCount++;
            if (distanceCount == distance) {
                if (dir == 4) {
                    dir = 1;
                } else {
                    dir++;
                }
                spinCount++;
                distanceCount = 0;
            }
            if (spinCount == 2) {
                distance++;
                spinCount = 0;
            }
        }
        print(map, row, col);
    }

    static void print(int[][] map, int row, int col) {
        for (int[] rows : map) {
            for (int cols : rows) {
                answer.append(cols).append(" ");
            }
            answer.append("\n");
        }
        answer.append(row).append(" ").append(col);
        System.out.println(answer);
    }
}
