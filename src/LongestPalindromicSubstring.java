public class LongestPalindromicSubstring {

    public String longestPalindrome(String s) {
            int revIndex = s.length();
            if (revIndex == 0 || revIndex > 1000) return "";
            String longest = s.substring(0, 1);

            for (int i = 0; i < s.length(); i++) {
                while (i < revIndex - 1) {
                    if (isPalindrome(s.substring(i, revIndex))) {
                        if (s.substring(i, revIndex).length() > longest.length()) {
                            longest = s.substring(i, revIndex);
                        }
                    }
                    revIndex--;
                }
                revIndex = s.length();
            }
            return longest;
    }

    public Boolean isPalindrome(String s) {
        return s.equals(reverseString(s));
    }
    public String reverseString(String s) {
        StringBuilder revString = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            revString.append(s.charAt(i));
        }
        return revString.toString();
    }

    public static void main(String[] args) {
//        String s = "babad";
//        String s = "cbbd";
//        String s = "zzazzmadamimadamx";
//        String s = "ac";
        String s = "zudfweormatjycujjirzjpyrmaxurectxrtqedmmgergwdvjmjtstdhcihacqnothgttgqfywcpgnuvwglvfiuxteopoyizgehkwuvvkqxbnufkcbodlhdmbqyghkojrgokpwdhtdrwmvdegwycecrgjvuexlguayzcammupgeskrvpthrmwqaqsdcgycdupykppiyhwzwcplivjnnvwhqkkxildtyjltklcokcrgqnnwzzeuqioyahqpuskkpbxhvzvqyhlegmoviogzwuiqahiouhnecjwysmtarjjdjqdrkljawzasriouuiqkcwwqsxifbndjmyprdozhwaoibpqrthpcjphgsfbeqrqqoqiqqdicvybzxhklehzzapbvcyleljawowluqgxxwlrymzojshlwkmzwpixgfjljkmwdtjeabgyrpbqyyykmoaqdambpkyyvukalbrzoyoufjqeftniddsfqnilxlplselqatdgjziphvrbokofvuerpsvqmzakbyzxtxvyanvjpfyvyiivqusfrsufjanmfibgrkwtiuoykiavpbqeyfsuteuxxjiyxvlvgmehycdvxdorpepmsinvmyzeqeiikajopqedyopirmhymozernxzaueljjrhcsofwyddkpnvcvzixdjknikyhzmstvbducjcoyoeoaqruuewclzqqqxzpgykrkygxnmlsrjudoaejxkipkgmcoqtxhelvsizgdwdyjwuumazxfstoaxeqqxoqezakdqjwpkrbldpcbbxexquqrznavcrprnydufsidakvrpuzgfisdxreldbqfizngtrilnbqboxwmwienlkmmiuifrvytukcqcpeqdwwucymgvyrektsnfijdcdoawbcwkkjkqwzffnuqituihjaklvthulmcjrhqcyzvekzqlxgddjoir";
        System.out.println("String: " + s);
        System.out.println(new LongestPalindromicSubstring().longestPalindrome(s));
    }
}
