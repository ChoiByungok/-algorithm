package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/**
 * 2840 행운의 바퀴 (Silver4)
 * 문제부터 이해안가서 질문게시판 가서 어떻게 푸는 문제인지 간신히 이해함
 * wheel 배열에다가 인덱스 값 계산해서 문자 집어넣은거 까지는 괜찮았음
 * 근데 행운의 바퀴인지 아닌지 구분을 하라는데 문제를 봐도 이해를 못해서 그냥 때려 맞춤
 * 이미 배열에 값이 초기화 됐는데 그 초기화된 값이랑 다른 문자가 들어오면 행운의 바퀴가 아닌가봄 첫번째 예제가 그러함
 * 예를 들어 [][A][][] 인 상황에서 2번째 자리에 A 가 들어오는건 상관없지만 B가 들어오게 되면 행운의 바퀴가 아니게 됨
 * 그리고 배열에 중복된 문자가 있어도 행운의 바퀴가 아니임 이건 제출 한번 했는데 틀려가지고 반례 찾아봄
 * 예를 들어 [A][A][B][C] 로 완성되면 행운의 바퀴가 아님 돌림판 문자는 중복되면 안됨
 * 그래서 두번째꺼 구분하려고 억지로 카운트 변수랑 Set 자료구조 사용함
 * 깔끔하게 풀지 못하고 부족한 부분 땜빵메꾸면서 억지로 푼느낌이라 풀어도 찝찝함
 */
public class Solution158 {
    public static void main(String[] args) throws IOException {
        StringBuilder answer = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] nk = bufferedReader.readLine().split(" ");
        int N = Integer.parseInt(nk[0]);
        int K = Integer.parseInt(nk[1]);
        String[] wheel = new String[N];
        Set<String> set = new HashSet<>();

        int index = 0;
        boolean error = false;
        for (int i = 0; i < K; i++) {
            String[] sc = bufferedReader.readLine().split(" ");
            int s = Integer.parseInt(sc[0]);
            String word = sc[1];
            index = (index + s) % N;
            if (wheel[index] == null) {
                wheel[index] = word;
            } else {
                if (!wheel[index].equals(word)) { //새로 들어온 문자가 해당 인덱스에 들어가야하는데 이미 값이 초기화 되어 있으면서 값이 다르면 행운의 바퀴가아님
                    error = true;
                }
            }
            set.add(word);
        }

        int count = 0;
        for (String s : wheel) { //null 이 아닌 칸을 중복이 된지 안된지 구분을 하며 카운트를 계산함
            if (s != null) {
                set.add(s);
                count++;
            }
        }

        if (error || set.size() != count) { //카운트를 셌는데 그값이 set 의 사이즈랑 같지 않으면 행운의 바퀴가 아님
            answer.append("!");             //예를 들어 바퀴값은 [a][a][a] 라 카운트는 3인데 set 에는 a 하나만 저장되어 사이즈가 1이 나오기 때문
        } else { // 그 외에는 행운의 바퀴임
            while (N-- > 0) {
                if (index == -1) { // -1 로 갔다는건 오버플로우가 일어났다는 뜻이니 배열의 맨 마지막 위치로 보내줌
                    index = wheel.length - 1;
                }
                if (wheel[index] != null) { //값이 초기화가 되어있으면 그냥 그값을 출력해줌
                    answer.append(wheel[index]);
                } else {
                    answer.append("?"); //null 값이면 ?를 출력해줌
                }
                index--; //현재 인덱스 값이 돌림판의 화살표 위치임 그리고 시계방향이랬으니 줄여줌
            }
        }
        System.out.println(answer);
    }
}
