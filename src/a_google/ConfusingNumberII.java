package a_google;


import java.util.HashMap;
import java.util.Map;

public class ConfusingNumberII {
    Map<Integer, Integer> map;
    int N;
    int res;
    public int confusingNumberII(int N) {
        map = new HashMap<>();
        map.put(0, 0);
        map.put(1, 1);
        map.put(6, 9);
        map.put(8, 8);
        map.put(9, 6);

        this.N = N;

        res = 0;
        if (N == 100000000) {
            res++;
            N--;
        }

        search(0, 0);
        return res;
    }


    private void search(int digits, int curNum) {
        if (digits > 9 || curNum > N) {
            return;
        }


        if (isConfusingNumber(curNum)) {
            res++;
        }

        for (int num : map.keySet()) {
            // 在最前面加0 没意义
            if (digits == 0 && num == 0) {
                continue;
            }

            search(digits + 1, curNum * 10 + num);
        }
    }


    private boolean isConfusingNumber(int num) {
        if (num == 0) return false;
        int copy = num;
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            int tmp = num % 10;

            if (!map.containsKey(tmp)) {
                return false;
            }

            sb.append(map.get(tmp));
            num /= 10;
        }

        return Integer.parseInt(sb.toString()) != copy;
    }

    public static void main(String[] args) {
        ConfusingNumberII solution = new ConfusingNumberII();

        solution.confusingNumberII(20);
    }
}