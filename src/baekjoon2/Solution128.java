package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 8911 거북이 (Silver3)
 * F: 한 눈금 앞으로
 * B: 한 눈금 뒤로
 * L: 왼쪽으로 90도 회전
 * R: 오른쪽으로 90도 회전
 * 의 규칙을 갖고 움직이는 거북이가 있고 FBLR로만 이루어져있는 문자열이 주어졌을때
 * 거북이가 지나간 영역을 모두 포함 할 수 있는 가장 작은 직사각형의 넓이를 구하는 문제이다.
 * 로직은 아무리봐도 맞게 짠거같은데 계속 4퍼센트에서 틀리길래 왜 그런가 했더니
 * R로 입력받았을때 현재 dir이 3이면 0으로 바꿔주어야 하는데 멍청하게 4로 바꾸었기 때문에 게속 틀렸다고 한것
 * 마침 문제의 예제에도 그러한 상황이 주어지지 않았기 때문에 계속 맞왜틀 하고 있었던 거임
 */
public class Solution128 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        int T = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < T; i++) {
            int firstX = 0;
            int lastX = 0;
            int firstY = 0;
            int lastY = 0;
            String readLine = bufferedReader.readLine();
            int startX = 0;
            int startY = 0;
            int dir = 0;
            for (int j = 0; j < readLine.length(); j++) {
                char c = readLine.charAt(j);
                switch (c) {
                    case 'F':
                        switch (dir) {
                            case 0:
                                startX--;
                                break;
                            case 1:
                                startY++;
                                break;
                            case 2:
                                startX++;
                                break;
                            case 3:
                                startY--;
                                break;
                        }
                        break;
                    case 'B':
                        switch (dir) {
                            case 0:
                                startX++;
                                break;
                            case 1:
                                startY--;
                                break;
                            case 2:
                                startX--;
                                break;
                            case 3:
                                startY++;
                                break;
                        }
                        break;
                    case 'L':
                        switch (dir) {
                            case 0:
                                dir = 3;
                                break;
                            case 1:
                                dir = 0;
                                break;
                            case 2:
                                dir = 1;
                                break;
                            case 3:
                                dir = 2;
                                break;
                        }
                        break;
                    case 'R':
                        switch (dir) {
                            case 0:
                                dir = 1;
                                break;
                            case 1:
                                dir = 2;
                                break;
                            case 2:
                                dir = 3;
                                break;
                            case 3:
                                dir = 0;
                                break;
                        }
                        break;
                }
                firstX = Math.min(firstX, startX);
                lastX = Math.max(lastX, startX);
                firstY = Math.min(firstY, startY);
                lastY = Math.max(lastY, startY);
            }
            answer.append(Math.abs(lastX - firstX) * Math.abs(lastY - firstY)).append("\n");
        }
        System.out.println(answer);
    }
}
