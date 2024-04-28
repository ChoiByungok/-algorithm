package baekjoon3;

import java.io.*;
import java.util.*;

/**
 * 1038 감소하는 수 (Gold5)
 * 브루트포스 알고리즘
 * 백트래킹
 * 한참 고민하다가 다른사람의 풀이를 보고 이해함
 * 첫번째 선택한 숫자보다 작은 숫자들의 리스트들을 조합하여 해결하면 되는데
 * 우선 0 ~ 9 까지 반복문을 돌려서 dfs 재귀를 실행시킨다.
 * 그 숫자를 나눠서 나머지가 0이 나오면 재귀를 탈출 시킨다. 1의 자리가 0이면 그보다 감소하는 수가 존재할 수 없기 때문이다.
 * 예를들어 start가 2이고 dfs에 메서드에 2를 넣고 재귀를 돌렸다면
 * 우선 리스트에 2를 담아주고 그 수를 10으로 나눈 나머지를 또 반복문을 돌린다.
 * 그 수가 2보다 작고 0보다 같거나 큰 수만 반복하기 때문에
 * 그 다음 수는 1이다. 그렇게 조합 된 수 21이 다음 재귀로 호출된다.
 * 21을 10으로 나눈 나머지는 1이고 이 수를 0까지 감소시키며 반복하면 0밖에 없다.
 * 그럼 다시 이 숫자들을 조합한다 그러면 210이 되고
 * 이 수를 매개변수로 또다시 재귀를 호출시킨다 그러면 10으로 나눴을때 0이되고 재귀를 탈출한다.
 * 이런식으로 감소하는 수를 리스트에 차례대로 담은 뒤 오름차순으로 정렬시키면 된다.
 * 0~9 까지 숫자로 감소하는 수를 조합하면 0을 포함해서 1023개가 나오게 된다.
 * 그리하여 1023번째 감소하는 숫자는 존재하지 않는다.
 */
public class Solution4 {
    static int N;
    static List<Long> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < 10; i++) {
            dfs(i);
        }
        Collections.sort(list);
        System.out.println(N <= 1022 ? list.get(N) : -1);
    }
    static void dfs(long num) {
        list.add(num);

        long mod = num % 10;
        if (mod == 0) {
            return;
        }

        for (long i = mod - 1; i >= 0; i--) {
            dfs(num * 10 + i);
        }
    }
}
