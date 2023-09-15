package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 1920 수 찾기
 */
public class Solution48 {
    static int[] nArr;
    static int[] mArr;
    static int N;
    static int M;
    static boolean exist = false;

    public static void main(String[] args) throws IOException {
        init();
        StringBuilder answer = new StringBuilder();
        Arrays.sort(nArr);

        //반복문 으로 푼 이진탐색
        for (int i = 0; i < mArr.length; i++) {
            int start = 0;
            int end = nArr.length - 1;
            while (true) {
                int middle = (start + end) / 2;
                if (mArr[i] == nArr[middle]) {
                    exist = true;
                    break;
                }
                if (start >= end) {
                    break;
                }
                if (mArr[i] > nArr[middle]) {
                    start = middle + 1;
                    continue;
                }
                if (mArr[i] < nArr[middle]) {
                    end = middle - 1;
                }
            }
            if (exist) {
                answer.append("1").append("\n");
            } else {
                answer.append("0").append("\n");
            }
            exist = false;
        }
        System.out.println(answer);

        // 재귀함수로 푼 이진탐색
//        for (int i = 0; i < mArr.length; i++) {
//            binarySearch(i, 0, nArr.length - 1);
//            if (exist) {
//                answer.append("1").append("\n");
//            } else {
//                answer.append("0").append("\n");
//            }
//            exist = false;
//        }
//        System.out.println(answer);
    }

//    private static void binarySearch(int index, int startIndex, int endIndex) {
//        int middleIndex = (startIndex + endIndex) / 2;
//        if (mArr[index] == nArr[middleIndex]) {
//            exist = true;
//            return;
//        }
//        if (startIndex >= endIndex) {
//            return;
//        }
//        if (mArr[index] > nArr[middleIndex]) {
//            binarySearch(index, middleIndex + 1, endIndex);
//        }
//
//        if (mArr[index] < nArr[middleIndex]) {
//            binarySearch(index, startIndex, middleIndex - 1);
//        }
//    }

    private static void init() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());
        nArr = new int[N];

        StringTokenizer nString = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < N; i++) {
            nArr[i] = Integer.parseInt(nString.nextToken());
        }

        M = Integer.parseInt(bufferedReader.readLine());
        mArr = new int[M];

        StringTokenizer mString = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < M; i++) {
            mArr[i] = Integer.parseInt(mString.nextToken());
        }
    }
}
