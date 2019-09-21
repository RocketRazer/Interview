package airbnb.onsite;

import java.util.*;

/**
 * https://rocket-razer.blogspot.com/2019/05/756-pyramid-transition-matrix.html?zx=6251ef02d5af7b9c
 */
public class PyramidTransitionMatrix {
    /**
     * DFS
     * 递归的解法，首先由于我们想快速知道两个字母上方可以放的字母，需要建立基座字符串和上方字符集合之间的映射，由于上方字符可以不唯一，所以用个HashSet来放字符。
     * 我们的递归函数有三个参数，当前层字符串cur，上层字符串above，还有我们的HashMap。如果cur的大小为2，above的大小为1，那么说明当前已经达到金字塔的顶端了，已经搭出来了，直接返回true。
     * 否则看，如果上一层的长度比当前层的长度正好小一个，说明上一层也搭好了，我们现在去搭上上层，于是调用递归函数，将above当作当前层，空字符串为上一层，将调用的递归函数结果直接返回。
     * 否则表示我们还需要继续去搭above层，我们先算出above层的长度pos，然后从当前层的pos位置开始取两个字符，就是above层接下来需要搭的字符的基座字符，
     */
    public boolean pyramidTransition(String bottom, List<String> allowed) {
        Map<String, Set<Character>> map = new HashMap<>();

        for (String str : allowed) {
            char[] charArray = str.toCharArray();

            String base = new String(new char[]{charArray[0], charArray[1]});

            Set<Character> charSet = map.getOrDefault(base, new HashSet<>());
            charSet.add(charArray[2]);
            map.put(base, charSet);
        }


        return dfs(bottom, "", map);
    }


    private boolean dfs(String bottom, String up, Map<String, Set<Character>> map) {
        if (bottom.length() == 2 && up.length() == 1) {
            return true;
        }


        if (bottom.length() - up.length() == 1) {
            return dfs(up, "", map);
        }

        int curPos = up.length();
        String curBase = bottom.substring(curPos, curPos + 2);

        if (map.containsKey(curBase)) {
            for (Character c : map.get(curBase)) {
                if (dfs(bottom, up + c, map)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     *  DP method
     *  下面来看一种迭代的写法，这是一种DP的解法，建立一个三维的dp数组，其中dp[i][j][ch]表示在金字塔(i, j)位置上是否可以放字符ch，金字塔的宽和高已经确定了，都是n。
     *  每个位置对应着nxn的数组的左半边，如下所示：
     F _ _
     D E _
     A B C
     除了底层，每个位置可能可以放多个字符，所以这里dp数组是一个三维数组，第三维的长度为7，因为题目中限定了字母只有A到G共7个，如果dp值为true，
     表示该位置放该字母，我们根据bottom字符串来初始化dp数组的底层。这里还是需要一个HashMap，不过跟上面的解法略有不同的是，我们建立上方字母跟其能放的基座字符串集合的映射，
     因为一个字母可能可以放多个位置，所以用个集合来表示。
     然后我们就开始从倒数第二层开始往顶部更新啦，对于金字塔的每个位置，我们都遍历A到G中所有的字母，如果当前字母在HashMap中有映射，则我们遍历对应的基座字符串集合中的所有字符串，
     基座字符串共有两个字母，左边的字母对应的金字塔中的位置是(i + 1, j)，右边的字母对应的位置是(i + 1, j + 1)，我们只要在这两个位置上分别查询对应的字母的dp值是否为true，是的话，
     说明当前位置有字母可以放，我们将当前位置的字母对应的dp值赋值为true。
     这样，当我们整个更新完成了之后，我们只要看金字塔顶端位置(0, 0)是否有字母可以放，有的话，说明可以搭出金字塔，返回true，否则返回false，参见代码如下
     */
    public boolean pyramidTransition_dp(String bottom, List<String> allowed) {
        Map<Character, Set<String>> charByBase = new HashMap<>();

        for (String s : allowed) {
            Set<String> baseSet = charByBase.getOrDefault(s.charAt(2), new HashSet<>());
            baseSet.add(s.substring(0, 2));
            charByBase.put(s.charAt(2), baseSet);
        }

        int len = bottom.length();
        boolean[][][] dp = new boolean[len][len][7];

        for (int i = 0; i < len; i++) {
            dp[len-1][i][bottom.charAt(i) - 'A'] = true;
        }


        for (int i = len -2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                for (char c = 'A' ; c <= 'G'; c++) {
                    if (!charByBase.containsKey(c)) {
                        continue;
                    } else {
                        for (String str : charByBase.get(c)) {
                            if (dp[i+1][j][str.charAt(0) - 'A'] && dp[i+1][j+1][str.charAt(1) - 'A']) {
                                dp[i][j][c - 'A'] = true;
                                break;
                            }
                        }

                    }
                }
            }
        }


        for (char c = 'A'; c <= 'G'; c++) {
            if (dp[0][0][c - 'A']) {
                return true;
            }
        }

        return false;
    }
}
