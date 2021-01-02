package HuffManEncoder;

import java.util.ArrayList;
import java.util.LinkedList;

public class MinHeap {
    private final LinkedList<Node> nodes = new LinkedList<>();
    private int size;

    public void insert(Node node) {
        //if (isFull())
        //throw new IllegalStateException();
        nodes.add(node);
        size++;
        bubbleUp();
    }

    public Node remove() {
        if (isEmpty())
            throw new IllegalStateException();
        size--;
        var root = nodes.removeFirst();
        //nodes.set(0, nodes.get(--size));

        bubbleDown();
        return root;
    }

    private void bubbleDown() {
        var index = 0;
        while (index <= nodes.size() && !isValidParent(index)) {
            var largerChildIndex = smallerChildIndex(index);
            swap(index, largerChildIndex);
            index = largerChildIndex;
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private int smallerChildIndex(int index) {
        if (!hasLeftChild(index))
            return index;

        if (!hasRightChild(index))
            return leftChildIndex(index);

        return (leftChild(index).key < rightChild(index).key) ?
                leftChildIndex(index) :
                rightChildIndex(index);
    }

    private boolean hasLeftChild(int index) {
        return leftChildIndex(index) < nodes.size();
    }

    private boolean hasRightChild(int index) {
        return rightChildIndex(index) < nodes.size();
    }

    private boolean isValidParent(int index) {
        if (!hasLeftChild(index))
            return true;

        var isValid = nodes.get(index).key <= leftChild(index).key;

        if (hasRightChild(index))
            isValid &= nodes.get(index).key <= rightChild(index).key;

        return isValid;
    }

    private Node rightChild(int index) {
        return nodes.get(rightChildIndex(index));
    }

    private Node leftChild(int index) {
        return nodes.get(leftChildIndex(index));
    }

    private int leftChildIndex(int index) {
        return index * 2 + 1;
    }

    private int rightChildIndex(int index) {
        return index * 2 + 2;
    }

    public boolean isFull() {
        return size == nodes.size();
    }

    private void bubbleUp() {
        var index = nodes.size() - 1;
        while (index > 0 && nodes.get(index).key < nodes.get(parent(index)).key) {
            swap(index, parent(index));
            index = parent(index);
        }
    }

    private int parent(int index) {
        return (index - 1) / 2;
    }

    private void swap(int first, int second) {
        var temp = nodes.get(first);
        nodes.set(first, nodes.get(second));
        nodes.set(second, temp);
    }
    public int size(){return nodes.size();}

}
