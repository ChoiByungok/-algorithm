package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 2263 트리의 순회 (Gold1)
 * 이전에 이 문제와 비슷한 문제를 풀었었다.
 * 그때는 전위순회와 중위순회 결과를 가지고 후위순회의 결과를 출력하는 문제였고
 * 이번에는 중위순회와 후위순회 결과를 가지고 전위순회의 결과를 출력하는 문제다.
 * 근데 왜이렇게 헷갈리는지 모르겠다. 후위순회의 맨 마지막 요소는 이 트리의 루트이다
 * 그 루트의 인덱스 번호를 중위순회에서 찾은 뒤 분할해 나가면 후위순회 결과를 도출해낼 수 있다고 생각했는데
 * 내 생각과는 다르게 루트에서 왼쪽 서브트리는 잘 찾아 들어가는데 오른쪽 서브트리는 못찾아들어가서
 * 계속 순환이 일어나 결국 스택오버플로우가 발생한다. 그냥 단지 조건이 살짝 바뀌었을 뿐인데 그냥 아예 못풀겠다.
 * 이전껀 전위 결과에서만 범위를 조정하면 됐었는데 이번껀 중위연산의 범위랑 후위연산의 범위를 같이 조정해나가면서 매개변수를 보내야 했기때문에
 * 어려웠던 것 결국 답안지를 보고 풀었다.
 */
public class Solution64 {
    static List<Integer> inOrder = new ArrayList<>();
    static List<Integer> postOrder = new ArrayList<>();
    static List<Integer> preOrder = new ArrayList<>();
    static StringBuilder answer = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < N; i++) {
            inOrder.add(Integer.parseInt(tokenizer.nextToken()));
        }

        tokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < N; i++) {
            postOrder.add(Integer.parseInt(tokenizer.nextToken()));
        }

        preOrder(0, N - 1, 0, N - 1);
        for (Integer integer : preOrder) {
            answer.append(integer).append(" ");
        }
        System.out.println(answer);
    }

    static void preOrder(int is, int ie, int ps, int pe) {
        if (is <= ie && ps <= pe) {
            Integer root = postOrder.get(pe);
            preOrder.add(root);

            int index = inOrder.indexOf(root);

            preOrder(is, index - 1, ps, ps + index - is - 1);
            preOrder(index + 1, ie, ps + index - is, pe - 1);
        }
    }
}
