package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 1931 회의실 배정
 * 1.종료시간 기준으로 정렬 단 종료시간이 같을 떈 시작시간이 먼저가도록 정렬
 * 2.활동시간이 서로 겹치지 않는 활동에 대해 종료시간이 빠르면 더 많은 활동을 선택할 수 있는 시간이 많아진다는 것
 */
public class Solution38 {
    static class Table {
        int start;
        int end;

        public Table(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        Table[] tables = new Table[N];

        for (int i = 0; i < N; i++) {
            String[] split = bufferedReader.readLine().split(" ");
            int start = Integer.parseInt(split[0]);
            int end = Integer.parseInt(split[1]);
            tables[i] = new Table(start, end);
        }

        Arrays.sort(tables, (o1, o2) -> {
            if (o1.end == o2.end) {
                return o1.start - o2.start;
            }
            return o1.end - o2.end;
        });

        int end = tables[0].end;
        int count = 1;
        for (int i = 1; i < tables.length; i++) {
            if (tables[i].start >= end) {
                end = tables[i].end;
                count++;
            }
        }
        System.out.println(count);
    }
}
