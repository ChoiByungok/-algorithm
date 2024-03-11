package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 1347 미로만들기 (Silver2)
 * 출발지점이 정해져 있는게 아니라 그냥 길이 있는 방향으로 움직이는 개념이라
 * 어떻게 해야 할지 고민하다가 그냥 시작지점을 0,0 이라 잡고
 * 방향 조정후에 F 명령어가 들어오면 이동한 공간으로 좌표를 저장하였다.
 * 1 북 2 동 3 남 4 서 라고 임의로 방향을 정하고
 * 처음에 남쪽을 바라본다고 했으니 3이라고 초기화 하고 시작하였다.
 * 처음에 RRF 라고 입력값이 들어온다고 가정하면 RR에 의해 방향은 북쪽이 되고 F에 의해 움직인다.
 * 그러게 하면 현재 좌표는 -1,0 이 된다. 그러면 홍준이 움직인 좌표리스트는 0,0 -1,0 이 된다.
 * 그렇게 홍준이 움직인 좌표들을 모두 저장하고 (단 중복된 좌표는 거르기 위하여 SET 자료구조를 사용하였다.)
 * 좌표를 저장하면서 x 좌표 최솟값과 최댓값, y 좌표 최솟값과 최댓값을 구한다.
 * 그리고 저장된 좌표들을 정렬한다. x 좌표가 같으면 y 좌표가 작은것이 앞으로 가게 하고
 * 아니면 x 좌표가 작은것이 앞으로 가도록 정렬한다.
 * 그리고 minX maxX minY maxY 까지 이중 반복문을 돌리면서
 * 그때 좌표에 저장된 값이랑 일치하면 '.' 을 출력하고 좌표리스트에 첫번째 값을 제거한다. 일치하지 않으면 '#' 을 출력해준다.
 * 반복문을 돌다가 좌표리스트가 비어버리면 '#' 만 출력하면 된다.
 */
public class Solution156 {
    static class HongJun {
        int x;
        int y;
        int dir;
        Set<Point> map = new HashSet<>();
        int minX = 0;
        int maxX = 0;
        int minY = 0;
        int maxY = 0;
        public HongJun(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            map.add(new Point(x, y));
        }

        public void move(char c) {
            if (c == 'F') {
                switch (dir) {
                    case 1:
                        x--;
                        break;
                    case 2:
                        y++;
                        break;
                    case 3:
                        x++;
                        break;
                    case 4:
                        y--;
                        break;
                }
                map.add(new Point(x, y));
                minX = Math.min(minX, x);
                maxX = Math.max(maxX, x);
                minY = Math.min(minY, y);
                maxY = Math.max(maxY, y);
            } else {
                if (c == 'R') {
                    if (dir == 4) {
                        dir = 1;
                    } else {
                        dir++;
                    }
                } else {
                    if (dir == 1) {
                        dir = 4;
                    } else {
                        dir--;
                    }
                }
            }
        }

        public StringBuilder drawMap() {
            StringBuilder map = new StringBuilder();
            List<Point> sortedMap = sortMap();
            for (int i = minX; i <= maxX; i++) {
                for (int j = minY; j <= maxY; j++) {
                    if (sortedMap.isEmpty()) {
                        map.append("#");
                        continue;
                    }
                    Point point = sortedMap.get(0);
                    int x = point.x;
                    int y = point.y;
                    if (i == x && j == y) {
                        map.append(".");
                        sortedMap.remove(point);
                    } else {
                        map.append("#");
                    }
                }
                map.append("\n");
            }
            return map;
        }

        private List<Point> sortMap() {
            List<Point> sortedMap = new ArrayList<>(this.map);
            sortedMap.sort((o1, o2) -> {
                if (o1.x == o2.x) {
                    return o1.y - o2.y;
                }
                return o1.x - o2.x;
            });
            return sortedMap;
        }
    }
    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        String readLine = bufferedReader.readLine();
        HongJun hongJun = new HongJun(0, 0, 3);
        for (int i = 0; i < readLine.length(); i++) {
            char c = readLine.charAt(i);
            hongJun.move(c);
        }

        System.out.println(hongJun.drawMap());
    }
}
