package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 1374 강의실
 * 예전에 강의실 배정이라는 문제를 복기시켜 푼 문제 아예 똑같은 문제라고 할 수 있다.
 * 예전에 풀었던걸 잊어버리지 않고 제대로 기억해 내었다는거에 만족해야할듯 싶다.
 * 1.시작시간 순으로 오름차순 정렬을 한다. 단 시작 시간이 같을 떄 종료시간이 빠른것이 먼저오도록 한다.
 * 2.우선순위 큐에 맨 처음 강의의 종료시간을 넣고 그 이후에 반복문을 돌면서 큐의 맨 앞의 값과 뒤의 강의 들의 시작 시간과 비교를한다.
 * 3.시작시간이 같거나 크면 큐의 맨 앞의 요소를 제거한다.
 */
public class Solution48 {
    static class Lecture {
        int num;
        int start;
        int end;

        public Lecture(int num, int start, int end) {
            this.num = num;
            this.start = start;
            this.end = end;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        List<Lecture> lectures = new ArrayList<>();
        Queue<Integer> queue = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            String[] split = bufferedReader.readLine().split(" ");
            int num = Integer.parseInt(split[0]);
            int start = Integer.parseInt(split[1]);
            int end = Integer.parseInt(split[2]);
            lectures.add(new Lecture(num, start, end));
        }
        lectures.sort((o1, o2) -> {
            if (o1.start == o2.start) {
                return o1.end - o2.end;
            }
            return o1.start - o2.start;
        });

        queue.offer(lectures.get(0).end);
        for (int i = 1; i < lectures.size(); i++) {
            if (queue.peek() <= lectures.get(i).start) {
                queue.poll();
            }
            queue.add(lectures.get(i).end);
        }

        System.out.println(queue.size());
    }
}
