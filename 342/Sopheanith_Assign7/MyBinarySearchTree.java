public class MyBinarySearchTree<Type extends Comparable<Type>> {
    public class Node {
        public Type item;
        public Node left;
        public Node right;
        public int height;
        public int balanceFactor;
        public Node (Type item) {
            this.item = item;
            this.left = null;
            this.right = null;
            this.height = 0;
            this.balanceFactor = 0;
        }
        public String toString() {
            return item + ":H" + height + ":B" + balanceFactor;
        }
    }
    protected Node root;
    protected int size;
    public long comparisons;
    public Integer rotations;
    protected boolean balancing;
    public MyBinarySearchTree() {
        this.root = null;
        this.size = 0;
        this.comparisons = 0;
        this.balancing = false;
    }
    public MyBinarySearchTree(boolean balancing) {
        this.balancing = balancing;
        this.rotations = 0;
    }
    public void add(Type item) {
        root = add(item, root);
    }
    private Node add(Type item, Node subTree) {
        if (subTree == null) {
            size++;
            return new Node(item);
        }
        if (item.compareTo(subTree.item) < 0) {
            subTree.left = add(item,subTree.left);
        } else if (item.compareTo(subTree.item) > 0 ) {
            subTree.right = add(item, subTree.right);
        }
        if (balancing) {
            subTree = rebalance(subTree);
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
        if (balancing) {
            subTree = rebalance(subTree);
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
            node.balanceFactor = (height(node.left) - height(node.right));
        }
    }
    private Node rotateRight(Node node) {
        Node left = node.left;
        node.left = left.right;
        left.right = node;
        updateHeight(node);
        updateHeight(left);
        rotations++;
        return left;
    }
    private Node rotateLeft(Node node) {
        Node right = node.right;
        node.right = right.left;
        right.left = node;
        updateHeight(node);
        updateHeight(right);
        rotations++;
        return right;
    }
    private Node rebalance(Node node) {
//        int balance = node.balanceFactor;
//        if (balance > 1) {
//            if (height(node.left.left) >= height(node.left.right)) {
//                node = rotateRight(node);
//            } else {
//                node.left = rotateLeft(node.left);
//                node = rotateRight(node);
//            }
//        } else if (balance < -1) {
//            if (height(node.right.right) >= height(node.right.left)) {
//                node = rotateLeft(node);
//            } else {
//                node.right = rotateRight(node.right);
//                node = rotateLeft(node);
//            }
//        }
//        return node;
//        if (node == null) {
//            return null;
//        }
//        int balance = node.balanceFactor;
//        if (balance > 1 && node.left.balanceFactor >= 0) {
//            return rotateRight(node);
//        }
//        if (balance > 1 && node.left.balanceFactor < 0) {
//            node.left = rotateLeft(node.left);
//            return rotateRight(node);
//        }
//        if (balance < -1 && node.right.balanceFactor <= 0) {
//            return rotateLeft(node);
//        }
//        if (balance < -1 && node.right.balanceFactor > 0) {
//            node.right = rotateRight(node.right);
//            return rotateLeft(node);
//        }
//        return node;
        if (node == null) {
            return null;
        }
        int balanceFactor = calculateBalanceFactor(node);
        if(balanceFactor > 1) {
            if(calculateBalanceFactor(node.left) < 0) {
                node.left = rotateLeft(node.left);
            }
            return rotateRight(node);
        }
        else if(balanceFactor < -1) {
            if(calculateBalanceFactor(node.right) > 0) {
                node.right = rotateRight(node.right);
            }
            return rotateLeft(node);
        }
        return node;
    }
    private int calculateBalanceFactor(Node node) {
        if (node == null) {
            return 0;
        }
        int leftHeight = (node.left == null) ? -1 : node.left.height;
        int rightHeight = (node.right == null) ? -1 : node.right.height;
        return leftHeight - rightHeight;
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