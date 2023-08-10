package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 28278 스택2
 */
public class Solution15 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        Stack<String> stack = new Stack<>();
        StringBuilder stringBuilder = new StringBuilder();
        int i = Integer.parseInt(bufferedReader.readLine());
        for (int j = 0; j < i; j++) {
            String[] split = bufferedReader.readLine().split(" ");
                switch (split[0]) {
                    case "1" :
                        stack.push(split[1]);
                        break;
                    case "2" :
                        if (stack.size() == 0) {
                            stringBuilder.append("-1").append("\n");
                        } else {
                            stringBuilder.append(stack.pop()).append("\n");
                        }
                        break;
                    case "3" :
                        stringBuilder.append(stack.size()).append("\n");
                        break;
                    case "4" :
                        if (stack.size() == 0) {
                            stringBuilder.append("1").append("\n");
                        } else {
                            stringBuilder.append("0").append("\n");
                        }
                        break;
                    case "5" :
                        if (stack.size() == 0) {
                            stringBuilder.append("-1").append("\n");
                        } else {
                            stringBuilder.append(stack.peek()).append("\n");
                        }
                        break;
                }
        }
        System.out.println(stringBuilder);
    }
}
