package Programmers;

/**
 * JadenCase 문자열 만들기
 */

public class Solution14 {
    public String solution(String s) {
        char[] chars = s.toLowerCase().toCharArray();
        boolean[] blank = new boolean[s.length()];
        int diff = 'a'-'A';
        if(chars[0] > 57){
            chars[0] -= diff;
        }
        for (int i = 0; i < chars.length; i++) {
            if(chars[i] == ' '){
                blank[i] = true;
            }
        }
        for (int i = 1; i < chars.length-1; i++) {
            if(blank[i]){
                if(!blank[i+1]){
                    if(chars[i+1] > 57){
                        chars[i+1] -= diff;
                    }
                }
            }
        }
        return String.valueOf(chars);
    }


    public static void main(String[] args) {
        Solution14 solution14 = new Solution14();
        System.out.println(solution14.solution("3people unFollowed me"));
    }
}
