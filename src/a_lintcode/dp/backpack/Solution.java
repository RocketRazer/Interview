package a_lintcode.dp.backpack;

import java.util.HashMap;
import java.util.Map;

/**
1650. Legal Article

        Given an article consisting of uppercase letters, lowercase letters, commas, and periods, find the number of letters that are illegal.
        There are 2 situations that can cause an article to be illegal:

        The first letter of a sentence is in lowercase.
        The letter which is not the first letter of the word is in uppercase.
        Example
        Example1

        Input: s="This won iz correkt. It has, No Mistakes et Oll. But there are two BIG mistakes in this sentence. and here is one more."
        Output: 3
        Explanation:
        The letter 'I' and 'G' in the word 'BIG' and the letter 'a' of the first word of the third sentence is incorrect.
        Example2

        input: s="Hahaha. HahaHa. hahahah."
        Output: 2
        The second 'H' in the word 'HahaHa' and the first 'h' in the word 'hahahah' is incorrect.
        Notice
        A sentence ends if and only if it is a period.
        The length of the article does not exceed 1000010000.
*/


public class Solution {
    /**
     * @param s: the article
     * @return: the number of letters that are illegal
     */
    public static int count(String s) {
        // Write your code here.
        int count = 0;

        if (s == null || s.length() == 0) {
            return count;
        }

        String[] sArray = s.trim().split(" ");


        for (int i = 0; i < sArray.length; i++) {
            String test = sArray[i];
            char[] charArray = test.toCharArray();
            // check char 1 ~ n-2 character
            for (int j = 1; j < charArray.length - 1; j++) {
                if (Character.isUpperCase(charArray[j])) {
                    count++;
                }
            }

            char lastChar = charArray[test.length()-1];
            if ( lastChar == '.' || lastChar == ',') {
                if (i < sArray.length - 1) {
                    String nextWord = sArray[i+1];
                    if (!Character.isUpperCase(nextWord.charAt(0))) {
                        count++;
                    }
                }
            } else {
                if (Character.isUpperCase(lastChar)) {
                    count++;
                }
            }
        }

        return count;
    }



    public static void main(String[] args) {
        //Solution.count("Hahaha. HahaHa. hahahah.");
        Solution3.mostFrequentlyAppearingLetters("ABCabcA");
    }

}

class Solution2 {
    /**
     * @param str: The identifier need to be judged.
     * @return: Return if str is a legal identifier.
     */
    public boolean isLegalIdentifier(String str) {
        if (str.length() == 0) {
            return false;
        }

        char[] cc = str.toCharArray();

        char firstChar = cc[0];
        if ( !(('A' <= firstChar && firstChar <= 'Z' ) || ('a' <= firstChar && firstChar <= 'z')  || (firstChar == '_' ) )) {
            return false;
        }

        for (int i = 1; i < cc.length; i++) {
            if (!isValidCharacter(cc[i])) {
                return false;
            }
        }

        return true;

    }

    private boolean isValidCharacter(char c) {
        return ('A' <= c && c <= 'Z' ) || ('a' <= c && c <= 'z')
                || ('0' <= c && c <= '9')  || (c == '_' );
    }
}


 class Solution3 {
    /**
     * @param str: the str
     * @return: the sum that the letter appears the most
     */
    public static int mostFrequentlyAppearingLetters(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }

        Map<Character, Integer> map = new HashMap<>();

        char[] cc = str.toCharArray();

        for (Character c : cc) {
            if (map.containsKey(c)) {
                int count = map.get(c);
                count++;
                map.put(c, count);
            } else {
                map.put(c, 1);
            }
        }

        int max = 0;
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            max = Math.max(max, entry.getValue());
        }

        return max;
    }
}