package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 10814 나이순 정렬
 */
public class Solution19 {
    static class Member {
        public Integer age;
        public String name;

        public Member(Integer age, String name) {
            this.age = age;
            this.name = name;
        }
        public Integer getAge() {
            return age;
        }
        public String getName() {
            return name;
        }
    }
    public static void main(String[] args) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        List<Member> members = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int repeat = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < repeat; i++) {
            String[] split = bufferedReader.readLine().split(" ");
            int age = Integer.parseInt(split[0]);
            String name = split[1];
            members.add(new Member(age, name));
        }
        members.sort(Comparator.comparingInt(Member::getAge));
        for (Member member : members) {
            stringBuilder.append(member.getAge()).append(" ").append(member.getName()).append("\n");
        }
        System.out.println(stringBuilder);
    }
}
