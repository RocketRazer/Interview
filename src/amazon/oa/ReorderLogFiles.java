package amazon.oa;

import java.util.Arrays;
import java.util.Comparator;

/**
 *
 * https://leetcode.com/problems/reorder-log-files/
 *
 You have an array of logs.  Each log is a space delimited string of words.

 For each log, the first word in each log is an alphanumeric identifier.  Then, either:

 Each word after the identifier will consist only of lowercase letters, or;
 Each word after the identifier will consist only of digits.

 We will call these two varieties of logs letter-logs and digit-logs.
 It is guaranteed that each log has at least one word after its identifier.

 Reorder the logs so that all of the letter-logs come before any digit-log.

 The letter-logs are ordered lexicographically ignoring identifier, with the identifier used in case of ties.
 The digit-logs should be put in their original order.

 Return the final order of the logs.


 *  Input: ["a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo"]
 * Output: ["g1 act car","a8 act zoo","ab1 off key dog","a1 9 2 3 1","zo4 4 7"]
 */
public class ReorderLogFiles {
    public static String[] reorderLogFiles(String[] logs) {
        Comparator<String> myComp = new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                // 这里分成两段就好了， 第二段直接拿来排序
                String[] aStrs = a.split(" ", 2);
                String[] bStrs = b.split(" ", 2);

                boolean a_isDigitLog = Character.isDigit(aStrs[1].charAt(0));
                boolean b_isDigitLog = Character.isDigit(bStrs[1].charAt(0));

                if (!a_isDigitLog && !b_isDigitLog) {
                    if (aStrs[1].equals(bStrs[1])) {
                        return aStrs[0].compareTo(bStrs[0]);
                    } else {
                        return aStrs[1].compareTo(bStrs[1]);
                    }
                } else {
                    //这里要注意，如果a不是digit， 直接return -1， a排在前面
                    if (a_isDigitLog) {
                        //如果a是digit， 且b是diigt，return 0 保持original order
                        //如果b不是digit return 1， b 排在a前面
                        return b_isDigitLog ? 0 : 1;
                    } else {
                        return -1;
                    }
                }
            }
        };

        Arrays.sort(logs, myComp);
        return logs;
    }

    public static void main(String[] args) {
        String[] logs = new String[]{"a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo"};


        //expected: ["g1 act car","a8 act zoo","ab1 off key dog","a1 9 2 3 1","zo4 4 7"]
        System.out.println("expected: [\"g1 act car\",\"a8 act zoo\",\"ab1 off key dog\",\"a1 9 2 3 1\",\"zo4 4 7\"]");
        System.out.println(Arrays.toString(reorderLogFiles(logs)));
    }
}
