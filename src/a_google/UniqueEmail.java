package a_google;


import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class UniqueEmail {
    public static int numUniqueEmails(String[] emails) {
        Set<String> set = new HashSet<>();

        for(String e : emails) {
            String[] parts = e.split("@");
            String domain = parts[1];
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < e.length(); i++) {
                if (e.charAt(i) == '+' || e.charAt(i) == '@') {
                    set.add(sb.toString() + "@" + domain);
                    break;
                } else if (e.charAt(i) == '.') {
                    continue;
                } else {
                    sb.append(e.charAt(i) + "");
                }
            }
        }
        System.out.println(set);
        return set.size();
    }

    public static void main(String[] args) {
        String[] emails = {"test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"};
        System.out.println(numUniqueEmails(emails));

        Random random = new Random();
        random.nextInt(10);
        String a = "abc";
        String b = "cdf";
//        match(a, b);
    }
}