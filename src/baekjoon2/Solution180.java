package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 1051 숫자 정사각형 (Silver3)
 * 완전탐색 문제로 해당 좌표부터 오른쪽, 아래쪽, 우측하단 대각선을 1칸씩 증가시키면서 좌표값과 같은지 비교하면된다
 * 예를 들어 0,0 의 값이 1일때
 * (0,1) (1,0) (1,1) 의 좌표값이 모두 1이면 정사각형이 되고 넓이는 4가 되는것이다.
 * 근데 (0,2) (2,0) (2,2) 의 좌표값도 1이면 넓이를 9로 갱신시켜주면된다.
 * 그렇게 해당 좌표부터 맵의 범위를 벗어날때까지 1씩 증가시키며 조건이 충족되면 최대 넓이를 갱신하고 아니면 1을 반환하면 된다.
 * 나는 이문제를 3번만에 통과시켰는데 정사각형이 아닌 직사각형의 넓이를 계산해서 틀렸기 때문이다.
 */
public class Solution180 {
    static int[][] map;
    static int N;
    static int M;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] split = bufferedReader.readLine().split(" ");
        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);
        int answer = 0;
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String readLine = bufferedReader.readLine();
            for (int j = 0; j < readLine.length(); j++) {
                map[i][j] = readLine.charAt(j) - '0';
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                answer = Math.max(answer, search(i, j));
            }
        }
        System.out.println(answer);
    }

    private static int search(int x, int y) {
        int num = map[x][y];
        int index = 1;
        int area = 1;
        while (x + index < N && y + index < M) {
            if (map[x][y + index] == num && map[x + index][y] == num && map[x + index][y + index] == num) {
                area = (index + 1) * (index + 1);
            }
            index++;
        }

        return area;
    }
}
