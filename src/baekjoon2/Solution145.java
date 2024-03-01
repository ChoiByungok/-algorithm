package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 1475 방번호 (Silver5)
 * 다른 사람들은 어떻게 구현했는지는 모르겠지만
 * 나같은 경우는 캐릭터형을 키값 중복된 횟수를 밸류값으로 만든 맵 자료구조에 문자를 넣어서 값이 있으면 1씩 증가시키는 방법으로 풀었다.
 * 단 6과 9는 서로 뒤집어서 사용할 수 있으므로
 * 만약 현재 입력받은 숫자가 6인데
 * 6은 2번 반복 되었고 9는 1번반복된 상황이면
 * 6을 9로 바꾸어 넣어준다.
 * 마찬가지로 입력받은 숫자가 9인데
 * 6은 1번 9는 2번 반복된 상황이면
 * 9를 6으로 바꾸어 맵에 넣어주었다.
 * 그 후 밸류값으로 내림차순 정렬하여
 * 첫번째 요소의 밸류값을 출력하면 된다.
 */
public class Solution145 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String readLine = bufferedReader.readLine();
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < readLine.length(); i++) {
            char c = readLine.charAt(i);
            if (c == '6' || c == '9') {
                Integer nine = map.getOrDefault('9', 0);
                Integer six = map.getOrDefault('6', 0);

                if (six > nine) {
                    c = '9';
                } else if (six < nine) {
                    c = '6';
                }
            }
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        List<Map.Entry<Character, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort((o1, o2) -> o2.getValue() - o1.getValue());
        System.out.println(list.get(0).getValue());
    }
}
