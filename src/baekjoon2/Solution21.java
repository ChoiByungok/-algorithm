package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 1003 피보나치 함수
 */
public class Solution21 {
    static class ZeroOne {
        int zeroCnt;
        int oneCnt;

        public ZeroOne(int zero, int one) {
            this.zeroCnt = zero;
            this.oneCnt = one;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bufferedReader.readLine());
        StringBuilder answer = new StringBuilder();
        ZeroOne[] count = new ZeroOne[41];
        count[0] = new ZeroOne(1, 0);
        count[1] = new ZeroOne(0, 1);
        for (int i = 2; i <= 40 ; i++) {
            count[i] = new ZeroOne(count[i - 2].zeroCnt + count[i - 1].zeroCnt, count[i - 2].oneCnt + count[i - 1].oneCnt);
        }
        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(bufferedReader.readLine());
            answer.append(count[N].zeroCnt).append(" ").append(count[N].oneCnt).append("\n");
        }
        System.out.println(answer);
    }

}
