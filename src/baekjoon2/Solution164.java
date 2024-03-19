package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 19238 스타트 택시 (Gold2)
 * 지금까지 풀었봤던 구현 시뮬레이션 문제중 가장 신경쓸게 많고 가장 어려웠던 문제
 * 우선 승객 객체를 만들어 승객의 현재 위치 좌표 목적지 좌표 거리 탑승 여부 변수를 만든다.
 * 그리고 승객리스트에 승객들을 넣는다.
 * 택시 객체를 만들어 현재 택시의 좌표와 택시의 이동거리 택시의 연료 변수를 만든다.
 * 그리고 맵에 승객들의 위치를 2로 초기화 한다.
 * bfs 탐색을 실시하여 모든 승객들의 거리를 계산한다.
 * 이때 갈 수는 있지만 연료가 부족한 승객은 거리가 0이라고 초기화 되는데
 * 이러면 정렬을 했을때 문제가 발생할 수도 있으니 탑승여부 변수를 false 로 둔다.
 * 탑승가능한 승객들 중 (탑승여부가 true 인)거리가 가장 가까운 거리가 동일하면 행의 위치가 낮은 행의 위치도 같다면 열의위치가 낮은 순으로 정렬한다.
 * 맨 앞에 승객을 태운 뒤 거리를 이용하여 연료를 계산한다.
 * 승객을 목적지까지 운송한 뒤 연료를 다시 계산해주고 승객리스트에서 제외시킨다.
 * 갈 수 없는 위치에 있는 승객이라던지 승객을 모시는 도중에 연료가 바닥난다면 반복문을 종료시키고 -1 을 출력시켜준다.
 * 풀면서 89퍼센트에서 막혔지만 스스로 반례를 만들어 해결 했기에 더더욱 뿌듯한 문제였다.
 */
public class Solution164 {
    static class Taxi {
        int x;
        int y;
        int distant;
        long fuel;

        public Taxi(int x, int y, int distant, long fuel) {
            this.x = x;
            this.y = y;
            this.distant = distant;
            this.fuel = fuel;
        }

        public void move(Passenger passenger) {
            this.x = passenger.x;
            this.y = passenger.y;
            this.fuel -= passenger.distant;
            this.distant = 0;
        }

        public void take(Passenger passenger) {
            this.fuel += this.distant * 2L;
            this.x = passenger.destinationX;
            this.y = passenger.destinationY;
            this.distant = 0;
        }
    }
    static class Passenger {
        int x;
        int y;
        int destinationX;
        int destinationY;
        int distant;
        boolean possible;

