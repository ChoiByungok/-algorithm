package Programmers2;

import java.util.*;

/**
 * 프로세스
 */
public class Solution25 {
    public int solution(int[] priorities, int location) {
        int answer = 1;
        Queue<Process> processes = new LinkedList<>();
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < priorities.length; i++) {
            processes.add(new Process(i, priorities[i]));
            list.add(priorities[i]);
        }

        Collections.sort(list);

        while (true) {
            Process process = processes.poll();
            if (process.getPriority() == list.get(list.size() - 1)) {
                if (process.getLocation() == location) {
                    break;
                }
                list.remove(list.size() - 1);
                answer++;
            } else {
                processes.add(process);
            }
        }
        return answer;
    }
    public static class Process{
        private Integer location;
        private Integer priority;

        public Process(int location, int priority) {
            this.priority = priority;
            this.location = location;
        }
        public int getPriority() {
            return priority;
        }
        public int getLocation() {
            return location;
        }
    }

    public static void main(String[] args) {
        int[] priorities1 = {2, 1, 3, 2};
        int location1 = 2;

        int[] priorities2 = {1, 1, 9, 1, 1, 1};
        int location2 = 0;
        System.out.println(new Solution25().solution(priorities1, location1));
    }
}
