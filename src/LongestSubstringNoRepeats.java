
import java.util.*;

public class LongestSubstringNoRepeats {
    public int lengthOfLongestSubstring(String s) {
        return findLongestSubStringRecursive(s, 0);
    }

    public int findLongestSubStringRecursive(String s, int currentLongestSubstring) {
        List<Character> subString = new ArrayList<Character>();
        int maxStringLength = currentLongestSubstring;

        if (s.length() == 0) {
            return currentLongestSubstring;
        } else {
            for (int i = 0; i < s.length(); i++) {
                Character c = s.charAt(i);
                int idx = subString.indexOf(c);
                if (idx > -1) {
                    if (subString.size() > maxStringLength) maxStringLength = subString.size();
                    String newStr = s.substring(idx+1);
                    return findLongestSubStringRecursive(newStr, maxStringLength);
                } else {
                    subString.add(c);
                }
            }
            if (subString.size() > maxStringLength) maxStringLength = subString.size();
            return maxStringLength;
        }
    }

    public static void main(String[] args) {
        String str[] = new String[6];
        str[0] = "abcabcbb";
        str[1] = "bbbbb";
        str[2] = "pwwkew";
        str[3] = "abcdefghhijklmnop";
        str[4] = "aab";
        str[5] = "dvdf";



        int maxStringLength = 0;
        LongestSubstringNoRepeats lsnr = new LongestSubstringNoRepeats();

        for (String s: str) {
            maxStringLength = lsnr.lengthOfLongestSubstring(s);
            System.out.println("String: " + s);
            System.out.println("Longest substring was: " + maxStringLength);

        }
     }
}
