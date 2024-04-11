package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 1790 수 이어 쓰기 2 (Gold5)
 * 숫자를 하나씩 StringBuilder에 넣어서 K번째 인덱스가 무슨 숫자인지 알아낼수 있다면
 * 참 쉬울텐데 그렇게 하면 메모리초과가 발생한다.
 * 그러면 수학적인 방법으로 해당 숫자를 찾아내야 하는데
 * 결국 나는 그 수학적인 방법을 찾지 못했고 구글링을 하여 문제를 풀었다.
 * 우선 target 넘버를 찾아야 하는데 그 target이 한자리수인지 두자리수인지 세자리수인지 파악해야 한다.
 * 자세히보면 규칙이 존재하는데
 * 한자리수일땐 1 * 9 두자리 수일땐 (1 * 9) + (2 * 90) 세자리 수일땐 (1 * 9) + (2 * 90) + (3 * 900) 이 된다.
 * 20 23 이라는 예시가주어졌을때 23번째 수의 타겟넘버는 두자리 수라는것을 알 수 있다.
 * 1 * 9(9) 보다는 크고 (1 * 9) + (2 * 90)(189)보다는 작기 때문이다.
 * 23 에서 9를 빼면 14가 된다. (14 - 1) / 2(자릿수) 연산을 했을 때 몫은 건너뛸 숫자의 갯수이며 나머지는 타겟넘버의 자릿수가 된다.
 * 즉 13 / 2 의 몫 6은 10 11 12 13 14 15 의 숫자들이 되고 target넘버는 16이 되며
 * (14 - 1) % 2의 나머지 1은 타겟 넘버 16의 두번째 자리인 6이되는것이다.
 */
public class Solution187 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] split = bufferedReader.readLine().split(" ");
        int N = Integer.parseInt(split[0]);
        int K = Integer.parseInt(split[1]);

        long digit = 1;
        long numCount = 9;
        long target = 0;

        while (K > digit * numCount) {
            K -= (digit * numCount);
            target += numCount;

            digit++;
            numCount *= 10;
        }

        target = (target + 1) + ((K - 1) / digit);

        if (target > N) {
            System.out.println(-1);
        } else {
            System.out.println(String.valueOf(target).charAt((int) ((K - 1) % digit)));
        }
    }
}
