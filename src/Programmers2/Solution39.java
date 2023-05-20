package Programmers2;

import java.util.Arrays;

/**
 * 기능개발
 */
public class Solution39 {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] temp = new int[100];
        int day = 0;
        for(int i=0; i<progresses.length; i++){
            while(progresses[i] + (speeds[i] * day) < 100){
                day++;
            }
            temp[day]++;
        }
        int count = 0;
        for(int n : temp){
            if(n != 0){
                count ++;
            }
        }
        int[] answer = new int[count];

        count = 0;
        for(int n : temp){
            if(n != 0){
                answer[count++] = n;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        int[] progresses1 = {93, 30, 55};
        int[] speeds1 = {1, 30, 5};

        int[] progresses2 = {95, 90, 99, 99, 80, 99};
        int[] speeds2 = {1, 1, 1, 1, 1, 1};
        System.out.println(Arrays.toString(new Solution39().solution(progresses2, speeds2)));
    }
}
