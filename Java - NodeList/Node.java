public class Node{
    private String data;
    private Node next;

    public Node(String data){
        this.setData(data);
        this.setNext(null);
    }

    public String getData() {
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Node getNext() {
        return this.next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

}