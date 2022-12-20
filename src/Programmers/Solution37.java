package Programmers;


import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 중복된 문자 제거
 */
public class Solution37 {
    public String solution(String my_string) {
        StringBuilder answer = new StringBuilder();
        Map<Character,Integer> map = new LinkedHashMap<>();
        for(int i = 0; i < my_string.length(); i++){
            map.put(my_string.charAt(i),map.getOrDefault(my_string.charAt(i),0)+1);
        }
        for(Map.Entry<Character,Integer> entry : map.entrySet()){
            answer.append(entry.getKey());
        }

        return answer.toString();
    }

    public static void main(String[] args) {
        String people = "people";
        String Watw = "We are the world";

        System.out.println(new Solution37().solution(Watw));

    }
}
