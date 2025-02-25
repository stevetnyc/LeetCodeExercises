public class LongestPalindromicSubstring {

    public String longestPalindrome(String s) {
            int endIdx = s.length();
            int maxLen = 1;
            int start = 0;
            if (endIdx == 0 || endIdx > 1000) return "";

            for (int i = 0; i < s.length(); i++) {
                for (int odd = 0; odd <= 1; odd++) {
                    int low = i;
                    int high = i + odd;
                    while (low >= 0 && high < endIdx && s.charAt(low) == s.charAt(high)) {
                        int currLen = high - low + 1;
                        if (currLen > maxLen) {
                            start = low;
                            maxLen = currLen;
                        }
                        low--;
                        high++;
                    }
                }

            }
        return s.substring(start, start + maxLen);
    }

//    public Boolean isPalindrome(String s) {
//        return s.equals(reverseString(s));
//    }
//    public String reverseString(String s) {
//        StringBuilder revString = new StringBuilder();
//        for (int i = s.length() - 1; i >= 0; i--) {
//            revString.append(s.charAt(i));
//        }
//        return revString.toString();
//    }

    public static void main(String[] args) {
//        String s = "babad";
//        String s = "cbbd";
        String s = "zzazzmadamimadamx";
//        String s = "ac";
//        String s = "zudfweormatjycujjirzjpyrmaxurectxrtqedmmgergwdvjmjtstdhcihacqnothgttgqfywcpgnuvwglvfiuxteopoyizgehkwuvvkqxbnufkcbodlhdmbqyghkojrgokpwdhtdrwmvdegwycecrgjvuexlguayzcammupgeskrvpthrmwqaqsdcgycdupykppiyhwzwcplivjnnvwhqkkxildtyjltklcokcrgqnnwzzeuqioyahqpuskkpbxhvzvqyhlegmoviogzwuiqahiouhnecjwysmtarjjdjqdrkljawzasriouuiqkcwwqsxifbndjmyprdozhwaoibpqrthpcjphgsfbeqrqqoqiqqdicvybzxhklehzzapbvcyleljawowluqgxxwlrymzojshlwkmzwpixgfjljkmwdtjeabgyrpbqyyykmoaqdambpkyyvukalbrzoyoufjqeftniddsfqnilxlplselqatdgjziphvrbokofvuerpsvqmzakbyzxtxvyanvjpfyvyiivqusfrsufjanmfibgrkwtiuoykiavpbqeyfsuteuxxjiyxvlvgmehycdvxdorpepmsinvmyzeqeiikajopqedyopirmhymozernxzaueljjrhcsofwyddkpnvcvzixdjknikyhzmstvbducjcoyoeoaqruuewclzqqqxzpgykrkygxnmlsrjudoaejxkipkgmcoqtxhelvsizgdwdyjwuumazxfstoaxeqqxoqezakdqjwpkrbldpcbbxexquqrznavcrprnydufsidakvrpuzgfisdxreldbqfizngtrilnbqboxwmwienlkmmiuifrvytukcqcpeqdwwucymgvyrektsnfijdcdoawbcwkkjkqwzffnuqituihjaklvthulmcjrhqcyzvekzqlxgddjoir";
        System.out.println("String: " + s);
        System.out.println(new LongestPalindromicSubstring().longestPalindrome(s));
    }
}
