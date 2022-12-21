package Programmers;

import java.util.*;

/**
 * 한 번만 등장한 문자
 */
public class Solution41 {
    public String solution(String s) {
        String answer = "";
        Map<Character,Integer> map = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i),map.getOrDefault(s.charAt(i),0)+1);
        }
        for(Map.Entry<Character,Integer> entry : map.entrySet()){
            if(entry.getValue() == 1){
                answer += entry.getKey();
            }
        }
        char[] chars = answer.toCharArray();
        Arrays.sort(chars);

        return String.valueOf(chars);
    }

    public static void main(String[] args) {
        String s = "abcd";
        System.out.println(new Solution41().solution(s));
    }
}

