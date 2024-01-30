package baekjoon2;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * 1406 에디터 (Silver2)
 * L	커서를 왼쪽으로 한 칸 옮김 (커서가 문장의 맨 앞이면 무시됨)
 * D	커서를 오른쪽으로 한 칸 옮김 (커서가 문장의 맨 뒤이면 무시됨)
 * B	커서 왼쪽에 있는 문자를 삭제함 (커서가 문장의 맨 앞이면 무시됨)
 * 삭제로 인해 커서는 한 칸 왼쪽으로 이동한 것처럼 나타나지만, 실제로 커서의 오른쪽에 있던 문자는 그대로임
 * P $	$라는 문자를 커서 왼쪽에 추가함
 * 명령어가 수행되기 전에 커서는 문장의 맨 뒤에 위치하고 있다고 한다.
 * 이 문제를 접햇을때 링크드 리스트를 이용하면 간단하게 해결 할 수 있을줄 알았다.
 * 그래서 커서라는 변수를 두고 명령어에 따라 커서의 값을 바꿔가며 리스트의 요소를 삭제하고 삽입하면 될줄 알았는데 시간초과가 발생하였다.
 * 삽입 삭제야 링크드 리스트 이기때문에 빠르지만 근본적으로 그 인덱스 값까지 찾아가는데 시간복잡도가 o(n) 이 걸리게 된다.
 * 매 반복마다 리스트의 처음 요소부터 커서의 위치까지 탐색하지 않고 그 위치에 고정이 되어있으면 좋겠다 싶을때
 * 사용하는것이 listIterator()라는 메서드이다. 이 메서드를 사용하면 ListIterator<T> 를 반환하는데
 * 해당 자료구조는 내부에 커서가 존재하며 중간에 요소를 삭제 삽입 시킬수도 있고 그렇게 하면
 * 원래의 리스트에도 영향이 가기때문에 이 문제를 해결하기에 가장 적합한 자료구조이지 않나싶다.
 * 커서의 초기값은 문장의 맨 뒤 이기때문에 처음에 커서를 맨 뒤로 이동시켜주고 시작한다.
 * 그리고 문제에서 주어진 조건에 맞게 커서를 이동시켜주고 요소를 삽입 삭제 시켜주면 된다.
 */
public class Solution114 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String readLine = bufferedReader.readLine();
        List<Character> list = new LinkedList<>();
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < readLine.length(); i++) {
            list.add(readLine.charAt(i));
        }
        ListIterator<Character> iterator = list.listIterator();
        while (iterator.hasNext()) {
            iterator.next();
        }

        int M = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < M; i++) {
            String order = bufferedReader.readLine();
            char c = order.charAt(0);
            switch (c) {
                case 'L':
                    if (iterator.hasPrevious()) {
                        iterator.previous();
                    }
                    break;
                case 'D' :
                    if (iterator.hasNext()) {
                        iterator.next();
                    }
                    break;
                case 'B' :
                    if (iterator.hasPrevious()) {
                        iterator.previous();
                        iterator.remove();
                    }
                    break;
                case 'P' :
                    iterator.add(order.charAt(2));
                    break;
            }
        }
        for (Character character : list) {
            answer.append(character);
        }
        System.out.println(answer);
    }
}
