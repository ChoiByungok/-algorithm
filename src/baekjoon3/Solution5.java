package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 7490 0 만들기 (Gold5)
 * 공백연산이라는게 뭔지 몰라서 한참 헤맸던 문제
 * 1-2 해서 -1 이되고 거기에 3을 공백연산해서 -13+4+5+6+7 이되는줄 알고 이게 어떻게 0이 되는지
 * 이해가 안가서 한참 헤맴 알고보니 1-2 3+4+5+6+7 는 1-23+4+5+6+7 이었던 거였다.
 * 연산을 해가면서 재귀호출을 해주고 싶었는데 1-23 을 할 수 있는 방법을 찾아내지 못했다.
 * 나는 1-23을 해서 -22를 넘기고 싶지만 아무리 생각해도 1-2를 먼저 연산해 -1을 넘길거같아서 포기했다.
 * 그래서 결국 수식을 완성시킨 후 연산하기로 했다.
 * 근데 수식을 완성시켜도 이걸 계산 할 방법이 마땅히 생각이 나지 않아서 또 헤맸다.
 * 우선 replaceAll 연산을 통해 공백은 제거했는데 어떻게 해야할지 몰라서 고민하다가
 * 결국 문자열을 하나씩 탐색해 연산자랑 피연산자를 구분한 뒤 그 이후에 합을 구했다.
 * 어떻게든 통과되긴 했는데 뭔가 전체적으로 아쉬운 코드가 완성이 되었다.
 */
public class Solution5 {
    static StringBuilder answer = new StringBuilder();
    static int N;
    static int[] operation = new int[3];
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < 3; i++) {
            operation[i] = i;
        }
        for (int i = 0; i < T; i++) {
            N = Integer.parseInt(bufferedReader.readLine());
            StringBuilder builder = new StringBuilder();
            builder.append("1");
            dfs(1, 2, builder);
            answer.append("\n");
        }
        System.out.println(answer);
    }

    static void dfs(int depth, int start, StringBuilder builder) {
        if (depth == N && calc(builder)) {
            answer.append(builder).append("\n");
        }

        for (int i = start; i <= N; i++) {
            for (int j = 0; j < operation.length; j++) {
                switch (operation[j]) {
                    case 0:
                        dfs(depth + 1, i + 1, new StringBuilder(builder).append(" ").append(i));
                        break;
                    case 1:
                        dfs(depth + 1, i + 1, new StringBuilder(builder).append("+").append(i));
                        break;
                    case 2:
                        dfs(depth + 1, i + 1, new StringBuilder(builder).append("-").append(i));
                        break;
                }
            }
        }
    }

    static boolean calc(StringBuilder builder) {
        String s = builder.toString().replaceAll(" ", "");
        List<Integer> operand = new ArrayList<>();
        List<Character> operator = new ArrayList<>();
        int num = 0;
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '+' || c == '-') {
                operator.add(c);
                operand.add(num);
                num = 0;
                count = 0;
            } else {
                if (count == 0) {
                    num = c - '0';
                    count++;
                } else {
                    num = num * 10 + c - '0';
                }
            }
        }
        operand.add(num);

        int sum = operand.get(0);
        for (int i = 0; i < operator.size(); i++) {
            Character character = operator.get(i);
            if (character == '+') {
                sum += operand.get(i + 1);
            } else {
                sum -= operand.get(i + 1);
            }
        }

        return sum == 0;
    }
}
