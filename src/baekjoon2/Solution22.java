package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 1149 RGB 거리
 */
public class Solution22 {
    static class Color {
        char color;
        int value;

        public Color(char color, int value) {
            this.color = color;
            this.value = value;
        }
    }
    static class RGB {
        Color red;
        Color green;
        Color blue;

        public RGB(Color red, Color green, Color blue) {
            this.red = red;
            this.green = green;
            this.blue = blue;
        }
    }
    static RGB[] rgb;
    static RGB[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        rgb = new RGB[N + 1];
        dp = new RGB[N + 1];

        for (int i = 1; i <= N; i++) {
            String[] split = bufferedReader.readLine().split(" ");
            rgb[i] = new RGB(new Color('R', Integer.parseInt(split[0])),
                    new Color('G', Integer.parseInt(split[1])),
                    new Color('B', Integer.parseInt(split[2])));
        }

        dp[1] = rgb[1];
        for (int i = 2; i <= N ; i++) {
            int redValue = dp[i - 1].red.value;
            int greenValue = dp[i - 1].green.value;
            int blueValue = dp[i - 1].blue.value;
            RGB now = rgb[i];

            Color newRed = new Color(now.red.color, Math.min(greenValue, blueValue) + now.red.value);
            Color newGreen = new Color(now.green.color, Math.min(redValue, blueValue) + now.green.value);
            Color newBlue = new Color(now.blue.color, Math.min(redValue, greenValue) + now.blue.value);
            dp[i] = new RGB(newRed, newGreen, newBlue);
        }

        System.out.println(Math.min(dp[N].red.value, Math.min(dp[N].green.value, dp[N].blue.value)));
    }

}
