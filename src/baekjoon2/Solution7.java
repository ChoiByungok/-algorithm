package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 13335 트럭
 */
public class Solution7 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        Queue<Integer> truck = new LinkedList<>();
        Queue<Integer> bridge = new LinkedList<>();
        int answer = 0;
        int weight = 0;

        String[] nwl = bufferedReader.readLine().split(" ");
        int n = Integer.parseInt(nwl[0]); // 트럭의 수
        int w = Integer.parseInt(nwl[1]); // 다리의 길이
        int l = Integer.parseInt(nwl[2]); // 다리의 하중

        String[] trucks = bufferedReader.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            truck.add(Integer.parseInt(trucks[i]));
        }
        for (int i = 0; i < w; i++) {
            bridge.add(0);
        }

        while (true) {
            answer++;
            if (bridge.size() == w) {
                weight -= bridge.poll();
                if (truck.isEmpty()) {
                    answer += bridge.size();
                    break;
                } else {
                    if (weight + truck.peek() > l) {
                        bridge.add(0);
                    } else {
                        weight += truck.peek();
                        bridge.add(truck.poll());
                    }
                }
            }
        }
        System.out.println(answer);
    }
}
