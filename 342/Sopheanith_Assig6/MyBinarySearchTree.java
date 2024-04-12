public class MyBinarySearchTree<Type extends Comparable<Type>> {
    public class Node {
        public Type item;
        public Node left;
        public Node right;
        public int height;
        public Node (Type item) {
            this.item = item;
            this.left = null;
            this.right = null;
            this.height = 0;
        }
        public String toString() {
            return item + ":H" + height;
        }
    }
    protected Node root;
    protected int size;
    public long comparisons;

    public MyBinarySearchTree() {
        this.root = null;
        this.size = 0;
        this.comparisons = 0;
    }
    public void add(Type item) {
        root = add(item, root);
    }
    private Node add(Type item, Node subTree) {
        //comparisons++;
        if (subTree == null) {
            size++;
            return new Node(item);
        }
        if (item.compareTo(subTree.item) < 0) {
            subTree.left = add(item,subTree.left);
        } else if (item.compareTo(subTree.item) > 0 ) {
            subTree.right = add(item, subTree.right);
        }
        updateHeight(subTree);
        return subTree;
    }

    public void remove(Type item) {
        root = remove(item, root);
    }
    private Node remove(Type item, Node subTree) {
        if (subTree == null) {
            return null;
        }
        if (item.compareTo(subTree.item) < 0) {
            subTree.left = remove(item,subTree.left);
        } else if (item.compareTo(subTree.item) > 0 ) {
            subTree.right = remove(item, subTree.right);
        } else {
            if (subTree.left == null) {
                size--;
                return subTree.right;
            } else if (subTree.right == null) {
                size--;
                return subTree.left;
            } else {
                Node temp = findMin(subTree.right);
                subTree.item = temp.item;
                subTree.right = remove(temp.item, subTree.right);
            }
        }
        updateHeight(subTree);
        return subTree;
    }
    private Node findMin(Node subTree) {
        while (subTree.left != null) {
            subTree = subTree.left;
        }
        return subTree;
    }
    public Type find(Type item) {
        return find(item, root);
    }

    private Type find(Type item, Node subTree) {
        comparisons++;
        if (subTree == null) {
            return null;
        }
        if (item.compareTo(subTree.item) == 0) {
            return subTree.item;
        } else if (item.compareTo(subTree.item) < 0) {
            return find(item, subTree.left);
        } else {
            return find(item, subTree.right);
        }
    }
    public int height() {
        return height(root);
    }
    private int height(Node node) {
        if (node == null) {
            return -1;
        } else {
            return node.height;
        }
    }
    public int size() {
        return size;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    private void updateHeight(Node node) {
        if (node != null) {
            node.height = Math.max(height(node.left), height(node.right)) + 1;
        }
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        if (root != null) {
            inorder(root, sb);
        }
        if (root == null) {
            sb.append("]");
        } else {
            sb.replace(sb.length()-2, sb.length(), "]");
        }
        return sb.toString();
    }
    private String inorder(Node node, StringBuilder sb) {
        if (node != null) {
            inorder(node.left, sb);
            sb.append(node.toString()).append(",").append(" ");
            inorder(node.right, sb);
        }
        return sb.toString();
    }
}