        public Passenger(int x, int y, int destinationX, int destinationY, boolean possible) {
            this.x = x;
            this.y = y;
            this.destinationX = destinationX;
            this.destinationY = destinationY;
            this.possible = possible;
        }
    }
    static List<Passenger> passengers = new ArrayList<>();
    static int[][] map;
    static boolean[][] visited;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());
        long fuel = Long.parseLong(tokenizer.nextToken());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        tokenizer = new StringTokenizer(bufferedReader.readLine());
        int x = Integer.parseInt(tokenizer.nextToken()) - 1;
        int y = Integer.parseInt(tokenizer.nextToken()) - 1;
        Taxi taxi = new Taxi(x, y, 0, fuel);

        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            int passengerX = Integer.parseInt(tokenizer.nextToken()) - 1;
            int passengerY = Integer.parseInt(tokenizer.nextToken()) - 1;
            int destinationX = Integer.parseInt(tokenizer.nextToken()) - 1;
            int destinationY = Integer.parseInt(tokenizer.nextToken()) - 1;
            map[passengerX][passengerY] = 2;
            Passenger passenger = new Passenger(passengerX, passengerY, destinationX, destinationY, false);
            passengers.add(passenger);
        }

        boolean closed = false;
        for (int i = 0; i < M; i++) {
            for (Passenger passenger : passengers) {
                passenger.possible = false;
            }
            if (!start(taxi)) {
                closed = true;
                break;
            }
            passengers.sort((o1, o2) -> {
                if (o1.distant == o2.distant) {
                    if (o1.x == o2.x) {
                        return o1.y - o2.y;
                    }
                    return o1.x - o2.x;
                }
                return o1.distant - o2.distant;
            });
            Passenger passenger = null;
            for (Passenger p : passengers) {
                if (p.possible) {
                    passenger = p;
                    break;
                }
            }
            if (passenger == null) {
                closed = true;
                break;
            }
            taxi.move(passenger);
            if (!goal(taxi, passenger)) {
                closed = true;
                break;
            }
            taxi.take(passenger);
            map[passenger.x][passenger.y] = 0;
            passengers.remove(passenger);
        }

        System.out.println(closed ? -1 : taxi.fuel);
    }

    private static boolean start(Taxi taxi) {
        int count = 0;
        visited = new boolean[N][N];
        Queue<Taxi> queue = new LinkedList<>();
        queue.add(taxi);
        visited[taxi.x][taxi.y] = true;
        while (!queue.isEmpty()) {
            Taxi car = queue.poll();
            int x = car.x;
            int y = car.y;
            long fuel = car.fuel;
            int distant = car.distant;
            if (map[x][y] == 2) {
                for (Passenger passenger : passengers) {
                    if (x == passenger.x && y == passenger.y) {
                        passenger.distant = distant;
                        passenger.possible = true;
                        break;
                    }
                }
                count++;
            }
            if (count == passengers.size()) {
                return true;
            }

            if (fuel == 0) {
                if (count == 0) {
                    return false;
                }
                break;
            }

            if (x + 1 < N && !visited[x + 1][y] && map[x + 1][y] != 1) {
                visited[x + 1][y] = true;
                queue.add(new Taxi(x + 1, y, distant + 1, fuel - 1));
            }

            if (x - 1 >= 0 && !visited[x - 1][y] && map[x - 1][y] != 1) {
                visited[x - 1][y] = true;
                queue.add(new Taxi(x - 1, y, distant + 1, fuel - 1));
            }

            if (y + 1 < N && !visited[x][y + 1] && map[x][y + 1] != 1) {
                visited[x][y + 1] = true;
                queue.add(new Taxi(x, y + 1, distant + 1, fuel - 1));
            }

            if (y - 1 >= 0 && !visited[x][y - 1] && map[x][y - 1] != 1) {
                visited[x][y - 1] = true;
                queue.add(new Taxi(x, y - 1, distant + 1, fuel - 1));
            }

        }
        return true;
    }

    private static boolean goal(Taxi taxi, Passenger passenger) {
        visited = new boolean[N][N];
        Queue<Taxi> queue = new LinkedList<>();
        queue.add(taxi);
        visited[taxi.x][taxi.y] = true;
        while (!queue.isEmpty()) {
            Taxi car = queue.poll();
            int x = car.x;
            int y = car.y;
            long fuel = car.fuel;
            int distant = car.distant;
            if (x == passenger.destinationX && y == passenger.destinationY) {
                taxi.fuel = fuel;
                taxi.distant = distant;
                return true;
            }

            if (fuel == 0) {
                continue;
            }

            if (x + 1 < N && !visited[x + 1][y] && map[x + 1][y] != 1) {
                visited[x + 1][y] = true;
                queue.add(new Taxi(x + 1, y, distant + 1, fuel - 1));
            }

            if (x - 1 >= 0 && !visited[x - 1][y] && map[x - 1][y] != 1) {
                visited[x - 1][y] = true;
                queue.add(new Taxi(x - 1, y, distant + 1, fuel - 1));
            }

            if (y + 1 < N && !visited[x][y + 1] && map[x][y + 1] != 1) {
                visited[x][y + 1] = true;
                queue.add(new Taxi(x, y + 1, distant + 1, fuel - 1));
            }

            if (y - 1 >= 0 && !visited[x][y - 1] && map[x][y - 1] != 1) {
                visited[x][y - 1] = true;
                queue.add(new Taxi(x, y - 1, distant + 1, fuel - 1));
            }
        }

        return false;
    }

}
