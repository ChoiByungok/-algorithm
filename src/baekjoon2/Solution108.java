package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * 3190 뱀 (Gold4)
 * 보드의 칸이 0이면 갈 수 있는 위치
 * 보드의 칸이 1이면 사과의 위치
 * 보드의 칸이 2 이면 갈 수 없는 위치
 * 뱀이 가는 위치에 사과가 존재한다면 큐에 현재 머리의 좌표를 넣어준다
 * 뱀이 가는 위치에 사과가 없다면  큐에 현재 머리 좌표를 넣어주고 꼬리의 위치정보를 제거해준다.
 * 주의해야 할 점은 뱀이 보드를 돌다가 자기 몸에 닿으면 게임오버시켜야 하는데
 * 뱀의 머리 좌표를 넣을때 해당 보드에 좌표값을 2로 바꿔주고 꼬리부분을 제거할땐 다시 0으로 바꿔주면 된다.
 * 뱀의 방향정보 1(오른쪽), 2(왼쪽), 3(위쪽) ,4(아래쪽) 기본값은 1로 설정하였다.
 * 입력받은 방향전환 정보는 맵에 담아놓고 현재 시간(key)이 맵에 있을때
 * 해당 키의 값을 거내어 D 인지 L인지 구분하고 방향을 재조정 해주었다.
 * 그러다 뱀의 머리가 자신의 몸통에 닿거나(다음에 갈 좌표의 값이 2) 보드의 범위(0 혹은 N)를 벗어나면 break 시켜주면 된다.
 * 그래프 탐색의 느낌이 나는 문제였지만 알고보니 자료구조를 어떻게 활용하는지가 관건인 문제였다.
 */
public class Solution108 {
    static int[][] board;
    static int N;
    static Map<Integer, String> input = new HashMap<>();
    static Queue<int[]> snake = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int second = 0;
        N = Integer.parseInt(bufferedReader.readLine());
        board = new int[N + 1][N + 1];
        int K = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < K; i++) {
            String[] apple = bufferedReader.readLine().split(" ");
            int x = Integer.parseInt(apple[0]);
            int y = Integer.parseInt(apple[1]);
            board[x][y] = 1; //해당 좌표를 1로 표시함으로써 사과의 위치라는것을 뜻함
        }

        int L = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < L; i++) {
            String[] xc = bufferedReader.readLine().split(" ");
            int X = Integer.parseInt(xc[0]);
            String C = xc[1];
            input.put(X, C);
        }

        board[1][1] = 2;
        int nowX = 1;
        int nowY = 1;
        int direction = 1;
        snake.offer(new int[]{nowX, nowY}); //초기값 설정

        while (true) {
            second++;
            switch (direction) {
                case 1 : nowY++;
                break;
                case 2 : nowY--;
                break;
                case 3 : nowX--;
                break;
                case 4 : nowX++;
                break;
            }
            if (nowX > N || nowX < 1 || nowY > N || nowY < 1 || board[nowX][nowY] == 2) {
                break;
            }

            if (board[nowX][nowY] == 1) { //가는 방향에 사과가 있으면
                board[nowX][nowY] = 0; //사과를 지워줌
            } else {
                int[] poll = snake.poll(); //사과가 없다면 꼬리의 정보를 제거해주고
                board[poll[0]][poll[1]] = 0; //해당 위치를 갈 수 있는 위치로 바꿔줌
            }
            snake.offer(new int[]{nowX, nowY});
            board[nowX][nowY] = 2;

            if (input.containsKey(second)) {
                String dir = input.get(second);
                if (dir.equals("D")) {
                    if (direction == 1) {
                        direction = 4;
                    } else if (direction == 2) {
                        direction = 3;
                    } else if (direction == 3) {
                        direction = 1;
                    } else {
                        direction = 2;
                    }
                } else {
                    if (direction == 1) {
                        direction = 3;
                    } else if (direction == 2) {
                        direction = 4;
                    } else if (direction == 3) {
                        direction = 2;
                    } else {
                        direction = 1;
                    }
                }
            }
        }
        System.out.println(second);
    }
}
