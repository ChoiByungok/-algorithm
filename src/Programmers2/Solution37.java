package Programmers2;

import java.util.*;

/**
 * 오픈 채팅방
 */
public class Solution37 {
    public String[] solution(String[] record) {
        Map<String, String> map = new HashMap<>();
        for (String value : record) {
            String[] s = value.split(" ");
            if (s[0].equals("Enter")) {
                map.put(s[1], s[2]);
            } else if (s[0].equals("Change")) {
                map.put(s[1], s[2]);
            }
        }
        List<String> answer = new ArrayList<>();
        for (String item : record) {
            String[] s = item.split(" ");
            String key = s[1];
            String value = map.get(key);

            if (s[0].equals("Enter")) {
                answer.add(value + "님이 들어왔습니다.");
            } else if (s[0].equals("Leave")) {
                answer.add(value + "님이 나갔습니다.");
            }
        }
        return answer.toArray(new String[0]);
    }

    public static void main(String[] args) {
        String[] record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"};
        System.out.println(Arrays.toString(new Solution37().solution(record)));
    }
}
