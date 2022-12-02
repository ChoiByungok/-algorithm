package Programmers;

import java.util.*;

/**
 * 완주하지 못한 선수
 */
public class Solution23 {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        Map<String, Integer> map = new HashMap<>();
        for (String player : participant) {
            map.put(player, map.getOrDefault(player, 0) + 1);
        }
        for (String player : completion) {
            map.put(player, map.get(player) - 1);
        }

        for (Map.Entry<String, Integer> next : map.entrySet()) {
            if (next.getValue() != 0) {
                answer = next.getKey();
            }
        }

        return answer;
    }



    public static void main(String[] args) {
        String[] participant = {"mislav", "stanko", "mislav","ana"};
        String[] completion = {"stanko", "ana", "mislav"};

        System.out.println(new Solution23().solution(participant,completion));
    }
}
