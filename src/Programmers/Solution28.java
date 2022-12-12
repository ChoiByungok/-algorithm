package Programmers;

import java.util.Arrays;

/**
 * 구명보트 나중에 복습
 */
public class Solution28 {
    public int solution(int[] people, int limit) {
        int answer = 0;
        Arrays.sort(people);
        int min = 0;
        int max = people.length - 1;
        for (; min <= max  ; max--) {
            if(people[min] + people[max] <= limit){
                min++;
            }

            answer++;
        }


        return answer;
    }

    public static void main(String[] args) {

        int [] people = {70, 50, 80};
        int limit = 100;

        System.out.println(new Solution28().solution(people,limit));
    }
}
