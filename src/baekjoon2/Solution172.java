package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 12904 A와 B (Gold5)
 * 처음에는 이 문제를 잘못이해하고 있어서 1번연산을 하고 그 이후에 2번연산을 진행하는게 한번의 사이클인줄 알았다.
 * 공교롭게 예제도 그렇게 했을때 정답이 나오길래 제출했는데 틀렸다고 나옴
 * 알고보니 두 연산중에 하나를 선택하여 문자열을 바꿔가는 거였고
 * 그 이후 bfs탐색으로 문제를 풀어보았는데 메모리초과가 발생하였다.
 * 그래서 마지막으로 T문자열을 S문자열로 바꿀수 있는지를 판별해보았다
 * 그 방법으로는 T문자열의 마지막 문자가 A 일시에는 1번연산을 역으로 진행하였고
 * T문자열의 마지막 문자가 B일시에는 2번연산을 역으로 진행하여
 * T문자열이 S문자열과 길이가 같아졌을때 문자가 같으면 가능한 문자이고
 * 다르면 불가능한 문자라고 판별하였더니 통과되었다. 통과는 되었지만
 * bfs로 풀었을때 왜 메모리 초과가 발생했는지 궁금한 문제였다.
 */
public class Solution172 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String S = bufferedReader.readLine();
        String T = bufferedReader.readLine();
        boolean possible = false;
        while (true) {
            StringBuilder builder = new StringBuilder(T);

            if (builder.length() == S.length()) {
                if (builder.toString().equals(S)) {
                    possible = true;
                }
                break;
            }
            if (builder.charAt(builder.length() - 1) == 'A') {
                builder.delete(builder.length() - 1, builder.length());
            } else {
                builder.delete(builder.length() - 1, builder.length());
                builder.reverse();
            }

            T = builder.toString();
        }

        System.out.println(possible ? 1 : 0);
    }
}
