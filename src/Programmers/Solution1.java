package Programmers;

/**
 * 이상한 숫자 만들기
 */
public class Solution1 {

    public String solution(String s) {
        StringBuilder answer = new StringBuilder();
        int count = 0;
        int toUpper = 'a' - 'A';

        for(int i = 0 ; i < s.length(); i++){
            if(s.charAt(i) == ' '){
                count = -1;
            }
            if(count % 2 == 0){
                if(s.charAt(i) >= 'a' && s.charAt(i) <= 'z'){
                    answer.append((char)(s.charAt(i)-toUpper));
                    count++;
                }else {
                    answer.append(s.charAt(i));
                    count++;
                }
            }else {
                if(s.charAt(i) >='A' && s.charAt(i) <= 'Z'){
                    answer.append((char)(s.charAt(i)+toUpper));
                    count++;
                }else {
                    answer.append(s.charAt(i));
                    count++;
                }
             }
        }
        return answer.toString();
    }

    public static void main(String[] args) {

        Solution1 solution1 = new Solution1();
        System.out.println(solution1.solution("PRoGrammErs praCtICE"));
    }
}
