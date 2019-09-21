package a_google;

public class ShipWithinDays  {
    public int shipWithinDays(int[] weights, int D) {
        if (weights == null || weights.length == 0) return 0;

        int n = weights.length;

        int maxWeight = weights[0];
        int sum = 0;
        for (int w : weights) {
            maxWeight = Math.max(maxWeight, w);
            sum += w;
        }

        if (D >= n) {
            return maxWeight;
        }

        int left = maxWeight, right = sum;
        while (left + 1 < right) {
            int mid = (right - left) / 2 + left;
            int days = caculateDays(mid, weights);

            if (days == D) {
                right = mid;
            } else if (days < D) {
                right = mid;
            } else {
                // days > D
                left = mid;
            }
        }

        if (caculateDays(left, weights) <= D) {
            return left;
        } else {
            return right;
        }
    }


    private int caculateDays(int shipWeight, int[] weights) {
        int days = 1;
        int curWeight = shipWeight;

        for (int w : weights) {
            if (curWeight >= w) {
                curWeight -= w;
            } else {
                days++;
                curWeight = shipWeight;
                curWeight -= w;
            }
        }

        return days;
    }


    public static void main(String[] args) {
        ShipWithinDays solution = new ShipWithinDays();
        System.out.println(solution.shipWithinDays(new int[]{1,2,3,4,5,6,7,8,9,10}, 5));
    }
}