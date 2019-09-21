package a_lintcode.basic.wk8.hash_func;

public class Solution {
    /**
     * @param key: A string you should hash
     * @param HASH_SIZE: An integer
     * @return: An integer
     */
    public int hashCode(char[] key, int HASH_SIZE) {
        // write your code here
        if (key == null || key.length == 0) {
            return 0;
        }

        int len = key.length;
        long hash = 0;
        for (int i = 0; i < len; i++) {
            char c = key[i];
            hash += (int)c * Math.pow(33,  len - 1 - i);
        }

        return (int)(hash % HASH_SIZE);
    }


    public static void main(String[] args) {
//        Solution s = new Solution();
//        s.hashCode(new char[]{'a', 'b', 'c', 'd'}, 1000);

        System.out.println(Math.pow(33, 3));
    }
}