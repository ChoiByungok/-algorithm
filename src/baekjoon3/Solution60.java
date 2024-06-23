package baekjoon3;

import java.io.*;
import java.util.*;

/**
 * 4256 트리 (Gold2)
 * 트리의 전위순회한 결과와 중위순회한 결과를 보고 후위순회를 출력해야하는 문제
 * 전위순회 결과로 루트 노드를 알 수 있고 중위순회 결과에서 해당 루트노드 기준으로
 * 배열의 왼쪽에 있는 요소들은 왼쪽 서브트리이고 오른쪽에 있는 요소들은 오른쪽서브트리 라는것을 알 수 있다.
 * 이 정보를 가지고 계속 분할해서 파고들어가다 보면 전체 트리의 모양을 알 수 있고 그것으로 후위순회를 구하면 된다.
 * 이론은 알아내었으니 이제 이것을 그대로 코드로 구현해보자
 * 1. 노드의 갯수만한 방문배열을 하나 생성한다.
 * 2. 전위 순회 리스트에서 아직 방문하지 않은 노드중 가장 먼저나온 것이 루트노드가 된다.
 * 3. 찾아낸 루트노드의 값을 중위순회 리스트에서 찾아서 해당 인덱스값을 찾아낸다.
 * 4. 매개변수로 넘어온 start 변수와 루트노드의 인덱스 -1 값 까지 잘라서 다시 재귀탐색을 실시한다 (왼쪽 서브트리 탐색)
 * 5. 마찬가지로 루트노드의 인덱스 + 1 값부터 매개변수로 넘어온 end 까지 잘라서 재귀탐색을 실시한다 (오른쪽 서브트리 탐색)
 * 6. 매개변수로 넘어온 start와 end의 값이 같다면 리프트리라는 뜻이다. 그 값을 우리가 구해야 할 postOrder 리스트에 담고 해당 노드를 방문처리한다.
 * 7. start 변수가 end 변수보다 크다면 그냥 리턴시켜준다.
 * 8. 그렇게 왼쪽 서브트리와 오른쪽 서브트리를 전부 탐색하고 나오면 마지막으로 루트노드를 postOrder 리스트에 담아준다.
 * 이론은 금방 정립되었는데 그것을 코드로 구현하는게 생각보다 너무 어려웠다. 예전에 풀었던 이진탐색이 생각나는 문제였다.
 * 다른사람의 코드보다는 성능이 조금 달리지만 그래도 스스로 구현해내었다는게 굉장히 만족스러운 문제
 */
public class Solution60 {
    static int N;
    static List<Integer> preOrder;
    static List<Integer> inOrder;
    static List<Integer> postOrder;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        int T = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < T; i++) {
            N = Integer.parseInt(bufferedReader.readLine());
            preOrder = new ArrayList<>();
            inOrder = new ArrayList<>();
            postOrder = new ArrayList<>();
            visited = new boolean[N + 1];
            StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < N; j++) {
                preOrder.add(Integer.parseInt(tokenizer.nextToken()));
            }

            tokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < N; j++) {
                inOrder.add(Integer.parseInt(tokenizer.nextToken()));
            }

            postOrder(0, N - 1);

            for (int node : postOrder) {
                answer.append(node).append(" ");
            }
            answer.append("\n");
        }

        System.out.println(answer);
    }

    static void postOrder(int start, int end) {
        int standard = 0;

        if (start > end) { // start 가 end 보다 크다면 당연히 리턴
            return;
        }

        if (start == end) {
            postOrder.add(inOrder.get(start)); //start 와 end 가 같다는 것은 리프노드라는 뜻
            visited[inOrder.get(start)] = true; //리스트에 담아둔 뒤 방문처리 하고 리턴
            return;
        }

        for (int i = start; i <= end; i++) {
            Integer integer = preOrder.get(i);
            if (!visited[integer]) { //아직 방문하지 않은 노드중 가장 먼저나온 노드가 루트노드이다.
                visited[integer] = true; //망문 처리하고
                standard = inOrder.indexOf(integer); //루트노드의 인덱스값을 찾아냄
                break;
            }
        }

        postOrder(start, standard - 1); //찾아낸 인덱스 값을 기준으로 왼쪽 서브트리 탐색
        postOrder(standard + 1, end); //찾아낸 인덱스 값을 기준으로 오른쪽 서브트리 탐색
        postOrder.add(inOrder.get(standard)); //루트노드 추가
    }
}
