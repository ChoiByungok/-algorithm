package baekjoon2;

import java.io.*;
import java.util.Stack;

/**
 * 4889 안정적인 문자열
 * 연산은 여는 괄호를 닫는 괄호로 바꾸거나, 닫는 괄호를 여는 괄호로 바꾸는 것 2가지이다.
 * 일단 해당 문자열이 안정적인지 아닌지 판별하기 위한 메서드를 하나 만들어야 함
 * 문자열의 맨처음은 무조건 '{' 여야 되고 마지막은 '}' 이어야 된다.
 * 그리하여 내가 생각해낸 방법은 "}}{}{}}}{}" 이런 문자열이 들어왔다고 가정했을 때
 * 처음 문자는 '}' 이므로 '{'로 바꾼다 마지막은 '}' 이니깐 바꿀 필요가 없다. 하나가 바뀌었으니 카운트는 1이되고
 * 처음과 마지막 문자를 없애준다. 그러면 "}{}{}}}{" 가된다. 마찬가지로 맨 처음과 맨 마지막을 바꾸어 줘야한다.
 * 그러면 카운트는 3이되고 {}{}}}이 남게 된다. 계속 반복하면 답은 4가나오게 된다.
 * 라고 처음에 생각했었는데 해당 문자열의 안정화를 위한 최소 연산의 수는 2이기 때문에 틀렸다.
 *
 * 결국 구글링 해서 풀었음 '{' 가 들어왔을 떈 그냥 스택에 넣어주고
 * '}' 가 들어왔을 때 스택이 비어있으면 '{' 로 바꾸어 넣어주고 카운트를 증가시킨다. 아니면 그냥 pop()해준다
 * 마지막에 count 값이랑 (stack.size() / 2)랑 더하는 이유는 "{{{{{}" 같은 문자열이 들어왔을떄
 * 연산을 다 끝내도 스택안에 {{{{ 가 들어있기 때문에 (해당 요소들의 갯수 / 2) 를 하면
 * 딱 올바른 문자열을 만드는데 필요한 최소한의 연산의 수가 된다.
 */
public class Solution66 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        int repeat = 1;
        while (true) {
            int count = 0;
            Stack<Character> stack = new Stack<>();
            String readLine = bufferedReader.readLine();
            if (readLine.contains("-")) {
                break;
            }
            for (int i = 0; i < readLine.length(); i++) {
                char c = readLine.charAt(i);
                if (c == '{') {
                    stack.push(c);
                } else {
                    if (stack.isEmpty()) {
                        stack.push('{');
                        count++;
                    } else {
                        stack.pop();
                    }
                }
            }
            answer.append(repeat++).append(". ").append(count + (stack.size() / 2)).append("\n");
        }
        System.out.println(answer);
    }

}
