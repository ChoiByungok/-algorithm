package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 9019 DSLR (Gold4)
 * 그냥 간단하게 문제에서 주어진 연산을 진행 하고
 * 방문배열 선언 한 뒤 넓이 우선탐색을 진행하면 쉽게 될 줄 알았는데
 * 생각보다 많이 어려웠고 막상 통과했는데 성능도 엄청 안좋게 나왔다.
 * 다른 사람의 코드를 보니 L R 연산을 수학적으로 처리한 모습을 볼 수 있었다.
 * 나는 이걸 보자마자 덱 자료구조를 사용해서 풀어야 겠다고 생각했는데 나는 아직 멀었다고 느껴졌다.
 * 그리고 나는 명령어 나열을 StringBuilder 을 이용하여 해결했는데
 * 다른 사람은 String 배열을 선언하여 해결한 모습을 볼 수 있다.
 */
public class Solution75 {
    static class DSLR {
        StringBuilder command;
        int num;

        public DSLR(int num, StringBuilder command) {
            this.command = command;
            this.num = num;
        }
    }
    static boolean[] visited;
    static StringBuilder answer = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < T; i++) {
            visited = new boolean[10001];
            String[] split = bufferedReader.readLine().split(" ");
            int A = Integer.parseInt(split[0]);
            int B = Integer.parseInt(split[1]);
            visited[A] = true;
            answer.append(bfs(A, B)).append("\n");
        }
        System.out.println(answer);
    }

    static String bfs(int A, int B) {
       Queue<DSLR> queue = new LinkedList<>();
       queue.add(new DSLR(A, new StringBuilder()));
       String answer = "";
       while (!queue.isEmpty()) {
           DSLR poll = queue.poll();
           int num = poll.num;
           StringBuilder command = poll.command;

           if (num == B) {
               answer = command.toString();
               break;
           }

           int D = (num * 2) % 10000;
           int S = num == 0 ? 9999 : num - 1;

           Deque<Integer> next;
           next = getDeque(num);
           next.addLast(next.pollFirst());
           int L = getInteger(next);

           next = getDeque(num);
           next.addFirst(next.pollLast());
           int R = getInteger(next);

           if (!visited[D]) {
               visited[D] = true;
               queue.add(new DSLR(D, new StringBuilder(command).append("D")));
           }

           if (!visited[S]) {
               visited[S] = true;
               queue.add(new DSLR(S, new StringBuilder(command).append("S")));
           }

           if (!visited[L]) {
               visited[L] = true;
               queue.add(new DSLR(L, new StringBuilder(command).append("L")));
           }

           if (!visited[R]) {
               visited[R] = true;
               queue.add(new DSLR(R, new StringBuilder(command).append("R")));
           }
       }

       return answer;
    }

    private static Deque<Integer> getDeque(int A) {
        Deque<Integer> deque = new ArrayDeque<>();
        while (A != 0) {
            deque.addFirst(A % 10);
            A = A / 10;
        }
        while (deque.size() < 4) {
            deque.addFirst(0);
        }
        return deque;
    }

    private static int getInteger(Deque<Integer> deque) {
        Integer integer = deque.pollFirst();
        while (!deque.isEmpty()) {
            integer = (integer * 10) + deque.pollFirst();
        }
        return integer;
    }
}
