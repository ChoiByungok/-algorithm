package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 18808 스티커 붙이기 (Gold3)
 * 전형적인 머리로는 이해가 가는데 뇌버깅이 되지 않아서 한참 걸렸던 문제
 * 범위 안에 스티커들을 싹다 비교하고 없으면 옆으로 뒤집어서 비교하고
 * 그러다 안되면 스킵하고 그렇게 스티커를 붙힌 후 면적 계산하는 문제인데
 * 인덱스 잘라내고 확인하고 이러한 과정이 너무 헷갈림 진짜 너무 헷갈림
 * 공책에 적어가면서 어떻게 인덱스를 잘라야 할지
 * 어떻게 해야 인덱스를 벗어나지 않을지 적으면서 풀어나갔는데도 진짜 너무 헷갈림
 * 디버깅을 해도 어디서 틀린지 찾을수가 없어서 결국 테스트 케이스 클래스 하나 따로 만들어서
 * 계속 출력해가면서 어디가 틀렸는지 찾아가면서 풀었음
 * 알고보니 전체 맵 인덱스랑 스티커 인덱스랑 숫자가 같이 올라야 하는데 따로 오르니깐 안되는거였음
 */
public class Solution157 {
    static boolean[][] map;
    static int N;
    static int M;
    static List<boolean[][]> stickers = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        int answer = 0;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        map = new boolean[N][M];
        int K = Integer.parseInt(tokenizer.nextToken());

        for (int i = 0; i < K; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            int n = Integer.parseInt(tokenizer.nextToken());
            int m = Integer.parseInt(tokenizer.nextToken());
            boolean[][] sticker = new boolean[n][m];
            for (int j = 0; j < n; j++) {
                tokenizer = new StringTokenizer(bufferedReader.readLine());
                for (int k = 0; k < m; k++) {
                    sticker[j][k] = tokenizer.nextToken().equals("1");
                }
            }
            stickers.add(sticker);
        }

        for (boolean[][] sticker : stickers) {
            int count = 0;
            label:
            while (count <= 3) {
                int row = sticker.length;
                int col = sticker[0].length;
                if (N >= row && M >= col) {
                    for (int i = 0; i + row <= N; i++) {
                        for (int j = 0; j + col <= M; j++) {
                            if (check(i, j, sticker)) {
                                stick(i, j, sticker);
                                break label;
                            }
                        }
                    }
                }
                count++;
                sticker = spin(sticker);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j]) {
                    answer++;
                }
            }
        }
        System.out.println(answer);
    }

    static boolean check(int startRow, int startCol, boolean[][] sticker) {
        int endRow = startRow + sticker.length;
        int endCol = startCol + sticker[0].length;
        int row = 0;
        for (int i = startRow; i < endRow; i++) {
            int col = 0;
            for (int j = startCol; j < endCol; j++, col++) {
                if (map[i][j] && sticker[row][col]) {
                    return false;
                }
            }
            row++;
        }

        return true;
    }

    private static void stick(int startRow, int startCol, boolean[][] sticker) {
        int endRow = startRow + sticker.length;
        int endCol = startCol + sticker[0].length;
        int row = 0;
        for (int i = startRow; i < endRow; i++) {
            int col = 0;
            for (int j = startCol; j < endCol; j++, col++) {
                if (sticker[row][col]) {
                    map[i][j] = true;
                }
            }
            row++;
        }
    }

    private static boolean[][] spin(boolean[][] sticker) {
        int row = sticker.length;
        int col = sticker[0].length;
        boolean[][] newSticker = new boolean[col][row];
        for (int i = 0; i < newSticker.length; i++) {
            int rowIndex = row - 1;
            for (int j = 0; j < newSticker[i].length; j++) {
                newSticker[i][j] = sticker[rowIndex--][i];
            }
        }
        return newSticker;
    }

    static void print(boolean[][] map) {
        for (boolean[] booleans : map) {
            for (boolean aBoolean : booleans) {
                System.out.print(aBoolean ? "■ " : "□ ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
