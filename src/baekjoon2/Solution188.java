package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1027 고층 건물 (Gold4)
 * 직관적인 문제라 문제 이해자체는 쉬운데 어떻게 접근해야 할지 어려웠던 문제
 * 결국 구글링을 하여 제출하였다.
 * 이문제의 키포인트는 두 건물사이의 기울기인데
 * 기울기를 구하는 공식은 기준점을 A 비교대상을 B라고 가정했을때
 * (A - B) / (A와 B사이의 거리) 이다.
 * 임의의 건물 A에서 왼쪽 건물들과 오른쪽 건물들을 차례대로 기울기를 재가면서 볼 수 있는 빌딩인지 아닌지 구분해야 한다.
 * 예를 들어 5개의 건물이 있고 임의의 건물의 위치는 3번째라고 가정한다.
 * 그러면 2번째 건물과의 기울기를 구한뒤 1번째 건물의 기울기가 3 2 건물의 기울기보다 작으면 볼수있는 건물이다. 아니면 볼수 없다.
 * 그리고 4번째 건물과의 기울기를 구한뒤 5번째 건물의 기울기가 3 4 건물의 기울기보다 크면 볼수있는 건물이다. 아니면 볼수 없다.
 * 여기서 중요한건 일관성을 가져가야 한다는 것이다.
 * 오른쪽으로 재면 인덱스값이 올라 거리가 -가 될텐데 나는 거리가 -가 되면 안된다고 생각해서 기울기를 구하는 공식의 분모값에 절대값을 씌워줬다.
 * 그러면 음수가 나와야할 기울기가 양수가 나오게 되고 양수가 나와야할 기울기가 음수가 나오게 되서 일관성을 잃게 된다.
 * 그냥 왼쪽으로 재던 오른쪽으로 재던 기준점을 잡은 인덱스 값에서 비교할 인덱스의 값을 똑같이 빼주면 된다.
 * 이거 때문에 한참 헷갈렸고 한참 해맸다. 그리고 이 문제 자체를 기울기로 접근해야 한다는 발상은 어떻게 했는지 진짜 대단하다.
 * 나는 백만년이 걸려도 이 문제를 기울기로 해결할 생각을 절대 못했을것이다.
 */
public class Solution188 {
    static int[] buildings;
    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        buildings = new int[N];
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < N; i++) {
            buildings[i] = Integer.parseInt(tokenizer.nextToken());
        }

        for (int i = 0; i < buildings.length; i++) {
            answer = Math.max(answer, count(i));
        }

        System.out.println(answer);
    }

    static int count(int index) {
        double scope = 0;
        int count = 0;
        int standard = buildings[index];

        // 빌딩의 왼쪽 기울기가 감소해야함
        for (int i = index - 1; i >= 0; i--) {
            int comparison = buildings[i];
            double v = (double) (standard - comparison) / (index - i);
            if (v < scope || index - 1 == i) {
                count++;
                scope = v;
            }
        }

        //빌딩의 오른쪽 기울기가 증가해야함
        for (int i = index + 1; i < buildings.length; i++) {
            int comparison = buildings[i];
            double v = (double) (standard - comparison) / (index - i);
            if (v > scope || index + 1 == i) {
                count++;
                scope = v;
            }
        }

        return count;
    }
}
