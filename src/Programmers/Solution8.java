package Programmers;

/**
 * 삼총사
 */
public class Solution8 {
    public int solution(int[] number) {
        int answer = 0;
        int sum = 0;
        for (int i = 0; i < number.length-2; i++) {
            for (int j = i+1; j < number.length; j++) {
                for (int k = j+1; k < number.length; k++) {
                    sum = number[i]+number[j]+number[k];
                        if(sum == 0){
                            answer++;
                        }
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        Solution8 solution8 = new Solution8();
        int[] number = {-3, -2, -1, 0, 1, 2, 3};
        System.out.println("answer = " +solution8.solution(number));
    }
}
