package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 11899 괄호 끼워넣기
 * 열린 괄호가 들어왔을떄 카운트랑 닫힌 괄호가 들어왔을 떄 카운트 변수를 2개 놓고
 * 스택자료구조를 이용해 완전한 괄호열을 만든 뒤에
 * 스택사이즈랑 입력받은 문자열의 길이의 차이를 이용해 답을 구했다.
 * 내 코드는 가독성이 떨어지고 너무 길어졌는데 다른사람의 풀이를 보니
 * 열린 괄호 '(' 가 들어왔을 떄는 그냥 스택에 넣고
 * 닫힌 괄호가 들어왔을때 스택이 비어있으면 카운트를 1 증가시키고
 * 스택이 비어있지 않으면 스택에서 요소를 하나 제거하는 식으로 해서
 * 카운트 + 스택 사이즈로 계산을 하였다.
 * 즉 ()()))라는 문자열을 입력 받았을때 스택사이즈는 0 카운트는 2 답은 2가 되는것이다.
 */
public class Solution83 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String readLine = bufferedReader.readLine();
        Stack<Character> stack = new Stack<>();

        // 나의 풀이
        int openBracket = 0; //열린괄호 카운트
        int closeBracket = 0; //닫힌괄호 카운트
        for (int i = 0; i < readLine.length(); i++) {
            char c = readLine.charAt(i);
            if (c == '(') { //열린 괄호일떄
                if (closeBracket > 0) {
                    int count = closeBracket;
                    while (closeBracket > 0) {
                        stack.push('(');
                        closeBracket--;
                    }
                    while (count > 0) {
                        stack.push(')');
                        count--;
                    }
                }
                stack.push(c);
                openBracket++;
            } else if (c == ')') { //닫힌 괄호일떄
                if (stack.isEmpty()) {
                    closeBracket++;
                } else {
                    if (stack.peek() == '(') {
                        stack.push(c);
                        openBracket--;
                    } else {
                        if (openBracket > 0) {
                            stack.push(c);
                            openBracket--;
                        } else {
                            closeBracket++;
                        }
                    }
                }
            }
        }
        while (closeBracket > 0) {
            stack.push('(');
            stack.push(')');
            closeBracket--;
        }
        while (openBracket > 0) {
            stack.push(')');
            openBracket--;
        }
        System.out.println(stack.size() - readLine.length());

        //다른사람의 풀이
        /*int closeCount = 0;
        for (int i = 0; i < readLine.length(); i++) {
            char c = readLine.charAt(i);
            if (c == '(') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    closeCount++;
                } else {
                    stack.pop();
                }
            }
        }
        System.out.println(stack.size() + closeCount);*/
    }
}
