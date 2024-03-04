package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 21610 마법사 상어와 비바라기 (Gold5)
 * 구현 시뮬레이션 카테고리의 알고리즘들은 언제나 그러하듯이
 * 복잡한 알고리즘 기법은 필요하지 않지만 얼마나 가독성이 있느냐
 * 효율이 좋으냐 이러한것들이 관건인거같다 문제 자체는 그냥 하라는대로
 * 풀면 되긴 하는데 나같은 경우는 구름이 0행이나 0열에서 맵 밖으로 벗어났을때
 * N열이나 N행으로 이동시키는걸 반복문을 사용하여 무식하게 처리하였지만
 * 아마 나머지 연산같은것을 사용하였으면 좀더 효율적인 코드가 나왔지 않았나 싶다.
 * 그리고 대각선 카운트 세는 부분도 배열을 이용하였으면 좀더 깔끔하게 가독성 있는 코드로 작성되었을거같다.
 */
public class Solution148 {
    static int N;
    static int[][] map;
    static List<int[]> cloud = new ArrayList<>();
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int answer = 0;
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }
        cloud.add(new int[]{N - 2, 0});
        cloud.add(new int[]{N - 2, 1});
        cloud.add(new int[]{N - 1, 0});
        cloud.add(new int[]{N - 1, 1});

        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            int d = Integer.parseInt(tokenizer.nextToken());
            int s = Integer.parseInt(tokenizer.nextToken());
            visited = new boolean[N][N];
            switch (d) {
                case 1:
                    for (int[] ints : cloud) {
                        int y = ints[1];
                        for (int j = 0; j < s; j++) {
                            y--;
                            if (y == -1) {
                                y = N - 1;
                            }
                        }
                        ints[1] = y;
                    }
                    break;
                case 2:
                    for (int[] ints : cloud) { //구름 이동
                        int x = ints[0];
                        int y = ints[1];
                        for (int j = 0; j < s; j++) {
                            x--;
                            y--;
                            if (x == -1) {
                                x = N - 1;
                            }

                            if (y == -1) {
                                y = N - 1;
                            }
                        }
                        ints[0] = x;
                        ints[1] = y;
                    }
                    break;
                case 3:
                    for (int[] ints : cloud) {
                        int x = ints[0];
                        for (int j = 0; j < s; j++) {
                            x--;
                            if (x == -1) {
                                x = N - 1;
                            }
                        }
                        ints[0] = x;
                    }
                    break;
                case 4:
                    for (int[] ints : cloud) {
                        int x = ints[0];
                        int y = ints[1];
                        for (int j = 0; j < s; j++) {
                            x--;
                            y++;
                            if (x == -1) {
                                x = N - 1;
                            }

                            if (y == N) {
                                y = 0;
                            }
                        }
                        ints[0] = x;
                        ints[1] = y;
                    }
                    break;
                case 5:
                    for (int[] ints : cloud) {
                        int y = ints[1];
                        for (int j = 0; j < s; j++) {
                            y++;
                            if (y == N) {
                                y = 0;
                            }
                        }
                        ints[1] = y;
                    }
                    break;
                case 6:
                    for (int[] ints : cloud) {
                        int x = ints[0];
                        int y = ints[1];
                        for (int j = 0; j < s; j++) {
                            x++;
                            y++;
                            if (x == N) {
                                x = 0;
                            }

                            if (y == N) {
                                y = 0;
                            }
                        }
                        ints[0] = x;
                        ints[1] = y;
                    }
                    break;
                case 7:
                    for (int[] ints : cloud) {
                        int x = ints[0];
                        for (int j = 0; j < s; j++) {
                            x++;
                            if (x == N) {
                                x = 0;
                            }
                        }
                        ints[0] = x;
                    }
                    break;
                case 8:
                    for (int[] ints : cloud) {
                        int x = ints[0];
                        int y = ints[1];
                        for (int j = 0; j < s; j++) {
                            x++;
                            y--;
                            if (x == N) {
                                x = 0;
                            }

                            if (y == -1) {
                                y = N - 1;
                            }
                        }
                        ints[0] = x;
                        ints[1] = y;
                    }
                    break;
            }
            cloud = step();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                answer += map[i][j];
            }
        }
        System.out.println(answer);
    }

    private static List<int[]> step() {
        List<int[]> newCloud = new ArrayList<>();
        for (int[] ints : cloud) { //비 내리고 구름 사라짐
            map[ints[0]][ints[1]]++;
            visited[ints[0]][ints[1]] = true;
        }

        for (int[] ints : cloud) { //대각선 4방향 카운트 세서 물채움
            int count = 0;
            int x = ints[0];
            int y = ints[1];
            if (x - 1 >= 0 && y - 1 >= 0 && map[x - 1][y - 1] != 0) {
                count++;
            }

            if (x - 1 >= 0 && y + 1 < N && map[x - 1][y + 1] != 0) {
                count++;
            }

            if (x + 1 < N && y - 1 >= 0 && map[x + 1][y - 1] != 0) {
                count++;
            }

            if (x + 1 < N && y + 1 < N && map[x + 1][y + 1] != 0) {
                count++;
            }
            map[x][y] += count;
        }

        for (int j = 0; j < N; j++) { //새 구름 생성
            for (int k = 0; k < N; k++) {
                if (map[j][k] >= 2 && !visited[j][k]) {
                    map[j][k] -= 2;
                    newCloud.add(new int[]{j, k});
                }
            }
        }

        return newCloud;
    }
}
