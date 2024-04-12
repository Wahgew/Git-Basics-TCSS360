import java.awt.print.Book;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collections;
import java.util.List;

public class UniqueWords {
    //add size and comparsions
    public BookReader book;
    public MyArrayList<String> alOfUniqueWords;
    public MyLinkedList<String> llOfUniqueWords;
    public MyOrderedList<String> olOfUniqueWords;

    public MyTrie trieOfUniqueWords;
    public UniqueWords() throws IOException {
        // this.book = new BookReader("test.txt");
        this.book = new BookReader("WarAndPeace.txt");
        this.alOfUniqueWords = new MyArrayList<>();
        this.llOfUniqueWords = new MyLinkedList<>();
        this.olOfUniqueWords = new MyOrderedList<>();
        this.trieOfUniqueWords = new MyTrie();
    }
    public void addUniqueWordsToLinkedList() {
        String file = book.words.first();
        long time;
        long start = System.currentTimeMillis();
        long duration = System.currentTimeMillis() - start;
        outputDuration("Adding unique words to a linked list", duration);
        while (file != null) {
            if (!llOfUniqueWords.contains(book.words.current())) {
                llOfUniqueWords.addBefore(book.words.current());
            }
            file = book.words.next();
        }
        System.out.println(llOfUniqueWords.size() + " unique words were found.");
        System.out.println(llOfUniqueWords.comparisons + " comparisons were made.");

        start = System.currentTimeMillis();
        llOfUniqueWords.sort();
        duration = System.currentTimeMillis() - start;
        outputDuration("Bubble sorting linked list", duration);
    }
    private void outputDuration(String action, long duration) {
        System.out.print(action + "...");
        if (duration < 1000) {
            System.out.println(" in " + duration + " milliseconds.");
        } else {
            long pre = duration / 1000;
            long post = duration % 1000;
            System.out.println(" in " + pre + "." + post + " seconds.");
        }
    }

    public void addUniqueWordsToArrayList() {
        String file = book.words.first();
        long start = System.currentTimeMillis();
        long duration = System.currentTimeMillis() - start;

        while (file != null) {
            if (!alOfUniqueWords.contains(book.words.current())) {
                alOfUniqueWords.insert(book.words.current(), alOfUniqueWords.size());
                //System.out.print(alOfUniqueWords.size());
            }
            file = book.words.next();
        }
        System.out.println(alOfUniqueWords.size() + " unique words were found.");
        System.out.println(alOfUniqueWords.comparisons + " comparisons were made.");
        start = System.currentTimeMillis();
        alOfUniqueWords.sort();
        duration = System.currentTimeMillis() - start;
        outputDuration("Sorting array list of unique words", duration);
    }
    public void addUniqueWordsToOrderedList() {
        String file = book.words.first();
        long start = System.currentTimeMillis();

        while (file != null) {
            // Binary search to check if the word is already in the ordered list
            String currentWord = book.words.current();
            boolean isWordFound = (olOfUniqueWords.binarySearch(currentWord) != null);

            if (!isWordFound) {
                // Word not found, add it to the list
                olOfUniqueWords.add(currentWord);
            }

            // Move to the next word in the book only if it's not null
            file = book.words.next();
        }

        long duration = System.currentTimeMillis() - start;
        System.out.println(olOfUniqueWords.size() + " unique words were found.");
        System.out.println(olOfUniqueWords.comparisons + " comparisons were made.");
    }



        public void addUniqueWordsToTrie() {
        String file = book.words.first();
        long start = System.currentTimeMillis();
        long duration = System.currentTimeMillis() - start;


        while(file != null) {
            if(!trieOfUniqueWords .find(file)) {
                trieOfUniqueWords .insert(file);

            }
            file = book.words.next();

        }
        String wordsInOrder =  trieOfUniqueWords.toString();
        outputDuration("Adding unique words to a trie", duration);
        System.out.println( trieOfUniqueWords.size() + " unique words were found" );
        System.out.println( trieOfUniqueWords.comparisons + " comparisons were made.");


    }
}
