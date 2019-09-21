package airbnb.onsite;

import java.util.ArrayList;
import java.util.List;

public class IpToCIDR {
    public List<String> ipToCIDR(String ip, int n) {
        String[] ips = ip.split("\\.");

        //用long表示ip地址
        long ipInLong = 0;

        //把ip地址转换成相应的二进制数 用long来表示
        for (int i = 0; i < ips.length; i++) {
            ipInLong = Integer.parseInt(ips[i]) + 256 * ipInLong;
        }

        // ipInLong : 11111111 00000000 00000000 00000111
        System.out.println(Long.toBinaryString(ipInLong));

        List<String> res = new ArrayList<>();


        while (n > 0) {
            //ip地址又不能小于给定的ip地址，所以我们只能将0变为1, 所以要找到从右往左的第一个1
            //找末尾1有个trick，就是利用 x & -x 来快速找到
            //coveredNumOfIps 代表末尾最后一个1代表的二进制数， 用long表示，
            // 及可求出末尾最后一个1代表的数可以cover多少个ip
            long coveredNumOfIps = ipInLong & -ipInLong;

            //尽可能多的cover，但不能超过 n
            while (coveredNumOfIps > n) {
                coveredNumOfIps = coveredNumOfIps / 2;
            }

            //加到下一个开始的ip地址
            res.add(longToIp(ipInLong, (int)coveredNumOfIps));

            //更新下一个开始的ip地址
            ipInLong += coveredNumOfIps;

            //减掉已经cover的ip地址
            n -= coveredNumOfIps;
        }

        return res;
    }


    private String longToIp(Long ipInLong, int coveredNumOfIps) {
        int[] res = new int[4];
        //每次取最后8位二进制 转换成int
        res[0] = (int)(ipInLong & 255);
        ipInLong = ipInLong >> 8;

        res[1] = (int)(ipInLong & 255);
        ipInLong = ipInLong >> 8;

        res[2] = (int)(ipInLong & 255);
        ipInLong = ipInLong >> 8;

        res[3] = (int)(ipInLong & 255);

        //剪掉相应的用掉的个数的对应的二进制位数
        int cidr = 33;
        while (coveredNumOfIps > 0) {
            cidr--;
            coveredNumOfIps = coveredNumOfIps / 2;
        }


        return res[3] + "." + res[2] + "." + res[1] + "." + res[0] + "/" + cidr;
    }
    public static void main(String[] args) {
        IpToCIDR itc = new IpToCIDR();
        List<String>  res = itc.ipToCIDR("255.0.0.124", 10);
        System.out.println(res);
    }
}
