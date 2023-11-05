package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 2331 반복수열
 */
public class Solution29 {
    static Stack<Integer> stack = new Stack<>();
    static int A;
    static int P;
    static int duplication;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] split = bufferedReader.readLine().split(" ");
        A = Integer.parseInt(split[0]);
        P = Integer.parseInt(split[1]);
        dfs(A);
        while (stack.peek() != duplication) {
            stack.pop();
        }
        stack.pop();

        System.out.println(stack.size());
    }

    private static void dfs(int a) {
        if (stack.contains(a)) {
            duplication = a;
            return;
        }
        stack.add(a);
        int newA = 0;

        while (a != 0) {
            int i = a % 10;
            newA += Math.pow(i, P);
            a = a / 10;
        }
        dfs(newA);
    }
}
