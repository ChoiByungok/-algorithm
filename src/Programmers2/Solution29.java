package Programmers2;

import java.util.*;

/**
 * 실패율
 * 실패율은 다음과 같이 정의한다.
 * 스테이지에 도달했으나 아직 클리어하지 못한 플레이어의 수 / 스테이지에 도달한 플레이어 수
 */
public class Solution29 {
    public int[] solution(int N, int[] stages) {
        Map<Integer, Double> map = new HashMap<>();
        int[] userFailCnt = new int[N + 2];
        int[] userTotalCnt = new int[N + 1];

        for (int stage : stages) {
            userFailCnt[stage]++;
        }

        userTotalCnt[N] = userFailCnt[N] + userFailCnt[N + 1];
        for (int i = N - 1; i >= 1 ; i--) {
            userTotalCnt[i] = userFailCnt[i] + userTotalCnt[i + 1];
        }
        System.out.println("userFailCnt = " + Arrays.toString(userFailCnt));
        System.out.println("userTotalCnt = " + Arrays.toString(userTotalCnt));
        for (int i = 1; i < userTotalCnt.length; i++) {
            if (userFailCnt[i] == 0 || userTotalCnt[i] == 0) {
                map.put(i, 0.0);
            }else {
                map.put(i, (double) userFailCnt[i] / userTotalCnt[i]);
            }
        }
        System.out.println("map = " + map);
        List<Map.Entry<Integer, Double>> list = new ArrayList<>(map.entrySet());
        list.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        System.out.println("list = " + list);
        return list.stream().mapToInt(Map.Entry::getKey).toArray();
    }

    public static void main(String[] args) {
        int N1  = 5;
        int[] stages1 = {2, 1, 2, 6, 2, 4, 3, 3};

        int N2  = 4;
        int[] stages2 = {4,4,4,4,4};

        System.out.println(Arrays.toString(new Solution29().solution(N1, stages1)));
    }
}
