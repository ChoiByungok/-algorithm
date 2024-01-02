package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 15815 천재 수학자 성필
 * 후위표기법을 계산하는 문제이다
 * 피연산자들을 만나면 스택에 넣고 연산자들을 만나면 스택에서 요소 2개를 꺼내어 해당 연산자 연산을 수행한 뒤
 * 그 그결과를 다시 스택에 넣어주면 된다. 이것들을 반복하면 최종결과가 스택에 들어가 있게 된다.
 * 예제처럼 123*+ 이라는 문자열이 들어오면 스택에는 1 2 3이 들어가게 되고
 * * 를 만나는 순간 2 와 3 이 나와 * 연산을 수행한다. 그럼 6이 되고 6은 다시 스택에 들어간다.
 * 그럼 1 6 이 스택에 들어가게 되는데 다시 +를 만나 +연산을 수행하여 7이 되고 7은 스택에 들어가게 된다.
 * 문자열이 끝났으므로 최종 답은 스택에 들어가있는 7이 되게 되는 것이다.
 */
public class Solution86 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String readLine = bufferedReader.readLine();
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < readLine.length(); i++) {
            char c = readLine.charAt(i);
            switch (c) {
                case '+' :
                    int pop1 = stack.pop();
                    int pop2 = stack.pop();
                    stack.push(pop2 + pop1);
                    break;
                case '-' :
                    int pop3 = stack.pop();
                    int pop4 = stack.pop();
                    stack.push(pop4 - pop3);
                    break;
                case '*' :
                    int pop5 = stack.pop();
                    int pop6 = stack.pop();
                    stack.push(pop6 * pop5);
                    break;
                case '/' :
                    int pop7 = stack.pop();
                    int pop8 = stack.pop();
                    stack.push(pop8 / pop7);
                    break;
                default:
                    stack.push(Character.getNumericValue(c));
            }
        }
        System.out.println(stack.pop());
    }
}
