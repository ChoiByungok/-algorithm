package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 15683 감시 (Gold4)
 * 모든 cctv의 방향을 확인하면서 사각지대가 가장 적은 경우를 찾아내야함
 * 5번 cctv의 경우 방향 회전이 없길래 탐색하기 전에 5번cctv의 범위를 먼저 맵에 표시해줌
 * 그리고 1 3 4 번 cctv는 방향이 4종류지만 2번 cctv는 2종류라 살짝 구분했음
 * CCTV라는 객체를 만들어서 5번 cctv를 제외하고 리스트에 담아줌
 * 그리고 리스트만큼 반복문을 돌리고 또 그안에 cctv의 방향만큼 반복문을 돌아주면서 재귀를 진행했음
 * 이때 매개변수로 받은 맵이 기존의 값을 유지해야 하기에 깊은 복사를 진행한뒤 매개변수로 넘겨줌
 * 그리고 그 이후는 문제에서 하라는대로 함 6번 즉 벽을 만나면 그 이후에 빈공간은 감시를 못하고
 * 감시 범위에 cctv가 있다면 상관없음 그대로 하니깐 다른사람의 코드보다는 성능이 많이 좋지는 않지만
 * 어쨋든 통과는 됨 이제 다른 사람들은 어떻게 풀었는지 한번 살펴봐야겠음
 */
public class Solution1 {
    static class CCTV {
        int x;
        int y;
        int num;
        int[] dir;
        public CCTV(int x, int y, int num) {
            this.x = x;
            this.y = y;
            this.num = num;
        }

