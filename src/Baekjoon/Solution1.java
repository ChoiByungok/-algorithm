package Baekjoon;
/**
 * 2751번 수 정렬하기2
 */

import java.util.*;

public class Solution1 {
    public static void main(String[] args) {

        ArrayList<Integer> list = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();

        for (int i = 0; i < input; i++) {
            list.add(sc.nextInt());
        }

        Collections.sort(list);
        for(int value : list){
            stringBuilder.append(value).append("\n");
        }
        System.out.println(stringBuilder);

    }
}
