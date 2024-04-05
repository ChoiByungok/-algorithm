package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 1388 바닥 장식 (Silver4)
 * 바닥장식은 두가지 패턴만 존재한다.
 * -일때는 같은행에 연속적인 - 가 존재하면 같은 판자이고
 * |일때는 같은 열에 연속적인 | 가 존재하면 같은 판자이다.
 * 나는 주어진 입력값으로 char형 2차원 배열과 방문 표시를 해줄 boolean형 2차원 배열을 만들었다.
 * 그리고 0,0좌표부터 탐색을 시작하는대 해당 좌표에 있는 패턴이
 * - 이면 그 좌표의 행만 비교를한다.
 * | 일시에는 열만 비교를 한다.
 * 그렇게 해서 다른패턴이 나오기 전까지는 방문표시 2차원 배열에 방문을 했다고 체크해주면 되고 다른 패턴이 나오면 반복문을 탈출시켜준다.
 * (0,0)의 좌표 패턴이 - 이고 (0,1) (0,2) 까지 - 이며 0,3 부터 |일때
 * 방문 배열에는 0,0 0,1 0,2 까지 방문했다고 표시시킨다.
 * 그러면 그 다음 탐색은 0,3부터 진행하는 것이다.
 * 그렇게 탐색 메서드가 호출되는 횟수가 곧 나무판자의 갯수이고 호출될때마다 카운트를 증가시켜
 * 호출 카운트를 출력하면 되는 문제이다.
 */
public class Solution181 {
    static int N;
    static int M;
    static char[][] floor;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] split = bufferedReader.readLine().split(" ");
        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);
        floor = new char[N][M];
        visited = new boolean[N][M];
        int board = 0;
        for (int i = 0; i < N; i++) {
            String readLine = bufferedReader.readLine();
            for (int j = 0; j < M; j++) {
                floor[i][j] = readLine.charAt(j);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j]) {
                    visited[i][j] = true;
                    board++;
                    search(i, j);
                }
            }
        }
        System.out.println(board);
    }

    private static void search(int i, int j) {
        char pattern = floor[i][j];

        if (pattern == '-') {
            for (int k = j + 1; k < M; k++) {
                if (floor[i][k] == '|') {
                    break;
                } else {
                    visited[i][k] = true;
                }
            }
        } else {
            for (int k = i + 1; k < N; k++) {
                if (floor[k][j] == '-') {
                    break;
                } else {
                    visited[k][j] = true;
                }
            }
        }
    }
}
