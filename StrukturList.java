public class StrukturList {
    private Node HEAD = null;

    public boolean isEmpty() {
        return HEAD == null;
    }

    public void addTail(int data) {
        Node newNode = new Node(data);
        if (isEmpty()) {
            HEAD = newNode;
        } else {
            Node curNode = HEAD;
            while (curNode.getNext() != null) {
                curNode = curNode.getNext();
            }
            curNode.setNext(newNode);
        }
    }

    public void addHead(int data) {
        Node newNode = new Node(data);
        newNode.setNext(HEAD);
        HEAD = newNode;
    }

    public void addMid(int data, int position) {
        Node newNode = new Node(data);

        if (position <= 1 || isEmpty()) {
            addHead(data);
            return;
        }

        Node curNode = HEAD;
        int i = 1;

        while (curNode != null && i < position - 1) {
            curNode = curNode.getNext();
            i++;
        }

        if (curNode == null) {
            addTail(data);
        } else {
            newNode.setNext(curNode.getNext());
            curNode.setNext(newNode);
        }
    }

    public void displayElement() {
        Node curNode = HEAD;
        
        if (isEmpty()) {
            System.out.println("List Kosong");
            return;
            
        } else {
            System.out.print("Elemen Node : ");
        }
        while (curNode != null) {
            System.out.print(curNode.getData() + " ");
            curNode = curNode.getNext();
        } 
    }

    public void removeHead() {
        if (isEmpty()) {
            System.out.println("List Kosong");
        } else {
            Node temp = HEAD;
            HEAD = HEAD.getNext();
            dispose(temp);
        }
    }

    public void removeTail() {
        if (isEmpty()) {
            System.out.println("List Kosong");
            return;
        }
        if (HEAD.getNext() == null) {
            dispose(HEAD);
            HEAD = null;
            return;
        }
        Node curNode = HEAD;
        Node prevNode = null;
        while (curNode.getNext() != null) {
            prevNode = curNode;
            curNode = curNode.getNext();
        }
        prevNode.setNext(null);
        dispose(curNode);
    }

    public void removeMid(int data) {
        if (isEmpty()) {
            System.out.println("Elemen List Kosong");
            return;
        }
        if (HEAD.getData() == data) {
            removeHead();
            return;
        }
        Node curNode = HEAD;
        Node prevNode = null;
        while (curNode != null && curNode.getData() != data) {
            prevNode = curNode;
            curNode = curNode.getNext();
        }
        if (curNode != null) {
            prevNode.setNext(curNode.getNext());
            dispose(curNode);
        } else {
            System.out.println("Data tidak ditemukan");
        }
    }

    private void dispose(Node temp) {
        temp.setNext(null);
    }
}