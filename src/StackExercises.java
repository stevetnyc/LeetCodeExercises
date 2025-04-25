import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class StackExercises {


    public static String decodeString(String s) {
        // Input: s = "3[a]2[bc]"
        // Output: "aaabcbc"

        Stack<String> subStrings = new Stack<>();
        Set<Character> nums = new HashSet<>();

        nums.add('0');
        nums.add('1');
        nums.add('2');
        nums.add('3');
        nums.add('4');
        nums.add('5');
        nums.add('6');
        nums.add('7');
        nums.add('8');
        nums.add('9');


        int idx = 0;
        String tmpStr = "";
        String tmpMult = "";
        boolean multStr = false;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);


            if (nums.contains(c)) {
                tmpMult += c;
            } else if (c == '[') {
                multStr = true;
            } else if (c == ']') {
                int mult = Integer.valueOf(tmpMult);
                for (int b = 2; b <= mult; b++) {
                    tmpStr += tmpStr;
                }
                subStrings.push(tmpStr);
                tmpStr = "";
                tmpMult = "";
            } else {
                tmpStr += c;
            }

        }
        subStrings.push(tmpStr);

        String result = "";
        while (!subStrings.empty()) {

            result += subStrings.remove(0);
        }
        return result;
    }

    public static int[] dailyTemperatures(int[] temperatures) {
//        Input: temperatures = [73,74,75,71,69,72,76,73]
//        Output: [1,1,4,2,1,1,0,0]

        int[] result = new int[temperatures.length];
        Stack<Integer> idxStack = new Stack<>();

        for (int i = 0; i < temperatures.length; i++) {
            while (!idxStack.empty() && temperatures[i] > temperatures[idxStack.peek()]) {
                int idx = idxStack.pop();
                result[idx] = i - idx;
            }
            idxStack.push(i);
        }

        return result;


    }

    public static void main(String[] args) {
//        String s = "3[a]2[bc]";
//        String s = "2[abc]3[cd]ef";
//        System.out.println(decodeString(s));

        int[] temperatures = {73,74,75,71,69,72,76,73};
        Utils.printArr (dailyTemperatures(temperatures));
    }
}
