class ReverseInteger {

    static int reverse(int x) {
        boolean neg = (x < 0);
        if (neg) x = x * -1;
        Integer newInt = new Integer(x);

        String intStr = newInt.toString();
        StringBuilder revStr = new StringBuilder();


        for (int i = intStr.length() - 1; i >= 0; i--) {
            revStr.append(intStr.charAt(i));
        }

        if (revStr.length() > 10) return 0;
        if (revStr.length() == 10){
            int div10Int = Integer.parseInt(revStr.substring(0, 9));
            int lastDigit = Integer.parseInt(revStr.substring(9));
            if (div10Int > 214748364) return 0;
            if (div10Int == 214748364) {
                if ((neg && lastDigit >= 8) || (!neg && lastDigit >= 7)) {
                    return 0;
                }
            }
        }
        if (neg) revStr.insert(0, "-");
        Integer  revInt = new Integer(revStr.toString());
        return (int) revInt;

    }
    public static void main(String[] args) {


        System.out.println(reverse(-1534236469));

    }
}
