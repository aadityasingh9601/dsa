public class BitMan {
    public static String reverseString(String s){
        String str = "";

        for(int i = s.length() -1; i >= 0;i--){
            str += s.charAt(i);
        }

        return str;
    }

    public static String convertToBinary(int n){
        String s = "";
        while(n > 1){
            s += n & 1;
            n = n>>1;
        }
        if(n == 1) s += '1';
        return reverseString(s);
    }

    public static int convertToDecimal(String s){
        int ans = 0;
        int n = 1;

        for(int i = s.length()-1; i>=0; i--){
            if(s.charAt(i) == '1'){
                ans += n;
            }
            n = n * 2;
        }

        return ans;
    }

    public static int getBit(int n, int i){
        int bitMask = 1 << i;

        if((n & bitMask) != 0){
            return 1;
        }
        else{
            return 0;
        }
    }

    public static int setBit(int n, int i){
        int bitMask = 1 << i;

        n = (n | bitMask);

        return n;
    }

    public static int clearBit(int n, int i){
        int bitMask = 1 << i;

        n = (n & (~bitMask));

        return n;
    }

    public static int toggleBit(int n, int i){
        int bitMask = 1 << i;

        n = (n ^ bitMask);

        return n;
    }

    public static int removeLastSetBit(int n){
        return n & (n-1);
    }

    public static boolean checkPowerOf2(int n){
        if((n & (n-1)) == 0){
           return true;
        }
        else {
            return false;
        }
    }

    public static int countSetBits1(int n){
        int count = 0;
        
        while(n > 1){
            if(n % 2 == 1){
                count++;
            }
            n = n / 2;
        }
        if(n == 1) count++;

        return count;
    }

    public static int countSetBits2(int n){
        int count = 0;
        //We've used bitwise operators here because they are slightly faster than normal operators.
        while(n > 1){
            // n & 1 return 1 for odd number and 0 for even number.
            count += n & 1;
            n =  n >> 1;
        }
        if(n == 1) count++;

        return count;
    }
    public static void main(String[] args) {
        System.out.println("Hello everyone");

        System.out.println(convertToBinary(13));

        System.out.println(convertToDecimal("1101"));

        System.out.println(getBit(13, 2));

        System.out.println(setBit(13, 1));

        System.out.println(clearBit(13, 2));

        System.out.println(toggleBit(13, 3));

        System.out.println(removeLastSetBit(12));

        System.out.println(checkPowerOf2(1289));

        System.out.println(countSetBits1(15));

        System.out.println(countSetBits2(15));
    }
}
