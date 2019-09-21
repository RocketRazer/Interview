package algorithm.kmp;

import java.util.Arrays;

public class KMP {

    void getNext(String pattern, int next[]) {
        int j = 0;
        int k = -1;
        int len = pattern.length();
        next[0] = -1;

        while (j < len - 1) {
            if (k == -1 || pattern.charAt(k) == pattern.charAt(j)) {

                j++;
                k++;
                next[j] = k;
            } else {

                // 比较到第K个字符，说明p[0——k-1]字符串和p[j-k——j-1]字符串相等，而next[k]表示
                // p[0——k-1]的前缀和后缀的最长共有长度，所接下来可以直接比较p[next[k]]和p[j]
                k = next[k];
            }
        }

    }

    int kmp(String s, String pattern) {
        int i = 0;
        int j = 0;
        int slen = s.length();
        int plen = pattern.length();

        int[] next = new int[plen];


        getNext(pattern, next);

        System.out.println("next array is : " + Arrays.toString(next));


        while (i < slen && j < plen) {

            if (s.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            } else {
                if (next[j] == -1) {
                    i++;
                    j = 0;
                } else {
                    j = next[j];
                }

            }

            if (j == plen) {
                return i - j;
            }
        }

        return -1;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        KMP kmp = new KMP();
        String str = "abaacababcac";
        String pattern = "ababc";
        System.out.println(kmp.kmp(str, pattern));
    }
}
//
//
//---------------------
//    作者：syy0377
//    来源：CSDN
//    原文：https://blog.csdn.net/syy0377/article/details/17352539
//    版权声明：本文为博主原创文章，转载请附上博文链接！



