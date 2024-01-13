package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 10775 공항
 * 처음에는 단순하게 들어온 비행기의 번호랑 같은 게이트가 비어있으면 도킹시키고
 * 그렇지 않으면 게이트의 수를 1씩 줄여가면서 도킹이 가능한지 확인하며 문제를 풀어보았다.
 * 그러니깐 시간초과가 발생하였다. 그리하여 검색해본 결과 유니온 파인드라는 알고리즘으로 해결하는 것을 보았다.
 * 유니온 파인드란 간단하게 부분집합의 루트노드가 무엇인지 찾고(파인드) 그 부분집합이 너무 한쪽으로 편향되어 있으면
 * 하나로 합치는(유니온) 작업을 수행하는 알고리즘이다. 합치는 이유는 이 알고리즘이 재귀반복을 사용하기 때문이다.
 * 유니온 파인드 알고리즘을 이 문제에 어떻게 적용하는지가 관건인데
 * 우선 이 문제의 예제 2번을 예를들어 설명하자면
 * 처음에 2번비행기가 날아온다. 2번비행기는 2번게이트에 도킹하는것이 최선이다.
 * 만약 2번게이트에 도킹이 불가능 하다면 1번게이트를 차선책으로 둔다.
 * 그래서 find 메서드를 통하여 어떤 게이트에 도킹이 가능한지 찾는다.
 * 처음에는 1번부터 4번까지 모든 배열이 자신의 인덱스에 맞게 설정되어있다. 그래서 2번게이트를 반환받아서 2번게이트에 도킹이 가능하게 된다.
 * 그 이후 union 작업을 통해 2번인덱스의 값이 2에서 1로 바뀌게 된다. 2번게이트 의 루트는 1번이 되게 되는 것이다.
 * 그 다음 2번비행기가 두번째로 오게 되는데 해당 비행기도 2번게이트 이하에서만 도킹이 가능하다.
 * 물론 2번비행기의 도킹도 2번게이트에서 이루어지는게 최선이겠지만 find 메서드로 반환받은 값은 1번 게이트이다.(2번 게이트의 루트는 1번게이트가 되었으므로)
 * 그리고 union 메서드에 의해서 1번게이트의 루트는 0번게이트를 가리키게 된다.
 * 3번 비행기가 세번째로 오게 된다. 해당 비행기가 도킹 할 수 있는 게이트의 최선책은 3번게이트이고 차선책은 0번게이트가 된다.
 * 다행이도 이 비행기는 3번 게이트에 도킹이 가능하고 그 이후 union 메서드에 의해서 3번게이트의 루트는 0번게이트가 되게 된다.
 * 3번 비행기가 네번째로 오게된다. 해당 비행기의 최선책(3번게이트)은 이미 도킹이 불가능 하고 차선책은 0번게이트가 된다.
 * 차선책이 0번게이트가 되는 순간 이 공항에는 더이상 비행기를 도킹시킬수 없기에 반복문을 종료하고
 * 그동안 카운트했던 비행기의 수를 출력하면 되는 문제이다.
 */
public class Solution97 {
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int G = Integer.parseInt(bufferedReader.readLine());
        int P = Integer.parseInt(bufferedReader.readLine());
        int count = 0;

        parent = new int[G + 1];
        for (int i = 1; i <= G; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < P; i++) {
            int plane = Integer.parseInt(bufferedReader.readLine());

            int gate = findGate(plane);

            if (gate == 0) {
                break;
            }

            count++;
            union(gate, gate - 1);
        }

        System.out.println(count);
    }

    public static int findGate(int plane) {

        if (plane == parent[plane]) {
            return plane;
        }

        return parent[plane] = findGate(parent[plane]);
    }

    public static void union(int best, int second) {
        best = findGate(best);
        second = findGate(second);

        if (best != second) {
            parent[best] = second;
        }
    }
}
