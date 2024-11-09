package Programmers3;

import java.util.*;
import java.util.stream.IntStream;

/**
 * 줄 서는 방법 Lv.2
 * 생각나는 대로 느낌대로 풀어보았는데 시간초과 발생
 * k번째 배열을 반환하라 했는데 매개변수로 들어온 값이 long 형태길래
 * 일반적인 배열로 해결할 수 없겠다 판단하여 map을 이용하여 풀어봄
 * 근데 문제 설명을 들어보니 n의 값이 20이하라 나올 수 있는 k의 최대값은 20!이 되버리기 때문에 시간초과, 메모리 초과가 발생하는듯 싶다.
 * 그렇다면 이 문제를 어떻게 해결하야 할까
 * 결국 챗지피티의 힘을 빌렸다....
 */
public class Solution54 {
    static Map<Long, int[]> map = new HashMap<>();
    static long index = 1;
    static boolean[] visited;
    public int[] solution(int n, long k) {
        int[] num = IntStream.range(0, n).map(i -> i + 1).toArray();
        visited = new boolean[n];
        dfs(n, 0, num, "");
        return map.get(k);
    }

    static void dfs(int n, int depth, int[] num, String s) {
        if (n == depth) {
            map.put(index++, Arrays.stream(s.split(" ")).mapToInt(Integer::parseInt).toArray());
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(n, depth + 1, num, s + num[i] + " ");
                visited[i] = false;
            }
        }
    }

    public int[] solution1(int n, long k) {
        int[] answer = new int[n];
        List<Integer> numbers = new ArrayList<>();
        long factorial = 1;

        for (int i = 1; i <= n; i++) {
            numbers.add(i);
            factorial *= i;
        }

        k--;

        for (int i = 0; i < n; i++) {
            factorial /= (n - i);
            int index = (int) (k / factorial);
            answer[i] = numbers.remove(index);
            k %= factorial;
        }

        return answer;
    }
    public static void main(String[] args) {
        Solution54 solution54 = new Solution54();
        System.out.println(Arrays.toString(solution54.solution(3, 5)));
        System.out.println(Arrays.toString(solution54.solution1(3, 5)));
    }
}
