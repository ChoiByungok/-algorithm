package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 12100 2048 (Easy) (Gold2)
 * 백트래킹을 이용하여 방향의 경우의 수를 전부 완전탐색한 후
 * 매개변수로 배열을 넘기면서 가장 큰 블록의 값을 출력하면 되는 문제
 * 백트래킹 로직짜는거는 할만했는데 오히려 블록을 한쪽으로 모는 로직이 훨씬 어려웠던 문제
 * 결국 남이 푼 코드를 보고 참고를 했다.
 * 이런 구현력을 늘려야 한걸음 더 성장하는데 아직 멀었다.
 */
public class Solution200 {
    static int N;

    static final int COUNT = 5;
    static int[] dir;
    static int answer = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        dir = new int[4];
        for (int i = 0; i < dir.length; i++) {
            dir[i] = i;
        }

        dfs(0, map);
        System.out.println(answer);
    }

    static void dfs(int depth, int[][] map) {
        if (depth == COUNT) {
            answer = Math.max(answer, maxBlock(map));
            return;
        }

        for (int j : dir) {
            dfs(depth + 1, move(j, copy(map)));
        }
    }
    public static int[][] move(int dir, int[][] map) {
        switch(dir) {
            case 0:
                for(int i = 0; i < N; i++) {
                    int index = 0;
                    int block = 0;
                    for(int j = 0; j < N; j++) {
                        if(map[j][i] != 0) {
                            if(block == map[j][i]) {
                                map[index - 1][i] = block * 2;
                                block = 0;
                                map[j][i] = 0;
                            } else {
                                block = map[j][i];
                                map[j][i] = 0;
                                map[index][i] = block;
                                index++;
                            }
                        }
                    }
                }
                break;
            case 1:
                for(int i = 0; i < N; i++) {
                    int index = N - 1;
                    int block = 0;
                    for(int j = N - 1; j >= 0; j--) {
                        if(map[j][i] != 0) {
                            if(block == map[j][i]) {
                                map[index + 1][i] = block * 2;
                                block = 0;
                                map[j][i] = 0;
                            } else {
                                block = map[j][i];
                                map[j][i] = 0;
                                map[index][i] = block;
                                index--;
                            }
                        }
                    }
                }
                break;
            case 2:
                for(int i = 0; i < N; i++) {
                    int index = 0;
                    int block = 0;
                    for(int j = 0; j < N; j++) {
                        if(map[i][j] != 0) {
                            if(block == map[i][j]) {
                                map[i][index - 1] = block * 2;
                                block = 0;
                                map[i][j] = 0;
                            } else {
                                block = map[i][j];
                                map[i][j] = 0;
                                map[i][index] = block;
                                index++;
                            }
                        }
                    }
                }
                break;
            case 3:
                for(int i = 0; i < N; i++) {
                    int index = N - 1;
                    int block = 0;
                    for(int j = N - 1; j >= 0; j--) {
                        if(map[i][j] != 0) {
                            if(block == map[i][j]) {
                                map[i][index + 1] = block * 2;
                                block = 0;
                                map[i][j] = 0;
                            } else {
                                block = map[i][j];
                                map[i][j] = 0;
                                map[i][index] = block;
                                index--;
                            }
                        }
                    }
                }
                break;
        }
        return map;
    }

    static int[][] copy(int[][] map) {
        int[][] copy = new int[N][N];
        for (int i = 0; i < N; i++) {
            System.arraycopy(map[i], 0, copy[i], 0, N);
        }
        return copy;
    }

    static int maxBlock(int[][] map) {
        int block = 2;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                block = Math.max(block, map[i][j]);
            }
        }
        return block;
    }

    static void print(int[][] map) {
        System.out.println();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}
