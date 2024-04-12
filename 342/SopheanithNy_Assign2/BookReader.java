import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BookReader {
    public String book;
    public MyLinkedList<String> words;
    public BookReader(String filename) {
        this.words = new MyLinkedList<>();
        readBook(filename);
        parseWords();
    }
    public MyLinkedList<String> getWords() {
        return words;
    }
    public void readBook(String filename) {
        long start = System.currentTimeMillis();
        long duration = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader("WarAndPeace.txt"))) {
            StringBuilder bookBuilder = new StringBuilder();
            int c;
            while ((c = reader.read()) != -1) {
                bookBuilder.append((char) c);
            }
            book = bookBuilder.toString();
            reader.close();
            long now = System.currentTimeMillis();
            duration = now-start;
            System.out.println(bookBuilder.length() + " Character read in " +
                    duration + " milliseconds");
            System.out.println();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
    public void parseWords() {
        long start = System.currentTimeMillis();
        StringBuilder wordBuff =  new StringBuilder();
        for (int i = 0; i < book.length(); i++) {
            Character Char = book.charAt(i);
            if  (Char.compareTo('A') >= 0 && Char.compareTo('Z') <= 0 ||
                Char.compareTo('a') >= 0 && Char.compareTo('z') <= 0  ||
                Char.compareTo('0') >= 0 && Char.compareTo('9') <= 0
                        || Char.equals('\'')) {
                wordBuff.append(Char);
            } else {
                if (wordBuff.length() > 0) {
                    words.addBefore(wordBuff.toString());
                    wordBuff.setLength(0);
                }
            }
        }
        long now = System.currentTimeMillis();
        long duration = now - start;
        System.out.println(wordBuff.length() + " Character read in " +
                duration + " milliseconds");
        System.out.println();
    }
}
