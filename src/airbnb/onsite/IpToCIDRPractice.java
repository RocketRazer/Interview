package airbnb.onsite;

import java.util.ArrayList;
import java.util.List;




public class IpToCIDRPractice {
    public List<String> ipToCIDR(String ip, int n) {
        String[] ipParts = ip.split("\\.");

        long ipInLong = 0;
        for (String p : ipParts) {
            ipInLong = Integer.parseInt(p) + ipInLong * 256;
        }

        System.out.println(Long.toBinaryString(ipInLong));

        List<String> res = new ArrayList<>();
        while (n > 0) {

            long coveredNumOfIps = ipInLong & -ipInLong;

            while (coveredNumOfIps > n) {
                coveredNumOfIps = coveredNumOfIps / 2;
            }

            res.add(longToIp(ipInLong, (int)coveredNumOfIps));

            ipInLong = ipInLong + coveredNumOfIps;

            n = n - (int)coveredNumOfIps;
        }

        return  res;
    }


    private String longToIp(Long ipInLong, int coveredNumOfIps) {
        int[] ipParts = new int[4];

        ipParts[0] = (int)(ipInLong & 255);
        ipInLong = ipInLong >> 8;

        ipParts[1] = (int)(ipInLong & 255);
        ipInLong = ipInLong >> 8;

        ipParts[2] = (int)(ipInLong & 255);
        ipInLong = ipInLong >> 8;

        ipParts[3] = (int)(ipInLong & 255);

        int cidr = 33;

        while (coveredNumOfIps > 0) {
            cidr--;
            coveredNumOfIps = coveredNumOfIps / 2;
        }


        return ipParts[3] + "." + ipParts[2] + "."  + ipParts[1] + "." + ipParts[0] + "/" + cidr;
    }
    public static void main(String[] args) {
        IpToCIDRPractice itc = new IpToCIDRPractice();
        List<String>  res = itc.ipToCIDR("255.0.0.7", 10);
        System.out.println(res);
    }
}
