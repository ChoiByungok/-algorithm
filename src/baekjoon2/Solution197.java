package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 16935 배열 돌리기 3 (Gold5)
 * 구현 자체는 하라는대로 하면 되는데 배열의 가로와 세로가 계속 바뀌는걸 안바꿔줌
 * 최초에 입력받은 N M 은 고정되어있고
 * 3 번 4번연산을 진행하게 되면 N M 의 값이 서로 바뀌는데
 * 그걸 안바꾸고 그대로 냅둬서 틀림
 */
public class Solution197 {
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

        tokenizer = new StringTokenizer(bufferedReader.readLine());
        while (R-- > 0) {
            int calc = Integer.parseInt(tokenizer.nextToken());
            map = calc(calc);
        }
        print();

    }

    static int[][] calc(int calc) {
        int[][] temp = null;
        int n = map.length;
        int m = map[0].length;
        switch (calc) {
            case 1:
                temp = new int[n][m];
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        temp[i][j] = map[n - i - 1][j];
                    }
                }
                break;
            case 2:
                temp = new int[n][m];
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        temp[i][j] = map[i][m - j - 1];
                    }
                }
                break;
            case 3:
                temp = new int[m][n];
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < n; j++) {
                        temp[i][j] = map[n - j - 1][i];
                    }
                }
                break;
            case 4:
                temp = new int[m][n];
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < n; j++) {
                        temp[i][j] = map[j][m - i - 1];
                    }
                }
                break;
            case 5:
                temp = new int[n][m];
                for (int i = 0; i < n / 2; i++) {
                    for (int j = 0; j < m / 2; j++) {
                        temp[i][j] = map[i + n / 2][j];
                    }
                }

                for (int i = 0; i < n / 2; i++) {
                    for (int j = m / 2; j < m; j++) {
                        temp[i][j] = map[i][j - m / 2];
                    }
                }

                for (int i = n / 2; i < n; i++) {
                    for (int j = m / 2; j < m; j++) {
                        temp[i][j] = map[i - n / 2][j];
                    }
                }

                for (int i = n / 2; i < n; i++) {
                    for (int j = 0; j < m / 2; j++) {
                        temp[i][j] = map[i][j + m / 2];
                    }
                }
                break;
            case 6:
                temp = new int[n][m];
                for (int i = 0; i < n / 2; i++) {
                    for (int j = 0; j < m / 2; j++) {
                        temp[i][j] = map[i][j + m / 2];
                    }
                }

                for (int i = 0; i < n / 2; i++) {
                    for (int j = m / 2; j < m; j++) {
                        temp[i][j] = map[i + n / 2][j];
                    }
                }

                for (int i = n / 2; i < n; i++) {
                    for (int j = m / 2; j < m; j++) {
                        temp[i][j] = map[i][j - m / 2];
                    }
                }

                for (int i = n / 2; i < n; i++) {
                    for (int j = 0; j < m / 2; j++) {
                        temp[i][j] = map[i - n / 2][j];
                    }
                }
                break;
        }
        return temp;
    }

    static void print() {
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                answer.append(map[i][j]).append(" ");
            }
            answer.append("\n");
        }
        System.out.println(answer);
    }
}
