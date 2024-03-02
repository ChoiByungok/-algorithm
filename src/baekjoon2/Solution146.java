package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 1138 한줄로 줄서기 (Silver2)
 * 쉬운문제인줄 알고 접근했다가 결국 해결 못하고
 * 다른사람의 풀이를 보고 간신히 이해함
 * 앞에 키 큰 사람 숫자를 인덱스로 사용할수 있겠다 싶어서 나도
 * 이렇게 도전했었는데 중복되는값을 어떻게 처리할 지 몰라서 못풀었다가
 * 리스트에 인덱스값을 주고 그냥 밀어버리는 방식을 쓸거라곤 생각조차도 못했음
 */
public class Solution146 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        int N = Integer.parseInt(bufferedReader.readLine());
        int[] arr = new int[N + 1];
        List<Integer> list = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());

        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(tokenizer.nextToken());
        }
        System.out.println("arr = " + Arrays.toString(arr));

        for(int i = N; i >= 1; i--) {
            list.add(arr[i], i);
            System.out.println("list = " + list);
        }

        for (Integer integer : list) {
            answer.append(integer).append(" ");
        }

        System.out.println(answer);
    }
}
