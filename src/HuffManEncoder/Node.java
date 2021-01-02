package HuffManEncoder;

public class Node {
    int key;
    char value;
    Node left,right;

    public Node(int i, Node leftChild, Node rightChild) {
        setRight(rightChild);
        setLeft(leftChild);
        setValue((char) i);
    }

    public int getKey() {
        return key;
    }

    public char getValue() {
        return value;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void setValue(char value) {
        this.value = value;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node(int key, char value) {
        this.key = key;
        this.value = value;
    }

    public Node(int key) {
        this.key=key;
    }


    @Override
    public String toString() {
        return   key + "," + value ;
    }
}