package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * 17479 정식당
 * 분명히 맞게 풀었다고 생각했는데 자꾸 9%에서 틀림
 * 무슨 차이인가 싶어서 다른사람의 풀이를 참조했는데
 * 나는 입력받는 과정에서 틀린주문이라고 생각되면 거기서 바로 break 를 걸었는데
 * 다른사람들은 입력을 다 받고 나서 해당 주문이 틀린주문인지 옳은 주문인지 구분을 함
 * 그래서 나도 그렇게 바꿔봤더니 통과됨
 */
public class Solution99 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] abc = bufferedReader.readLine().split(" ");
        Map<String, String> kind = new HashMap<>();
        Map<String, Long> menu = new HashMap<>();
        long commonTotal = 0;
        long specialTotal = 0;
        int serviceCount = 0;

        int A = Integer.parseInt(abc[0]);
        int B = Integer.parseInt(abc[1]);
        int C = Integer.parseInt(abc[2]);

        for (int i = 0; i < A; i++) {
            String[] split = bufferedReader.readLine().split(" ");
            kind.put(split[0], "common");
            menu.put(split[0], Long.parseLong(split[1]));
        }

        for (int i = 0; i < B; i++) {
            String[] split = bufferedReader.readLine().split(" ");
            kind.put(split[0], "special");
            menu.put(split[0], Long.parseLong(split[1]));
        }

        for (int i = 0; i < C; i++) {
            String readLine = bufferedReader.readLine();
            kind.put(readLine, "service");
            menu.put(readLine, 0L);
        }

        int N = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < N; i++) {
            String food = bufferedReader.readLine();
            String type = kind.get(food);

            if (type.equals("common")) {
                commonTotal += menu.get(food);
            } else if (type.equals("special")) {
                specialTotal += menu.get(food);
            } else {
                serviceCount++;
            }
        }

        if ((commonTotal < 20000 && specialTotal > 0)
        || (commonTotal + specialTotal < 50000 && serviceCount > 0)
        || (serviceCount > 1)) {
            System.out.println("No");
        } else {
            System.out.println("Okay");
        }
    }
}
