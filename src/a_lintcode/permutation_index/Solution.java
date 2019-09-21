package a_lintcode.permutation_index;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    /**
     * @param A: An array of integers
     * @return: A long integer
     */
    public long permutationIndex(int[] A) {
        long an, sum=0;
        for(int i=0; i < A.length; i++){
            an=0;
            for(int j = i+1; j < A.length; j++){
                if( A[i] > A[j])
                    an++;
            }

            sum += an *factorial(A.length-i-1);
        }
        // sum 求出来是当前排列前面总共有多少种排列
        // 当前排列是第几个要 + 1
        return sum+1;
    }

    private long factorial (long n) {
        long sum = 1;

        if (n == 0) {
            return sum;
        }


        while (n > 0) {
            sum *= n;
            n--;
        }

        return sum;
    }



    public long permutationIndexII(int[] A) {
        // write your code here
        Map<Integer,Integer> map = new HashMap<>();
        long count = 1L,dup = 1;
        for(int i=A.length-1;i>=0;i--){
            if(map.containsKey(A[i])){
                map.put(A[i],map.get(A[i])+1);
                dup *= map.get(A[i]);
            }else{
                map.put(A[i],1);
            }
            int less= count_less(A[i],i+1,A);
            long a =  (less*factorial(A.length-i-1))/dup;
            count += a;
        }
        return count;
    }

    public long factorial(int num){
        if(num == 0){
            return 1;
        }
        return num*factorial(num-1);
    }

    public int count_less(int number,int start,int[] array){
        int count = 0;
        for(int i= start;i<array.length;i++){
            if(array[i] < number){
                count++;
            }
        }
        return count;
    }


    public static void main(String[] args) {
        //int[] A = {3, 5, 7, 4, 1, 2, 9, 6, 8};
        int[] A = {3,2,2,1,1};
        Solution s = new Solution();
        System.out.println(s.permutationIndexII(A));
    }
}