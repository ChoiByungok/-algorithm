package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 2580 스도쿠 (Gold4)
 * 9 X 9 이차원 행 열 그리고 3 X 3 구역으로 9개로 나눴을 떄
 * 모든 숫자들이 1부터 9까지 중복이 되지 않게 배치하면 된다.
 * 이 문제를 어떻게 백트래킹을 이용하여 구현할 수 있을까
 * 우선 0,0 좌표부터 탐색을 시작한다 해당 칸이 0일시 (0은 비어있는 칸이라고 생각하면 된다.)
 * 그 자리에 들어갈 수 있는 숫자들을 하나씩 넣어본다.
 * 그리고 다음 열로 넘어가는데 거기서 막히면 다시 돌아오는 식으로 탐색을 진행한다.
 * 만약 막히지 않고 마지막 열에 도착했다면 다시 열은처음으로 돌아오고 다음행으로 넘겨주면 된다.
 * 이 문제 또한 혼자서 해결하지 못해 다른사람의 풀이를 보고 해결하였다.
 */
public class Solution2 {
    static int[][] map = new int[9][9];
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 9; i++) {
            StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < 9; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }
        sudoku(0, 0);
    }

    static void sudoku(int x, int y) {
        if (y == 9) { //해당 행의 모든 열의 탐색을 마쳤으면 다음 행으로 넘어간다.
            sudoku(x + 1, 0);
            return;
        }

        if (x == 9) { //모든 행과 열을 탐색했다는 뜻으로 이때 맵을 출력하고 프로그램을 강제로 종료시킨다.
            print(map);
            System.exit(0);
        }

        if (map[x][y] == 0) { //해당 칸이 0이면 탐색을 시작한다.
            for (int i = 1; i <= 9; i++) {
                if (check(x, y, i)) {
                    map[x][y] = i;
                    sudoku(x, y + 1);
                }
            }
            map[x][y] = 0; //다음 열을 탐색했는데 1~9까지 모든 숫자가 들어갈 수 없으면 반환되어 다시 여기로 와서
            //해당 칸을 0으로 초기화 시킨뒤 그 다음 숫자부터 탐색을 다시 진행하게 된다.
            return;
        }
        sudoku(x, y + 1); //해당 칸이 0이 아닐시 다음 열로 넘어간다.
    }

    static boolean check(int x, int y, int num) {
        for (int i = 0; i < 9; i++) {
            if (map[x][i] == num) {
                return false;
            }
        }

        for (int i = 0; i < 9; i++) {
            if (map[i][y] == num) {
                return false;
            }
        }

        int startX = (x / 3) * 3;
        int startY = (y / 3) * 3;
        int endX = startX + 3;
        int endY = startY + 3;

        for (int i = startX; i < endX; i++) {
            for (int j = startY; j < endY; j++) {
                if (map[i][j] == num) {
                    return false;
                }
            }
        }

        return true;
    }
    static void print(int[][] map) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                builder.append(map[i][j]).append(" ");
            }
            builder.append("\n");
        }
        System.out.println(builder);
    }
}
