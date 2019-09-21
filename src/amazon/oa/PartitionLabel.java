package amazon.oa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 *
    https://leetcode.com/problems/partition-labels/


    参考： https://www.cnblogs.com/grandyang/p/8654822.html

    建立好映射之后，就需要开始遍历字符串S了，我们维护一个start变量，是当前子串的起始位置，还有一个last变量，是当前子串的结束位置，每当我们遍历到一个字母，我们需要在HashMap中提取出其最后一个位置，因为一旦当前子串包含了一个字母，其必须包含所有的相同字母，所以我们要不停的用当前字母的最后一个位置来更新last变量，只有当i和last相同了，即当i = 8时，当前子串包含了所有已出现过的字母的最后一个位置，即之后的字符串里不会有之前出现过的字母了，此时就应该是断开的位置，我们将长度9加入结果res中，同理类推，我们可以找出之后的断开的位置，参见代码如下

    思路：

    先建立 Map<Character, lastIndex>

    然后记录一个 startIndex 和 lastIndex，并且遍历String的char

    先check当前char的lastIndex 是不是比记录的lastIndex大， 如果大更新lastIndex to 当前的index

    再check当前遍历的index是不是和lastIndex相等，如果相等说明，之前见过的所有的char都在当前startIndex和lastIndex里，

    更新startIndex
 */
public class PartitionLabel {
    public static List<Integer> partitionLabels(String S) {
        List<Integer> res = new ArrayList<>();

        if (S == null || S.length() == 0) return res;

        Map<Character, Integer> lastIdxByChar = new HashMap<>();

        for (int i = 0; i < S.length(); i++) {
            lastIdxByChar.put(S.charAt(i), i);
        }

        //System.out.println(lastIdxByChar);

        int startIdex = 0, lastIndex = lastIdxByChar.get(S.charAt(0));
        for (int i = 0; i < S.length(); i++) {
            //注意这里先update last index
            char currentChar = S.charAt(i);
            int currentCharLastIdx = lastIdxByChar.get(currentChar);
            if (currentCharLastIdx > lastIndex ) {
                lastIndex = currentCharLastIdx;
            }

            //再check 当前pointer 是不是lastIndex 相等
            if (i == lastIndex) {
                res.add(lastIndex - startIdex + 1);
                startIdex = i + 1;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        //expected: [9,7,8]
        System.out.println(partitionLabels("ababcbacadefegdehijhklij"));
    }
}
