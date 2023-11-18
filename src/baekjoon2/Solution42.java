package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 16953 A → B
 */
public class Solution42 {
    static class AtoB {
        long a;
        int step;

        public AtoB(long a, int step) {
            this.a = a;
            this.step = step;
        }
    }
    static long A;
    static long B;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] ab = bufferedReader.readLine().split(" ");
        int answer = -1;
        A = Long.parseLong(ab[0]);
        B = Long.parseLong(ab[1]);

        Queue<AtoB> queue = new LinkedList<>();
        queue.add(new AtoB(A, 1));

        while (!queue.isEmpty()) {
            AtoB now = queue.poll();
            long a = now.a;
            int step = now.step;

            if (a > 1000000000) {
                continue;
            }

            if (a == B) {
                answer = step;
                break;
            }

            queue.add(new AtoB(a * 2, step + 1));
            queue.add(new AtoB(Long.parseLong(a + "1"), step + 1));
        }
        System.out.println(answer);
    }
}
