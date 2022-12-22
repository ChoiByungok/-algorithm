package Programmers;


import java.util.ArrayList;
import java.util.List;

/**
 * 안전지대
 */

public class Solution46 {
    public int solution(int[][] board) {
        int answer = 0;
        int[][] mine = new int[board.length][board.length];
        List<Integer> list = new ArrayList<>();
        int row = 0;

        for (int i = 0; i < board.length * board.length; i++) {
            if(board[row][i % board.length] == 1){
                list.add(i);
                mine[row][i % board.length] = 1;
            }
            if(i % board.length == board.length-1){
                row++;
            }
        }
        if(list.size() == board.length * board.length){
            return 0;
        }

        for(int value : list){
            if((value / mine.length) == 0){                         //행의 위치가 0일때
                if((value % mine.length == 0)) {                     //(0,0) 일때
                    mine[0][1]++;
                    mine[1][0]++;
                    mine[1][1]++;
                }else if (value % mine.length == mine.length - 1) { //(0,length -1) 일때
                    mine[0][mine.length - 2]++;
                    mine[1][mine.length - 2]++;
                    mine[1][mine.length - 1]++;
                }else {                                             //그 외 행의 위치가 0일때
                    mine[0][(value % mine.length) - 1]++;
                    mine[0][(value % mine.length) + 1]++;
                    mine[1][(value % mine.length) - 1]++;
                    mine[1][(value % mine.length)]++;
                    mine[1][(value % mine.length) + 1]++;
                }
            }else if (value / mine.length == mine.length -1) {
                if(value % mine.length == 0){                       //(length-1 ,0) 일때
                    mine[mine.length - 2][0]++;
                    mine[mine.length - 2][1]++;
                    mine[mine.length - 1][1]++;
                }else if (value % mine.length == mine.length -1) { //(length-1,length-1) 일때
                    mine[mine.length - 2][mine.length - 2]++;
                    mine[mine.length - 2][mine.length - 1]++;
                    mine[mine.length - 1][mine.length - 2]++;
                }else {                                             //그 외 행의 위치가 length -1 일때
                    mine[mine.length - 1][value % mine.length - 1]++;
                    mine[mine.length - 1][value % mine.length + 1]++;
                    mine[mine.length - 2][value % mine.length - 1]++;
                    mine[mine.length - 2][value % mine.length]++;
                    mine[mine.length - 2][value % mine.length + 1]++;
                }
            }else{
                if(value % mine.length == 0){                        //열의 위치가 0일때
                    mine[value / mine.length - 1][0]++;
                    mine[value / mine.length - 1][1]++;
                    mine[value / mine.length][1]++;
                    mine[(value / mine.length) + 1][0]++;
                    mine[(value / mine.length) + 1][1]++;
                }else if (value % mine.length == mine.length -1) {   //열의 위치가 length-1일때
                    mine[value / mine.length - 1][mine.length - 2]++;
                    mine[value / mine.length - 1][mine.length - 1]++;
                    mine[value / mine.length][mine.length - 2]++;
                    mine[value / mine.length + 1][mine.length - 2]++;
                    mine[value / mine.length + 1][mine.length - 1]++;
                }else {                                              //그 외
                    mine[value / mine.length - 1][value % mine.length - 1]++;
                    mine[value / mine.length - 1][value % mine.length]++;
                    mine[value / mine.length - 1][value % mine.length + 1]++;
                    mine[value / mine.length][value % mine.length - 1]++;
                    mine[value / mine.length][value % mine.length + 1]++;
                    mine[value / mine.length + 1][value % mine.length - 1]++;
                    mine[value / mine.length + 1][value % mine.length]++;
                    mine[value / mine.length + 1][value % mine.length + 1]++;
                }
            }
        }

        for (int[] ints : mine) {
            for (int j = 0; j < mine.length; j++) {
                if (ints[j] == 0) {
                    answer++;
                }
            }

        }
        return answer;
    }

    public static void main(String[] args) {
        int[][] board1 = {{0,0,0,0,0},
                          {0,0,0,0,0},
                          {0,0,0,0,0},
                          {0,0,1,0,0},
                          {0,0,0,0,0}};

        int[][] board2 = {{0,0,0,0,0},
                          {0,0,0,0,0},
                          {0,0,0,0,0},
                          {0,0,1,1,0},
                          {0,0,0,0,0}};

        int[][] board3 = {{1,1,1,1,1},
                          {1,1,1,1,1},
                          {1,1,1,1,1},
                          {1,1,1,1,1},
                          {1,1,1,1,1}};

        int[][] board4 = {{1,1,1},
                          {0,1,1},
                          {1,1,0}};

        int[][] board5 = {{1,0,1},
                          {0,0,0},
                          {1,0,1}};

        System.out.println(new Solution46().solution(board1));
    }
}
