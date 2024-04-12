public class UniqueWords {
    public BookReader book;
    public MyArrayList<String> alOfUniqueWords;
    public MyLinkedList<String> llOfUniqueWords;
    public MyOrderedList<String> olOfUniqueWords;

    public UniqueWords() {
        this.book = new BookReader(".\\WarAndPeace.txt");
        this.alOfUniqueWords = new MyArrayList<>();
        this.llOfUniqueWords = new MyLinkedList<>();
        this.olOfUniqueWords = new MyOrderedList<>();
    }
    public void addUniqueWordsToLinkedList() {
        long startTime = System.currentTimeMillis();
        MyLinkedList<String> wordsList = book.getWords();
        wordsList.first();
        while (wordsList.current() != null) {
            String word = wordsList.current();
            if (!llOfUniqueWords.contains(word)) {
                llOfUniqueWords.addBefore(word);
            }
            wordsList.next();
        }
        //llOfUniqueWords.sort();

        long endTime = System.currentTimeMillis();
        System.out.println("Adding unique words to linked list... in " +
                (endTime - startTime) + " milliseconds.");
        System.out.println(llOfUniqueWords.size() + " unique word were found.");
        System.out.println(llOfUniqueWords.comparisons + " comparisons were made.");
        llOfUniqueWords.sort();
        System.out.println();
    }
    public void addUniqueWordsToArrayList() {
        long startTime = System.currentTimeMillis();
        MyLinkedList<String> wordsList = book.getWords();
        wordsList.first();
        while (wordsList.current() != null) {
            String word = wordsList.current();
            if (!alOfUniqueWords.contains(word)) {
                alOfUniqueWords.insert(word, alOfUniqueWords.size());
            }
            wordsList.next();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Adding unique words to array list... in " +
                (endTime - startTime) + " milliseconds.");
        System.out.println(alOfUniqueWords.size() + " unique word were found.");
        System.out.println(alOfUniqueWords.comparisons + " comparisons were made.");
        System.out.println();
    }
    public void addUniqueWordsToOrderedList() {
        long startTime = System.currentTimeMillis();

        MyLinkedList<String> wordsList = book.getWords();
        wordsList.first();
        while (wordsList.current() != null) {
            String word = wordsList.current();
            if (!olOfUniqueWords.binarySearch(word)) {
                olOfUniqueWords.add(word);
            }
            wordsList.next();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Adding unique words to ordered list... in " +
                (endTime - startTime) + " milliseconds.");
        System.out.println(olOfUniqueWords.size() + " unique word were found.");
        System.out.println(olOfUniqueWords.comparisons + " comparisons were made.");
        System.out.println();
    }
}
