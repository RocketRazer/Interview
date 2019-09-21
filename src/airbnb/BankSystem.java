package airbnb;

import java.util.*;

/**
 * design 一个 bank system, 跟地里之前的面经一样，要实现一个
 * deposite(id, timestamp, amount)，
 * withdraw(id, timestamp, amount)，
 * check(id) --> return int;
 * 另外还要实现一个 balance 的 function， 这个 function 跟地里的面经不太一样，要求在 logn 的时间复杂度内完成;
 * 给的参数是 ID， startTime, endTime, 但是要注意 startTime 是不包含在内的。
 * 比如说给你一个 startTime 0, 这个时间 点下一个时间是 10 的话，你算 balance 的时间段应该是从 10 开始，而不是从 0 开始。
 * 另外如果 startTime 是负数的话，那么 startTime 就从 0 开始算;
 * 这题之前在面经上看到过，看到的做法是 用一个 map 来记录 timestamp 与 amount 的之间的对应关系，
 * 但是这样有一个问题便是 hashmap 中的元素是无序的，所以如果你直接用 hashmap 的话，你得事先排序，这样时间复杂度就不是 log 级别了。
 *
 *
 * 因为准备的时间有效，当时看面经的时候没有考虑到这点，其实这里应该另外用一个 Map<id, List<timestamp>>来存属于那个用户的时间线，
 * 这里 suppose 用户交易的时间是顺序的 (跟面试官确认下)，所以这样我们存的 list 就是有序的，就不需要额外的排序了，直接用 binary search 就好了;
 */
public class BankSystem {

    Map<Long, Double> accountBalance = new HashMap<>();
    Map<Long, LinkedHashMap<Long, Double>> balanceByTimeMap = new HashMap<>();

    /**
     * deposit money
     * @param account_id
     * @param timeStamp
     * @param amount
     * @return the balance after the transaction
     */
    public double deposit(long account_id, long timeStamp, double amount) {
        Double currentBalance = accountBalance.getOrDefault(account_id, 0d);
        Double newBalance = currentBalance + amount;
        accountBalance.put(account_id, newBalance);

        LinkedHashMap<Long, Double> balanceByTime = balanceByTimeMap.getOrDefault(account_id, new LinkedHashMap<Long, Double>());
        balanceByTime.put(timeStamp, newBalance);
        balanceByTimeMap.put(account_id, balanceByTime);

        return newBalance;
    }


    /**
     * withdraw money
     * @param account_id
     * @param timeStamp
     * @param amount
     * @return the balance after the transaction
     */
    public double withdraw(long account_id, long timeStamp, double amount) {
        Double currentBalance = accountBalance.getOrDefault(account_id, 0d);

        if (currentBalance < amount) {
            throw new RuntimeException("You don't have enough balance!");
        }

        Double newBalance = currentBalance - amount;
        accountBalance.put(account_id, newBalance);

        LinkedHashMap<Long, Double> balanceByTime = balanceByTimeMap.getOrDefault(account_id, new LinkedHashMap<Long, Double>());
        balanceByTime.put(timeStamp, newBalance);
        balanceByTimeMap.put(account_id, balanceByTime);


        return 0;
    }


    /**
     * Check account balance
     * @param account_id
     * @return the account balance
     */
    public double check(long account_id) {
        Double currentBalance = accountBalance.getOrDefault(account_id, 0d);

        return currentBalance;
    }


    /**
     * Check the balance between two times, time complexity needs to be in logn
     * @param account_id
     * @param startTime excluded
     * @param endTime   included
     * @return the account balance
     */
    public double checkBalanceBetween(long account_id, long startTime, long endTime) {
        double startBalance = findTimeStampBalance(startTime, account_id);
        double endBalance = findTimeStampBalance(endTime - 1, account_id);
        return endBalance - startBalance;
    }


    private double findTimeStampBalance(long timestamp, long account_id) {
        LinkedHashMap<Long, Double> balanceByTime = balanceByTimeMap.getOrDefault(account_id, new LinkedHashMap<Long, Double>());
        Set<Long> keySet = balanceByTime.keySet();
        long[] timeStamps = new long[keySet.size()];

        int i = 0;
        for (Long ts : keySet) {
            timeStamps[i++] = ts;
        }

        long ts = findFirstTimeGreaterThanTarget(0, timeStamps.length -1, timestamp, timeStamps);

        if (ts == -1) {
            throw new RuntimeException("Invalid start time!");
        }

        return balanceByTime.get(ts);
    }

    private long findFirstTimeGreaterThanTarget(int start, int end, long target, long[] timeStamps) {
        int left = start;
        int right = end;

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;

            if (timeStamps[mid] == target) {
                left = mid;
            } else if (target > timeStamps[mid]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        if (timeStamps[left] > target) {
            return timeStamps[left];
        } else if (timeStamps[right] > target) {
            return timeStamps[right];
        } else {
            return -1;
        }
    }


    public static void main(String[] args) {
        Date date = new Date();


        BankSystem bs = new BankSystem();
        bs.deposit(1, 1, 10.0);
        bs.deposit(1, 2, 10.1);
        bs.deposit(1, 3, 10.2);

        bs.withdraw(1, 4, 0.1);
        System.out.println(bs.check(1));
        System.out.println(bs.balanceByTimeMap);

        bs.deposit(1, 5, 10.3);
        bs.deposit(1, 6, 10.4);
        bs.deposit(1, 7, 10.5);
        bs.deposit(1, 8, 10.6);
        bs.deposit(1, 9, 10.7);
        bs.deposit(1, 10, 10.8);
        bs.deposit(1, 11, 10.9);
        System.out.println(bs.check(1));
        System.out.println(bs.balanceByTimeMap);


        //{1={1=10.0, 2=20.1, 3=30.3, 4=30.2, 5=40.5, 6=50.9, 7=61.4, 8=72.0, 9=82.7, 10=93.5, 11=104.4}}

        System.out.println(bs.checkBalanceBetween(1, 4, 11)); // 104.4 - 40.5 =
        System.out.println(bs.checkBalanceBetween(1, 2, 6));  // 50.9 - 30.3 =

    }
}
