package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 * 11286 절대값 힙
 */
public class Solution23 {
    static class Value {
        private final int originalValue;
        private final int absoluteValue;

        public Value(int originalValue, int absoluteValue) {
            this.originalValue = originalValue;
            this.absoluteValue = absoluteValue;
        }

        public int getOriginalValue() {
            return originalValue;
        }

        public int getAbsoluteValue() {
            return absoluteValue;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stringBuilder = new StringBuilder();
        PriorityQueue<Value> queue = new PriorityQueue<>((o1, o2) -> {
            if (o1.getAbsoluteValue() == o2.getAbsoluteValue()) {
                return o1.getOriginalValue() - o2.getOriginalValue();
            }
            return o1.getAbsoluteValue() - o2.getAbsoluteValue();
        });
        int repeat = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < repeat; i++) {
            String readLine = bufferedReader.readLine();
            if (readLine.equals("0")) {
                if (queue.isEmpty()) {
                    stringBuilder.append("0").append("\n");
                } else {
                    stringBuilder.append(queue.poll().getOriginalValue()).append("\n");
                }
            } else {
                int value = Integer.parseInt(readLine);
                queue.add(new Value(value, Math.abs(value)));
            }
        }
        System.out.println(stringBuilder);
    }
}
