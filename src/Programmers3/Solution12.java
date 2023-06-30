package Programmers3;

import java.util.*;

/**
 * 방문 길이 성공
 * 좌표가 아닌 돌아다닌 선분을 String 형태로 set 에 저장해봄 선분의 위치는 좌표와 좌표사이 0.5라고 표현함
 */
public class Solution12 {
    static class Point {
        private double x;
        private double y;

        public Point() {

        }

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public double getX() {
            return x;
        }

        public double getY() {
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
        Set<String> coordinate = new LinkedHashSet<>();
        Point point = new Point();
        for (int i = 0; i < dirs.length(); i++) {
            if (dirs.charAt(i) == 'U') {
                if (point.getY() == 5) {
                    continue;
                }
                point.U();
                coordinate.add(point.getX() + "," + (point.getY() - 0.5));
            } else if (dirs.charAt(i) == 'D') {
                if (point.getY() == -5) {
                    continue;
                }
                point.D();
                coordinate.add(point.getX() + "," + (point.getY() + 0.5));

            } else if (dirs.charAt(i) == 'R') {
                if (point.getX() == 5) {
                    continue;
                }
                point.R();
                coordinate.add((point.getX() - 0.5) + "," + point.getY());

            } else {
                if (point.getX() == -5) {
                    continue;
                }
                point.L();
                coordinate.add((point.getX() + 0.5) + "," + point.getY());

            }
        }
        System.out.println("point = " + point);
        System.out.println("coordinate = " + coordinate);
        return coordinate.size();
    }

    public static void main(String[] args) {
        String dirs1 = "ULURRDLLU";
        String dirs2 = "LULLLLLLU";

        System.out.println(new Solution12().solution(dirs2));
    }
}
