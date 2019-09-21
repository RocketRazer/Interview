package a_google;

import java.util.HashSet;
import java.util.Set;

public class CrackTheSafe {
    public static String crackSafe(int n, int k) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append("0");
        }

        String res = sb.toString();
        Set<String> visited = new HashSet<>();
        visited.add(res);

        int total = (int) (Math.pow(k, n));

        for (int i = 0; i < total; i++) {
            String pre = res.substring(res.length() - (n - 1), res.length());
            for (int j = k - 1; j >= 0; j--) {
                String newPass = pre + j;
                if (!visited.contains(newPass)) {
                    visited.add(newPass);
                    res = res + j;
                    break;
                }
            }
        }

        return res;
    }


    public static void main(String[] args) {
        System.out.println(crackSafe(1, 2));
    }
}
