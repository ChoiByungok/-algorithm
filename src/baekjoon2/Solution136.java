package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 2578 빙고 (Silver4)
 * 뭔가 편리한 방법이 있을거같다고 생각했지만
 * 결국 노가다로 해결한 문제
 * 입력받은 값을 boolean 타입으로 방문처리하고
 * 그 후 각각의행 각각의열 우측하단대각선 우측상단대각선 을 모두 일일이 학인한 후
 * 5개가 모두 방문이 되어 있다면 빙고카운트를 늘려 빙고카운트가 3이상이 되면 반복을 종료하는 식으로 해결하였다.
 * 이 때 주의 해야할 점은 빙고카운트가 2에서 한번에 4로 점프할 수있으니
 * 반복문 탈출 조건을 bingoCount == 3 이 아닌 bingoCount >= 3 으로 해야 한다는 것이다.
 */
public class Solution136 {
    static final int N = 5;

    static class Bingo {
        int num;
        boolean erase;

        public Bingo(int num, boolean erase) {
            this.num = num;
            this.erase = erase;
        }
    }
    static Bingo[][] map = new Bingo[N][N];
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;
        int answer = 0;
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = new Bingo(Integer.parseInt(tokenizer.nextToken()), false);
            }
        }

        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < N; j++) {
                list.add(Integer.parseInt(tokenizer.nextToken()));
            }
        }

        for (Integer num : list) {
            answer++;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j].num == num) {
                        map[i][j].erase = true;
                    }
                }
            }
            int bingoCount = bingoCount();
            if (bingoCount >= 3) {
                break;
            }
        }
        System.out.println(answer);
    }

    static int bingoCount() {
        int bingoCount = 0;
        for (int i = 0; i < N; i++) {
            int count = 0;
            for (int j = 0; j < N; j++) {
                if (map[i][j].erase) {
                    count++;
                }
            }
            if (count == N) {
                bingoCount++;
            }
        }

        for (int i = 0; i < N; i++) {
            int count = 0;
            for (int j = 0; j < N; j++) {
                if (map[j][i].erase) {
                    count++;
                }
            }
            if (count == N) {
                bingoCount++;
            }
        }

        int count = 0;
        for (int i = 0; i < N; i++) {
            if (map[i][i].erase) {
                count++;
            }
        }
        if (count == N) {
            bingoCount++;
        }

        count = 0;
        for (int i = 0; i < N; i++) {
            if (map[N - 1 - i][i].erase) {
                count++;
            }
        }
        if (count == N) {
            bingoCount++;
        }

        return bingoCount;
    }
}
