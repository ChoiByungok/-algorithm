package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 20056 마법사 상어와 파이어볼 (Gold4)
 * 어떻게든 쥐어짜서 간신히 통과는 했으나 효율이 좋지는 않은 코드
 * 분배된 질량이 0이면 파이어볼은 소멸한다 라는 문구를 보지 못하여
 * 그대로 진행했다가 시간초과가 나왔다.
 * 그래서 분배된 질량이 0이면 반복문을 진행하지 않는다라는 조건을 하나 넣으니깐 통과되었다.
 */
public class Solution149 {
    static class FireBall {
        int x;
        int y;
        int m;
        int s;
        int d;

        public FireBall(int x, int y, int m, int s, int d) {
            this.x = x;
            this.y = y;
            this.m = m;
            this.s = s;
            this.d = d;
        }

        public void move() {
            switch (d) {
                case 0:
                    for (int i = 0; i < s; i++) {
                        x--;
                        if (x == -1) {
                            x = N - 1;
                        }
                    }
                    break;
                case 1:
                    for (int i = 0; i < s; i++) {
                        x--;
                        y++;
                        if (x == - 1) {
                            x = N - 1;
                        }
                        if (y == N) {
                            y = 0;
                        }
                    }
                    break;
                case 2:
                    for (int i = 0; i < s; i++) {
                        y++;
                        if (y == N) {
                            y = 0;
                        }
                    }
                    break;
                case 3:
                    for (int i = 0; i < s; i++) {
                        x++;
                        y++;
                        if (x == N) {
                            x = 0;
                        }
                        if (y == N) {
                            y = 0;
                        }
                    }
                    break;
                case 4:
                    for (int i = 0; i < s; i++) {
                        x++;
                        if (x == N) {
                            x = 0;
                        }
                    }
                    break;
                case 5:
                    for (int i = 0; i < s; i++) {
                        x++;
                        y--;
                        if (x == N) {
                            x = 0;
                        }
                        if (y == - 1) {
                            y = N - 1;
                        }
                    }
                    break;
                case 6:
                    for (int i = 0; i < s; i++) {
                        y--;
                        if (y == -1) {
                            y = N - 1;
                        }
                    }
                    break;
                case 7:
                    for (int i = 0; i < s; i++) {
                        x--;
                        y--;
                        if (x == -1) {
                            x = N - 1;
                        }
                        if (y == -1) {
                            y = N - 1;
                        }
                    }
                    break;
            }
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
    static class Merge {
        int number;
        int sumOfMass;
        int sumOfSpeed;
        List<Integer> directions = new ArrayList<>();

        public Merge(int sumOfMass, int sumOfSpeed, int d) {
            this.number = 1;
            this.sumOfMass = sumOfMass;
            this.sumOfSpeed = sumOfSpeed;
            directions.add(d);
        }
    }
    static int N;
    public static void main(String[] args) throws IOException {
        int answer = 0;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());
        int K = Integer.parseInt(tokenizer.nextToken());
        List<FireBall> list = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            int r = Integer.parseInt(tokenizer.nextToken());
            int c = Integer.parseInt(tokenizer.nextToken());
            int m = Integer.parseInt(tokenizer.nextToken());
            int s = Integer.parseInt(tokenizer.nextToken());
            int d = Integer.parseInt(tokenizer.nextToken());
            list.add(new FireBall(r - 1, c - 1, m, s, d));
        }

        for (int i = 0; i < K; i++) {
            Map<Point, Merge> map = new HashMap<>();
            List<FireBall> fireBallList = new ArrayList<>();
            for (FireBall fireBall : list) {
                fireBall.move();
                int x = fireBall.x;
                int y = fireBall.y;
                int m = fireBall.m;
                int s = fireBall.s;
                int d = fireBall.d;
                Point key = new Point(x, y);
                if (map.containsKey(key)) {
                    Merge merge = map.get(key);
                    merge.number++;
                    merge.sumOfMass += m;
                    merge.sumOfSpeed += s;
                    merge.directions.add(d);
                } else {
                    Merge merge = new Merge(m, s, d);
                    map.put(key, merge);
                }
            }
            List<Map.Entry<Point, Merge>> entries = new ArrayList<>(map.entrySet());
            for (Map.Entry<Point, Merge> entry : entries) {
                Point key = entry.getKey();
                Merge value = entry.getValue();
                if (value.number >= 2) {
                    int newMass = value.sumOfMass / 5;
                    if (newMass == 0) {
                        continue;
                    }
                    int newSpeed = value.sumOfSpeed / value.number;
                    int oddCount = 0;
                    int evenCount = 0;

                    for (Integer direction : value.directions) {
                        if (direction % 2 == 0) {
                            evenCount++;
                        } else {
                            oddCount++;
                        }
                    }
                    if (value.directions.size() == evenCount || value.directions.size() == oddCount) {
                        for (int j = 0; j < 4; j++) {
                            FireBall fireBall = new FireBall(key.x, key.y, newMass, newSpeed, j * 2);
                            fireBallList.add(fireBall);
                        }
                    } else {
                        for (int j = 0; j < 4; j++) {
                            FireBall fireBall = new FireBall(key.x, key.y, newMass, newSpeed, (j * 2) + 1);
                            fireBallList.add(fireBall);
                        }
                    }
                } else {
                    fireBallList.add(new FireBall(key.x, key.y, value.sumOfMass, value.sumOfSpeed, value.directions.get(0)));
                }
            }
            list = fireBallList;
        }

        for (FireBall fireBall : list) {
            answer += fireBall.m;
        }

        System.out.println(answer);
    }
}
