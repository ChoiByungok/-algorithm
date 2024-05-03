package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 16198 에너지 모으기 (Silver1)
 * 재귀반복의 가장 중요한 요소는 함수를 반환하는 조건을 잘 짜야한다는 것이다.
 * 이 문제의 경우 맨 앞 구슬과 맨 뒤 구슬을 선택할 수 없다고 했으니 매개변수로 넘어오는 배열의 길이가 2일때
 * 반환 조건을 만들어 주었다. 그때 같이 넘어온 매개변수 에너지랑 지금까지 기록한 에너지중 더 큰것이 정답이 된다.
 * 근데 이거 보다 까다로웠던 것은 새로운 배열을 요소를 하나 제거한 뒤에 넘겨주어야 한다는것인데.
 * 어떻게 해야 예외가 안터질 수 있을까 고민하다가 그냥 단순하게 인덱스 까지 끊고 복사하고
 * 인덱스부터 마지막까지 복사하고 이런식으로 해결하였다.
 */
public class Solution9 {
    static int N;
    static int[] marble;
    static int answer = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        marble = new int[N];
        for (int i = 0; i < N; i++) {
            marble[i] = Integer.parseInt(tokenizer.nextToken());
        }
        dfs(marble, 0);
        System.out.println(answer);
    }

    static void dfs(int[] arr, int energy) {
        if (arr.length == 2) {
            answer = Math.max(answer, energy);
            return;
        }

        for (int i = 1; i < arr.length - 1; i++) {
            dfs(makeNewArray(arr, i), energy + (arr[i - 1] * arr[i + 1]));
        }
    }

    static int[] makeNewArray(int[] arr, int index) {
        int[] newArr = new int[arr.length - 1];
        for (int i = 0; i < index; i++) {
            newArr[i] = arr[i];
        }
        for (int i = index + 1; i < arr.length; i++) {
            newArr[i - 1] = arr[i];
        }
        return newArr;
    }
}
