package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * 1351 무한 수열 (Gold5)
 * A0 = 1
 * Ai = A⌊i/P⌋ + A⌊i/Q⌋ (i ≥ 1)
 * 문제 자체는 간단해보이지만 입력받는 값중 하나가 long 형임
 * 자바에서는 long 형의 길이만한 배열을 만들 수가 없음
 * 해당 문제의 알고리즘 분류를 보니 해시맵과 다이나믹 프로그래밍을 이용하여 풀어야함
 * 대충 int 형으로 배열 만들어보고 값이 어떻게 담기나 출력 테스트 해봤는데
 * 중복되는 값들이 너무많음 이래서 해시맵을 써야 하는거같음
 * 고민하다가 어떻게 풀여야 할지 전혀 감이 잡히지 않아서 결국 구글링하여 풀음
 * 역시나 창의력이 가장 관건인 다이나믹 프로그래밍 알고리즘이 제일 어려운거같음
 * dp 알고리즘은 아래서부터 값을 찾아가는 bottom_up 방식이 있고
 * 위에서부터 값을 찾아 내려가는 top_down 방식이 있는데 해당 문제는 top_down 방식을 이용하여 풀어야 함
 * 우선 맵에 초기값 0, 1을 넣음
 * 그다음 찾아야 할 값을 재귀함수로 보냄
 * 재귀 함수는 매개변수로 넘어온 값이 맵에 들어있으면 그 값을 반환하고
 * 아니면 매개변수/P 의 값과 매개변수/Q 의값을 다시 재귀함수로 보내는것을 반복한다.
 * 이러면 중복값도 발생하지 않고 원하는값을 빠르게 찾을 수 있다.
 */
public class Solution110 {
    static int P;
    static int Q;
    static Map<Long, Long> map = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] npq = bufferedReader.readLine().split(" ");
        map.put(0L, 1L);
        long N = Long.parseLong(npq[0]);
        P = Integer.parseInt(npq[1]);
        Q = Integer.parseInt(npq[2]);
        System.out.println(find(N));
    }
    static long find(Long n) {
        if (map.containsKey(n)) {
            return map.get(n);
        }

        long p = find(n/P);
        long q = find(n/Q);
        long sum = p + q;
        map.put(n, sum);
        return sum;
    }
}
