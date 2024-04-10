package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 3613 Java vs C++ (Silver3)
 * 호기롭게 도전했다가 신경써야 할게 엄청많아서 고전했던 문제
 * c++ -> java, java -> c++ 변환 자체는 쉬운데 해당 문자열이 에러인지 아닌지 찾아내는게 힘들었다.
 * 결국 질문게시판에서 조건을 찾아봄 이런걸 내가 스스로 찾아내야 진정한 실력인데 아직 경지에 다다르지 못함
 * C++: 맨 뒤, 맨 앞 문자가 '_' 이면 에러, '_' 연속 두개면 에러
 * Java: 맨 앞 문자가 대문자면 에러
 * Cpp와 Java를 혼용하는 경우: 대문자와 '_'가 혼종이면 에러
 * 그 외: 소문자만 입력 될 경우 정상, 대문자 연속일 경우 정상
 * 해당 조건을 모두 집어넣었더니 간신히 통과하였다.
 */
public class Solution186 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        String readLine = bufferedReader.readLine();
        boolean error = false;
        char first = readLine.charAt(0);

        if (first == '_' || (first >= 'A' && first <'Z')) {
            error = true;
        }

        int lowBarCount = 0;
        boolean cpp = false;
        boolean java = false;
        if (!error) {
            for (int i = 1; i < readLine.length(); i++) {
                char c = readLine.charAt(i);
                if (c == '_' || (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')) {
                    if (c == '_') {
                        cpp = true;
                        lowBarCount++;
                        if (i == readLine.length() - 1) {
                            error = true;
                            break;
                        }
                    } else {
                        lowBarCount = 0;
                    }

                    if (lowBarCount > 1) {
                        error = true;
                        break;
                    }

                    if (c <= 'Z') {
                        java = true;
                    }

                    if (cpp && java) {
                        error = true;
                        break;
                    }
                } else {
                    error = true;
                    break;
                }
            }
        }

        if (error) {
            answer.append("Error!");
        } else {
            String[] split = readLine.split("_");
            int diff = 'a' - 'A';
            if (split.length == 1) { // java -> c++
                for (int i = 0; i < readLine.length(); i++) {
                    char c = readLine.charAt(i);
                    if (c >= 'A' && c <= 'Z') {
                        answer.append("_");
                        c = (char) (c + diff);
                    }
                    answer.append(c);
                }

            } else { //c++ -> java
                answer.append(split[0]);
                for (int i = 1; i < split.length; i++) {
                    StringBuilder builder = new StringBuilder(split[i]);
                    builder.replace(0, 1, String.valueOf((char) (builder.charAt(0) - diff)));
                    answer.append(builder);
                }
            }
        }

        System.out.println(answer);
    }
}
