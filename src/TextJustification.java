import java.io.Console;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TextJustification {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List <String> lines = new ArrayList<>();
        int currentWordIndex = 0;
         String delimiter = "";

        while (currentWordIndex < words.length) {
            String nextLine = "";
            int currentLineLength = 0;
            int numWordsInLine = 0;
            while (currentLineLength <= maxWidth) {
                if (nextLine == "") {
                    delimiter = "";
                } else {
                    delimiter = " ";
                }
                String nextWord = delimiter + words [currentWordIndex];
                if (currentLineLength + nextWord.length() > maxWidth) {
                    //TODO: pad extra spaces to full justify
                    break;
                } else {
                    nextLine += nextWord;
                    numWordsInLine += 1;
                    currentLineLength += nextWord.length();
                    currentWordIndex += 1;
                    if (currentWordIndex >= words.length) break;
                }
            }
            System.out.println(nextLine.toString());
            lines.add(nextLine);
        }
        return lines;
    }


    public static void main(String[] args) {
        String[] words = new String[] {"This", "is", "an", "example", "of", "text", "justification."};
        int maxWidth = 16;

        List<String> result = new TextJustification().fullJustify(words, maxWidth);
        System.out.println("Final result:");
        System.out.println(result.toString());


    }
}



