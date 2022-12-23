package Programmers;

/**
 * 컨트롤 제트
 */
public class Solution48 {
    public int solution(String s) {
        int answer = 0;
        String[] s1 = s.split(" ");
        if(!s1[s1.length-1].equals("Z")){
            answer += Integer.parseInt(s1[s1.length-1]);
        }
        for (int i = 0; i < s1.length-1; i++) {
            if(s1[i].equals("Z")){
                continue;
            }
            if(!s1[i+1].equals("Z")){
                answer += Integer.parseInt(s1[i]);
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        String s1 = "1 2 Z 3";
        String s2 = "10 20 30 40";
        System.out.println(new Solution48().solution(s2));
    }
}
