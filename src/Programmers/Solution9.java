package Programmers;
/**
 * 숫자 문자열과 영단어
 */

import java.util.*;

public class Solution9 {
    public int solution(String s) {
        List<String> list = new ArrayList<>();
        list.add("zero");
        list.add("one");
        list.add("two");
        list.add("three");
        list.add("four");
        list.add("five");
        list.add("six");
        list.add("seven");
        list.add("eight");
        list.add("nine");
        for (int i = 0; i < list.size(); i++) {
               s = s.replace(list.get(i),String.valueOf(i));
        }
        return Integer.parseInt(s);
    }

    public static void main(String[] args) {
        Solution9 solution9 = new Solution9();
        String s = "2three45sixseven";
        System.out.println("answer = " + solution9.solution(s));

    }
}
