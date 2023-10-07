package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 17298 오큰수
 */
public class Solution70 {
    public static void main(String[] args) throws IOException {
        Stack<Integer> stack = new Stack<>();
        StringBuilder answer = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        int[] data = new int[N];
        int[] temp = new int[N];
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < N; i++) {
            data[i] = Integer.parseInt(stringTokenizer.nextToken());
        }
        stack.push(0);
        for (int i = 1; i < data.length; i++) {
            if (data[stack.peek()] >= data[i]) {
                stack.push(i);
            } else {
                while (data[stack.peek()] < data[i]) {
                    Integer index = stack.peek();
                    temp[index] = data[i];
                    stack.pop();
                    if (stack.isEmpty()) {
                        break;
                    }
                }
                stack.push(i);
            }
        }
        while (!stack.isEmpty()) {
            temp[stack.pop()] = -1;
        }

        for (int i : temp) {
            answer.append(i).append(" ");
        }
        System.out.println(answer);
    }
}
