package HuffManEncoder;

public class PriorityQ {

    public boolean containsTwoNode;
    MinHeap root;

    public PriorityQ() {
        root = new MinHeap();
    }
    public int size(){
        return root.size();
    }
    public void enque(Node node) {
        root.insert(node);
    }

    public Node deque() {
        return root.remove();
    }

    public boolean containsTwoNode() {return root.size()>1;
    }
}


