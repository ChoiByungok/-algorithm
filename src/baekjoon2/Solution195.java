package baekjoon2;

import java.io.*;
import java.util.*;

/**
 * 15658 연산자 끼워넣기 (2) (Silver2)
 * 미리 연산자의 순서를 정해놓고 연산을 하려니깐 시간초과가 발생했다.
 * 그래서 이번엔 새로운 시도로 중간중간 연산을 하면서 매개변수로 넘겨주는 방식으로 한번 해보기로 했다.
 * 근데 문제의 1번예제 2번예제 까지는 통과하는데 3번예제에서 막힌다.
 * 이게 재귀호출 문제라 어디 로직이 잘못됐는지 디버깅하기도 까다롭다. 과연 어디서 틀린걸까
 * 스위치문을 사용했는데 각각의 케이스마다 연산자가 한번 쓰이면 줄이고 재귀를 빠져나오면 다시 늘려주는 식으로 했다가
 * 공통된 로직이라 줄이고 늘리는 로직을 스위치 밖으로 끄집어 내니깐 예제3번도 통과되었고 시간초과 없이 통과되었다.
 * 주의 할점은 재귀를 마치고 돌아왔을때 기존의 중간연산값을 유지하기 위해서
 * 연산을 끝내고 재귀를 호출하는것이 아니라 매개변수로 보내면서 연산을 해야한다는 것이다.
 */
public class Solution195 {
    static int N;
    static int operation;
    static int[] arr;
    static int[] operations;
    static int MAX = Integer.MIN_VALUE;
    static int MIN = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());
        operation = N - 1;
        arr = new int[N];
        operations = new int[4];

        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(tokenizer.nextToken());
        }

        tokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < 4; i++) {
            operations[i] = Integer.parseInt(tokenizer.nextToken());
        }

//        List<Integer> list = new ArrayList<>();
        permutation(0, arr[0]);
        System.out.println(MAX + "\n" + MIN);
    }

    /*static void permutation(int count, List<Integer> list, int start) {
        if (count == operation) {
            System.out.println("list = " + list);
            int calc = arr[0];
            for (int i = 1; i < arr.length; i++) {
                Integer operation = list.get(i - 1);
                switch (operation) {
                    case 0:
                        calc += arr[i];
                        break;
                    case 1:
                        calc -= arr[i];
                        break;
                    case 2:
                        calc *= arr[i];
                        break;
                    case 3:
                        if (calc > 0) {
                            calc /= arr[i];
                        } else {
                           calc = -1 * ((-1 * calc) / arr[i]);
                        }
                        break;
                }
            }
            MAX = Math.max(MAX, calc);
            MIN = Math.min(MIN, calc);
        }

        for (int i = start; i < operations.length; i++) {
            if (operations[i] > 0) {
                list.add(i);
                operations[i]--;
                permutation(count + 1, list, start);
                list.remove(count);
                operations[i]++;
            }
        }
    }*/

    static void permutation(int count, int calc) {
        if (count == operation) {
            MAX = Math.max(MAX, calc);
            MIN = Math.min(MIN, calc);
            return;
        }

        for (int i = 0; i < operations.length; i++) {
            if (operations[i] > 0) {
                operations[i]--;
                switch (i) {
                    case 0:
                        permutation(count + 1, calc + arr[count + 1]);
                        break;
                    case 1:
                        permutation(count + 1, calc - arr[count + 1]);
                        break;
                    case 2:
                        permutation(count + 1, calc * arr[count + 1]);
                        break;
                    case 3:
                        if (calc > 0) {
                            permutation(count + 1, calc / arr[count + 1]);
                        } else {
                            permutation(count + 1, -1 * ((-1 * calc) / arr[count + 1]));
                        }
                        break;
                }
                operations[i]++;
            }
        }
    }
}
