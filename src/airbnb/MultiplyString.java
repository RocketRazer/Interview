package airbnb;

public class MultiplyString {
    public String multiply(String num1, String num2) {
        int n1 = num1.length();
        int n2 = num2.length();

        int[] num = new int[n1 + n2 + 1];
        for(int i = 0; i < n2; i ++){
            int carry = 0;
            int a = num2.charAt(n2 - 1 - i) - '0';
            for(int j = 0; j < n1; j ++){
                int b = num1.charAt(n1 - 1 - j) - '0';
                num[i + j] += a * b + carry;
                carry = num[i + j] / 10;
                num[i + j] = num[i + j] % 10;
            }
            // 123*9 carry add to the left side of digit '1'
            num[i + n1] = carry;
        }

        int i  = num.length -1;
        while( i > 0 && num[i] == 0){
            i--;
        }

        StringBuilder sb = new StringBuilder();
        while(i >= 0){
            sb.append(num[i--]);
        }
        return sb.toString();

    }

    public String multiply2(String num1, String num2) {
        int m = num1.length(), n = num2.length();
        int[] pos = new int[m + n];

        for(int i = m - 1; i >= 0; i--) {
            for(int j = n - 1; j >= 0; j--) {
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int p1 = i + j, p2 = i + j + 1;
                int sum = mul + pos[p2];

                pos[p1] += sum / 10;
                pos[p2] = (sum) % 10;
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int p : pos) {
            if(!(sb.length() == 0 && p == 0)) {
                sb.append(p);
            }
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }

    public static void main(String[] args) {
        MultiplyString ms = new MultiplyString();
        System.out.println(ms.multiply2("89", "76"));
    }

}
