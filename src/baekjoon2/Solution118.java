package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 16236 아기 상어 (Gold3)
 * 다른사람들은 어떻게 풀었는지는 아직 확인 안해봤지만 일단 내방식대로 풀었을땐 성능이 최악이긴했다.
 * 일단은 이 문제를 풀었다는거에 만족스럽긴 하지만 최적화는 피해갈 수 없을것 같다.
 * 우선 입력값을 받아서 2차원 배열을 초기화 시킨다. 이때 상어의 위치는 0으로 초기화시킨다. 상어의 최초 좌표와 사이즈를 초기화시킨다.
 * 그리고 반복문을 돌면서 물고기 큐라는것을 만든다.
 * 물고기큐에는 Fish 라는 객체가 들어가는데 해당 객체는 물고기의 좌표와 상어로부터 거리정보가 담겨있는 객체이다.
 * 2차원 배열을 순회하여 현재 상어의 사이즈보다 작은 물고기들의 좌표값을 구한다 좌표값을 bfs 메서드로 보낸다
 * bfs탐색을 통해 각 물고기별 거리를 구한다. 이때 해당 물고기가 갈 수 없는 위치에 존재하면 큐에 넣지 않는다.
 * 그렇게 물고기큐에는 현재 상어가 잡아먹을 수 있는 물고기들의 좌표와 거리가 담기게 되는데
 * (이 때 큐에 아무 물고기도 들어있지 않다면 더이상 먹을 물고기가 없다는 뜻으로 반복문을 탈출 시킨다.)
 * 해당큐는 우선순위 큐로 구현되어있어 거리순으로 오름차순, 거리가 같다면 x좌표 순으로 오름차순,x좌표가 같다면 y좌표순으로 오름차순 될것이다.
 * 그리하여 큐에서 맨 앞에 요소를 제거하면 비로소 상어가 다음에 먹어야 할 물고기가 나오게 되는것이다.
 * 해당 물고기가 있던 자리는 0으로 바꿔준 뒤 상어의 좌표와 사이즈 그리고 걸린시간을 재조정해준다.
 * 상어의 사이즈는 카운트 변수를 두어 카운트가 사이즈랑 같아지는 순간 사이즈를 증가시켜주고 카운트를 다시0으로 초기화시켜주면 된다.
 * 그렇게 계속 반복하다 더이상 먹을물고기가 없어진다면(큐가 빈다면) 반복문을 탈출하게 되고
 * 거리의 누적합을 출력시켜주면된다.
 */
public class Solution118 {
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
            Queue<Fish> fishQueue = new PriorityQueue<>((o1, o2) -> {
                if (o1.distance == o2.distance) {
                    if (o1.x == o2.x) {
                        return o1.y - o2.y;
                    }
                    return o1.x - o2.x;
                }
                return o1.distance - o2.distance;
            });

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] != 0 && map[i][j] < size) {
                        int distance = bfs(i, j);
                        if (distance != -1) {
                            fishQueue.add(new Fish(i, j, distance));
                        }
                    }
                }
            }
            if (fishQueue.isEmpty()) {
                break;
            }

            Fish fish = fishQueue.poll();
            map[fish.x][fish.y] = 0;
            shark.eatFish(fish);
        }
        System.out.println(shark.time);
    }
    private static int bfs(int goalX, int goalY) {
        int distance = -1;
        boolean[][] visited = new boolean[N][N];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{shark.x, shark.y, 0});
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

            if (x == goalX && y == goalY) {
                distance = count;
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

        return distance;
    }
}
