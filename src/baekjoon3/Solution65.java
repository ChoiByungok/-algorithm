package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 11812 K진 트리 (Gold3)
 * 루트가 1로 시작하고 K진 트리를 만들어 완성 시킨뒤
 * 공통조상을 찾아서 거리를 구하면 되는 문제인줄 알고 구현한 뒤
 * 제출했는데 NumberFormatException 이 발생하였다.
 * 알고보니 N의 범위가 int 형 범위를 벗어난다.
 * 이렇게 된 이상 내가 구현했던 방법을 전부 갈아엎어야 한다. 배열의 길이를 long 형으로 만들 수 없기 때문이다.
 * 이건 트리를 구현하여서 만드는 문제가 아니라 n진 트리라는것이 정해져 있기때문에 임의의 노드를 받으면
 * 해당 노드의 level이 몇인지 그러면 두 노드의 공통 조상은 어떤 노드인지 계산을 통하여 구하는 문제인 것이다.
 * 그러면 특정 노드를 입력 받았을 때 그 노드의 레벨을 구하는 공식과 그 노드의 부모노드를 구하는 공식을 정립하고
 * 계산을 해야 한다. 이래서 알고리즘 분류에 수학이 들어가 있던것이다.
 * 두 노드의 레벨을 공식을 이용하여 구하고 레벨을 동기화 시킨다음에
 * 부모노드 구하는 공식을 이용하여 부모가 같아지면 그때 노드의 거리를 출력하면 될거같은데
 * 공식을 어떻게 구해야 할지 참 어렵다.
 * 결국 답지를 보고 풀었다. 부모노드의 인덱스를 구하는 공식은 현재노드 인덱스 -2 / K + 1 이다
 * 두 노드의 크기를 비교하여 숫자가 큰 노드를 부모노드의 인덱스를 구하는 공식을 이용하여 바꾼다 그때 카운트를 1씩 증가시킨다.
 * 그것을 계속 반복하다보면 두 노드의 숫자가 같아지는데 이때 증가한 카운트가 두 노드의 거리가 된다.
 * 그리고 주의할 점은 편향 이진트리일때 인데 이때는 그냥 두 노드의 차를 계산하면된다.
 */
public class Solution65 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        long N = Long.parseLong(tokenizer.nextToken());
        int K = Integer.parseInt(tokenizer.nextToken());
        int Q = Integer.parseInt(tokenizer.nextToken());

        for (int i = 0; i < Q; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            long node1 = Long.parseLong(tokenizer.nextToken());
            long node2 = Long.parseLong(tokenizer.nextToken());

            if (K == 1) {
                answer.append(Math.abs(node1 - node2)).append("\n");
            } else {
                int cnt = 0;
                while(node1 != node2){
                    if (node1 > node2) {
                        node1 = (node1 - 2) / K + 1;
                    } else {
                        node2 = (node2 - 2) / K + 1;
                    }
                    cnt++;
                }
                answer.append(cnt).append("\n");
            }
        }

        System.out.println(answer);
    }
}
