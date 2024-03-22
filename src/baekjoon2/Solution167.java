package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 2477 참외밭 (Silver2)
 * 우선 조건에 의하면 (밭의 넓이) X (1평당 참외수) 해도 int형 범위를 벗어나지 못하니 답은 int 형으로 한다.
 * 밭의 모양은 6각형이라고 했으니 입력은 6번만 반복하면 되는데 문제는 움푹패인 작은 부분을 어떤 방식으로 구분해 내느냐가 관건인거같다.
 * 가로길이 세로길이 max를 구한 뒤 작은 부분을 빼주면 될거같은데 작은 부분 구분법을 어떻게 해야할지 모르겠다.
 * 결국 고민하다가 구글링을 했다.
 * 작은부분의 가로길이는 가장길이가 긴 세로의 왼쪽 오른쪽 가로 길이의 차이이고
 * 작은 부분의 세로길이는 가장길이가 긴 가로의 왼쪽 오른쪽 세로 길이의 차이이다.
 * 그래서 가장긴 세로길이와 가로길이를 곱한 뒤 작은 부분의 세로길이와 가로길이를 곱한 값을 뺀 뒤
 * 1평당 수확가능한 참외의 수를 곱하면 되는것이였다.
 * 1년전에 도전했다가 못풀었었던 문제였는데 역시나 어려운 문제였다.
 */
public class Solution167 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(bufferedReader.readLine());
        int[] lengths = new int[6];
        int maxCol = Integer.MIN_VALUE;
        int maxColIndex = -1;
        int maxRow = Integer.MIN_VALUE;
        int maxRowIndex = -1;

        for (int i = 0; i < 6; i++) {
            String[] split = bufferedReader.readLine().split(" ");
            String dir = split[0];
            int length = Integer.parseInt(split[1]);
            lengths[i] = length;

            if (dir.equals("1") || dir.equals("2")) {
                if (maxRow < length) {
                    maxRow = length;
                    maxRowIndex = i;
                }
            } else {
                if (maxCol < length) {
                    maxCol = length;
                    maxColIndex = i;
                }
            }
        }

        int minCol = Math.abs(lengths[(maxRowIndex + 1) % 6] - lengths[(maxRowIndex + 5) % 6]);
        int minRow = Math.abs(lengths[(maxColIndex + 1) % 6] - lengths[(maxColIndex + 5) % 6]);

        int waterMelon = ((maxRow * maxCol) - (minRow * minCol)) * K;
        System.out.println(waterMelon);

    }
}
