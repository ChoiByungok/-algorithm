package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 14713 앵무새
 * 큐 리스트 형태의 자료구조에 입력받은 각각의 문자열을 공백기준으로 자른 뒤 순서에 맞게 집어넣는다.
 * 3
 * i want to see you
 * next week
 * good luck
 * 의 입력을 받는다면 리스트의 사이즈는3이고 큐는 총 3개가 만들어진다.
 * 각 각의 큐에는 [i, want, to, see, you][0], [next, week][1], [good, luck][2] 가 담긴다.
 * 그리고 i want next good luck week to see you 의 문자열도 공백 기준으로 자른 뒤
 * 문자열의 순서대로 리스트를 순회 하며 각각의 큐의 제일 앞에 있는 요소와 같은지 비교를 해서 같으면
 * 큐에서 요소를 제거 해준다. 그러다 큐의 제일 앞에 있는 요소와 같은 문자가 없다면 그 문자열은 불가능한 문자열 이므로 break 시켜준다.
 * 단 여기서 주의 해야 할 점은 앵무새가 말한 모든 단어가 받아 적은 문장에 전부포함 되어있어야 한다는 것이다.
 * 예를 들어
 * 2
 * ab cd
 * ef gh ij
 * ab ef gh ij
 * 라고 입력 받으면 받아적은 문장에 앵무새가 말한 cd가 존재하지 않기 때문에 불가능한 단어가 된다.
 * 그래서 나는 카운트를 2개 둬서 앵무새가 말한 단어 갯수를 카운트하고 받아적은 문자의 갯수도 카운트를 하여
 * 그 두 카운트가 같지 않으면 불가능한 문자열이라고 구현을 하였다.
 */
public class Solution87 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        List<Queue<String>> list = new ArrayList<>();
        int count1 = 0;
        int count2;
        for (int i = 0; i < N; i++) {
            list.add(new LinkedList<>());
        }


        for (int i = 0; i < N; i++) {
            String[] split = bufferedReader.readLine().split(" ");
            for (String s : split) {
                list.get(i).add(s);
                count1++;
            }
        }

        String[] split = bufferedReader.readLine().split(" ");
        count2 = split.length;
        boolean possible = false;
        if (count1 == count2) {
            for (String s : split) {
                possible = false;
                for (Queue<String> strings : list) {
                    if (s.equals(strings.peek())) {
                        strings.poll();
                        possible = true;
                        break;
                    }
                }
                if (!possible) {
                    break;
                }
            }
        }

        System.out.println(possible ? "Possible" : "Impossible");
    }
}