        public void setDir(int[] dir) {
            this.dir = dir;
        }
    }
    static int N;
    static int M;
    static int[][] map;
    static List<CCTV> list = new ArrayList<>();
    static boolean[] visited;
    static int answer = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
                if (map[i][j] > 0 && map[i][j] < 5) {
                    list.add(new CCTV(i, j, map[i][j]));
                }
            }
        }
        visited = new boolean[list.size()];
        for (int i = 0; i < list.size(); i++) {
            CCTV cctv = list.get(i);
            if (cctv.num == 2) {
                cctv.setDir(new int[] {0, 1});
            } else {
                cctv.setDir(new int[] {0, 1, 2, 3});
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 5) {
                    for (int k = i - 1; k >= 0; k--) { //위쪽
                        if (map[k][j] == 0) {
                            map[k][j] = -1;
                        } else if (map[k][j] == 6) {
                            break;
                        }
                    }

                    for (int k = j + 1; k < M; k++) { //오른쪽
                        if (map[i][k] == 0) {
                            map[i][k] = -1;
                        } else if (map[i][k] == 6) {
                            break;
                        }
                    }

                    for (int k = i + 1; k < N; k++) { //아래쪽
                        if (map[k][j] == 0) {
                            map[k][j] = -1;
                        } else if (map[k][j] == 6) {
                            break;
                        }
                    }

                    for (int k = j - 1; k >= 0; k--) { //왼쪽
                        if (map[i][k] == 0) {
                            map[i][k] = -1;
                        } else if (map[i][k] == 6) {
                            break;
                        }
                    }
                }
            }
        }
        dfs(0, 0, map);
        System.out.println(answer);
    }

    static void dfs(int depth, int start, int[][] map) {
        if (depth == list.size()) {
            answer = Math.min(answer, blindSpotCount(map));
            return;
        }

        for (int i = start; i < list.size(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                CCTV cctv = list.get(i);
                int[] dir = cctv.dir;
                boolean[] dirVisited = new boolean[dir.length];
                for (int j = 0; j < dir.length; j++) {
                    if (!dirVisited[j]) {
                        dirVisited[j] = true;
                        dfs(depth + 1, i + 1, watch(cctv, dir[j], copy(map)));
                        dirVisited[j] = false;
                    }
                }
                visited[i] = false;
            }
        }
    }
    static int[][] copy(int[][] map) {
        int[][] copy = new int[N][M];
        for (int i = 0; i < N; i++) {
            if (M >= 0) System.arraycopy(map[i], 0, copy[i], 0, M);
        }
        return copy;
    }
    
    static int[][] watch(CCTV cctv, int dir, int[][] map) {
        int num = cctv.num;
        int x = cctv.x;
        int y = cctv.y;
        switch (num) {
            case 1:
                switch (dir) {
                    case 0:
                        for (int i = x - 1; i >= 0; i--) { //위쪽
                            if (map[i][y] == 0) {
                                map[i][y] = -1;
                            } else if (map[i][y] == 6) {
                                break;
                            }
                        }
                        break;
                    case 1:
                        for (int i = y + 1; i < M; i++) { //오른쪽
                            if (map[x][i] == 0) {
                                map[x][i] = -1;
                            } else if (map[x][i] == 6) {
                                break;
                            }
                        }
                        break;
                    case 2:
                        for (int i = x + 1; i < N; i++) { //아래쪽
                            if (map[i][y] == 0) {
                                map[i][y] = -1;
                            } else if (map[i][y] == 6) {
                                break;
                            }
                        }
                        break;
                    case 3:
                        for (int i = y - 1; i >= 0; i--) { //왼쪽
                            if (map[x][i] == 0) {
                                map[x][i] = -1;
                            } else if (map[x][i] == 6) {
                                break;
                            }
                        }
                        break;
                }
                break;
            case 2:
                switch (dir) {
                    case 0:
                        for (int i = y + 1; i < M; i++) { //오른쪽
                            if (map[x][i] == 0) {
                                map[x][i] = -1;
                            } else if (map[x][i] == 6) {
                                break;
                            }
                        }
                        for (int i = y - 1; i >= 0; i--) { //왼쪽
                            if (map[x][i] == 0) {
                                map[x][i] = -1;
                            } else if (map[x][i] == 6) {
                                break;
                            }
                        }
                        break;
                    case 1:
                        for (int i = x - 1; i >= 0; i--) { //위쪽
                            if (map[i][y] == 0) {
                                map[i][y] = -1;
                            } else if (map[i][y] == 6) {
                                break;
                            }
                        }
                        for (int i = x + 1; i < N; i++) { //아래쪽
                            if (map[i][y] == 0) {
                                map[i][y] = -1;
                            } else if (map[i][y] == 6) {
                                break;
                            }
                        }
                        break;
                }
                break;
            case 3:
                switch (dir) {
                    case 0:
                        for (int i = x - 1; i >= 0; i--) { //위쪽
                            if (map[i][y] == 0) {
                                map[i][y] = -1;
                            } else if (map[i][y] == 6) {
                                break;
                            }
                        }
                        for (int i = y + 1; i < M; i++) { //오른쪽
                            if (map[x][i] == 0) {
                                map[x][i] = -1;
                            } else if (map[x][i] == 6) {
                                break;
                            }
                        }
                        break;
                    case 1:
                        for (int i = y + 1; i < M; i++) { //오른쪽
                            if (map[x][i] == 0) {
                                map[x][i] = -1;
                            } else if (map[x][i] == 6) {
                                break;
                            }
                        }
                        for (int i = x + 1; i < N; i++) { //아래쪽
                            if (map[i][y] == 0) {
                                map[i][y] = -1;
                            } else if (map[i][y] == 6) {
                                break;
                            }
                        }
                        break;
                    case 2:
                        for (int i = x + 1; i < N; i++) { //아래쪽
                            if (map[i][y] == 0) {
                                map[i][y] = -1;
                            } else if (map[i][y] == 6) {
                                break;
                            }
                        }
                        for (int i = y - 1; i >= 0; i--) { //왼쪽
                            if (map[x][i] == 0) {
                                map[x][i] = -1;
                            } else if (map[x][i] == 6) {
                                break;
                            }
                        }
                        break;
                    case 3:
                        for (int i = y - 1; i >= 0; i--) { //왼쪽
                            if (map[x][i] == 0) {
                                map[x][i] = -1;
                            } else if (map[x][i] == 6) {
                                break;
                            }
                        }
                        for (int i = x - 1; i >= 0; i--) { //위쪽
                            if (map[i][y] == 0) {
                                map[i][y] = -1;
                            } else if (map[i][y] == 6) {
                                break;
                            }
                        }
                        break;
                }
                break;
            case 4:
                switch (dir) {
                    case 0:
                        for (int i = y - 1; i >= 0; i--) { //왼쪽
                            if (map[x][i] == 0) {
                                map[x][i] = -1;
                            } else if (map[x][i] == 6) {
                                break;
                            }
                        }
                        for (int i = x - 1; i >= 0; i--) { //위쪽
                            if (map[i][y] == 0) {
                                map[i][y] = -1;
                            } else if (map[i][y] == 6) {
                                break;
                            }
                        }
                        for (int i = y + 1; i < M; i++) { //오른쪽
                            if (map[x][i] == 0) {
                                map[x][i] = -1;
                            } else if (map[x][i] == 6) {
                                break;
                            }
                        }
                        break;
                    case 1:
                        for (int i = x - 1; i >= 0; i--) { //위쪽
                            if (map[i][y] == 0) {
                                map[i][y] = -1;
                            } else if (map[i][y] == 6) {
                                break;
                            }
                        }
                        for (int i = y + 1; i < M; i++) { //오른쪽
                            if (map[x][i] == 0) {
                                map[x][i] = -1;
                            } else if (map[x][i] == 6) {
                                break;
                            }
                        }
                        for (int i = x + 1; i < N; i++) { //아래쪽
                            if (map[i][y] == 0) {
                                map[i][y] = -1;
                            } else if (map[i][y] == 6) {
                                break;
                            }
                        }
                        break;
                    case 2:
                        for (int i = y + 1; i < M; i++) { //오른쪽
                            if (map[x][i] == 0) {
                                map[x][i] = -1;
                            } else if (map[x][i] == 6) {
                                break;
                            }
                        }
                        for (int i = x + 1; i < N; i++) { //아래쪽
                            if (map[i][y] == 0) {
                                map[i][y] = -1;
                            } else if (map[i][y] == 6) {
                                break;
                            }
                        }
                        for (int i = y - 1; i >= 0; i--) { //왼쪽
                            if (map[x][i] == 0) {
                                map[x][i] = -1;
                            } else if (map[x][i] == 6) {
                                break;
                            }
                        }
                        break;
                    case 3:
                        for (int i = x + 1; i < N; i++) { //아래쪽
                            if (map[i][y] == 0) {
                                map[i][y] = -1;
                            } else if (map[i][y] == 6) {
                                break;
                            }
                        }
                        for (int i = y - 1; i >= 0; i--) { //왼쪽
                            if (map[x][i] == 0) {
                                map[x][i] = -1;
                            } else if (map[x][i] == 6) {
                                break;
                            }
                        }
                        for (int i = x - 1; i >= 0; i--) { //위쪽
                            if (map[i][y] == 0) {
                                map[i][y] = -1;
                            } else if (map[i][y] == 6) {
                                break;
                            }
                        }
                        break;
                }
                break;
        }
        return map;
    }

    static int blindSpotCount(int[][] map) {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    count++;
                }
            }
        }
        return count;
    }
    static void print(int[][] map) {
        System.out.println();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == -1) {
                    System.out.print("■ ");
                } else {
                    System.out.print(map[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}
