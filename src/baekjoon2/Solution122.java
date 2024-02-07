package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 17144 미세먼지 안녕! (Gold4)
 * 겉보기엔 그래프 탐색같은 문제이지만 사실은 엄청난 노가다가 필요한 문제
 * 우선 먼지가 확산 되려면 해당 칸의 먼지가 5이상이어야 한다.
 * 5미만은 5로 나누어 봤자 0이기 때문이다.
 * 그리고 상하좌우 어디로 확산할 수 있을지 판별해서 카운트를 센다
 * 예를들어 상 하 좌 이렇게 3방향으로만 확산할 수 있다면
 * 먼지 - (먼지 / 5) * (카운트) 공식을 이용하여 확산 가능한 방향으로 먼지를 확산시킨다.
 * 이렇게 온 맵을 전부 확산 시킨 후
 * 이제 공기청정기를 작동해야 하는데 공기청정기 기준으로 위에는 시계방향 아래로는 반시계방향으로 먼지들을 회전시켜준다.
 * 그렇게 회전한 먼지는 공기청정기를 만났을때 소멸한다. 그리고 공기청정기는 깨끗한 공기만 분출한다.
 * 내가 좀더 똑똑했더라면 스마트하게 코드를 작성했겠지만 우선 맞춘것만으로 만족해야 할거같다.
 * 그렇게 확산 + 작동 한번의 사이클이 1초이다. 이것을 주어진 T 만큼
 * 반복한 후 최종적으로 맵에 남은 먼지들의 총량을 출력시켜 주면 된다.
 */
public class Solution122 {
    static int R;
    static int C;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] rct = bufferedReader.readLine().split(" ");
        int answer = 0;
        R = Integer.parseInt(rct[0]);
        C = Integer.parseInt(rct[1]);
        int T = Integer.parseInt(rct[2]);
        map = new int[R][C];
        List<Integer> airCleaner = new ArrayList<>();

        for (int i = 0; i < R; i++) {
            StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
                if (map[i][j] == -1) {
                    airCleaner.add(i);
                }
            }
        }

        Integer clockWise = airCleaner.get(0);
        Integer counterClockWise = airCleaner.get(1);

        for (int i = 0; i < T; i++) {
            int[][] newMap = new int[R][C];
            dustSpread(newMap);
            rotateClockWise(clockWise, newMap);
            rotateCounterClockWise(counterClockWise, newMap);
            map = newMap;
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == -1) {
                    continue;
                }
                answer += map[i][j];
            }
        }

        System.out.println(answer);
    }

    private static void dustSpread(int[][] newMap) {
        for (int j = 0; j < R; j++) {
            for (int k = 0; k < C; k++) {
                if (map[j][k] >= 5) {
                    int count = 0;
                    int dust = map[j][k];
                    boolean up = false;
                    boolean down = false;
                    boolean right = false;
                    boolean left = false;
                    if (j + 1 < R && map[j + 1][k] != -1) {
                        count++;
                        down = true;
                    }
                    
                    if (j - 1 >= 0 && map[j - 1][k] != -1) {
                        count++;
                        up = true;
                    }
                    
                    if (k + 1 < C && map[j][k + 1] != -1) {
                        count++;
                        right = true;
                    }
                    
                    if (k - 1 >= 0 && map[j][k - 1] != -1) {
                        count++;
                        left = true;
                    }

                    int diffusion = dust / 5;
                    dust = dust - (diffusion * count);

                    newMap[j][k] += dust;

                    if (up) {
                        newMap[j - 1][k] += diffusion;
                    }

                    if (down) {
                        newMap[j + 1][k] += diffusion;
                    }

                    if (right) {
                        newMap[j][k + 1] += diffusion;
                    }

                    if (left) {
                        newMap[j][k - 1] += diffusion;
                    }
                } else {
                    newMap[j][k] += map[j][k];
                }
            }
        }
    }

    private static void rotateCounterClockWise(Integer counterClockWise, int[][] newMap) {
        int start2 = counterClockWise + 1;
        newMap[start2][0] = 0;
        for (int j = start2 + 1; j < R; j++) {
            newMap[j - 1][0] = newMap[j][0];
        }

        for (int j = 1; j < C; j++) {
            newMap[R - 1][j - 1] = newMap[R - 1][j];
        }

        for (int j = R - 2; j >= counterClockWise; j--) {
            newMap[j + 1][C - 1] = newMap[j][C - 1];
        }

        for (int j = C - 2; j >= 1; j--) {
            newMap[counterClockWise][j + 1] = newMap[counterClockWise][j];
        }
        newMap[counterClockWise][1] = 0;
    }

    private static void rotateClockWise(Integer clockWise, int[][] newMap) {
        int start1 = clockWise - 1;
        newMap[start1][0] = 0;
        for (int j = start1 - 1; j >= 0; j--) {
            newMap[j + 1][0] = newMap[j][0];
        }

        for (int j = 1; j < C; j++) {
            newMap[0][j - 1] = newMap[0][j];
        }

        for (int j = 1; j <= clockWise; j++) {
            newMap[j - 1][C - 1] = newMap[j][C - 1];

        }

        for (int j = C - 2; j >= 1; j--) {
            newMap[clockWise][j + 1] = newMap[clockWise][j];
        }
        newMap[clockWise][1] = 0;
    }
}
