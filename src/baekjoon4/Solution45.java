package baekjoon4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 14395 4연산 (Gold4)
 * s 에서 * + - / 의 4연산을 사용하여 t 가 된다면 그 과정을 제출하면된다.
 * 최적의 연산과정중 연산자의 아스키코드값으로 오름차순해서 제출하랬으므로
 * * + - / 이 순서로 연산을 진행하여 탐색하면 된다.
 * 다만 방문처리를 배열로 하지 않고 set으로 한 이유는
 * s의 값이 int 형을 벗어날 수 있는데 배열의 길이는 int형이 한계이므로 set을 이용하여 방문처리한다.
 * 그리고 연산중 - 와 / 는 현재 숫자를 0과 1로 만들어버리는 연산인데
 * 얘네들은 한번 연산이 진행되면 그 이후에는 하지않는 연산이라 판단하여
 * 처음부터 큐에 넣고 진행해봤는데 41퍼센트에서 틀렸다고 나왔다.
 * 이유는 잘 모르겠는데 최적의 해를 찾는 방법이 아닌가보다.
 */
public class Solution45 {
    static class Operator {
        long num;
        StringBuilder op;

        public Operator(long num, StringBuilder op) {
            this.num = num;
            this.op = op;
        }
    }
    static long t;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] split = bufferedReader.readLine().split(" ");
        long s = Long.parseLong(split[0]);
        t = Long.parseLong(split[1]);
        if (s == t) {
            System.out.println(0);
        } else {
            System.out.println(bfs(s));
        }
    }
    static String bfs(long s) {
        String operator = "-1";
        Set<Long> set = new HashSet<>();
        set.add(s);
        Queue<Operator> queue = new LinkedList<>();
        queue.add(new Operator(s, new StringBuilder()));
        while (!queue.isEmpty()) {
            Operator now = queue.poll();
            long num = now.num;
            StringBuilder op = now.op;

            if (num == t) {
                operator = op.toString();
                break;
            }

            if (!set.contains(num * num)) {
                set.add(num * num);
                queue.add(new Operator(num * num, new StringBuilder().append(op).append("*")));
            }

            if (!set.contains(num + num)) {
                set.add(num + num);
                queue.add(new Operator(num + num, new StringBuilder().append(op).append("+")));
            }

            if (!set.contains(num - num)) {
                set.add(num - num);
                queue.add(new Operator(num - num, new StringBuilder().append(op).append("-")));
            }

            if (num != 0 && !set.contains(num / num)) {
                set.add(num / num);
                queue.add(new Operator(num / num, new StringBuilder().append(op).append("/")));
            }

        }

        return operator;
    }
}
