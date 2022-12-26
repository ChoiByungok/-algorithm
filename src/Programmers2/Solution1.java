package Programmers2;

import java.util.Arrays;

/**
 * OX퀴즈
 */
public class Solution1 {
    public String[] solution(String[] quiz) {
        String[] answer = new String[quiz.length];
        for (int i = 0; i < quiz.length; i++) {
            String[] split = quiz[i].split(" ");
            int x = Integer.parseInt(split[0]);
            int y = Integer.parseInt(split[2]);
            int z = Integer.parseInt(split[split.length-1]);

            if(split[1].equals("+")){
                if((x + y) == z){
                    answer[i] = "O";
                }else {
                    answer[i] = "X";
                }
            }else {
                if((x - y) == z){
                    answer[i] = "O";
                }else {
                    answer[i] = "X";
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        String[] quiz = {"3 - 4 = -3", "5 + 6 = 11"};
        System.out.println(Arrays.toString(new Solution1().solution(quiz)));
    }
}
