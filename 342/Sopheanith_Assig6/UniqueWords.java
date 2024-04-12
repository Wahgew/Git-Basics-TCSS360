public class UniqueWords {
    public BookReader book;
    public MyBinarySearchTree<String> bstOfUniqueWords;

    public UniqueWords() {
        this.book = new BookReader(".\\WarAndPeace.txt");
        this.bstOfUniqueWords = new MyBinarySearchTree<>();
    }
    public void addUniqueWordsToBST() {
        long startTime = System.currentTimeMillis();

        MyLinkedList<String> wordsList = book.getWords();
        wordsList.first();
        while (wordsList.current() != null) {
            String word = wordsList.current();
            if (bstOfUniqueWords.find(word) == null) {
                bstOfUniqueWords.add(word);
            }
            wordsList.next();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Adding unique words to binary search tree... in " +
                (endTime - startTime) + " milliseconds.");
        System.out.println(bstOfUniqueWords.size() + " unique words were found.");
        System.out.println("Binary search tree height: " + bstOfUniqueWords.height() +
                " Number of comparisons made: " + bstOfUniqueWords.comparisons);
        System.out.println();
    }
}
