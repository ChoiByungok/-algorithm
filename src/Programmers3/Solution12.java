package Programmers3;

import java.util.*;

/**
 * 방문 길이 실패
 * 원인 한번 간적이 있는 좌표를 중복에서 아예 제거 해버렸는데 순환 구조 일시 좌표는 중복이어도 선분은 중복이 되지 않음
 * 어떻게 해야 할지 감이 안잡힘
 */
public class Solution12 {
    static class Point {
        int x;
        int y;

        public Point() {

        }

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public void U() {
            y++;
        }

        public void D() {
            y--;
        }

        public void R() {
            x++;
        }

        public void L() {
            x--;
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

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
    public int solution(String dirs) {
        int answer = 0;
        Set<Point> points = new LinkedHashSet<>();
        Point point = new Point();
        for (int i = 0; i < dirs.length(); i++) {
            if (dirs.charAt(i) == 'U') {
                if (point.getY() == 5) {
                    continue;
                }
                point.U();
                Point pointU = new Point(point.getX(), point.getY());
                points.add(pointU);
            } else if (dirs.charAt(i) == 'D') {
                if (point.getY() == -5) {
                    continue;
                }
                point.D();
                Point pointD = new Point(point.getX(), point.getY());
                points.add(pointD);
            } else if (dirs.charAt(i) == 'R') {
                if (point.getX() == 5) {
                    continue;
                }
                point.R();
                Point pointR = new Point(point.getX(), point.getY());
                points.add(pointR);
            } else {
                if (point.getX() == -5) {
                    continue;
                }
                point.L();
                Point pointL = new Point(point.getX(), point.getY());
                points.add(pointL);
            }
        }
        System.out.println("point = " + point);
        System.out.println("points = " + points);
        return points.size();
    }

    public static void main(String[] args) {
        String dirs1 = "ULURRDLLU";
        String dirs2 = "LULLLLLLU";

        System.out.println(new Solution12().solution(dirs1));
    }
}
