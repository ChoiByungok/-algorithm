package Programmers2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 *[3차] 파일명 정렬(실패) 정답률 80%
 */
public class Solution48 {
     class FileName {
        String head;
        String number;
        String tail;

         public FileName(String head, String number, String tail) {
             this.head = head;
             this.number = number;
             this.tail = tail;
         }

         public String getHead() {
            return head;
        }

        public void setHead(String head) {
            this.head = head;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getTail() {
            return tail;
        }

        public void setTail(String tail) {
            this.tail = tail;
        }

         @Override
         public String toString() {
             return "FileName{" +
                     "head='" + head + '\'' +
                     ", number=" + number +
                     ", tail='" + tail + '\'' +
                     '}';
         }
         public String perfectFileName() {
             return head + number + tail;
         }
     }

    public String[] solution(String[] files) {
        List<FileName> list = new ArrayList<>();
        for (String file : files) {
            int head = 0;
            int number = 0;
            for (int i = 0; i < file.length(); i++) {
                if (file.charAt(i) >= '0' && file.charAt(i) <= '9') {
                    head = i;
                    break;
                }
            }
            int count = 0;

            for (int i = head; i < file.length(); i++) {
                if (!(file.charAt(i) >= '0' && file.charAt(i) <= '9') || count > 4) {
                    number = i;
                    break;
                } else {
                    count++;
                }
            }
            if (number == 0) {
                number = file.length();
            }

            String header = file.substring(0, head);
            String num = file.substring(head, number);
            String tail = file.substring(number);
            FileName fileName = new FileName(header, num, tail);
            list.add(fileName);
        }
        System.out.println("list = " + list);
        list.sort((o1, o2) -> {
            if (o1.getHead().equalsIgnoreCase(o2.getHead())) {
                return Integer.parseInt(o1.getNumber()) - Integer.parseInt(o2.getNumber());
            } else {
                return o1.getHead()
                        .toUpperCase()
                        .replaceAll("[^A-Z]", "")
                        .compareToIgnoreCase(o2.getHead()
                                .toUpperCase()
                                .replaceAll("[^A-Z]", ""));
            }
        });
        return IntStream
                .range(0, files.length)
                .mapToObj(i -> list.get(i).perfectFileName())
                .toArray(String[]::new);
    }

    public static void main(String[] args) {
        String[] files1 = {"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"};
        String[] files2 = {"F-5 Freedom Fighter", "B-50 Superfortress", "A-10 Thunderbolt II", "F-14 Tomcat", "F-15"};
        String[] files3 = {"-F-15f42345", "F-15342345", "g341fdsaf.jpg", "H g000342.jpg", "H g00 1342.jpg", "f- g 345.jpg"};
        String[] files4 = {"F-15"};

        System.out.println(Arrays.toString(new Solution48().solution(files4)));
    }
}
