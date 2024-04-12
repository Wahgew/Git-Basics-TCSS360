import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
class FrequencyNode implements Comparable<FrequencyNode>{
    public Character character;
    public Integer count;
    public FrequencyNode(Character character, Integer count) {
        this.character = character;
        this.count = count;
    }
    public int compareTo(FrequencyNode other) {
        return (character.compareTo(other.character));
    }
    public String toString() {
        return character + ":" + count;
    }
}
class HuffmanNode implements Comparable<HuffmanNode>{
    public Character character;
    public Integer weight;
    public HuffmanNode left;
    public HuffmanNode right;
    public String word;
    public HuffmanNode(Character character, Integer weight) {
        this.character = character;
        this.weight = weight;
    }
    public HuffmanNode(HuffmanNode left, HuffmanNode right) {
        this.left = left;
        this.right = right;
        this.weight = left.weight + right.weight;
    }
    public HuffmanNode(String word, Integer wt) {
        this.word = word;
        this.weight = wt;
    }
    public int compareTo(HuffmanNode other) {
        return weight.compareTo(other.weight);
    }
    public String toString() {
        return word + ":" + weight;
    }
}
class CodeNode implements Comparable<CodeNode>{
    public Character character;
    public String code;
    public CodeNode(Character character, String code) {
        this.character = character;
        this.code = code;
    }
    public int compareTo(CodeNode other) {
        return (character.compareTo(other.character));
    }
    public String toString() {
        return character + ":" + code;
    }
}
public class HuffmanEncoder {
    protected String inputFileName;
    protected String outputFileName;
    protected String codesFileName;
    protected BookReader book;
    protected boolean wordCodes = true;
    protected MyHashTable<String, Integer> frequenciesHash;
    protected MyHashTable<String, String> codesHash;
    protected HuffmanNode huffmanTree;
    public byte[] encodedText;

