import java.util.HashMap;
import java.util.LinkedHashMap;

public class IntToRoman {

    static String toRoman(int num) {
        StringBuilder romanNum = new StringBuilder();

        while (num > 0) {
            while (num >= 1000) {
                romanNum.append("M");
                num -= 1000;
            }
            while (num >= 900) {
                romanNum.append("CM");
                num-=900;
            }
            while (num >= 500) {
                romanNum.append("D");
                num-=500;
            }
            while (num >= 400) {
                romanNum.append("CD");
                num-=400;
            }
            while (num >= 100) {
                romanNum.append("C");
                num-=100;
            }
            while (num >= 90) {
                romanNum.append("XC");
                num-=90;
            }
            while (num >= 50) {
                romanNum.append("L");
                num-=50;
            }
            while (num >= 40) {
                romanNum.append("XL");
                num-=40;
            }
            while (num >= 10) {
                romanNum.append("X");
                num-=10;
            }
            while (num >= 9) {
                romanNum.append("IX");
                num-=9;
            }
            while (num >= 5) {
                romanNum.append("V");
                num-=5;
            }
            while (num >= 4) {
                romanNum.append("IV");
                num-=4;
            }
            while (num >= 1) {
                romanNum.append("I");
                num-=1;
            }
        }

        return romanNum.toString();
    }

    static String intToRoman(int num) {

        LinkedHashMap <Integer, String> intToRomanMap = new LinkedHashMap<>();
        intToRomanMap.put(1000,"M");
        intToRomanMap.put(900,"DM");
        intToRomanMap.put(500,"D");
        intToRomanMap.put(400,"CD");
        intToRomanMap.put(100,"C");
        intToRomanMap.put(90,"XC");
        intToRomanMap.put(50,"L");
        intToRomanMap.put(40,"XL");
        intToRomanMap.put(10,"X");
        intToRomanMap.put(9,"IX");
        intToRomanMap.put(5,"V");
        intToRomanMap.put(4,"IV");
        intToRomanMap.put(1,"I");

        StringBuilder romanNum = new StringBuilder();
        for (int intVal: intToRomanMap.keySet()) {
            while (num >= intVal) {
                romanNum.append(intToRomanMap.get(intVal));
                num-=intVal;
            }
        }
        return romanNum.toString();
    }



    public static void main(String[] args) {
        String ans = intToRoman(58);
        System.out.println(ans);
    }
}
