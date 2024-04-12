public class UniqueWords {
    public BookReader book;
    public MyHashTable<String, String> hashOfUniqueWords;


    public UniqueWords() {
        this.book = new BookReader(".\\WarAndPeace.txt");
        this.hashOfUniqueWords = new MyHashTable<>(32768);
    }
    public void addUniqueWordsToHashTable() {
        long startTime = System.currentTimeMillis();
        MyLinkedList<String> wordsList = book.getWords();
        wordsList.first();
        while (wordsList.current() != null) {
            String word = wordsList.current();
            if (hashOfUniqueWords.get(word) == null) {
                hashOfUniqueWords.put(word, word);
            }
            wordsList.next();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Adding unique words to hashtable took " +  (endTime - startTime) + " ms");
        System.out.println("Number of words: " + hashOfUniqueWords.size());
        System.out.println("Comparisons made: " + hashOfUniqueWords.comparisons);
        System.out.println("Maxprobe: " + hashOfUniqueWords.maxProbe);

    }
}
