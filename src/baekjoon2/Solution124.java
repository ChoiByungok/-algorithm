package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 14719 빗물 (Gold5)
 * 4 8
 * 3 1 2 3 4 1 1 2 를 입력받고 인덱스값에 맞추어 boolean형 2차원배열을 초기화하면 이런 모습이 된다.
 * 1□□□□■□□□
 * 2■□□■■□□□
 * 3■□■■■□□■
 * 4■■■■■■■■
 * 빈칸은 false 벽은 true이다
 * 여기까지 했으면 거의다 한거다.
 * 1행부터 빗물이 고일만한 칸을 계산하면 되는데
 * 나의 경우는 벽을만나면 카운트를 증가시키기 시작하고 그 다음 벽을 만나면 증가시킨 카운트를 최종결과에 누적합 시켜준다.
 * 벽을 만나지 않는다면 카운트는 그냥 날아가버림
 * 예를 들어 위의 예제 2행에서는 첫번째 열이 벽이다 그러므로 그 다음 열부터 카운트를 증가시킨다.
 * 4번째 열에서 벽을 만났다 그러므로 증가했던 카운트2를 누적합 시켜주고 다시 카운트를 0으로 초기화 시켜준다.
 * 그다음 5열도 벽이지만 카운트가 0이므로 그냥 넘어간다. 6 7 8 열은 벽이 아니지만 그다음 벽이 나오지 않았으므로
 * 증가했던 카운트3은 그냥 날려버린다. 결국 2행의 빗물이 고이는 양은 2이다.
 * 그렇게 3행도 진행시키면 3이라는 값이나오고
 * 4행은 모두 벽이므로 계산되지 않는다
 * 그리하여 최종답은 5가 나오게 되는 것이다.
 */
public class Solution124 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] hw = bufferedReader.readLine().split(" ");
        int H = Integer.parseInt(hw[0]);
        int W = Integer.parseInt(hw[1]);
        int answer = 0;
        boolean[][] map = new boolean[H][W];
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());

        for (int i = 0; i < W; i++) {
            int token = Integer.parseInt(tokenizer.nextToken());
            int index = H - 1;
            while (token --> 0) {
                map[index--][i] = true;
            }
        }

        for (int i = 0; i < H; i++) {
            boolean wall = false;
            int count = 0;
            for (int j = 0; j < W; j++) {
                if (map[i][j]) {
                    if (!wall) {
                        wall = true;
                    } else {
                        if (count != 0) {
                            answer += count;
                            count = 0;
                        }
                    }
                } else {
                    if (wall) {
                        count++;
                    }
                }
            }
        }
        System.out.println(answer);
    }
}
