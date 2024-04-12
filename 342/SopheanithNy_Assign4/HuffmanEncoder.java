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
    public HuffmanNode(Character character, Integer weight) {
        this.character = character;
        this.weight = weight;
    }
    public HuffmanNode(HuffmanNode left, HuffmanNode right) {
        this.left = left;
        this.right = right;
        this.weight = left.weight + right.weight;
    }
    public int compareTo(HuffmanNode other) {
        return weight.compareTo(other.weight);
    }
    public String toString() {
        return character + ":" + weight;
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
    protected MyOrderedList<FrequencyNode> frequencies;
    protected HuffmanNode huffmanTree;
    protected MyOrderedList<CodeNode> codes;
    protected byte[] encodedText;
    public HuffmanEncoder() {
        inputFileName = "./WarAndPeace.txt";
        outputFileName = "./WarAndPeace-compressed.bin";
        codesFileName = "./WarAndPeace-code.txt";

        book = new BookReader(inputFileName);
        frequencies = new MyOrderedList<>();
        huffmanTree = null;
        codes = new MyOrderedList<>();
        encodedText = null;

        countFrequency();
        buildTree();
        encode();
        writeFile();
    }
    protected void countFrequency() {
        long start = System.currentTimeMillis();
        int length = book.book.length();
        for (int i = 0; i < length; i++) {
            char ch = book.book.charAt(i);
            FrequencyNode node = frequencies.binarySearch(new FrequencyNode(ch, 0));
            if (node != null) {
                node.count++;
            } else {
                frequencies.add(new FrequencyNode(ch, 1));
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("\nCounting frequencies of characters... " + frequencies.size() +
                " unique characters found in " + (end - start) + " milliseconds.");
    }
    protected void buildTree() {
        long start = System.currentTimeMillis();
        MyPriorityQueue<HuffmanNode> priorityQueue = new MyPriorityQueue<>();
        for (int i = 0; i < frequencies.size(); i++) {
            priorityQueue.insert(new HuffmanNode(frequencies.get(i).character, frequencies.get(i).count));
        }
        while (priorityQueue.size() > 1) {
            HuffmanNode left = priorityQueue.removeMin();
            HuffmanNode right = priorityQueue.removeMin();
            HuffmanNode parent = new HuffmanNode('\0', left.weight + right.weight);
            parent.left = left;
            parent.right = right;
            priorityQueue.insert(parent);
        }
        huffmanTree = priorityQueue.removeMin();
        extractCodes(huffmanTree, "");
        long endTime = System.currentTimeMillis();
        System.out.println("\nBuilding a Huffman tree and reading codes... in "
                + (endTime - start) + " milliseconds.");
    }
    protected void extractCodes(HuffmanNode node, String code) {
        if (node != null) {
            if (node.character != '\0') {
                codes.add(new CodeNode(node.character, code));
            }
            extractCodes(node.left, code + "0");
            extractCodes(node.right, code + "1");
        }
    }
    protected void encode() {
        long start = System.currentTimeMillis();
        StringBuilder textCode = new StringBuilder();
        for(int i = 0; i < book.book.length(); i++) {
            char ch = book.book.charAt(i);
            CodeNode code = codes.binarySearch(new CodeNode(ch, ""));
            //textCode.append(code.code);
            if (code != null) {
                textCode.append(code.code);
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
    protected void writeFile() {
        long start = System.currentTimeMillis();
        try (FileOutputStream compressedFileOutputStream = new FileOutputStream(outputFileName);
             PrintWriter codesPrintWriter = new PrintWriter(codesFileName)) {
            compressedFileOutputStream.write(encodedText);
            for (int i = 0; i < codes.size(); i++) {
                CodeNode codeNode = codes.get(i);
                codesPrintWriter.println(codeNode.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("\nWriting compressed file... " + encodedText.length +
                " bytes written in " + (endTime - start) + " milliseconds.");
    }
}
