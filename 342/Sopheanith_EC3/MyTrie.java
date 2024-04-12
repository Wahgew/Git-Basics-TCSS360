public class MyTrie {
    private Node root;
    private int size;
    public long comparisons;

    public MyTrie() {
        root = new Node();
        root.addChildren();
    }

    private static class Node implements Comparable<Node> {
        private boolean terminal = false;
        private Character character;
        private MyOrderedList<Node> children;
        public Node() {
        }
        public Node(char theChar) {
            character = theChar;

        }

        @Override
        public int compareTo(Node other) {
            return Character.compare(this.character, other.character);
        }

        public void addChildren() {
            children = new MyOrderedList<>();

        }
    }
    public void insert(String item) {
        Node currentNode = root;
        for (int i = 0; i < item.length(); i++) {
            comparisons++;
            char currentChar = item.charAt(i);
            Node child = currentNode.children.binarySearch(new Node(currentChar));

            if (child == null) {
                child = new Node(currentChar);
                child.addChildren();
                currentNode.children.add(child);
            }
            currentNode = child;
        }
        currentNode.terminal = true;
        size++;
    }

    public void remove(String item) {
        Node currentNode = root;

        for (char c : item.toCharArray()) {
            Node child = currentNode.children.binarySearch(new Node(c));

            if (child == null) return;

            currentNode = child;
        }

        if (currentNode != null && currentNode.terminal) {
            currentNode.terminal = false;
            size--;

            if (currentNode.children.isEmpty() && !currentNode.terminal) {
                Node parent = root;

                for (int i = 0; i < item.length() - 1; i++) {
                    char currentChar = item.charAt(i);
                    Node potentialParent = parent.children.binarySearch(new Node(currentChar));

                    if (potentialParent == null) return;

                    parent = potentialParent;
                }

                parent.children.remove(currentNode);
            }
        }
    }
    public boolean find(String item) {
        Node currentNode = root;
        for (Character c : item.toCharArray()) {
            comparisons++;
            if (currentNode == null) {
                return false;  // Node is null, the item is not in the trie
            }
            boolean foundChild = false;
            for (int i = 0; i < currentNode.children.size(); i++) {

                Node child = currentNode.children.get(i);
                if (child.character == c) {
                    foundChild = true;
                    currentNode = child;
                    break;
                }
            }
            if (!foundChild) {
                return false;  // Child node not found, the item is not in the trie
            }
        }
        return currentNode != null && currentNode.terminal;
    }
    public int size() {
        return size;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public String toString() {
        StringBuilder output = new StringBuilder();
        addWords(root, "", output);
        String result = output.toString();
        if (result.endsWith(", ")) {
            result = result.substring(0, result.length() - 2);
        }
        return "[" + result + "]";
    }
    protected void addWords(Node node, String str, StringBuilder output) {
        if(node.character != null) {
            str += node.character;
        }
        if(node.terminal) {
            output.append(str);
            output.append(", ");
        }
        for(int i = 0; i < node.children.size(); i++) {
            Node child = node.children.get(i);
            addWords(child, str, output);
        }
    }
}
