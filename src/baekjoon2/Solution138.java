package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 2980 도로와 신호등 (Silver4)
 * 나는 이 문제를 보자마자 이런식으로 객체를 만들어서 풀어야 겠다고 생각했는데
 * 남들이 푼거 보니 전부다 수식 한줄로 해결했음
 * 나는 수학적 사고방식이 달려서 이렇게밖에 생각을 못했음
 * 애초에 풀이부터 다르니깐 내 코드가 어디서 틀렸는지 참조할수가 없음
 * 그리고 남이 푼거랑 내꺼랑 똑같은 예제를 넣어도 답은 똑같이 나오는데
 * 어디서 틀린건지 모르겠으므로 일단 넘어가고 나중에 해결할것
 */
public class Solution138 {
    static class StopLight {
        int r;
        int g;
        boolean signal;

        public StopLight(int r, int g, boolean signal) {
            this.r = r;
            this.g = g;
            this.signal = signal;
        }

        public void signal(int second) {
            if (signal) {
                if (second % g == 0) {
                    signal = false;
                }
            } else {
                if (second % r == 0) {
                    signal = true;
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] nl = bufferedReader.readLine().split(" ");
        int start = 0;
        int second = 0;
        int N = Integer.parseInt(nl[0]);
        int L = Integer.parseInt(nl[1]);
        Map<Integer, StopLight> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String[] split = bufferedReader.readLine().split(" ");
            int D = Integer.parseInt(split[0]);
            int R = Integer.parseInt(split[1]);
            int G = Integer.parseInt(split[2]);
            map.put(D, new StopLight(R, G , false));
        }
        List<Map.Entry<Integer, StopLight>> list = new ArrayList<>(map.entrySet());

        while (start != L) {
            second++;
            if (map.containsKey(start)) {
                StopLight stopLight = map.get(start);
                if (stopLight.signal) {
                    start++;
                }
            } else {
                start++;
            }

            for (Map.Entry<Integer, StopLight> entry : list) {
                entry.getValue().signal(second);
            }
        }
        System.out.println(second);


        /*
        남의 풀이
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        int position = 0;
        int time = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            int D = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());
            int G = Integer.parseInt(st.nextToken());

            time += D - position;
            position = D;

            int gap = time % (R + G);
            if (gap < R) {
                time += R - gap;
            }
        }

        time += L - position;

        System.out.println(time);*/
    }
}
