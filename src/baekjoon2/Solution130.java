package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 23299 주사위 굴리기 2 (Gold3)
 * 이전에 풀었던 주사위 굴리기에 그래프 탐색까지 더한 문제
 * 주사위 라는 객체를 하나 생성한다 주사위는 위 아래 앞 뒤 오른쪽 왼쪽의 값을 가지고 있고
 * 초기값은 정해져있다. 그리고 주사위 객체는 현재 자신의 좌표값도 가지고 있다.
 * 초기에는 0,0 에 위치해있고 방향은 동쪽이라고 되어있다.
 * 나는 방향에 임의의 수를 대입하였다 북 = 0 동 = 1 남 = 2 서 = 3
 * 처음에 동쪽으로 한번 굴리면 주사위 내부의 좌표는 0,1 이되며
 * 기존의 왼쪽이었던 값이 주사위의 위가되고 위는 오른쪽이 되며 오른쪽은 바닥이 되고 바닥은 왼쪽이 된다.
 * 이런식으로 어떤 방향이냐에 따라서 주사위 객체 내부의 값들을 계속 조정해 나가면 된다.
 * 주사위를 굴린 뒤 현재 주사위가 있는 위치에 쓰여져 있는 맵의 값과 같은 값이 근처에 있는지 그래프 탐색을 실시한다.
 * 0,1의 값은 1이고 근처에 값이 끊기지 않고 계속 1인 칸이 4칸이 있으므로 1 * 4 = 4 를 누적합 해준다.
 * 그리고 그 이후에 방향을 조정해주어야 하는데 현재 주사위 바닥의 값과 주사위가 놓여져 있는 좌표의 값과 비교하여
 * 크면 시계방향 작으면 반시계방향으로 조정한다. 조정 한 후 해당칸이 맵의 끝칸이면 다시 반대방향으로 조정하면 되는 문제이다.
 * 하라는대로 그대로 구현만 하면 되는 문제이긴하나 그래프 탐색 등 여러가지 개념을 알고 있어야 풀 수 있는 문제였다.
 */
public class Solution130 {
    static class Dice {
        int top;
        int bottom;
        int right;
        int left;
        int front;
        int back;
        int x;
        int y;
        public Dice() {
            this.top = 1;
            this.bottom = 6;
            this.right = 3;
            this.left = 4;
            this.front = 5;
            this.back = 2;
            this.x = 0;
            this.y = 0;
        }

        public void roll(int dir) {
            int tempTop;
            int tempBottom;
            int tempRight;
            int tempLeft;
            int tempFront;
            int tempBack;
            switch (dir) {
                case 0:
                    tempTop = top;
                    tempBack = back;
                    tempBottom = bottom;
                    tempFront = front;
                    top = tempFront;
                    back = tempTop;
                    bottom = tempBack;
                    front = tempBottom;
                    x--;
                    break;
                case 1:
                    tempTop = top;
                    tempRight = right;
                    tempBottom = bottom;
                    tempLeft = left;
                    top = tempLeft;
                    right = tempTop;
                    bottom = tempRight;
                    left = tempBottom;
                    y++;
                    break;
                case 2:
                    tempTop = top;
                    tempBack = back;
                    tempBottom = bottom;
                    tempFront = front;
                    top = tempBack;
                    back = tempBottom;
                    bottom = tempFront;
                    front = tempTop;
                    x++;
                    break;
                case 3:
                    tempTop = top;
                    tempRight = right;
                    tempBottom = bottom;
                    tempLeft = left;
                    top = tempRight;
                    right = tempBottom;
                    bottom = tempLeft;
                    left = tempTop;
                    y--;
                    break;
            }
        }
    }
    static int N;
    static int M;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        Dice dice = new Dice();
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        int answer = 0;
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        int k = Integer.parseInt(tokenizer.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        int dir = 1;
        while (k-- > 0) {
            dice.roll(dir);
            answer += bfs(dice.x, dice.y);
            if (dice.bottom > map[dice.x][dice.y]) {
                if (dir == 3) {
                    dir = 0;
                } else {
                    dir++;
                }
            } else if (dice.bottom < map[dice.x][dice.y]) {
                if (dir == 0) {
                    dir = 3;
                } else {
                    dir--;
                }
            }
            if (dice.x == 0 && dir == 0) {
                dir = 2;
            }
            if (dice.x == N - 1 && dir == 2) {
                dir = 0;
            }
            if (dice.y == 0 && dir == 3) {
                dir = 1;
            }
            if (dice.y == M - 1 && dir == 1) {
                dir = 3;
            }
        }

        System.out.println(answer);
    }
    
    static int bfs(int row, int col) {
        int count = 1;
        int space = map[row][col];
        boolean[][] visited = new boolean[N][M];
        visited[row][col] = true;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{row, col});
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int x = poll[0];
            int y = poll[1];

            if (x + 1 < N && map[x + 1][y] == space && !visited[x + 1][y]) {
                queue.add(new int[]{x + 1, y});
                visited[x + 1][y] = true;
                count++;
            }

            if (x - 1 >= 0 && map[x - 1][y] == space && !visited[x - 1][y]) {
                queue.add(new int[]{x - 1, y});
                visited[x - 1][y] = true;
                count++;
            }

            if (y + 1 < M && map[x][y + 1] == space && !visited[x][y + 1]) {
                queue.add(new int[] {x, y + 1});
                visited[x][y + 1] = true;
                count++;
            }

            if (y - 1 >= 0 && map[x][y - 1] == space && !visited[x][y - 1]) {
                queue.add(new int[] {x, y - 1});
                visited[x][y - 1] = true;
                count++;
            }
        }
        return count * space;
    }
}
