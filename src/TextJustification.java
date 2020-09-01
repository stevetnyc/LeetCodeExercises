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
            List<String> nextLine = new ArrayList<String>();
            int currentLineLength = 0;

            // When testing length remember to account for minimum delimiters
            //      which is n-1 where n = number of words
            while (currentLineLength + nextLine.size() - 1 <= maxWidth) {
                String nextWord = words[currentWordIndex];
                if (currentLineLength + nextWord.length() + nextLine.size() + 1 > maxWidth) {
                    break;
                } else {
                    nextLine.add(nextWord);

                    currentLineLength += nextWord.length();
                    currentWordIndex += 1;
                    if (currentWordIndex >= words.length) break;
                }
            }


            String lineToAdd = new String();
            lineToAdd = returnPaddedLine(nextLine, currentLineLength, maxWidth, currentWordIndex >= words.length);
            System.out.println(lineToAdd);
            lines.add(lineToAdd);
        }
        return lines;
    }

    public String returnPaddedLine(List<String>lineWords, int currentWidth, int maxWidth, boolean lastLine) {

        StringBuilder paddedLine = new StringBuilder();

        // We need to add at least 1 space delimiter for all but the last word
        int numOfDelimiters = lineWords.size() - 1;

        int paddedWidth = currentWidth;
        int paddingNeeded = maxWidth - currentWidth;
        int avgSpacesPerDelimiter = paddingNeeded/numOfDelimiters;
        int extraPadCounter = paddingNeeded - (avgSpacesPerDelimiter * numOfDelimiters);

        if (!lastLine) {
            // for all but the last line,
            // add padding between all inner words.
            for (int i = 0; i < lineWords.size() - 1; i++) {
                String padding = new String();
                paddedLine.append(lineWords.get(i) + " ".repeat(avgSpacesPerDelimiter + extraPadCounter));
                if (extraPadCounter > 0) {
                    extraPadCounter--;
                } else {
                    extraPadCounter = 0;
                }
            }
            // Add last word without padding
            paddedLine.append(lineWords.get(lineWords.size() - 1));
        } else {
            // Only add one space beteen words
            // Add extra padding to end

        }

        return paddedLine.toString();
    }

    public static void main(String[] args) {
        //String[] words = new String[] {"This", "is", "an", "example", "of", "text", "justification."};

//        String[] words = new String[] {"This", "is", "an", "example", "of", "text", "justification",
//           "and", "it", "goes", "on", "for", "quite", "a", "while"};

        //String[] words = new String[] {"Hello", "world"};

//        String[] words = new String[] {"Hello"};

//        int maxWidth = 16;

        String[] words = new String[] {"Science","is","what","we","understand","well","enough","to","explain",
                "to","a","computer.","Art","is","everything","else","we","do"};
        int maxWidth = 20;

        List<String> result = new TextJustification().fullJustify(words, maxWidth);
        System.out.println("Final result:");
        System.out.println(result.toString());


    }
}



