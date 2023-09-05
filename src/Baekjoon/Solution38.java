package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 11651 좌표 정렬하기 2
 */
public class Solution38 {
    public static class Point {
        private int x;
        private int y;

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

        @Override
        public String toString() {
            return x + " " + y + "\n";
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stringBuilder = new StringBuilder();
        Queue<Point> queue = new PriorityQueue<>((o1, o2) -> {
            if (o1.getY() == o2.getY()) {
                return o1.getX() - o2.getX();
            }
            return o1.getY() - o2.getY();
        });
        int repeat = Integer.parseInt(bufferedReader.readLine());

        for (int i = 0; i < repeat; i++) {
            String[] xy = bufferedReader.readLine().split(" ");
            int x = Integer.parseInt(xy[0]);
            int y = Integer.parseInt(xy[1]);

            Point point = new Point(x, y);
            queue.add(point);
        }
        while (!queue.isEmpty()) {
            stringBuilder.append(queue.poll());
        }
        System.out.println(stringBuilder);
    }
}
