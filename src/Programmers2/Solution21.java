package Programmers2;
/**
 * 둘만의 암호
 */

import java.util.*;
public class Solution21 {
    public String solution(String s, String skip, int index) {
        StringBuilder answer = new StringBuilder();
        List<Character> alphabetList = getAlphabetList(skip);

        for (int i = 0; i < s.length(); i++) {
            int indexOf = alphabetList.indexOf(s.charAt(i));
            StringBuilder tmp = new StringBuilder();
            for (int j = 0; j < index; j++) {
                Character character = alphabetList.get(((indexOf + 1) % alphabetList.size()));
                tmp.append(character);
                indexOf++;
            }
            answer.append(tmp.charAt(tmp.length() - 1));
        }

        return answer.toString();
    }

    private List<Character> getAlphabetList(String skip) {
        List<Character> alphabet = new ArrayList<>();
        for (char a ='a'; a <= 'z'; a++) {
            if (skip.contains(a + "")) {
                continue;
            }
            alphabet.add(a);
        }
        return alphabet;
    }

    public static void main(String[] args) {
        String s = "aukks";
        String skip = "wbqd";
        int index = 5;

        System.out.println(new Solution21().solution(s, skip, index));
    }
}
