package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 16236 아기 상어 (Gold3)
 * 나름대로 최대한 최적화를 해본풀이 616ms 까진 줄였다.
 */
public class Solution118_1 {
    static class Fish {
        int x;
        int y;
        int distance;
        public Fish(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }
    static class Shark {
        int x;
        int y;
        int size;
        int count;
        int time;

        public Shark(int x, int y, int size, int count, int time) {
            this.x = x;
            this.y = y;
            this.size = size;
            this.count = count;
            this.time = time;
        }
        public void eatFish(Fish fish) {
            shark.x = fish.x;
            shark.y = fish.y;
            shark.time += fish.distance;
            shark.count++;
            if (shark.size == shark.count) {
                shark.size++;
                shark.count = 0;
            }
        }
    }
    static int N;
    static int[][] map;
    static Shark shark;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < N; j++) {
                int token = Integer.parseInt(tokenizer.nextToken());
                if (token != 9) {
                    map[i][j] = token;
                } else {
                    shark = new Shark(i, j, 2, 0, 0);
                    map[i][j] = 0;
                }
            }
        }

        while (true) {
            int size = shark.size;
            List<int[]> list = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] != 0 && map[i][j] < size) {
                        list.add(new int[]{i,j});
                    }
                }
            }
            Fish fish = bfs(shark.x, shark.y, list);
            if (fish == null) {
                break;
            }
            shark.eatFish(fish);
            map[fish.x][fish.y] = 0;
        }
        System.out.println(shark.time);
    }

    private static Fish bfs(int nowX, int nowY, List<int[]> list) {
        int distance = Integer.MAX_VALUE;
        Fish fish = null;
        for (int[] ints : list) {
            int goalX = ints[0];
            int goalY = ints[1];
            Queue<int[]> queue = new LinkedList<>();
            queue.add(new int[]{nowX, nowY, 0});
            boolean[][] visited = new boolean[N][N];
            while (!queue.isEmpty()) {
                int[] poll = queue.poll();
                int x = poll[0];
                int y = poll[1];
                int count = poll[2];

                if (visited[x][y]) {
                    continue;
                } else {
                    visited[x][y] = true;
                }

                if (count > distance) {
                    continue;
                }
                if (x == goalX && y == goalY) {
                    if (fish == null) {
                        fish = new Fish(x, y, count);
                        distance = count;
                    } else {
                        if (fish.distance > count) {
                            fish = new Fish(x, y ,count);
                            distance = count;
                        } else if (fish.distance == count) {
                            if (fish.x > x) {
                                fish = new Fish(x, y, count);
                                distance = count;
                            } else if (fish.x == x) {
                                if (fish.y > y) {
                                    fish = new Fish(x, y, count);
                                    distance = count;
                                }
                            }
                        }
                    }
                }

                if (x - 1 >= 0 && map[x - 1][y] <= shark.size && !visited[x - 1][y]) {
                    queue.add(new int[]{x - 1, y, count + 1});
                }

                if (x + 1 < N && map[x + 1][y] <= shark.size && !visited[x + 1][y]) {
                    queue.add(new int[]{x + 1, y, count + 1});
                }

                if (y - 1 >= 0 && map[x][y - 1] <= shark.size && !visited[x][y - 1]) {
                    queue.add(new int[]{x, y - 1, count + 1});
                }

                if (y + 1 < N && map[x][y + 1] <= shark.size && !visited[x][y + 1]) {
                    queue.add(new int[]{x, y + 1, count + 1});
                }
            }
        }
        return fish;
    }
}
