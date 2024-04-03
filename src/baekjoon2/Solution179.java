package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 1699 제곱수의 합 (Silver2)
 * 처음에는 이 문제를 이렇게 접근했었다.
 * 5라는 숫자가 주어진다고 가정했을 때 5랑 가장 가까운 제곱수를 찾는다.
 * (int)Math.sqrt(5) 를 하면 2가 나오고 그값을 다시 제곱한 뒤 5에서 뺀다 5 - 4 = 1
 * 그럼 1이되고 마찬가지로 한번더 반복하면 1이나오고 그 값을 1에서 빼면 0이된다. 이렇게 주어진 값이 0이 됐을때 반복문을 탈출 시켜주었고
 * 총 몇번을 반복했는가를 출력하였다. 예제도 다 맞길래 제출했더니 1퍼센트에서 틀렸다고 나왔다.
 * 753이라는 숫자가 주어졌을때 내 로직때로 하면 첫번째 숫자로 27이 나와서 753 - 729 = 24
 * 그 다음 4 가 나와서 24 - 16 = 8, 그다음은 8 - 4 = 4, 그 다음 4 - 4 = 0 이 나와서 총 4번을 반복하게 되는데
 * 사실 753은 25 8 8 의 제곱수로 이루어져 있어 3번으로 가능한것이였다.
 * 그래서 이걸 어떻게 접근해야 하나 고민하고있었는데 이 문제의 알고리즘 분류는 다이나믹프로그래밍이었던 것이다.
 * 그럼 규칙이 존재하고 점화식을 유추해내야 하는데 도저히 내 머리로 유추할 수가 없어서 결국 구글링을 하였다.
 * 사실 이 글을 쓰는 시점에서도 아직도 저 점화식이 이해가안간다. 손코딩으로 배열을 하나하나 채워보며 어떻게 돌아가는지는 이해했으나
 * 혼자서 짜보라고 했으면 백만년이 걸려도 못풀었을거같다. 역시 다이나믹프로그래밍이야말로 순수한 알고리즘 재능영역인거같다.
 */
public class Solution179 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        int[] arr = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            arr[i] = i;
            for (int j = 1; j * j <= i; j++) {
                if (arr[i] > arr[i - j * j] + 1) {
                    arr[i] = arr[i - j * j] + 1;
                }
            }
        }
        System.out.println(arr[N]);
    }
}
