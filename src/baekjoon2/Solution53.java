package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 1935 후위표기식2
 * 처음에 어떻게 알파벳에 숫자를 대입할 수 있을까 고민함
 * c - 'A' + 1 이런식으로 접근했다가 예제는 다 맞추고 5퍼센트에서 틀림
 * 위 방법은 무조건 A는1 B는2 C는3 처럼 순차적이어야 함 예를 들어 1 3 5 7 9 같은 숫자가 들어오면 무조건 틀리게 되어있음
 * 후위 연산 자체는 어떻게 하는지 아는데 알파벳을 숫자에 대입 하는 방법을 몰라서 해맴
 */
public class Solution53 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        Stack<Double> stack = new Stack<>();
        int N = Integer.parseInt(bufferedReader.readLine());
        String readLine = bufferedReader.readLine();
        double[] arr = new double[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Double.parseDouble(bufferedReader.readLine());
        }
        for (int i = 0; i < readLine.length(); i++) {
            char c = readLine.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                stack.push(arr[c - 'A']);
            } else {
                Double pop1 = stack.pop();
                Double pop2 = stack.pop();
                switch (c) {
                    case '*' :
                        stack.push(pop2 * pop1);
                        break;
                    case '/' :
                        stack.push(pop2 / pop1);
                        break;
                    case '+' :
                        stack.push(pop2 + pop1);
                        break;
                    case '-' :
                        stack.push(pop2 - pop1);
                        break;
                }
            }
        }
        System.out.printf("%.2f", stack.pop());
    }
}
