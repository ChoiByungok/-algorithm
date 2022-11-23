package Programmers;

/**
 * 시저 암호
 */
public class Solution2 {
    public String solution(String s, int n) {
        StringBuilder answer = new StringBuilder();
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) >='a' && s.charAt(i) <='z'){
                if(s.charAt(i)+n > 'z'){
                    answer.append((char)(('a'-1)+ (s.charAt(i)+n)-'z'));
                }else {
                    answer.append((char)(s.charAt(i)+n));
                }
            }
            if(s.charAt(i) >='A' && s.charAt(i) <='Z'){
                if(s.charAt(i)+n > 'Z'){
                    answer.append((char)(('A'-1)+ (s.charAt(i)+n)-'Z'));
                }else {
                    answer.append((char)(s.charAt(i)+n));
                }
            }
            if(s.charAt(i) == ' '){
                answer.append(s.charAt(i));
            }
        }
        return answer.toString();
    }

    public static void main(String[] args) {
        Solution2 solution2 = new Solution2();
        System.out.println(solution2.solution("a B z",4));


    }
}
