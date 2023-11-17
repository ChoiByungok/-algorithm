package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 11000 강의실 배정
 */
public class Solution41 {
    static class Lecture {
        int start;
        int end;

        public Lecture(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    static Lecture[] lectures;
    static boolean[] assignment;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());

        lectures = new Lecture[N];
        assignment = new boolean[N];

        for (int i = 0; i < N; i++) {
            String[] split = bufferedReader.readLine().split(" ");
            int start = Integer.parseInt(split[0]);
            int end = Integer.parseInt(split[1]);
            lectures[i] = new Lecture(start, end);
        }

        Arrays.sort(lectures, (o1, o2) -> {
            if (o1.start == o2.start) {
                return o1.end - o2.end;
            }
            return o1.start - o2.start;
        });

        Queue<Integer> queue = new PriorityQueue<>();
        queue.offer(lectures[0].end);

        for (int i = 1; i < lectures.length; i++) {

            if (queue.peek() <= lectures[i].start) {
                queue.poll();
            }
            queue.offer(lectures[i].end);
        }

        System.out.println(queue.size());
    }

}
