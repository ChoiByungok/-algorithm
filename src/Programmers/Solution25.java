package Programmers;

/**
 * 짝지어 제거하기
 * 효율성 실패
 */
public class Solution25 {
    public int solution(String s) {

        StringBuilder newS = new StringBuilder();
        newS.append(s);
        for (int i = 0; i < newS.length()-1 ; i++) {
            if(newS.charAt(i) == newS.charAt(i+1)){
                newS.delete(i,i+2);
                i = -1;
            }
        }

        if(newS.length() == 0){
            return 1;
        }else {
            return 0;
        }

    }

    public static void main(String[] args) {

        String s = "cdcd";
        System.out.println(new Solution25().solution(s));
    }
}
