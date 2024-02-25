package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 20057 마법사 상어와 토네이도 (Gold3)
 * 내가 풀어봤던 구현 시뮬레이션 문제중 역대급으로 노가다 였던 문제
 * N * N 배열이 주어졌을 떄 (N은 홀수임)
 * map[N/2][N/2] 부터 반시계방향으로 회전하는 토네이도가 map[0][0]에 도착하는 과정에서 맵 밖으로 나간 모래의 양을 출력해야 한다.
 * 우선 토네이도의 이동경로를 프로그래밍 해야 하는데 여기서부터 난관이었다.
 * 반시계 방향으로 2번꺾으면 토네이도가 직진하는 길이가 1씩 늘어나는걸 이용해서
 * 토네이도의 spin()이란 메서드를 2번 호출할때마다 길이를 1씩 늘어나도록 설정을했다.
 * 이렇게 하면 마지막줄에서 예외가 발생하므로 토네이도의 좌표가 0,0 이되면 반복문을 탈출하도록 했다.
 * 이제 토네이도의 이동방향의 프로그래밍이 완성됐으니 그 이후부터는 문제예시에서 보여준거처럼 노가다를 해주면된다.
 * 토네이도의 이동방향에 따라 모래가 맵 밖으로 넘어가면 최종값에 누적합 해주고 아니면 해당 좌표에 모래를 누적합 해주면 된다.
 */
public class Solution140 {
    static class Tornado {
        int x;
        int y;
        int length;
        int dir;
        int spinCount;
        public Tornado(int x, int y, int length, int dir, int spinCount) {
            this.x = x;
            this.y = y;
            this.length = length;
            this.dir = dir;
            this.spinCount = spinCount;
        }

        public void move() {
            switch (dir) {
                case 1:
                    y++;
                    break;
                case 2:
                    x++;
                    break;
                case 3:
                    y--;
                    break;
                case 4:
                    x--;
                    break;
            }
        }

