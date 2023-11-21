package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 1713 후보추천하기
 * 문제 자체는 이해한대로 구현했는데 4%에서 계속 틀렸다고 나옴
 * 출력도 추천수가 가장 많은 후보가 먼저나오고 추천수가 같다면 들어간 순서대로 출력이 된다고 생각하고 풀었는데
 * "사진틀에 사진이 게재된 최종 후보의 학생 번호를 증가하는 순서대로 출력한다". 라는 문구를 읽지 못했음 문제를 꼼꼼히 읽어보고 풀어야 할듯
 */
public class Solution45 {
    static class Student {
        int num;
        int recommend;
        int order;

        public Student(int num, int recommend, int order) {
            this.num = num;
            this.recommend = recommend;
            this.order = order;
        }

        public void recommend () {
            recommend++;
        }

        public void recommendClear() {
            recommend = 0;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        int repeat = Integer.parseInt(bufferedReader.readLine());
        String[] numbers = bufferedReader.readLine().split(" ");
        StringBuilder answer = new StringBuilder();

        Student[] students = new Student[101];
        for (int i = 1; i <= 100; i++) {
            students[i] = new Student(i, 0, 0);
        }

        List<Student> list = new LinkedList<>();

        for (int i = 0; i < repeat; i++) {
            int num = Integer.parseInt(numbers[i]);
            Student student = students[num];
            if (list.contains(student)) {
                student.recommend();
            } else {
                student.recommend();
                student.order = i + 1;
                if (list.size() == N) {
                    list.sort((o1, o2) -> {
                        if (o1.recommend == o2.recommend) {
                            return o1.order - o2.order;
                        }
                        return o1.recommend - o2.recommend;
                    });
                    Student remove = list.remove(0);
                    remove.recommendClear();
                }
                list.add(student);
            }
        }

        list.sort(Comparator.comparingInt(o -> o.num));

        for (Student student : list) {
            answer.append(student.num).append(" ");
        }
        System.out.println(answer);
    }
}
