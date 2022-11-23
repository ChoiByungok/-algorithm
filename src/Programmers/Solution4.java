package Programmers;
/**
 * 문자열 내 마음대로 정렬하기
 */

import java.util.Arrays;

public class Solution4 {
    public String[] solution(String[] strings, int n) {

        for(int i = 0; i < strings.length; i++){
            for(int j = 0; j < strings.length-1-i; j++ ){
                if(strings[j].charAt(n) > strings[j+1].charAt(n)){
                    String tmp = strings[j+1];
                    strings[j+1] = strings[j];
                    strings[j] = tmp;
                }
                if(strings[j].charAt(n) == strings[j+1].charAt(n)){
                    int compare = strings[j].compareTo(strings[j + 1]);
                    if(compare > 0){
                        String tmp = strings[j+1];
                        strings[j+1] = strings[j];
                        strings[j] = tmp;
                    }
                }
            }
        }
        return strings;
    }

    public static void main(String[] args) {

        Solution4 solution4 = new Solution4();
        String[] strings1 = {"sun", "bed", "car"};
        String[] strings2 = {"abce","abcd","cdx"};
        System.out.println(Arrays.toString(solution4.solution(strings2,2)));



    }
}