        public void spin() {
            if (dir == 1) {
                dir = 4;
            } else {
                dir --;
            }

            spinCount++;
            if (spinCount % 2 == 0) {
                length++;
            }

        }
    }
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int answer = 0;
        int N = Integer.parseInt(bufferedReader.readLine());
        map = new int[N][N];
        Tornado tornado = new Tornado(N / 2, N / 2, 1, 3, 0);
        for (int i = 0; i < N; i++) {
            StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }
        label:
        while (true) {
            for (int i = 0; i < tornado.length; i++) {
                tornado.move();
                int x = tornado.x;
                int y = tornado.y;
                int sand = map[x][y];
                if (sand >= 10) {
                    int ten = (int) (sand * 0.1);
                    int seven = (int) (sand * 0.07);
                    int five = (int) (sand * 0.05);
                    int two = (int) (sand * 0.02);
                    int one = (int) (sand * 0.01);
                    int alpha = sand - ((2 * ten) + (2 * seven) + (five) + (2 * two) + (2 * one));
                    switch (tornado.dir) {
                        case 1:
                            if (x - 1 >= 0 && y + 1 < N) {
                                map[x - 1][y + 1] += ten;
                            } else {
                                answer += ten;
                            }

                            if (x + 1 < N && y + 1 < N) {
                                map[x + 1][y + 1] += ten;
                            } else {
                                answer += ten;
                            }

                            if (x - 1 >= 0) {
                                map[x - 1][y] += seven;
                            } else {
                                answer += seven;
                            }

                            if (x + 1 < N) {
                                map[x + 1][y] += seven;
                            } else {
                                answer += seven;
                            }

                            if (x - 2 >= 0) {
                                map[x - 2][y] += two;
                            } else {
                                answer += two;
                            }

                            if (x + 2 < N) {
                                map[x + 2][y] += two;
                            } else {
                                answer += two;
                            }

                            if (x - 1 >= 0 && y - 1 >= 0) {
                                map[x - 1][y - 1] += one;
                            } else {
                                answer += one;
                            }

                            if (x + 1 < N && y - 1 >= 0) {
                                map[x + 1][y - 1] += one;
                            } else {
                                answer += one;
                            }

                            if (y + 2 < N) {
                                map[x][y + 2] += five;
                            } else {
                                answer += five;
                            }

                            if (y + 1 < N) {
                                map[x][y + 1] += alpha;
                            } else {
                                answer += alpha;
                            }
                            break;
                        case 2:
                            if (x + 1 < N && y - 1 >= 0) {
                                map[x + 1][y - 1] += ten;
                            } else {
                                answer += ten;
                            }

                            if (x + 1 < N && y + 1 < N) {
                                map[x + 1][y + 1] += ten;
                            } else {
                                answer += ten;
                            }

                            if (y - 1 >= 0) {
                                map[x][y - 1] += seven;
                            } else {
                                answer += seven;
                            }

                            if (y + 1 < N) {
                                map[x][y + 1] += seven;
                            } else {
                                answer += seven;
                            }

                            if (y - 2 >= 0) {
                                map[x][y - 2] += two;
                            } else {
                                answer += two;
                            }

                            if (y + 2 < N) {
                                map[x][y + 2] += two;
                            } else {
                                answer += two;
                            }

                            if (x - 1 >= 0 && y - 1 >= 0) {
                                map[x - 1][y - 1] += one;
                            } else {
                                answer += one;
                            }

                            if (x - 1 >= 0 && y + 1 < N) {
                                map[x - 1][y + 1] += one;
                            } else {
                                answer += one;
                            }

                            if (x + 2 < N) {
                                map[x + 2][y] += five;
                            } else {
                                answer += five;
                            }

                            if (x + 1 < N) {
                                map[x + 1][y] += alpha;
                            } else {
                                answer += alpha;
                            }
                            break;
                        case 3:
                            if (x - 1 >= 0 && y - 1 >= 0) {
                                map[x - 1][y - 1] += ten;
                            } else {
                                answer += ten;
                            }

                            if (x + 1 < N && y - 1 >= 0) {
                                map[x + 1][y - 1] += ten;
                            } else {
                                answer += ten;
                            }

                            if (x - 1 >= 0) {
                                map[x - 1][y] += seven;
                            } else {
                                answer += seven;
                            }

                            if (x + 1 < N) {
                                map[x + 1][y] += seven;
                            } else {
                                answer += seven;
                            }

                            if (x - 2 >= 0) {
                                map[x - 2][y] += two;
                            } else {
                                answer += two;
                            }

                            if (x + 2 < N) {
                                map[x + 2][y] += two;
                            } else {
                                answer += two;
                            }

                            if (x - 1 >= 0 && y + 1 < N) {
                                map[x - 1][y + 1] += one;
                            } else {
                                answer += one;
                            }

                            if (x + 1 < N && y + 1 < N) {
                                map[x + 1][y + 1] += one;
                            } else {
                                answer += one;
                            }

                            if (y - 2 >= 0) {
                                map[x][y - 2] += five;
                            } else {
                                answer += five;
                            }

                            if (y - 1 >= 0) {
                                map[x][y - 1] += alpha;
                            } else {
                                answer += alpha;
                            }
                            break;
                        case 4:
                            if (x - 1 >= 0 && y - 1 >= 0) {
                                map[x - 1][y - 1] += ten;
                            } else {
                                answer += ten;
                            }

                            if (x - 1 >= 0 && y + 1 < N) {
                                map[x - 1][y + 1] += ten;
                            } else {
                                answer += ten;
                            }

                            if (y - 1 >= 0) {
                                map[x][y - 1] += seven;
                            } else {
                                answer += seven;
                            }

                            if (y + 1 < N) {
                                map[x][y + 1] += seven;
                            } else {
                                answer += seven;
                            }

                            if (y - 2 >= 0) {
                                map[x][y - 2] += two;
                            } else {
                                answer += two;
                            }

                            if (y + 2 < N) {
                                map[x][y + 2] += two;
                            } else {
                                answer += two;
                            }

                            if (x + 1 < N && y - 1 >= 0) {
                                map[x + 1][y - 1] += one;
                            } else {
                                answer += one;
                            }

                            if (x + 1 < N && y + 1 < N) {
                                map[x + 1][y + 1] += one;
                            } else {
                                answer += one;
                            }

                            if (x - 2 >= 0) {
                                map[x - 2][y] += five;
                            } else {
                                answer += five;
                            }

                            if (x - 1 >= 0) {
                                map[x - 1][y] += alpha;
                            } else {
                                answer += alpha;
                            }
                            break;
                    }
                } else {
                    switch (tornado.dir) {
                        case 1:
                            if (y + 1 < N) {
                                map[x][y + 1] += sand;
                            } else {
                                answer += sand;
                            }
                            break;
                        case 2:
                            if (x + 1 < N) {
                                map[x + 1][y] += sand;
                            } else {
                                answer += sand;
                            }
                            break;
                        case 3:
                            if (y - 1 >= 0) {
                                map[x][y - 1] += sand;
                            } else {
                                answer += sand;
                            }
                            break;
                        case 4:
                            if(x - 1 >= 0) {
                                map[x - 1][y] += sand;
                            } else {
                                answer += sand;
                            }
                            break;
                    }
                }
                map[x][y] = 0;
                if (tornado.x == 0 && tornado.y == 0) {
                    break label;
                }
            }
            tornado.spin();
        }

        System.out.println(answer);
    }
}
