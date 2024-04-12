import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class BookReader {
    public String book;
    public MyLinkedList<String> words = new MyLinkedList<>();
    public long fileSize;  // Added variable to store file size

    public BookReader(String filename) {
        readBook(filename);
        parseWords();
    }

    public void readBook(String filename) {
        long startTime = System.currentTimeMillis();
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader( new FileReader(filename))) {
            //int character = br.read();
            while(br.ready()) {
                content.append((char) br.read());
            }
            //content.append((char) br.read());
            book = content.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        book = content.toString();
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;

        // Get the size of the file
        File file = new File(filename);
        fileSize = file.length();

        System.out.println("Reading input file \"" + filename + "\"... " + fileSize + " characters read in " + elapsedTime + " milliseconds");
        System.out.println(book.length());
    }
    public void parseWords() {
        StringBuilder wordBuffer = new StringBuilder();

        for (int i = 0; i <= book.length()-1; i++) {
//            if (i == book.length()-3){
//                System.out.println("Last character" + book.charAt(i));
//            }
            if (validCharacter(book.charAt(i)) || (book.charAt(i)) == '\'') {
                wordBuffer.append(book.charAt(i));
            } else {
                if (!wordBuffer.isEmpty()) {
                    words.addBefore(wordBuffer.toString());
                    wordBuffer.setLength(0);

                }

            }
        }

        if (!wordBuffer.isEmpty()) {
            words.addBefore(wordBuffer.toString());
        }
    }

    private boolean validCharacter(char letter) {
        return numberCharacters(letter) || upperCaseCharacters(letter) || lowerCaseCharacters(letter);
    }

    private boolean numberCharacters(char letter) {
        return letter >= '0' && letter <= '9';
    }

    private boolean upperCaseCharacters(char letter) {
        return letter >= 'A' && letter <= 'Z';
    }

    private boolean lowerCaseCharacters(char letter) {
        return letter >= 'a' && letter <= 'z';
    }

}