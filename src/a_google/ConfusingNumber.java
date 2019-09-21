package a_google;

public class ConfusingNumber {
    public static boolean confusingNumber(int N) {
        if (N == 0) return false;

        int copy = N;
        StringBuilder sb = new StringBuilder();
        while (N > 0) {
            int tmp = N % 10;

            if (tmp == 6 || tmp == 9) {
                sb.append(tmp == 6 ? 9 : 6);
            } else if (tmp == 1 || tmp == 0 || tmp == 8) {
                sb.append(tmp);
            } else {
                return false;
            }

            N /= 10;
        }

        return Integer.parseInt(sb.toString()) != copy;
    }

    public static void main(String[] args) {
        System.out.println(confusingNumber(1000000000));
    }
}
