package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 12101 1, 2, 3 더하기 2 (Silver1)
 * 매개변수로 숫자의 합이랑 StringBuider로 조합한 문자열을 보내주었다.
 * 이 때 매개변수로 넘어간 숫자의 합이 N보다 크다면 뒤에는 볼 필요가 없으니 백트래킹 해주고
 * 숫자가 같으면 여러가지 방법중 하나이니 넘어온 StringBuilder을 리스트에 넣어준다.
 * 그렇게 재귀를 다 마치고 나면 1 2 3 으로 N을 만드는 방법이 전부 리스트에 담겨져 있을것이다.
 * 어차피 1부터 살펴보았기 때문에 재귀가 끝난 뒤 정렬을 해줄필요가 없고
 * 마지막에 입력으로 주어진 K가 완성된 리스트의 사이즈보다 크면 -1을 출력해주고 아니면 리스트의 K-1 번째 요소를 출력해주면 된다.
 */
public class Solution21 {
    static int N;
    static int K;
    static List<StringBuilder> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] split = bufferedReader.readLine().split(" ");
        N = Integer.parseInt(split[0]);
        K = Integer.parseInt(split[1]);
        for (int i = 1; i <= 3; i++) {
            backTracking(i, new StringBuilder().append(i));
        }
        System.out.println(list.size() >= K ? list.get(K - 1) : -1);
    }

    static void backTracking(int sum, StringBuilder builder) {
        if (sum > N) {
            return;
        }
        if (sum == N) {
            list.add(builder);
            return;
        }

        for (int i = 1; i <= 3; i++) {
            backTracking(sum + i, new StringBuilder(builder).append("+").append(i));
        }
    }
}
