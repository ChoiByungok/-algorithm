package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 2346 풍선 터뜨리기
 * 아마 아래 알고리즘 분류에 덱 이라고 쓰여저 있지 않으면 백만년이 걸려도 못풀었을 문제
 * 처음에는 원형큐를 써야 하나 생각하다가 예전에 비슷한 문제를 푼적이 있어서 기억을 더듬어 풀어봄
 * 5
 * 3 2 1 -3 -1
 * 이라고 입력 받았을 때 1 4 5 3 2 가 출력이 되야 하는데 1 5 4 3 2 가 출력이되어서 생각해보니
 * 음수 방향은 덱에서 맨 뒤에 요소를 숫자만큼 앞으로 가져오기만 하면되기 때문에 상관없는데
 * 양수 방향일때 맨 앞 요소를 뒤로 보내는데 맨 앞에 풍선이 터져 버리니 숫자보다 + 1 만큼 더 가게 되어서 -1 만큼 반복하니 해결되었음
 */
public class Solution46 {
    static class Balloon {
        int index;
        int num;

        public Balloon(int index, int num) {
            this.index = index;
            this.num = num;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        StringBuilder answer = new StringBuilder();
        int[] num = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Deque<Balloon> deque = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            deque.add(new Balloon(i + 1, num[i]));
        }

        while (!deque.isEmpty()) {
            Balloon balloon = deque.pollFirst();
            answer.append(balloon.index).append(" ");
            int pointer = balloon.num;
            if (deque.isEmpty()) {
                break;
            }
            if (pointer > 0) {
                for (int i = 0; i < pointer - 1; i++) {
                    deque.addLast(deque.pollFirst());
                }
            } else {
                int abs = Math.abs(pointer);
                for (int i = 0; i < abs; i++) {
                    deque.addFirst(deque.pollLast());
                }
            }
        }
        System.out.println(answer);
    }
}
