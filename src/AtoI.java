import java.util.HashMap;
import java.util.Set;

public class AtoI {

    public int myAtoi(String str) {
        int rtnVal = 0;
        String valChars = " +-0123456789";
        StringBuilder intChars = new StringBuilder();
        boolean signFound = false;
        boolean isNegative = false;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (valChars.indexOf(c) >= 0) {
                if (c != ' ') {
                    if (c == '+' || c == '-') {
                        //if this is a +/- trailing legit numbers, break
                        if (intChars.length() > 0) break;

                        //if this isn't the first sign character, ignore
                        if (!signFound) {
                            signFound = true;
                            if (c == '-') isNegative = true;
                        } else {
                            break;
                        }
                    } else {
                        intChars.append(c);
                    }
                } else {
                    //if this is a space trailing legit numbers, break
                    if (intChars.length() > 0 || signFound) break;
                }
            } else {
                break;
            }
        }

        if (intChars.length() > 0) {

            int tensColumns = intChars.length();
            String intCharsReversed = intChars.reverse().toString();

            // use reversed string to calculate numbers by their 10s position
            long longVal = 0;
            for (int i = 0; i < tensColumns; i++) {
                longVal += Character.getNumericValue(intCharsReversed.charAt(i)) * Math.pow(10, i);
            }

            if (longVal <= Integer.MAX_VALUE) rtnVal = (int) longVal; else rtnVal = Integer.MAX_VALUE;
            if (isNegative) {
                rtnVal *= -1;
                if (longVal > Integer.MAX_VALUE) rtnVal -= 1;
            }
        }
        return rtnVal;
    }

    public static void main(String[] args) {
        String[] inputs = new String[5];

        inputs[0] = "42";
        inputs[1] = "   -42";
        inputs[2] = "4193 with words";
        inputs[3] = "+-2";
        inputs[4] = "-91283472332";

        for (int i = 0; i < inputs.length; i++) {
            int result = new AtoI().myAtoi(inputs[i]);

            System.out.println("Result: " + result);

            System.out.println("----------------------");
        }
    }

}

