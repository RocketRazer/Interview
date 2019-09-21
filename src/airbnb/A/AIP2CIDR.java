package airbnb.A;

import java.util.ArrayList;
import java.util.List;

public class AIP2CIDR {
    public List<String> ipToCIDR(String ip, int n) {
        List<String> res = new ArrayList<>();

        String[] splitedIps = ip.split("\\.");

        long ipInLong = 0;
        for (int i = 0; i < splitedIps.length; i++) {
            ipInLong = Integer.parseInt(splitedIps[i]) + ipInLong * 256;
        }

        System.out.println(Long.toBinaryString(ipInLong));

        while (n > 0) {
            long avaibleIps = ipInLong & -ipInLong;

            while (avaibleIps > n) {
                avaibleIps = avaibleIps / 2;
            }

            res.add(longToIp(ipInLong, (int)avaibleIps));

            ipInLong += avaibleIps;

            n = n - (int)avaibleIps;
        }

        return res;
    }

    private String longToIp(long ipInLong, int avaibleIps) {
        int[] res = new int[4];

        res[0] = (int)(ipInLong & 255);
        ipInLong = ipInLong >> 8;

        res[1] = (int)(ipInLong & 255);
        ipInLong = ipInLong >> 8;

        res[2] = (int)(ipInLong & 255);
        ipInLong = ipInLong >> 8;

        res[3] = (int)(ipInLong & 255);

        int covertDigits = 33;
        while (avaibleIps > 0) {
            avaibleIps = avaibleIps / 2;
            covertDigits--;
        }

        return res[3] + "." + res[2] + "." + res[1] + "." + res[0] + "/" + covertDigits;
    }

    public static void main(String[] args) {
        AIP2CIDR itc = new AIP2CIDR();
        List<String>  res = itc.ipToCIDR("255.0.0.7", 10);
        System.out.println(res);
    }
}
