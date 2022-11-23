package Programmers;
/**
 * K번째수
 */

import java.util.Arrays;

public class Solution3 {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        for(int a = 0; a < commands.length; a++){
            int i = commands[a][0];
            int j = commands[a][1];
            int k = commands[a][2];
            int[] tmpArr = new int[j-(i-1)];
            for(int b = 0; b < tmpArr.length; b++){
                tmpArr[b] = array[i-1];
                i++;
            }
            Arrays.sort(tmpArr);
            answer[a] = tmpArr[k-1];
        }
        return answer;
    }

    public static void main(String[] args) {
        Solution3 solution3 = new Solution3();
        int[] array = {1, 5, 2, 6, 3, 7, 4};
        int[][] commands = {{2,5,3},{4,4,1},{1,7,3}};
        System.out.println(Arrays.toString(solution3.solution(array,commands)));

    }
}