    public HuffmanEncoder() throws IOException{
        inputFileName = "./WarAndPeace.txt";
        outputFileName = "./WarAndPeace-compressed.bin";
        codesFileName = "./WarAndPeace-code.txt";

        book = new BookReader(inputFileName);
        frequenciesHash = new MyHashTable<>(32768);
        codesHash = new MyHashTable<>(32768);
        huffmanTree = null;
        encodedText = null;

        countFrequency();
        buildTree();
        encode();
        writeFiles();
    }
//    protected void countFrequency() {
//        long start = System.currentTimeMillis();
//        String text = book.book;
//        for (int i = 0; i < text.length(); i++) {
//            char ch = text.charAt(i);
//            String key = String.valueOf(ch);
//            if (wordCodes && Character.isWhitespace(ch)) {
//                key = " ";
//            }
//            Integer count = frequenciesHash.get(key);
//            if (count != null) {
//                frequenciesHash.put(key, count + 1);
//            } else {
//                frequenciesHash.put(key, 1);
//            }
//        }
//        long end = System.currentTimeMillis();
//        System.out.println("\nCounting frequencies of characters... " + frequenciesHash.size() +
//                " unique characters found in " + (end - start) + " milliseconds.");
//    }
    protected void countFrequency() {
        System.out.println();
        long duration = 0;
        long start = System.currentTimeMillis();

        if (wordCodes) {
            book.wordsAndSeparators.first();
            while (book.wordsAndSeparators.current() != null) {
                String s = book.wordsAndSeparators.current();
                Integer frequency = this.frequenciesHash.get(s);
                if (frequency == null) {
                    this.frequenciesHash.put(s, 1);
                } else {
                    this.frequenciesHash.put(s, frequency + 1);
                }
                book.wordsAndSeparators.next();
            }
            duration = System.currentTimeMillis() - start;
            System.out.println("Counting frequencies of words and separators... " + this.frequenciesHash.size()
                    + " unique words and separators found in " + duration + " milliseconds.");

        } else {
            this.frequencies = new MyHashTable<>(32768);
            String readbook = book.book;
            for (int i = 0; i < readbook.length(); i++) {
                char c = readbook.charAt(i);
                FrequencyNode thisCharacter = new FrequencyNode();
                thisCharacter.character = c;
                FrequencyNode existingNode = frequencies.get(thisCharacter.character);
                if (existingNode == null) {
                    thisCharacter.count = 1;
                    frequencies.put(thisCharacter.character, thisCharacter);

                } else {
                    existingNode.count++;
                }
            }
            duration = System.currentTimeMillis() - start;
            System.out.println("Counting frequencies of characters... " + frequencies.size()
                    + " unique characters found in " + duration + " milliseconds.");
        }
    }
//    protected void buildTree() {
//        long start = System.currentTimeMillis();
//        MyPriorityQueue<HuffmanNode> priorityQueue = new MyPriorityQueue<>();
//        // Iterate through all keys in the frequencies hash table
////        for (int i = 0; i < frequenciesHash.capacity; i++) {
////            if (frequenciesHash.keyBuckets[i] != null && frequenciesHash.valueBuckets[i] != null) {
////                String key = (String) frequenciesHash.keyBuckets[i];
////                Integer frequency = (Integer) frequenciesHash.valueBuckets[i]; // Cast to Integer
////                priorityQueue.insert(new HuffmanNode(key, frequency));
////            }
////        }
//        // Construct the Huffman tree using the priority queue
//        while (priorityQueue.size() > 1) {
//            HuffmanNode left = priorityQueue.removeMin();
//            HuffmanNode right = priorityQueue.removeMin();
//            HuffmanNode parent = new HuffmanNode('\0', left.weight + right.weight);
//            parent.left = left;
//            parent.right = right;
//            priorityQueue.insert(parent);
//        }
//        // Retrieve the Huffman tree root
//        huffmanTree = priorityQueue.removeMin();
//        // Extract codes from the Huffman tree
//        extractCodes(huffmanTree, "");
//        long endTime = System.currentTimeMillis();
//        System.out.println("\nBuilding a Huffman tree and reading codes... in "
//                + (endTime - start) + " milliseconds.");
//    }
    protected void buildTree() {
        System.out.println();
        MyPriorityQueue<HuffmanNode> huffmanQueue = new MyPriorityQueue<>();
        long duration = 0;
        long startTime = System.currentTimeMillis();

        if (wordCodes) {
            for (int i = 0; i < this.frequenciesHash.size(); i++) {
                String key = this.frequenciesHash.keys.get(i);
                huffmanQueue.insert(new HuffmanNode(key, this.frequenciesHash.get(key)));
            }

            while (huffmanQueue.size() > 1) {
                HuffmanNode leftNode = huffmanQueue.removeMin();
                HuffmanNode rightNode = huffmanQueue.removeMin();
                if (rightNode != null && leftNode != null) {
                    HuffmanNode mergedNode = new HuffmanNode(leftNode, rightNode);
                    mergedNode.word = " ";
                    huffmanQueue.insert(mergedNode);
                } else if (leftNode != null) {
                    huffmanQueue.insert(leftNode);
                }
            }
            this.huffmanTree = huffmanQueue.min();
            extractCodes(huffmanTree, "");
        } else {
            for (int i = 0; i < frequenciesHash.size(); i++) {
                // Get the key (character) from the hash table
                Character key = frequencies.getKeyByIndex(i);

                // Get the corresponding FrequencyNode from the hash table
                FrequencyNode node = frequenciesHah.get(key);

                // Insert the character and its frequency into the Huffman queue
                huffmanQueue.insert(new HuffmanNode(node.character, node.count));
            }

            while (huffmanQueue.size() > 1) {
                HuffmanNode leftNode = huffmanQueue.removeMin();
                HuffmanNode rightNode = huffmanQueue.removeMin();
                if (rightNode != null && leftNode != null) {
                    HuffmanNode mergedNode = new HuffmanNode(leftNode, rightNode);
                    mergedNode.character = ' ';
                    huffmanQueue.insert(mergedNode);
                } else if (leftNode != null) {
                    huffmanQueue.insert(leftNode);
                }
            }
            this.huffmanTree = huffmanQueue.min();
            extractCodes(huffmanTree, "");
        }
        duration = System.currentTimeMillis() - startTime;
        System.out.println("Building a Huffman tree and reading codes... " + duration + " milliseconds.");
    }
    protected void extractCodes(HuffmanNode node, String code) {
        if (node != null) {
            if (node.character != '\0') {
                codesHash.put(node.word, code);
            }
            extractCodes(node.left, code + "0");
            extractCodes(node.right, code + "1");
        }
    }
    protected void encode() {
        long start = System.currentTimeMillis();
        StringBuilder textCode = new StringBuilder();
        String bookText = book.book;
        for (int i = 0; i < bookText.length(); i++) {
            String ch = String.valueOf(bookText.charAt(i));
            String code = codesHash.get(ch);
            if (code != null) {
                textCode.append(code);
            }
        }
        String encodedString = textCode.toString();
        encodedText = new byte[encodedString.length() / 8 + 1];
        int index = 0;
        for (int i = 0; i < encodedString.length(); i += 8) {
            String byteStr = encodedString.substring(i, Math.min(i + 8, encodedString.length()));
            int value = Integer.parseInt(byteStr, 2);
            encodedText[index] = (byte) value;
            index++;
        }
        long end = System.currentTimeMillis();
        System.out.println("\nEncoding message... in " + (end - start) + " milliseconds.");
    }
    private void writeFiles() {
        long start = System.currentTimeMillis();
        try (FileOutputStream compressedFileOutputStream = new FileOutputStream(outputFileName);
             PrintWriter codesPrintWriter = new PrintWriter(codesFileName)) {
            compressedFileOutputStream.write(encodedText);
            // Iterate through all keys in the codes hash table
            for (int i = 0; i < codesHash.capacity; i++) {
                if (codesHash.keyBuckets[i] != null) {
                    String key = codesHash.keyBuckets[i];
                    String code = codesHash.get(key);
                    codesPrintWriter.println(key + ":" + code);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("\nWriting compressed file... " + encodedText.length +
                " bytes written in " + (endTime - start) + " milliseconds.");
    }
}
