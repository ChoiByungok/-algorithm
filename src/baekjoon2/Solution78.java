package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * 2002 추월
 * 해당 차량이 추월차량인지 아닌지 구분해야 하는 문제 즉 "추월"을 정의내려야함
 * 12345 로 들어갔는데 25413으로 나오면 3대가 추월한거임
 * 터널에서 나온 결과 값인 25413으로 비교를 해보면
 * 맨 앞에 나온 2보다 작은 수가(1) 뒤쪽에 있으니 해당 차는 추월한거임
 * 그다음 5도 5보다 작은 수가(413) 뒤에 있으니 추월한거고
 * 4도 뒤에 작은 수가 있어서(13) 추월한거임
 * 1은 뒤에 자신보다 작은 수가 없기때문에 추월한 차가 아니고
 * 3은 맨 마지막에 나온차라 추월한 차가 아님
 */
public class Solution78 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        Map<String, Integer> cars = new HashMap<>();
        int count = 0;
        int[] overtaking = new int[N];
        for (int i = 1; i <= N; i++) {
            String car = bufferedReader.readLine();
            cars.put(car, i);
        }

        for (int i = 0; i < N; i++) {
            String car = bufferedReader.readLine();
            overtaking[i] = cars.get(car);
        }

        for (int i = 0; i < overtaking.length - 1; i++) {
            for (int j = i + 1; j < overtaking.length; j++) {
                if (overtaking[i] > overtaking[j]) {
                    count++;
                    break;
                }
            }
        }
        System.out.println(count);
    }
}
