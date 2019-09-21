package cat.play_game;

/**
 *
 *
 * 1671. play game
 cat-only-icon
 CAT Only
 中文English
 N individuals are playing games, each game has a referee and N-1 civilian players. Given an array A, A[i] represents that the player i needs to be at least a civilian A[i] times, returning the minimum number of games played.

 Example
 Example 1

 Input：A = [2, 2, 2, 2]
 Output : 3
 Explanation：A[0] = 2 means that player 0 needs to be at least 2 times civilian
 The first game: Player 0 serves as the referee, at this time A[0] = 0, A[1] = 1, A[2] = 1, A[3] =1
 Second game: Player 1 serves as the referee, at this time A[0] = 1, A[1] = 1, A[2] = 2, A[3] = 2
 The third game: Player 2 serves as the referee, at this time A[0] = 2, A[1] = 2, A[2] = 2, A[3] = 3
 At this point, each player has met the requirements, so you can play three games.
 Notice
 ∑Ai<=1e18
 n>1
 */

public class Solution {
    /**
     * @param A:
     * @return: nothing
     */
    public long playGames(int[] A) {
        long max = (long)A[0];
        long sum = (long)A[0];
        for (int i = 1; i < A.length; i++)  {
            max = Math.max(max, (long)A[i]);
            sum += (long)A[i];
        }


        long start = max, end = sum;

        while (start + 1 < end) {
            long mid = start + (end - start) / 2;

            long refereeTimes = caculateRefereeTimes(mid, A);

            if (refereeTimes == mid) {
                return mid;
            } else if (refereeTimes > mid) {
                // move left
                end = mid;
            } else {
                start = mid;
            }
        }

        if (caculateRefereeTimes(start, A) >= start) {
            return start;
        } else {
            return end;
        }
    }

    private long caculateRefereeTimes(long playTimes, int[] A) {
        long refereeTimes = 0;

        for (Integer i : A) {
            refereeTimes += playTimes - (long)i;
        }

        return refereeTimes;
    }


    public static void main(String[] args) {
        Solution s = new Solution();
        int[] A = {84, 53};
        System.out.println(s.playGames(A));
    }
}
