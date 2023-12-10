package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 1662 압축
 * 처음에는 answer 라는 변수를 두어서 계산을 했다 45(123)56(11) 이라는 문자가 들어왔을 떄 답은 29가 나와야 하는데
 * 내가 짠 로직은 서로 상관없는 괄호간에 간섭이 일어나 5(123) 에서 나온 15의 값이
 * 뒤쪽의 6(11) 에도 간섭이 되어 102가 되었고 최종답이 104가 되는 결과가 일어났다. 문제를 제출 하기 전까지
 * 문제에서 제공되는 예제가 모두 통과되어 답인줄 알았으나 6%에서 틀렸다고 뜨길래 계속 고민하다가 괄호가 서로 독립적일때 간섭 받으면
 * 안된다는 결과를 도출하게 되었다. 나는 클래스를 하나 만들어 압축되지 않은 문자열은 false로 두고 압축된 문자열은 true로 두어
 * 구분한 뒤 ')' 라는 문자가 들어왔을 떄 스택에서 클래스를 빼고 그 빠진 클래스가 true인지 false인지 구분하여 true면 계산되었던 문자열의
 * 길이를 더해주고 아니면 1만 증가시키도록 하니 통과되었다.
 */
public class Solution64 {
    static class Length {
        String length;
        boolean isLength;

        public Length(String length, boolean isLength) {
            this.length = length;
            this.isLength = isLength;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String readLine = bufferedReader.readLine();
        Stack<Length> stack = new Stack<>();
        int answer = 0;
        for (int i = 0; i < readLine.length(); i++) {
            char c = readLine.charAt(i);
            if (c != ')') {
                stack.add(new Length(String.valueOf(c), false));
            } else {
                int count = 0;
                while (!stack.peek().length.equals("(")) {
                    Length pop = stack.pop();
                    if (pop.isLength) {
                        count += Integer.parseInt(pop.length);
                    } else {
                        count++;
                    }
                }
                stack.pop();
                stack.add(new Length(String.valueOf(count * Integer.parseInt(stack.pop().length)), true));
            }
        }
        while (!stack.isEmpty()) {
            Length pop = stack.pop();
            if (pop.isLength) {
                answer += Integer.parseInt(pop.length);
            } else {
                answer++;
            }
        }
        System.out.println(answer);
    }
}
