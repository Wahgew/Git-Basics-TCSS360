public class UniqueWords {
    public BookReader book;
    public MyBinarySearchTree<String> bstOfUniqueWords;
    public MyBinarySearchTree<String> avlOfUniqueWords;

    public UniqueWords() {
        this.book = new BookReader(".\\WarAndPeace.txt");
        this.avlOfUniqueWords = new MyBinarySearchTree<>(true);
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
    public void addUniqueWordsToAVL() {
        long startTime = System.currentTimeMillis();
        MyLinkedList<String> wordsList = book.getWords();
        wordsList.first();
        while (wordsList.current() != null) {
            String word = wordsList.current();
            if (avlOfUniqueWords.find(word) == null) {
                avlOfUniqueWords.add(word);
            }
            wordsList.next();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Adding unique words to AVL took " +  (endTime - startTime) + " ms");
        System.out.println("Number of words: " + avlOfUniqueWords.size());
        System.out.println("Tree height: " + avlOfUniqueWords.height());
        System.out.println("Comparisons made: " + avlOfUniqueWords.comparisons);
        System.out.println("Rotations made: " + avlOfUniqueWords.rotations);

    }
}
