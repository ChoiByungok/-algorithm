package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 17140 이차원 배열과 연산 (Gold4)
 * 1.우선 초기에 3X3 크기의 이차원배열을 입력받아서 초기화를 시킨다
 * 2.행크기와 열크기를 비교하여 행 >= 열 일시와 행 < 열 일 시 구분을 하여 연산을 한다.
 * 3.행 >= 열 일때는 각 각의 행의 요소들의 갯수를 파악한 뒤 (이때 맵을 이용하면 좋다.)
 *      갯수가 높은 순으로 갯수가 같을 시에는 숫자가 낮은 순으로 정렬을 실행한다.
 *      그 후 가장 긴 행을 기준으로 이차원 배열을 리사이징한다.
 *      이 때 가장 긴 행을 제외한 나머지행들의 빈 자리는 0으로 초기화 시킨다.
 * 4.행 < 열 일때는 열 기준으로 각 요소들의 갯수를 파악 한 뒤
 *      행과 마찬가지로 정렬을 하고 가장 긴 열을 기준으로 이차원 배열을 리사이징한다.
 * 5.그렇게 리사이징된 배열을 기준으로 반복문의 조건식을 파악한다.
 *      조건식 (map[r-1][c-1] = k 혹은 반복문이 100번 넘게 반복 되었는가)
 *      이 때 주의 할 점은 리사이징된 배열의 크기가 map[r-1][c-1] 보다 작을 수 있으니
 *      리사이징된 배열의 행 크기와 열 크기가 입력받은 r c 보다 큰지 먼저 확인해야 한다.
 * 6.그 후 100번 안에 조건식에 맞는 값이 나오면 몇번 반복했는지 출력하면 되고 아니면 -1 을 출력하면 된다.
 */
public class Solution134 {
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        int r = Integer.parseInt(tokenizer.nextToken());
        int c = Integer.parseInt(tokenizer.nextToken());
        int k = Integer.parseInt(tokenizer.nextToken());
        int answer = -1;
        map = new int[3][3];
        for (int i = 0; i < 3; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < 3; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }
        while (true) {
            answer++;
            int R = map.length;
            int C = map[0].length;
            if (R >= r && C >= c && map[r - 1][c - 1] == k) {
                break;
            }
            if (answer > 100) {
                answer = -1;
                break;
            }
            List<List<Map.Entry<Integer, Integer>>> list = new ArrayList<>();
            int size = 0;
            if (R >= C) {
                for (int[] ints : map) {
                    Map<Integer, Integer> map1 = new HashMap<>();
                    for (int j = 0; j < C; j++) {
                        int integer = ints[j];
                        if (integer == 0) {
                            continue;
                        }
                        map1.put(integer, map1.getOrDefault(integer, 0) + 1);
                    }
                    List<Map.Entry<Integer, Integer>> list1 = new ArrayList<>(map1.entrySet());
                    list1.sort((o1, o2) -> {
                        if (o1.getValue().equals(o2.getValue())) {
                            return o1.getKey() - o2.getKey();
                        }
                        return o1.getValue() - o2.getValue();
                    });
                    size = Math.max(size, list1.size());
                    list.add(list1);
                }
                int[][] newMap = new int[R][size * 2];
                for (int i = 0; i < list.size(); i++) {
                    int index = 0;
                    for (Map.Entry<Integer, Integer> entry : list.get(i)) {
                        newMap[i][index++] = entry.getKey();
                        newMap[i][index++] = entry.getValue();
                    }
                }
                map = newMap;
            } else {
                for (int i = 0; i < C; i++) {
                    Map<Integer, Integer> map1 = new HashMap<>();
                    for (int[] ints : map) {
                        int integer = ints[i];
                        if (integer == 0) {
                            continue;
                        }
                        map1.put(integer, map1.getOrDefault(integer, 0) + 1);
                    }
                    List<Map.Entry<Integer, Integer>> list1 = new ArrayList<>(map1.entrySet());
                    list1.sort((o1, o2) -> {
                        if (o1.getValue().equals(o2.getValue())) {
                            return o1.getKey() - o2.getKey();
                        }
                        return o1.getValue() - o2.getValue();
                    });
                    size = Math.max(size, list1.size());
                    list.add(list1);
                }
                int[][] newMap = new int[size * 2][C];
                for (int i = 0; i < list.size(); i++) {
                    int index = 0;
                    for (Map.Entry<Integer, Integer> entry : list.get(i)) {
                        newMap[index++][i] = entry.getKey();
                        newMap[index++][i] = entry.getValue();
                    }
                }
                map = newMap;
            }
        }

        System.out.println(answer);
    }
}
