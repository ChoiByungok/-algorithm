package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 2023 신기한 소수 (Gold5)
 * 2 3 5 7 부터 차례대로 탐색을 진행한다.
 * 20 ~ 29 사이에 소수가 있는지 탐색을 한다.
 * 먼저 23이 나오므로 23을 다시 dfs탐색을 진행한다.
 * 230~239까지 소수를 찾아본다.
 * 233이 나오므로 233을 다시 dfs탐색한다.
 * 2330~ 2339 사이에서 소수를 찾는데
 * 2333이 나오고 이 숫자를 dfs탐색한다.
 * 2333은 4자리 이므로 출력하고 재귀를 종료시킨다.
 * 그러면 2330 에서 3번째 까지 탐색을 한 상태이니 4부터 9까지 다시 탐색을 진행한다.
 * 그러면 2339에서 걸리고 이 숫자를 다시 dfs를 보내고 해당 숫자는 4자리이므로 또 출력하고 종료한다.
 * 이렇게 계속 재귀를 반복하다보면 신기한 소수를 전부 찾게 될 수 있다.
 */
public class Solution3 {
    static int N;
    static StringBuilder answer = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());
        List<Integer> list = new ArrayList<>();

        list.add(2);
        list.add(3);
        list.add(5);
        list.add(7);

        for (Integer prime : list) {
            dfs(prime, 1);
        }

        System.out.println(answer);
    }

    static void dfs(int prime, int length) {
        if (length == N) {
            answer.append(prime).append("\n");
            return;
        }

        int num = prime * 10;

        for (int i = 0; i <= 9; i++) {
            if (isPrime(num + i)) {
                dfs(num + i, length + 1);
            }
        }
    }

    private static boolean isPrime(int num) {
        for (int i = 2; i < Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
