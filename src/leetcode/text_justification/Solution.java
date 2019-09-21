package leetcode.text_justification;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static List<String> fullJustify(String[] words, int maxWidth) {
        List<String> lines = new ArrayList<>();

        int startIndex = 0;
        while (startIndex < words.length) {
            int count = words[startIndex].length();
            int last = startIndex + 1;

            // find the lastIndex
            // where startIndex + lastIndex -1 can fit in one line
            while (last < words.length) {
                if (count + 1 + words[last].length() > maxWidth) {
                    break;
                }

                count += 1 + words[last].length();
                last++;
            }

            StringBuilder sb = new StringBuilder();
            int numOfWordsInLine = last - startIndex;
            // if last line or number of words in the line is 1, left-justified
            // last line or only has one word in line
            if (last == words.length || numOfWordsInLine == 1) {
                for (int i = startIndex; i < last; i++) {
                    sb.append(words[i] + " ");
                }

                //remove last space
                sb.deleteCharAt(sb.length() - 1);

                // append extra spaces
                for (int i = startIndex; i < maxWidth; i++) {
                    sb.append(" ");
                }
            } else {
                //middle justified
                int extraSpacesForEachWord = (maxWidth - count) / (numOfWordsInLine - 1);
                int remainSpace = (maxWidth - count) / (numOfWordsInLine - 1);

                for (int i = startIndex; i< last; i++) {
                    sb.append(words[i]);
                    if (i != last -1) {
                        sb.append(" ");
                    }
                    // do not append extra space after the last word
                    if (i < last - 1) {
                        //append extra spaces
                        int extraSpaceAppend = extraSpacesForEachWord + ((i - startIndex) < remainSpace ? 1 : 0);
                        for (int j = 0; j < extraSpaceAppend; j++) {
                            sb.append(" ");
                        }
                    }

                }


            }

            lines.add(sb.toString());
            startIndex = last;
        }

        return lines;
    }

    public static void main(String[] args) {
        String[] words = {"This", "is", "an", "example", "of", "text", "justification."};
        fullJustify(words, 16);
    }
}
