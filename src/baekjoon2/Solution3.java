package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 2841 외계인의 기타 연주
 */
public class Solution3 {
    static List<Stack<Integer>> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] split = bufferedReader.readLine().split(" ");
        int answer = 0;
        boolean equal;
        int N = Integer.parseInt(split[0]);
        int P = Integer.parseInt(split[1]);

        for (int i = 0; i < 7; i++) {
            Stack<Integer> stack = new Stack<>();
            list.add(stack);
        }

        for (int i = 0; i < N; i++) {
            equal = false;
            String[] split1 = bufferedReader.readLine().split(" ");
            int num = Integer.parseInt(split1[0]);
            int fret = Integer.parseInt(split1[1]);

            if (list.get(num).isEmpty()) {
                list.get(num).push(fret);
                answer++;
            } else {
                if (list.get(num).peek() < fret) {
                    list.get(num).push(fret);
                    answer++;
                } else {
                    while (list.get(num).peek() > fret) {
                        list.get(num).pop();
                        answer++;
                        if (list.get(num).isEmpty()) {
                            break;
                        }
                    }
                    if (list.get(num).isEmpty()) {
                        list.get(num).push(fret);
                        answer++;
                    } else if (list.get(num).peek() == fret) {
                        continue;
                    } else {
                        list.get(num).push(fret);
                        answer++;
                    }
                }
            }
        }
        System.out.println(answer);
    }
}
