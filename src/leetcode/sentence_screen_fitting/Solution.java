package leetcode.sentence_screen_fitting;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static int wordsTyping(String[] sentence, int rows, int cols) {
        if (sentence == null) {
            return 0;
        }

        if (sentence.length == 0) {
            return rows * cols;
        }

        int len = sentence.length;

        List<String> res = new ArrayList<>();

        int wordsIndex = 0;
        int rowCnt = 0;

        while (rowCnt < rows) {
            int startIndex = wordsIndex;
            int lastIndex = wordsIndex + 1;


            int charCnt = sentence[(startIndex % len)].length();

            while (charCnt + 1 + sentence[(lastIndex % len)].length() <= cols) {
                charCnt += 1 + sentence[(startIndex % len)].length();
                lastIndex++;
            }

            StringBuilder sb = new StringBuilder();
            for (int i = startIndex; i < lastIndex; i++) {
                sb.append(sentence[i % len]);

                if (i != lastIndex - 1) {
                    sb.append("-");
                }
            }

            for (int i = sb.length(); i  < cols; i++) {
                sb.append("-");
            }

            res.add(sb.toString());
            System.out.println(sb.toString());
            wordsIndex = lastIndex;
            rowCnt++;
        }

        return (wordsIndex + 1) / sentence.length;
    }

    public static void main(String[] args) {
        String[] sentence = {"I", "had", "apple", "pie"};
        wordsTyping(sentence, 4, 5);
    }
}