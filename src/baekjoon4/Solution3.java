package baekjoon4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 12908 텔레포트 (Gold5)
 * 출발지점 도착지점과 텔레포트 지점 각각을 노드로 판단하여
 * 출발지점은 0번 도착지점은 7번
 * 나머지 텔레포트 지점은 각각 1 2 3 4 5 6 번 노드로 지정한다.
 * 이렇게 노드로 판별하여 2차원 배열에 넣어놓으면 8X8 배열이 된다.
 * 다른 사람들은 어떻게 했는지 모르지만 노가다로 일일히 거리계산을 하여 초기화를 시켜준다음
 * 플로이드 와셜 알고리즘을 실행 시켜준뒤 0번 에서 7번 노드로 가는 최단거리를 출력시켜주면 된다.
 * 정말 내가 봐도 꼴뵈기 싫은 코드지만 그래도 풀었다는것에 의의를 두어야겠다.
 */
public class Solution3 {
    static int xs, ys, xe, ye;
    static long[][] distance;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        xs = Integer.parseInt(tokenizer.nextToken());
        ys = Integer.parseInt(tokenizer.nextToken());
        tokenizer = new StringTokenizer(bufferedReader.readLine());
        xe = Integer.parseInt(tokenizer.nextToken());
        ye = Integer.parseInt(tokenizer.nextToken());
        List<int[]> points = new ArrayList<>();
        distance = new long[8][8];
        for (int i = 0; i < 8; i++) {
            Arrays.fill(distance[i], Integer.MAX_VALUE);
            distance[i][i] = 0;
        }
        int init = Math.abs(Math.abs(xe - xs) + Math.abs(ye - ys));
        distance[0][7] = init;
        distance[7][0] = init;
        for (int i = 0; i < 3; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            int x1 = Integer.parseInt(tokenizer.nextToken());
            int y1 = Integer.parseInt(tokenizer.nextToken());
            int x2 = Integer.parseInt(tokenizer.nextToken());
            int y2 = Integer.parseInt(tokenizer.nextToken());
            points.add(new int[] {x1, y1, x2, y2});
            init = Math.min(Math.abs(Math.abs(x2 - x1) + Math.abs(y2 - y1)), 10);
            int abs1 = Math.abs(Math.abs(x1 - xs) + Math.abs(y1 - ys));
            int abs2 = Math.abs(Math.abs(x2 - xs) + Math.abs(y2 - ys));
            int abs3 = Math.abs(Math.abs(x1 - xe) + Math.abs(y1 - ye));
            int abs4 = Math.abs(Math.abs(x2 - xe) + Math.abs(y2 - ye));
            switch (i) {
                case 0:
                    distance[1][2] = init;
                    distance[2][1] = init;
                    distance[0][1] = abs1;
                    distance[1][0] = abs1;
                    distance[0][2] = abs2;
                    distance[2][0] = abs2;
                    distance[7][1] = abs3;
                    distance[1][7] = abs3;
                    distance[7][2] = abs4;
                    distance[2][7] = abs4;
                    break;
                case 1:
                    distance[3][4] = init;
                    distance[4][3] = init;
                    distance[0][3] = abs1;
                    distance[3][0] = abs1;
                    distance[0][4] = abs2;
                    distance[4][0] = abs2;
                    distance[7][3] = abs3;
                    distance[3][7] = abs3;
                    distance[7][4] = abs4;
                    distance[4][7] = abs4;
                    break;
                default:
                    distance[5][6] = init;
                    distance[6][5] = init;
                    distance[0][5] = abs1;
                    distance[5][0] = abs1;
                    distance[0][6] = abs2;
                    distance[6][0] = abs2;
                    distance[7][5] = abs3;
                    distance[5][7] = abs3;
                    distance[7][6] = abs4;
                    distance[6][7] = abs4;
                    break;
            }
        }

        for (int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {
                int[] ints1 = points.get(i);
                int[] ints2 = points.get(j);
                int x1 = ints1[0];
                int y1 = ints1[1];
                int x2 = ints1[2];
                int y2 = ints1[3];

                int x3 = ints2[0];
                int y3 = ints2[1];
                int x4 = ints2[2];
                int y4 = ints2[3];

                int abs1 = Math.abs(Math.abs(x3 - x1) + Math.abs(y3 - y1));
                int abs2 = Math.abs(Math.abs(x4 - x1) + Math.abs(y4 - y1));
                int abs3 = Math.abs(Math.abs(x3 - x2) + Math.abs(y3 - y2));
                int abs4 = Math.abs(Math.abs(x4 - x2) + Math.abs(y4 - y2));

                if (i == 0) {
                    if (j == 1) {
                        distance[1][3] = abs1;
                        distance[3][1] = abs1;
                        distance[1][4] = abs2;
                        distance[4][1] = abs2;
                        distance[2][3] = abs3;
                        distance[3][2] = abs3;
                        distance[2][4] = abs4;
                        distance[4][2] = abs4;
                    } else {
                        distance[1][5] = abs1;
                        distance[5][1] = abs1;
                        distance[1][6] = abs2;
                        distance[6][1] = abs2;
                        distance[2][5] = abs3;
                        distance[5][2] = abs3;
                        distance[2][6] = abs4;
                        distance[6][2] = abs4;
                    }
                } else {
                    distance[3][5] = abs1;
                    distance[5][3] = abs1;
                    distance[3][6] = abs2;
                    distance[6][3] = abs2;
                    distance[4][5] = abs3;
                    distance[5][4] = abs3;
                    distance[4][6] = abs4;
                    distance[6][4] = abs4;
                }
            }
        }

        floyd();
        System.out.println(distance[0][7]);
    }

    static void floyd() {
        for (int mid = 0; mid < 8; mid++) {
            for (int start = 0; start < 8; start++) {
                for (int end = 0; end < 8; end++) {
                    if (start == end || start == mid || mid == end) {
                        continue;
                    }
                    distance[start][end] = Math.min(distance[start][end], distance[start][mid] + distance[mid][end]);
                }
            }
        }
    }
}
