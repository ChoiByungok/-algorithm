package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 2661 좋은수열 (Gold4)
 * 완성되는 수열의 길이가 80자리일수도 있으니 이건 long형으로도 만들어지지 않아서
 * StringBuilder을 사용하였다. 그리고 문제에서 여러가지 좋은 수열이 완성되는데 그 중 가장
 * 작은 숫자를 출력하라고 했지만 이 문제는 결국 처음 완성되는 수열이 가장 작은 수열이 되는 문제이므로
 * 문자열을 비교할 필요가 없다 나는 이 사실을 모르고 완성된 수열문자열들을 비교하다가 메모리 초과가 발생하였다.
 * 그럼 어떻게 해당 수열이 좋은 수열인지 아닌지를 판단할 수 있을까
 * 처음에 1자리라면 1이 된다. 그다음 2자리일땐 12 3자리일땐 121이 된다 4자리일땐 1213 이런식으로 증가하게 된다.
 * 예를들어 123123이라는 수열이 들어왔을때 수열의 길이는 6자리이므로 3번만 반복하면된다.
 * 뒤에서부터 1자리를 잘라 비교한다. 그러면 2 와 3이 나오는데 같지않으므로 넘어간다.
 * 뒤에서부터 2자리를 잘라 비교한다. 그러면 31과 23 이나오는데 같지않으므로 넘어간다.
 * 뒤에서부터 3자리를 잘라 비교한다 그러면 123 과 123이 나오는데 같으므로 해당 수열은 좋은수열이 아닌것이다.
 * 문자는 뒤에서부터 추가되기때문에 뒤에서부터 비교를 하면 앞에서 비교를 하는거보다 빨리 판단할 수 있다.
 * 뒤에서부터 비교를 한다는건 다른사람의 풀이를 보고 배웠다.
 */
public class Solution12 {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());
        dfs(0, new StringBuilder());
    }

    static void dfs(int depth, StringBuilder builder) {
        if (depth == N) {
            System.out.println(builder);
            System.exit(0);
        }
        for (int i = 1; i <= 3; i++) {
            if (check(new StringBuilder(builder).append(i))) {
                dfs(depth + 1, new StringBuilder(builder).append(i));
            }
        }
    }

    static boolean check(StringBuilder builder) {
        int check = 1;
        for (int i = 0; i < builder.length() / 2; i++) {
            String substring1 = builder.substring(builder.length() - check);
            String substring2 = builder.substring(builder.length() - (2 * check), builder.length() - check);
            if (substring1.equals(substring2)) {
                return false;
            }
            check++;
        }
        return true;
    }
}
