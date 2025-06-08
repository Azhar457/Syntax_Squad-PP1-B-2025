package entity;
public class Node {
    private Antrian data;
    private Node next;

    public Node(Antrian data) {
        this.data = data;
        this.next = null;
    }

    public Antrian getData() {
        return data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
