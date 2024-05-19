package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 1963 소수 경로 (Gold4)
 * 나는 입력받은 정수를 배열로 바꿔서 1033 이면
 * [1, 0, 3, 3] 으로 바꿈
 * 이 배열을 0번째 인덱스부터 즉 천의 자리부터
 * 하나씩 바꿔가며 그 숫자가 소수이면 큐에 담는식으로 넓이 우선탐색을 진행하였다.
 * 1033 이 돌고돌아 1033이 되는것을 방지하기 위해 (무한루프에 빠지는것을 방지하기 위해)
 * 숫자를 해시에 담아 이미 해시에 담겨 있으면 큐에 담지 않는식으로중복을 걸렀다.
 * 그런데 제출하고 보니 다른사람의 풀이와 시간이 많이 차이나는것을 보았다.
 * 나처럼 그때그때 소수인지 아닌지 계산하여 판별하는것이 아니라
 * 미리 1000~9999까지의 숫자의 소수가 어떤 수인지 계산하여 배열에 담아놓고 체크하는것을 보았다.
 */
public class Solution25 {
    static class Num {
        int num;
        int step;

        public Num(int num, int step) {
            this.num = num;
            this.step = step;
        }
    }
    static StringBuilder answer = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < T; i++) {
            String[] split = bufferedReader.readLine().split(" ");
            int A = Integer.parseInt(split[0]);
            int B = Integer.parseInt(split[1]);
            dfs(A, B);
        }
        System.out.println(answer);
    }

    static void dfs(int A, int B) {
        Set<Integer> set = new HashSet<>();
        Queue<Num> queue = new LinkedList<>();
        queue.add(new Num(A, 0));
        boolean impossible = true;
        while (!queue.isEmpty()) {
            Num poll = queue.poll();
            int num = poll.num;
            int step = poll.step;
            set.add(num);
            if (num == B) {
                answer.append(step).append("\n");
                impossible = false;
                break;
            }
            int[] array = intToArray(num);

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 10; j++) {
                    if (i == 0 && (j == 0 || array[i] == j)) {
                        continue;
                    }
                    if (array[i] == j) {
                        continue;
                    }
                    int[] ints = makeNewArray(i, j, array);
                    int digit = arrayToInt(ints);
                    if (isPrime(arrayToInt(ints)) && !set.contains(digit)) {
                        queue.add(new Num(digit, step + 1));
                    }
                }
            }
        }
        if (impossible) {
            answer.append("Impossible").append("\n");
        }
    }

    static int[] intToArray(int A) {
        int[] array = new int[4];
        int index = 3;
        while (A != 0) {
            int mod = A % 10;
            array[index--] = mod;
            A /= 10;
        }
        return array;
    }

    static int arrayToInt(int[] array) {
        return (array[0] * 1000) + (array[1] * 100) + (array[2] * 10) + array[3];
    }
    static boolean isPrime(int num) {
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
    static int[] makeNewArray(int index, int num, int[] array) {
        int[] temp = new int[4];
        System.arraycopy(array, 0, temp, 0, 4);
        temp[index] = num;
        return temp;
    }
}
