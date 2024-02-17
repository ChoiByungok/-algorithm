package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 16926 배열돌리기1 (Silver1)
 * N * M 크기의 2차원 배열이 입력으로 주어지면 바깥쪽 테두리 부터 안쪽 테두리까지 반시계방향으로 회전시켜야 한다.
 * 내부반복문의 탈출 조건을 어떻게 해야 할지 고민하다가
 * rowStart = 0, rowEnd = N - 1, colStart = 0, colEnd = M - 1 의 초기값을 갖는 변수를 두어
 * 반복문 내부에서 start 들은 1씩 증가시켜주고 end 들은 1씩 감소 시켜준다
 * 그러다 둘중 하나라도 start 가 end 보다 커진다면 반복문을 종료시킨다. 그럼 딱 알맞게 회전시킬 수 있다.
 * 그 이후에는 start 와 end 변수들을 이용하여 반시계방향으로 한칸씩 당겨주면 된다.
 * 나는 새로운 배열을 만들어 거기다가 값을 할당하는 방식으로 문제를 해결하였다.
 */
public class Solution132 {
    static int N;
    static int M;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        int R = Integer.parseInt(tokenizer.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        while (R-- > 0) {
            int rowStart = 0;
            int rowEnd = N - 1;
            int colStart = 0;
            int colEnd = M - 1;
            int[][] newMap = new int[N][M];

            while (rowStart < rowEnd && colStart < colEnd) {

                for (int i = colStart; i < colEnd; i++) {
                    newMap[rowStart][i] = map[rowStart][i + 1];
                }

                for (int i = rowStart; i < rowEnd; i++) {
                    newMap[i][colEnd] = map[i + 1][colEnd];
                }

                for (int i = colEnd; i > colStart; i--) {
                    newMap[rowEnd][i] = map[rowEnd][i - 1];
                }

                for (int i = rowEnd; i > rowStart; i--) {
                    newMap[i][colStart] = map[i - 1][colStart];
                }
                rowStart++;
                rowEnd--;
                colStart++;
                colEnd--;
            }
            map = newMap;
        }
        System.out.println(answer());
    }
    static StringBuilder answer() {
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                answer.append(map[i][j]).append(" ");
            }
            answer.append("\n");
        }
        return answer;
    }
}
